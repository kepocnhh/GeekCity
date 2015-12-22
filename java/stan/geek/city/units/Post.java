package stan.geek.city.units;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import stan.geek.city.database.Tables;

public class Post
    extends GeekUnit
{
    public String title;
    public String status;
    public String type;
    public String excerpt;
    public String date;
    public Author author;
    public String category_slug;
    public Terms terms;
    public FeaturedImage featured_image;

    @Override
    public ContentValues getContentValues(ContentValues content)
    {
        content.put(Tables.PostSimple_date_COLUMN, date);
        content.put(Tables.PostSimple_excerpt_COLUMN, excerpt);
        content.put(Tables.PostSimple_featured_image_COLUMN, featured_image.guid);
        content.put(Tables.PostSimple_title_COLUMN, title);
        return content;
    }

    @Override
    public void setCursor(Cursor route)
    {
        ID = route.getInt(route.getColumnIndex(BaseColumns._ID));
        date = route.getString(route.getColumnIndex(Tables.PostSimple_date_COLUMN));
        excerpt = route.getString(route.getColumnIndex(Tables.PostSimple_excerpt_COLUMN));
        featured_image = new FeaturedImage(route.getString(route.getColumnIndex(Tables.PostSimple_featured_image_COLUMN)));
        title = route.getString(route.getColumnIndex(Tables.PostSimple_title_COLUMN));
    }
}