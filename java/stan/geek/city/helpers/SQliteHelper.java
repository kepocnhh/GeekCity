package stan.geek.city.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.List;

import stan.geek.city.database.SQliteApi;
import stan.geek.city.database.Tables;
import stan.geek.city.units.Post;
import stan.geek.city.units.taxonomy.Category;

public class SQliteHelper
{
    //______________________POSTSSIMPLE
    static public void savePostsSimple(List<Post> posts)
    {
        for(int i=0; i<posts.size(); i++)
        {
            posts.get(i).title = StringEscapeUtils.unescapeHtml4(posts.get(i).title);
            posts.get(i).excerpt = android.text.Html.fromHtml(posts.get(i).excerpt).toString().trim();
            SQliteApi.insertPostSimple(posts.get(i).getCV());
            if(posts.get(i).terms != null
                    && posts.get(i).terms.category != null
                    && posts.get(i).terms.category.size() > 0)
            {
                saveCategory(posts.get(i).terms.category, posts.get(i).ID);
            }
        }
    }
    static public void saveCategory(List<Category> category, int id)
    {
        for(int j = 0; j < category.size(); j++)
        {
            if(SQliteApi.getOnePostSimpleFromCategoryPage(category.get(j).ID, id).getCount()>0)
            {
                continue;
            }
            ContentValues values = new ContentValues();
            values.put(Tables.PostsAndCategory_category_id_COLUMN, category.get(j).ID);
            values.put(Tables.PostsAndCategory_post_id_COLUMN, id);
            SQliteApi.insertPostsAndCategory(values);
        }
    }

    //______________________CATEGORY
    static public Category tryGetCategoryFromId(int id)
    {
        Category unit = null;
        Cursor cursor = SQliteApi.getOneCategoryFromId(id + "");
        if(cursor != null && cursor.getCount() > 0)
        {
            Log.e("getOneCategoryFromId", cursor.getCount() + "");
            while(!cursor.isClosed() && cursor.moveToNext())
            {
                unit = new Category();
                unit.setCursor(cursor);
            }
            cursor.close();
        }
        return unit;
    }
}