package edu.feicui.contactsupdate.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

import edu.feicui.contactsupdate.bean.TelNumberInfo;
import edu.feicui.contactsupdate.bean.TelclassInfo;

/**
 * Created by ☆刘群☆ on 2016/5/3.
 */
public class DBRead {

    private static final String TAG = "DBRead";

    public static File telFile;

    static {
        String dbFileDir = "data/data/edu.feicui.contactsupdate/";

        File fileDir = new File(dbFileDir);

        fileDir.mkdirs();

        telFile = new File(fileDir, "commonnum.db");
    }

    public static boolean isExitsTeldbFile() {

        File toFile = DBRead.telFile;
        if (!toFile.exists() || toFile.length() <= 0) {
            return false;
        }
        return true;
    }

    public static ArrayList<TelclassInfo> readTeldbClasslist() throws Exception {
        ArrayList<TelclassInfo> telclassInfos = new ArrayList<>();

        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = SQLiteDatabase.openOrCreateDatabase(telFile, null);
            cursor = db.rawQuery("select * from classlist", null);

            Log.d(TAG, "电话列表长度: " + cursor.getCount());

            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    int idx = cursor.getInt(cursor.getColumnIndex("idx"));

                    TelclassInfo telclassInfo = new TelclassInfo(name, idx);

                    telclassInfos.add(telclassInfo);
                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (cursor != null) {
                    Log.d(TAG, "readTeldbClasslist: cursor 被关闭 ");
                    cursor.close();
                }
                if (db != null) {
                    Log.d(TAG, "readTeldbClasslist: db被关闭");
                    db.close();
                }

            } catch (Exception e) {
                throw e;
            }
            Log.d(TAG, "读取电话列表结束,列表长度为: " + telclassInfos.size());
        }

        return telclassInfos;
    }



    public static ArrayList<TelNumberInfo> readTeldbTable(int idx) throws Exception {

        ArrayList<TelNumberInfo> numberInfos = new ArrayList<>();

        String sql = "select * from table" + idx;

        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = SQLiteDatabase.openOrCreateDatabase(telFile, null);
            cursor = db.rawQuery(sql, null);

            Log.d(TAG, "电话号码表的长度为: " + cursor.getCount());

            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String number = cursor.getString(cursor.getColumnIndex("number"));

                    TelNumberInfo numberInfo = new TelNumberInfo(name, number);

                    numberInfos.add(numberInfo);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {

                if (cursor != null) {
                    cursor.close();
                }
                if (db != null) {
                    db.close();
                }
            } catch (Exception e) {
                throw e;
            }
            Log.d(TAG, "读取电话号码列表结束,表长为: " + numberInfos.size());
        }

        return numberInfos;
    }
}
