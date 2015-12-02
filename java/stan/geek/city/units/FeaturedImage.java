package stan.geek.city.units;

import android.content.ContentValues;
import android.database.Cursor;

public class FeaturedImage
    extends GeekUnit
{
    public String content;
    public String guid;

    public FeaturedImage(String g)
    {
        this.guid = g;
    }

    @Override
    public ContentValues getContentValues(ContentValues content)
    {
        return content;
    }

    @Override
    public void setCursor(Cursor route)
    {

    }
}