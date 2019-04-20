package com.example.wtstudentsay1005064.phonebook;

/*
   Every content provider class includes an authority string and a content_uri
   In this app, content_uri is the "//content " followed by the authority string followed by
   "/table_name"
   Each of the overriding methods will use a uri to indicate the table on which the data is
   requested.
 */

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class MyPhoneBook extends ContentProvider {
    private static  final  String AUTHORITY =
            "com.example.wtstudentsay1005064.phonebook.MyPhoneBook";
    private static final String DATABASE_NAME = "phoneBookDB.db";
    private static final String TABLE_PRODUCT = "Info";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FIRSTNAME = "FirstName";
    public static final String COLUMN_LASTNAME = "LastName";
    public static final String COLUMN_PHONENUMBER = "PhoneNum";
    private static final int DATABASE_VERSION = 1;
    public static final int PRODUCTS = 1;
    public static final int PRODUCTS_ID = 2;
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_PRODUCT);
    private SQLiteDatabase sqlDB;
    private UriMatcher uriMatcher;

    @Override
    public boolean onCreate() {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,TABLE_PRODUCT , PRODUCTS);
        uriMatcher.addURI(AUTHORITY,TABLE_PRODUCT + "/#", PRODUCTS_ID);
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        sqlDB = dbHelper.getWritableDatabase();
        if (sqlDB != null)
            return  true;
        else
            return false;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection,  String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TABLE_PRODUCT);
        int uriType = uriMatcher.match(uri);
        if(uriType != PRODUCTS)
            throw new UnsupportedOperationException("Unknown URI: " + uri);
        Cursor cursor = queryBuilder.query(sqlDB, projection, selection, selectionArgs,null ,null , sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(),uri );
        return cursor;
    }


    @Override
    public Uri insert( Uri uri,  ContentValues values) {
        int uriType = uriMatcher.match(uri);
        long id = 0;
        if(uriType == PRODUCTS)
            id = sqlDB.insert(TABLE_PRODUCT,null, values);
        else
            throw  new UnsupportedOperationException("Unknown URI: " + uri);
        getContext().getContentResolver().notifyChange(uri, null);
        return uri.parse(TABLE_PRODUCT + "/" + id);

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int uriType = uriMatcher.match(uri);
        int rowsUpdated = 0;
        if(uriType == PRODUCTS)
            rowsUpdated = sqlDB.update(TABLE_PRODUCT, values, selection, selectionArgs);
        else
            throw new UnsupportedOperationException( "Unknown URI: " + uri);
        return rowsUpdated;
    }

    @Override
    public int delete( Uri uri, String selection, String[] selectionArgs) {
        int uriType = uriMatcher.match(uri);
        int rowsDeleted = 0;
        if(uriType == PRODUCTS)
            rowsDeleted = sqlDB.delete(TABLE_PRODUCT, selection,selectionArgs );
        else
            throw new  UnsupportedOperationException("Unknown URI: " + uri);
        return rowsDeleted;
    }


    @Override
    public String getType( Uri uri) {
        return null;
    }

    private static  class DatabaseHelper extends SQLiteOpenHelper{
        public  DatabaseHelper(Context context){
            super(context,DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String create_product_table = "CREATE TABLE " + TABLE_PRODUCT + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY, "
                    + COLUMN_FIRSTNAME + " TEXT, " + COLUMN_LASTNAME + " TEXT, "
                    + COLUMN_PHONENUMBER + " TEXT )";
            db.execSQL(create_product_table);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
            onCreate(db);

        }
    }
}
