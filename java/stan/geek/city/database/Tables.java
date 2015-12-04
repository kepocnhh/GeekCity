package stan.geek.city.database;

import android.provider.BaseColumns;

public class Tables
{
//    public static final String GeekID = "geek_id";

    //_________________________PostSimple
    public static final String PostSimple_TABLE_NAME = "postsimple";
    public static final String PostSimple_title_COLUMN = "title";
    public static final String PostSimple_featured_image_COLUMN = "featured_image";
    public static final String PostSimple_excerpt_COLUMN = "excerpt";
    public static final String PostSimple_date_COLUMN = "date";

    public static final String PostSimple = "create table if not exists "
            + PostSimple_TABLE_NAME + " (" +
            BaseColumns._ID + " integer primary key autoincrement, " +
//            GeekID + " integer, " +
            PostSimple_title_COLUMN + " text," +
            PostSimple_featured_image_COLUMN + " text," +
            PostSimple_excerpt_COLUMN + " text," +
            PostSimple_date_COLUMN + " text" +
            ");";
}