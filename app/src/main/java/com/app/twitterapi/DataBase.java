package com.app.twitterapi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {
    public static final String Name ="Data.db";
    ByteArrayOutputStream byteArrayOutputStream;
    byte[] imageByte;
    public DataBase(@Nullable Context context) {
        super(context, Name, null, 1);
    }

//creates SQLiteDatabase
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Tab ( id INTEGER PRIMARY KEY AUTOINCREMENT , Name TEXT , Content TEXT , Writer TEXT, Image BOLB)");
    }
//removes database on upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Tab");
        onCreate(db);
    }

    //stores values such as Name, Writer, Content and the image in the database
    public void Store(Model model){
        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap bitmap = model.getImage();
        byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        imageByte = byteArrayOutputStream.toByteArray();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name",model.getName());
        contentValues.put("Writer",model.getWriter());
        contentValues.put("Content",model.getContent());
        contentValues.put("Image",imageByte);
        long result = db.insert("Tab",null,contentValues);

    }

    public List<Model> getAll(){
        List<Model> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Tab",null);
        res.moveToFirst();
        while(res.isAfterLast()==false){
            int t1 = res.getInt(0);
            String t2= res.getString(1);
            String t3 = res.getString(2);
            String t4 = res.getString(3);
            byte[] imageByte = res.getBlob(4);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte,0,imageByte.length);

            Model ic = new Model(t1,t2,t3,t4,bitmap);
            arrayList.add(ic);
            res.moveToNext();
        }
        return  arrayList;
    }
    public Model Search (int id){
        Model M1 = new Model();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Tab",null);
        res.moveToFirst();
        while(res.isAfterLast()==false){
            int t1 = res.getInt(0);
            if(id==t1) {
                String t2= res.getString(1);
                String t3 = res.getString(2);
                String t4 = res.getString(3);
                byte[] imageByte = res.getBlob(4);
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte,0,imageByte.length);
                Model ic = new Model(t1,t2,t3,t4,bitmap);
                return ic;
            };
            res.moveToNext();
        }
        return  M1;
    }
    //delete from database
    public Integer Delete(String ID){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Tab","id = ?",new String[]{ID});
    }
    //edit information in database
    public boolean Edit(String ID,String Name , String Writer,String Content,Bitmap bitmap){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name",Name);
        contentValues.put("Writer",Writer);
        contentValues.put("Content",Content);
        byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        imageByte = byteArrayOutputStream.toByteArray();
        contentValues.put("Image",imageByte);

        db.update("Tab",contentValues,"id= ?",new String[]{ID});
        return true;
    }
    //searches the database
    public Model SearchByName (String str){
        Model M1 = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Tab",null);
        res.moveToFirst();
        while(res.isAfterLast()==false){
            String Cat = res.getString(2);
            if(Cat.contains(str)) {
                int t1 = res.getInt(1);
                String t2= res.getString(1);
                String t3 = res.getString(2);
                String t4 = res.getString(3);
                byte[] imageByte = res.getBlob(4);
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte,0,imageByte.length);
                Model ic = new Model(t1,t2,t3,t4,bitmap);
                return ic;
            };
            res.moveToNext();
        }
        return  M1;
    }
}
