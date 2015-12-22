package stan.geek.city.rest.requests.posts;

import android.content.ContentValues;
import android.content.Context;

import org.apache.commons.lang3.StringEscapeUtils;

import stan.geek.city.database.SQliteApi;
import stan.geek.city.database.Tables;
import stan.geek.city.helpers.SQliteHelper;
import stan.geek.city.rest.requests.GeekRequest;
import stan.geek.city.rest.responses.posts.PostsResponse;

public class GetPosts
        extends GeekRequest
{
    private String category;
    private int page;

    public GetPosts(Context context, String c, int p)
    {
        super(context);
        category = c;
        page = p;
    }

    @Override
    public PostsResponse loadDataFromNetwork() throws Exception
    {
        super.loadDataFromNetwork();
        PostsResponse response = new PostsResponse(getService().getPostsFromCategoryPage(category, page));
        SQliteApi.startTransaction();
//        for(int i=0; i<response.posts.size(); i++)
//        {
//            response.posts.get(i).title = StringEscapeUtils.unescapeHtml4(response.posts.get(i).title);
//            SQliteApi.insertPostSimple(response.posts.get(i).getCV());
//            if(response.posts.get(i).terms != null
//                    && response.posts.get(i).terms.category != null
//                    && response.posts.get(i).terms.category.size() > 0)
//            {
//                for(int j = 0; j < response.posts.get(i).terms.category.size(); j++)
//                {
//                    if(SQliteApi.getOnePostSimpleFromCategoryPage(response.posts.get(i).terms.category.get(j).ID, response.posts.get(i).ID).getCount()>0)
//                    {
//                        continue;
//                    }
//                    ContentValues values = new ContentValues();
//                    values.put(Tables.PostsAndCategory_category_id_COLUMN, response.posts.get(i).terms.category.get(j).ID);
//                    values.put(Tables.PostsAndCategory_post_id_COLUMN, response.posts.get(i).ID);
//                    SQliteApi.insertPostsAndCategory(values);
//                }
//            }
//        }
        SQliteHelper.savePostsSimple(response.posts);
        SQliteApi.endTransaction();
        return response;
    }
}