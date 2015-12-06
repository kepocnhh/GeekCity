package stan.geek.city.units.taxonomy;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import stan.geek.city.database.Tables;
import stan.geek.city.units.GeekUnit;

public class Category
        extends GeekUnit
{
    public String name;
    public String slug;
    public String description;
    public Category parent;
    public int parent_id = -1;

    @Override
    public void setCursor(Cursor route)
    {
        ID = route.getInt(route.getColumnIndex(BaseColumns._ID));
        name = route.getString(route.getColumnIndex(Tables.Category_name_COLUMN));
        slug = route.getString(route.getColumnIndex(Tables.Category_slug_COLUMN));
        description = route.getString(route.getColumnIndex(Tables.Category_description_COLUMN));
        parent_id = route.getInt(route.getColumnIndex(Tables.Category_parent_id_COLUMN));
    }

    @Override
    public ContentValues getContentValues(ContentValues content)
    {
        content.put(Tables.Category_name_COLUMN, name);
        content.put(Tables.Category_slug_COLUMN, slug);
        content.put(Tables.Category_description_COLUMN, description);
        if(parent != null)
        {
            parent_id = parent.ID;
        }
        content.put(Tables.Category_parent_id_COLUMN, parent_id);
        return content;
    }
}