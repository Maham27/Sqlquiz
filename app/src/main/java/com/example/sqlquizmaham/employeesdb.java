package com.example.sqlquizmaham;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class employeesdb {
    public static final String K_ROWID = "_id";
    public static final String K_NAME = "_name";
    public static final String K_NUMBER = "_number";
    public static final String K_DEPARTMENT = "_department";



    private final String DATABASE_NAME = "employeedb";
    private final String DATABASE_TABLE = "employeestable";
    private final int DATABASE_VERSION = 1;

    private DBHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public employeesdb(Context context)
    {
        ourContext = context;
    }

    private class DBHelper extends SQLiteOpenHelper
    {

        public DBHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            /*
            CREATE TABLE ContactsTable(
            _id INTEGER PRIMARY KEY AUTOINCREMENT,
            person_name TEXT NOT NULL,
            _cell TEXT NOT NULL);
             */
            String sqlCode = "CREATE TABLE "+DATABASE_TABLE +"("
                    +K_ROWID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                    +K_DEPARTMENT+" TEXT NOT NULL,"
                    +K_NAME +" TEXT NOT NULL,"
                    +K_NUMBER +" TEXT NOT NULL);";
            db.execSQL(sqlCode);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Backup of database

            db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
            onCreate(db);
        }
    }

    public employeesdb open()
    {
        ourHelper = new DBHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        ourHelper.close();
    }

    public long createEntry(String name, String number,String department)
    {
        ContentValues c = new ContentValues();
        c.put(K_NAME, name);
        c.put(K_NUMBER,number);
        c.put(K_DEPARTMENT,department);

        return ourDatabase.insert(DATABASE_TABLE, null,c);
    }

    public String getData()
    {
        String []columns = new String[]{K_ROWID, K_NAME, K_NUMBER,K_DEPARTMENT};
        Cursor o = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null,null);
        String r = "";
        int iRID = o.getColumnIndex(K_ROWID);
        int iRName = o.getColumnIndex(K_NAME);
        int iRNumber = o.getColumnIndex(K_NUMBER);
        int iRDepartment=o.getColumnIndex(K_DEPARTMENT);


        for(o.moveToFirst(); !o.isAfterLast(); o.moveToNext())
        {
            r = r + o.getString(iRID) + " : "
                    + o.getString(iRName)+" "
                    + o.getString(iRDepartment)+" "
                    +o.getString(iRNumber) + "\n";
        }

        o.close();
        return r;

    }
    public long updateEntry(String rid, String n, String no,String d)
    {
        ContentValues c= new ContentValues();
        c.put(K_NAME, n);
        c.put(K_NUMBER, no);
        c.put(K_DEPARTMENT,d);

        return ourDatabase.update(DATABASE_TABLE, c, K_ROWID+"=?",new String[]{rid});
    }

    public long deleteEntry(String rid)
    {
        return ourDatabase.delete(DATABASE_TABLE, K_ROWID+"=?", new String[]{rid});
    }



}


