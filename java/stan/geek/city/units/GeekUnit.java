package stan.geek.city.units;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import stan.geek.city.database.Tables;

public abstract class GeekUnit
{
    public int ID;

    public ContentValues getCV()
    {
        ContentValues content = new ContentValues();
        content.put(BaseColumns._ID, ID);
        return getContentValues(content);
    }
    public abstract void setCursor(Cursor route);
    public abstract ContentValues getContentValues(ContentValues content);
}