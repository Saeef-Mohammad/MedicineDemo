package com.saeefmd.official.medicinedemo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.saeefmd.official.medicinedemo.model.Medicine;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Name
    public static final String DATABASE_NAME = "medicines_db";

    // Database Version
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String CREATE_TABLE = "CREATE TABLE " +
                Medicine.TABLE_NAME + " ( " +
                Medicine.COLUMN_ID + " TEXT PRIMARY KEY, " +
                Medicine.COLUMN_DRUGS + " TEXT, " +
                Medicine.COLUMN_INDICATIONS + " TEXT, " +
                Medicine.COLUMN_THERAPEUTIC_CLASS + " TEXT, " +
                Medicine.COLUMN_PHARMACOLOGY + " TEXT, " +
                Medicine.COLUMN_DOSAGE + " TEXT, " +
                Medicine.COLUMN_INTERACTION + " TEXT, " +
                Medicine.COLUMN_CONTRANDICATIONS + " TEXT, " +
                Medicine.COLUMN_SIDE_EFFECTS + " TEXT, " +
                Medicine.COLUMN_PREGNANCY + " TEXT, " +
                Medicine.COLUMN_PRECAUTIONS + " TEXT, " +
                Medicine.COLUMN_STORAGE + " TEXT" + " )";

        // Create table
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Delete table if exist
        db.execSQL("DROP TABLE IF EXISTS " + Medicine.TABLE_NAME);

        // Create table again
        onCreate(db);
    }

    public void insertMedicine(String id, String drugs, String indications, String therapeuticClass, String pharmacology,
                               String dosage, String interaction, String contraindications, String sideEffects, String pregnancy,
                               String precautions, String storage) {

        // Open database as writable
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Medicine.COLUMN_ID, id);
        values.put(Medicine.COLUMN_DRUGS, drugs);
        values.put(Medicine.COLUMN_INDICATIONS, indications);
        values.put(Medicine.COLUMN_THERAPEUTIC_CLASS, therapeuticClass);
        values.put(Medicine.COLUMN_PHARMACOLOGY, pharmacology);
        values.put(Medicine.COLUMN_DOSAGE, dosage);
        values.put(Medicine.COLUMN_INTERACTION, interaction);
        values.put(Medicine.COLUMN_CONTRANDICATIONS, contraindications);
        values.put(Medicine.COLUMN_SIDE_EFFECTS, sideEffects);
        values.put(Medicine.COLUMN_PREGNANCY, pregnancy);
        values.put(Medicine.COLUMN_PRECAUTIONS, precautions);
        values.put(Medicine.COLUMN_STORAGE, storage);

        // Insert new values into database
        db.insert(Medicine.TABLE_NAME, null, values);

        db.close();
    }

    public List<Medicine> getAllMediciness() {
        List<Medicine> medicineList = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Medicine.TABLE_NAME + " ORDER BY " +
                Medicine.COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {

            do {
                Medicine medicine = new Medicine();
                medicine.setId(cursor.getString(cursor.getColumnIndex(Medicine.COLUMN_ID)));
                medicine.setDrugs(cursor.getString(cursor.getColumnIndex(Medicine.COLUMN_DRUGS)));
                medicine.setIndications(cursor.getString(cursor.getColumnIndex(Medicine.COLUMN_INDICATIONS)));
                medicine.setTherapeuticClass(cursor.getString(cursor.getColumnIndex(Medicine.COLUMN_THERAPEUTIC_CLASS)));
                medicine.setPharmacology(cursor.getString(cursor.getColumnIndex(Medicine.COLUMN_PHARMACOLOGY)));
                medicine.setDosage(cursor.getString(cursor.getColumnIndex(Medicine.COLUMN_DOSAGE)));
                medicine.setInteraction(cursor.getString(cursor.getColumnIndex(Medicine.COLUMN_INTERACTION)));
                medicine.setContraindications(cursor.getString(cursor.getColumnIndex(Medicine.COLUMN_CONTRANDICATIONS)));
                medicine.setSideEffects(cursor.getString(cursor.getColumnIndex(Medicine.COLUMN_SIDE_EFFECTS)));
                medicine.setPregnancy(cursor.getString(cursor.getColumnIndex(Medicine.COLUMN_PREGNANCY)));
                medicine.setPrecautions(cursor.getString(cursor.getColumnIndex(Medicine.COLUMN_PRECAUTIONS)));
                medicine.setStorage(cursor.getString(cursor.getColumnIndex(Medicine.COLUMN_STORAGE)));

                medicineList.add(medicine);

            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return medicineList;
    }

    public int getMedicinesCount() {

        String countQuery = "SELECT  * FROM " + Medicine.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    public void deleteAllMedicines() {

        SQLiteDatabase db = this.getWritableDatabase();

        // Delete all rows from the table
        db.execSQL("DELETE FROM " + Medicine.TABLE_NAME);

        db.close();
    }
}
