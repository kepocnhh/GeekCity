package stan.geek.city.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.List;

import stan.geek.city.helpers.SQliteHelper;

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
        List<Integer> units = SQliteHelper.tryGetCategoryIdsFromParentId(category_id);
        String where = "";
        if(units != null && units.size()>0)
        {
            where = "WHERE " + Tables.Category_TABLE_NAME + "." + BaseColumns._ID + "=" + units.get(0);
            for(int i=1; i<units.size(); i++)
            {
                where += " OR " +Tables.Category_TABLE_NAME + "." + BaseColumns._ID + "=" + units.get(i);
            }
            where += " ";
        }
        Cursor cursor = sdb.rawQuery(
                "SELECT * "
                        + "FROM " + Tables.PostSimple_TABLE_NAME + " "
                        + "JOIN " + Tables.PostsAndCategory_TABLE_NAME + " "
                        + "ON " + Tables.PostSimple_TABLE_NAME + "." + BaseColumns._ID + "=" + Tables.PostsAndCategory_TABLE_NAME + "." + Tables.PostsAndCategory_post_id_COLUMN + " "
                        + "JOIN " + Tables.Category_TABLE_NAME + " "
                        + "ON " + Tables.Category_TABLE_NAME + "." + BaseColumns._ID + "=" + Tables.PostsAndCategory_TABLE_NAME + "." + Tables.PostsAndCategory_category_id_COLUMN + " "
                        + where
                        + "ORDER BY " + Tables.PostSimple_date_COLUMN + " DESC" + " "
                        + "LIMIT " + page * 10 + "; "
                , new String[]{});
        return cursor;
    }
    public static Cursor getCategoryFromParentId(int category_id)
    {
        Cursor cursor = sdb.rawQuery(
                "SELECT * "
                        + "FROM " + Tables.Category_TABLE_NAME + " "
                        + "WHERE " + Tables.Category_parent_id_COLUMN + " = " + category_id + " ; "
                , new String[]{});
        return cursor;
    }
    public static Cursor getCategoryIdsFromParentId(int category_id)
    {
        Cursor cursor = sdb.rawQuery(
                "SELECT " + BaseColumns._ID + " "
                        + "FROM " + Tables.Category_TABLE_NAME + " "
                        + "WHERE " + Tables.Category_parent_id_COLUMN + " = " + category_id + " ; "
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