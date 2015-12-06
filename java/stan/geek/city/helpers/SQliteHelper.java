package stan.geek.city.helpers;

import android.database.Cursor;
import android.util.Log;

import stan.geek.city.database.SQliteApi;
import stan.geek.city.units.taxonomy.Category;

public class SQliteHelper
{
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