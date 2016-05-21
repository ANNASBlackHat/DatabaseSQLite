package com.example.annasblackhat.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ANNASBlackHat on 3/23/2016.
 */
public class WalletDatabase extends SQLiteOpenHelper{

    private final static String DATABASE_NAME = "amcc_wallet.db";
    private final String TABLE_NAME = "wallet";
    private final static int DATABASE_VERSION = 1;
    private SQLiteDatabase sqLiteDatabase;

    private final static  String COLUMN_ID = "id";
    private final static  String COLUMN_JENIS = "jenis";
    private final static  String COLUMN_DESC = "deskripsi";
    private final static  String COLUMN_CAT = "kategori";
    private final static  String COLUMN_TOTAL = "total";
    private final static  String COLUMN_TGL = "tanggal";
    private Context context;

    public WalletDatabase(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" " +
                "("+COLUMN_ID+" INTEGER PRIMARY KEY autoincrement not null," +
                ""+COLUMN_JENIS+" TEXT," +
                ""+COLUMN_DESC+" TEXT, "+COLUMN_CAT+" TEXT, " +
                ""+COLUMN_TOTAL+" INTEGER, "+COLUMN_TGL+" TEXT)";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public void insertData(Wallet w){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_JENIS, w.getJenis());
        contentValues.put(COLUMN_DESC, w.getDeskripsi());
        contentValues.put(COLUMN_CAT, w.getKategori());
        contentValues.put(COLUMN_TOTAL, w.getTotal());
        contentValues.put(COLUMN_TGL, w.getTgl());
        db.insert(TABLE_NAME, null, contentValues);
    }

    public List<Wallet> getDataByJenis(String jenis){
        List<Wallet> walletList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCursor = db.query(
                TABLE_NAME,
                null, COLUMN_JENIS+"=?",
                new String[]{jenis},
                null, null, null);
        while (mCursor.moveToNext()){
            Wallet w = new Wallet();
            w.setTgl(mCursor.getString(mCursor.getColumnIndex(COLUMN_TGL)));
            w.setTotal(mCursor.getInt(mCursor.getColumnIndex(COLUMN_TGL)));
            w.setDeskripsi(mCursor.getString(mCursor.getColumnIndex(COLUMN_DESC)));
            w.setJenis(mCursor.getString(mCursor.getColumnIndex(COLUMN_JENIS)));
            w.setId(mCursor.getInt(mCursor.getColumnIndex(COLUMN_ID)));
            w.setKategori(mCursor.getString(mCursor.getColumnIndex(COLUMN_CAT)));
            System.out.println("XXXX JEnis : "+w.getDeskripsi());
            walletList.add(w);
        }
        mCursor.close();
        db.close();
        return walletList;
    }

    public void updateData(Wallet w){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CAT, w.getKategori());
        cv.put(COLUMN_DESC, w.getDeskripsi());
        cv.put(COLUMN_JENIS, w.getJenis());
        cv.put(COLUMN_TGL, w.getTgl());
        cv.put(COLUMN_TOTAL, w.getTotal());
        db.update(TABLE_NAME,
                cv,
                COLUMN_ID+"=?",
                new String[]{String.valueOf(w.getId())});
        db.close();
    }

    public void deleteData(int ID){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,
                COLUMN_ID+"=?",
                new String[]{String.valueOf(ID)});
        db.close();
    }
}

