package stan.geek.city.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class SQliteApi
{
    public static DatabaseHelper dbHelper;
    public static volatile SQLiteDatabase sdb;
    public static String DB_NAME = "geeckcity";
    public static int DB_VERSION = 1512060732;

    public static void createDb(Context context)
    {
        dbHelper = new DatabaseHelper(context, DB_NAME, null, DB_VERSION);
        sdb = dbHelper.getWritableDatabase();
        createDBTables(sdb);
    }

    public static void startTransaction()
    {
        sdb.beginTransaction();
    }

    public static void endTransaction()
    {
        sdb.setTransactionSuccessful();
        sdb.endTransaction();
    }

    // INSERT
    /* ************************************************************************ */
    public static long insertPostSimple(ContentValues content)
    {
        return sdb.insertWithOnConflict(Tables.PostSimple_TABLE_NAME, null, content, SQLiteDatabase.CONFLICT_REPLACE);
    }
    public static long insertCategory(ContentValues content)
    {
        return sdb.insertWithOnConflict(Tables.Category_TABLE_NAME, null, content, SQLiteDatabase.CONFLICT_REPLACE);
    }
    public static long insertPostsAndCategory(ContentValues content)
    {
        return sdb.insertWithOnConflict(Tables.PostsAndCategory_TABLE_NAME, null, content, SQLiteDatabase.CONFLICT_REPLACE);
    }

    // Clear table
    /* ************************************************************************ */
    public static void clearPostSimple()
    {
        sdb.execSQL("drop table if exists " + Tables.PostSimple_TABLE_NAME);
        sdb.execSQL(Tables.PostSimple);
    }

    // GET ALL
    /* ************************************************************************ */
    public static Cursor getPostSimple()
    {
        Cursor main = sdb.query(Tables.PostSimple_TABLE_NAME, null, null, null, null, null, null);
        return main;
    }
    public static Cursor getCategory()
    {
        Cursor main = sdb.query(Tables.Category_TABLE_NAME, null, null, null, null, null, null);
        return main;
    }

    // GET MANY BY PARAMS
    /* ************************************************************************ */

    public static Cursor getPostSimpleFromPage(int p)
    {
//        SELECT * FROM Products
//        WHERE Price BETWEEN 10 AND 20;
//        ORDER BY last_name;
//        LIMIT 20 OFFSET 10
        Cursor cursor = sdb.rawQuery(
                "SELECT * "
                        + "FROM " + Tables.PostSimple_TABLE_NAME + " "
//                        + "WHERE " + BaseColumns._ID + " BETWEEN 0 AND " + p*10 + " "
                        + "ORDER BY " + Tables.PostSimple_date_COLUMN + " DESC" + " "
//                        + "LIMIT 10 OFFSET " + p*10 + "; "
                        + "LIMIT " + p*10 + "; "
                , new String[]{});
        return cursor;
    }
    public static Cursor getPostSimpleFromCategoryPage(int category_id, int page)
    {
//        "SELECT * "
//                + "FROM " + Tables.SESSIONS_TABLE_NAME + " "
//                + "LEFT JOIN " + Tables.MOVIES_TABLE_NAME + " "
//                + "ON " + Tables.SESSIONS_TABLE_NAME + "." +Tables.SESSIONS_MOVIE_ID_COLUMN + " = " + Tables.MOVIES_TABLE_NAME + "." + BaseColumns._ID + " "
//                + "WHERE " + Tables.SESSIONS_DATE_COLUMN + "=\"" + date + "\" "
//                + "GROUP BY " + Tables.SESSIONS_MOVIE_ID_COLUMN + " "

//        String query =  "SELECT" + fields_to + fields_mid + fields_from +
//                " FROM " + ct.TABLE_FROM + " AS table_from" +
//                " JOIN " + ct.TABLE_NAME + " AS table_mid" +
//                " ON table_from." + Contract.ID + "=table_mid." + ct.CONN_FROM +
//                " JOIN " + ct.TABLE_TO + " AS table_to" +
//                " ON table_to." + Contract.ID + "=table_mid." + ct.CONN_TO +
//                " WHERE table_from." + Contract.ID + "=?";

//        String query = "SELECT DISTINCT " +
//                SHOPS_TABLE + "." + Contract.NAME + ", " +
//                SHOPS_TABLE + "." + Contract.ID + ", " +
//                SHOPS_TABLE + "." + Contract.Shop.LATITUDE + ", " +
//                SHOPS_TABLE + "." + Contract.Shop.LONGITUDE +
//                " FROM " + OFFERS_TABLE + " INNER JOIN " +
//                SHOPS_AND_OFFERS_TABLE +
//                " ON " + OFFERS_TABLE + "." + Contract.ID + "=" + SHOPS_AND_OFFERS_TABLE + "." + Contract.ShopsAndOffers.OFFER_ID +
//                " INNER JOIN " + SHOPS_TABLE + " ON " + SHOPS_TABLE + "." + Contract.ID + "=" + SHOPS_AND_OFFERS_TABLE + "." + Contract.ShopsAndOffers.SHOP_ID +
//                " WHERE " + OFFERS_TABLE + "." + Contract.ID + "=?";
        Cursor cursor = sdb.rawQuery(
                "SELECT *"
                        + "FROM " + Tables.PostSimple_TABLE_NAME + " "
                        + "JOIN " + Tables.PostsAndCategory_TABLE_NAME + " "
                        + "ON " + Tables.PostSimple_TABLE_NAME + "." + BaseColumns._ID + "=" + Tables.PostsAndCategory_TABLE_NAME + "." + Tables.PostsAndCategory_post_id_COLUMN + " "
                        + "JOIN " + Tables.Category_TABLE_NAME + " "
                        + "ON " + Tables.Category_TABLE_NAME + "." + BaseColumns._ID + "=" + Tables.PostsAndCategory_TABLE_NAME + "." + Tables.PostsAndCategory_category_id_COLUMN + " "
                        + "WHERE " + Tables.Category_TABLE_NAME + "." + BaseColumns._ID + "=" + category_id + " "
                        + "ORDER BY " + Tables.PostSimple_date_COLUMN + " DESC" + " "
                        + "LIMIT " + page*10 + "; "
                , new String[]{});
        return cursor;
    }

    // GET ONE
    /* ************************************************************************ */
    public static Cursor getOnePostSimpleFromId(String id)
    {
        Cursor cursor = sdb.query(Tables.PostSimple_TABLE_NAME, null, BaseColumns._ID + " = ?",
                new String[]{id}, null, null, null);
        return cursor;
    }
    public static Cursor getOneCategoryFromId(String id)
    {
        Cursor cursor = sdb.query(Tables.Category_TABLE_NAME, null, BaseColumns._ID + " = ?",
                new String[]{id}, null, null, null);
        return cursor;
    }
    public static Cursor getOnePostSimpleFromCategoryPage(int category_id, int post_id)
    {
        Cursor cursor = sdb.rawQuery(
                "SELECT * "
                        + "FROM " + Tables.PostsAndCategory_TABLE_NAME + " "
                        + "WHERE " + Tables.PostsAndCategory_post_id_COLUMN + "=" + post_id + " "
                + "AND " + Tables.PostsAndCategory_category_id_COLUMN + "=" + category_id
                , new String[]{});
        return cursor;
    }

    // CLEAR DB TABLES
    /* ************************************************************************ */
    public static void clearDB(SQLiteDatabase db)
    {
        db.execSQL("drop table if exists " + Tables.PostSimple_TABLE_NAME);
        db.execSQL("drop table if exists " + Tables.Category_TABLE_NAME);
        db.execSQL("drop table if exists " + Tables.PostsAndCategory_TABLE_NAME);
    }

    // CREATE DB TABLES
    /* ************************************************************************ */
    public static void createDBTables(SQLiteDatabase db)
    {
        db.execSQL(Tables.PostSimple);
        db.execSQL(Tables.Category);
        db.execSQL(Tables.PostsAndCategory);
    }
}