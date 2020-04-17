package com.example.myyoutubedemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class myDbAdapter {
    myDbHelper myhelper;

//    Context context1;

    public myDbAdapter(Context context) {
        myhelper = new myDbHelper(context);

//        context= this.context1;
    }


    public long insertData(String name,String uid, String publisher,String year,String viewers,String search) {

        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myhelper.NAME, name);
        contentValues.put(myhelper.UID,uid);
        contentValues.put(myhelper.PUBLISHER, publisher);
        contentValues.put(myhelper.YEAR,year);
        contentValues.put(myhelper.VIEVERS,viewers);
        contentValues.put(myhelper.SEARCH,search);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null, contentValues);
        return id;
    }





    public String getData() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.SLNO,myDbHelper.UID, myDbHelper.NAME, myDbHelper.YEAR};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME, null, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.SLNO));
            String uid = cursor.getString(cursor.getColumnIndex(myDbHelper.UID));
            String name = cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            String type = cursor.getString(cursor.getColumnIndex(myDbHelper.YEAR));
//            String search = cursor.getString(cursor.getColumnIndex(myDbHelper.SEARCH));
            buffer.append(cid + "   " +uid + "  " + name + "   " + type + " \n");
        }
        return buffer.toString();
    }


    public String getSearchResult() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.SLNO, myDbHelper.NAME,myDbHelper.UID, myDbHelper.PUBLISHER,myDbHelper.YEAR,myDbHelper.VIEVERS,myhelper.SEARCH};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        Log.e("StringBuffer","StringBuffer");
        while (cursor.moveToNext()) {
            Log.e("StringBuffer1","StringBuffer1");
            int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.SLNO));
            String name = cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            String uid = cursor.getString(cursor.getColumnIndex(myDbHelper.UID));
            String publisher = cursor.getString(cursor.getColumnIndex(myDbHelper.PUBLISHER));
            String year = cursor.getString(cursor.getColumnIndex(myDbHelper.YEAR));
            String viewers = cursor.getString(cursor.getColumnIndex(myDbHelper.VIEVERS));
            String search = cursor.getString(cursor.getColumnIndex(myDbHelper.SEARCH));
            buffer.append(cid + "   " +uid + "  " + name + "   " + publisher + "   " + year + "  " + search + " "+ viewers +"  \n");
        }
        Log.e("StringBuffer2","StringBuffer2");
        return buffer.toString();
    }





    public int delete(String uname) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs = {uname};

        int count = db.delete(myDbHelper.TABLE_NAME, myDbHelper.NAME + " = ?", whereArgs);
        return count;
    }
    public void deleteAllAtricles() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        db.delete(myDbHelper.TABLE_NAME,null,null);
        db.close();
    }


    public int updateName(String oldName, String newName) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME, newName);
        String[] whereArgs = {oldName};
        int count = db.update(myDbHelper.TABLE_NAME, contentValues, myDbHelper.NAME + " = ?", whereArgs);
        return count;
    }

    public String[] getItemsFromDb(){

        // add items on the array dynamically
        List<MyObject> products = myhelper.read();
        int rowCount = products.size();

        String[] item = new String[rowCount];
        int x = 0;

        for (MyObject record : products) {

            item[x] = record.objectName;
            x++;
        }

        return item;
    }




    public String[] getSearchResultFromDb(String searchTerm){

        // add items on the array dynamically
        List<MyObject> products = myhelper.readResult(searchTerm);
        int rowCount = products.size();

        String[] item = new String[rowCount];
        int x = 0;

        for (MyObject record : products) {

            item[x] = record.objectName;
            x++;
        }

        return item;
    }



    public String[] getUpdateTomDb(String uid){

        // add items on the array dynamically
        List<MyObject> products = myhelper.update(uid);
        int rowCount = products.size();

        String[] item = new String[rowCount];
        int x = 0;

        for (MyObject record : products) {

            item[x] = record.objectName;
            x++;
        }

        return item;
    }



    public class myDbHelper extends SQLiteOpenHelper
    {

        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "MyYoutube";   // Table Name
        private static final int DATABASE_Version = 1;   // Database Version
        private static final String SLNO = "slNo";
        private static final String UID="id";     // Column I (Primary Key)
        private static final String NAME = "Name";    //Column II
        private static final String PUBLISHER= "publisher";    // Column III
        private static final String YEAR ="year" ;
        private static final String VIEVERS ="vievers";
        private static final String SEARCH ="search";
        private static final String CREATE_TABLE ="CREATE TABLE "+TABLE_NAME+
                " ("+SLNO+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255) ,"+ UID+" VARCHAR(225),"+ PUBLISHER+" VARCHAR(255) ,"+ YEAR +" VARCHAR(225) ,"+ VIEVERS +" VARCHAR(225) ,"+SEARCH+" INTEGER DEFAULT 0);";

        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;


        private Context context;


        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);


            } catch (Exception e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
//            Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);


                onCreate(db);
            }catch (Exception e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        public boolean checkIfExists(String objectName){

            boolean recordExists = false;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT " + "*" + " FROM " + TABLE_NAME + " WHERE " + NAME + " = '" + objectName + "'", null);

            if(cursor!=null) {

                if(cursor.getCount()>0) {
                    recordExists = true;
                }
            }

            cursor.close();
            db.close();

            return recordExists;
        }



        // Read records related to the search term
        public List<MyObject> read() {

            List<MyObject> recordsList = new ArrayList<>();

            // select query
            String sql = "";
            sql += "SELECT * FROM " + TABLE_NAME;



            SQLiteDatabase db = this.getWritableDatabase();

            // execute the query
            Cursor cursor = db.rawQuery(sql, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {

                    // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));

                    String objectName = cursor.getString(cursor.getColumnIndex(NAME));
                    String objectUID = cursor.getString(cursor.getColumnIndex(UID));
                    String objectPublisher = cursor.getString(cursor.getColumnIndex(PUBLISHER));
                    String objectYear= cursor.getString(cursor.getColumnIndex(YEAR));
                    String objectViewers = cursor.getString(cursor.getColumnIndex(VIEVERS));
                    String objectSearch = cursor.getString(cursor.getColumnIndex(SEARCH));
                    MyObject result = new MyObject(objectName.concat("#").concat(objectUID).concat("#").concat(objectPublisher).concat("#").concat(objectYear).concat("#").concat(objectViewers).concat("#").concat(objectSearch));


                    // add to list
                    recordsList.add(result);

                } while (cursor.moveToNext());
            }

            cursor.close();
            db.close();

            // return the list of records
            return recordsList;
        }

        public List<MyObject> readResult(String searchTerm) {

            List<MyObject> recordsList = new ArrayList<>();

            // select query
            String sql = "";
//            sql += "SELECT * FROM (";
            sql += "SELECT * FROM " + TABLE_NAME;
            sql += " WHERE " + SEARCH + " LIKE '%" + searchTerm + "%'";

//            sql += " ORDER BY sortfield ASC" ;

//            order by sortfield DESC limit 10) order by sortfield ASC;

            SQLiteDatabase db = this.getWritableDatabase();

            // execute the query
            Cursor cursor = db.rawQuery(sql, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {

                    // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
                    String objectName = cursor.getString(cursor.getColumnIndex(NAME));
                    String objectUID = cursor.getString(cursor.getColumnIndex(UID));
                    String objectPublisher = cursor.getString(cursor.getColumnIndex(PUBLISHER));
                    String objectYear= cursor.getString(cursor.getColumnIndex(YEAR));
                    String objectViewers = cursor.getString(cursor.getColumnIndex(VIEVERS));
                    String objectSearch = cursor.getString(cursor.getColumnIndex(SEARCH));
                    MyObject result = new MyObject(objectName.concat("#").concat(objectUID).concat("#").concat(objectPublisher).concat("#").concat(objectYear).concat("#").concat(objectViewers).concat("#").concat(objectSearch));


                    // add to list
                    recordsList.add(result);

                } while (cursor.moveToNext());
            }

            cursor.close();
            db.close();

            // return the list of records
            return recordsList;
        }

        public List<MyObject> update(String uid) {

            List<MyObject> recordsList = new ArrayList<>();

            // select query
            String sql = "";
//            db.execSQL("UPDATE DB_TABLE SET YOUR_COLUMN='newValue' WHERE id=6 ");

            String CURRENT_TIMESTAMP = String.valueOf(System.currentTimeMillis());
            sql += " UPDATE " + TABLE_NAME;
            sql += " SET " + SEARCH + "=" + '1' ;
//            sql += " SET " +  ;
            sql += " WHERE " + UID + " LIKE '%" + uid + "%'";



            SQLiteDatabase db = this.getWritableDatabase();

            // execute the query
            Cursor cursor = db.rawQuery(sql, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {

                    // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
                    String objectName = cursor.getString(cursor.getColumnIndex(NAME));
                    String objectUID = cursor.getString(cursor.getColumnIndex(UID));
                    String objectPublisher = cursor.getString(cursor.getColumnIndex(PUBLISHER));
                    String objectYear= cursor.getString(cursor.getColumnIndex(YEAR));
                    String objectViewers = cursor.getString(cursor.getColumnIndex(VIEVERS));
                    String objectSearch = cursor.getString(cursor.getColumnIndex(SEARCH));
                    MyObject result = new MyObject(objectName.concat("-").concat(objectUID).concat("-").concat(objectPublisher).concat("-").concat(objectYear).concat(objectViewers).concat("-").concat(objectSearch));


                    // add to list
                    recordsList.add(result);

                } while (cursor.moveToNext());
            }

            cursor.close();
            db.close();

            // return the list of records
            return recordsList;
        }





    }

}
