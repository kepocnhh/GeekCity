package stan.geek.city.units;

import android.content.ContentValues;
import android.database.Cursor;

public class Author
        extends GeekUnit
{
    public String username;
    public String name;
    public String first_name;
    public String last_name;
    public String nickname;
    public String avatar;

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