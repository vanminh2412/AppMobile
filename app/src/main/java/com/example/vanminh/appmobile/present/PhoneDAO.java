package com.example.vanminh.appmobile.present;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.vanminh.appmobile.database.DBManager;
import com.example.vanminh.appmobile.model.ManuFacture;
import com.example.vanminh.appmobile.model.Phone;

import java.util.ArrayList;
import java.util.List;

public class PhoneDAO {

    private DBManager dbManager;
    private Context context;

    public PhoneDAO(DBManager dbManager, Context context) {
        this.dbManager = dbManager;
        this.context = context;
    }


    public List<Phone> getAllPhone(){
        SQLiteDatabase database = dbManager.getReadableDatabase();
        List<Phone> phoneList = new  ArrayList<>();
        String select_phone = "SELECT * FROM " + dbManager.TABLE_NAME;
        Cursor cursor = database.rawQuery(select_phone,null);
        if (cursor.moveToFirst()){
            do {
                Phone phone = new Phone();
                phone.setId(cursor.getInt(0));
                phone.setName(cursor.getString(1));
                phone.setPrice(cursor.getFloat(2));
                phone.setPro_id(cursor.getString(3));
                phoneList.add(phone);
            }while (cursor.moveToNext());
        }
        /*if (cursor != null){
            return null;
        }
        cursor.moveToFirst();
        while (cursor.isAfterLast()){
            int id = cursor.getInt(cursor.getColumnIndex(dbManager.ID));
            String name = cursor.getString(cursor.getColumnIndex(dbManager.PHONE_NAME));
            float price = cursor.getFloat(cursor.getColumnIndex(dbManager.PRICE));
            String pro_id = cursor.getString(cursor.getColumnIndex(dbManager.PRO_ID));
            phoneList.add(new Phone(id,name,price,pro_id));
            cursor.moveToNext();
        }
        cursor.close();*/
        database.close();
        return phoneList;
    }
    public void insertPhone(Phone phone){
        SQLiteDatabase database = dbManager.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(dbManager.ID,phone.getId());
        values.put(dbManager.PHONE_NAME,phone.getName());
        values.put(dbManager.PRICE,phone.getPrice());
        values.put(dbManager.PRO_ID,phone.getPro_id());
        database.insert(dbManager.TABLE_NAME,null,values);
        database.close();
    }
    public void updatePhone(Phone phone){
        SQLiteDatabase database = dbManager.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(dbManager.ID,phone.getId());
        values.put(dbManager.PHONE_NAME,phone.getName());
        values.put(dbManager.PRICE,phone.getPrice());
        values.put(dbManager.PRO_ID,phone.getPro_id());
        if (database.update(dbManager.TABLE_NAME,values,dbManager.ID + "=" + phone.getId(),null)>0){
            Toast.makeText(context,"Update success...!",Toast.LENGTH_SHORT).show();
        }
    }
    public void deletePhone(Phone phone){
        SQLiteDatabase database = dbManager.getWritableDatabase();
        if (database.delete(dbManager.TABLE_NAME,dbManager.ID + "=" + phone.getId(),null)>0){
            Toast.makeText(context,"Delete success...!",Toast.LENGTH_SHORT).show();
        }
    }

    public void insertPhonSX(ManuFacture manuFacture){
        SQLiteDatabase database = dbManager.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(dbManager.ID_SX,manuFacture.getIdSX());
        values.put(dbManager.NAME_SX,manuFacture.getNameSX());
        database.insert(dbManager.TABLE_NAME_1,null,values);
        database.close();
    }
    public List<ManuFacture> getListManu(){
        SQLiteDatabase database = dbManager.getReadableDatabase();
        List<ManuFacture> list = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + dbManager.TABLE_NAME_1;
        Cursor cursor = database.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do {
                ManuFacture manuFacture = new ManuFacture();
                manuFacture.setIdSX(cursor.getInt(0));
                manuFacture.setNameSX(cursor.getString(1));
                list.add(manuFacture);
            }while (cursor.moveToNext());
        }
        database.close();
        return list;
    }
}
