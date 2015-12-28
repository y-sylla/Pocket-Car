package elitetechsolutions.newpocketcar;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHandler {

    private static final int DATABASE_VERSION = 16;
    private static final String DATABASE_NAME = "pocket.db";

    //Table Names
    private static final String TABLE_USERS = "users";
    private static final String TABLE_PROFILES = "profiles";
    private static final String TABLE_CARDATA = "cardata";
    private static final String TABLE_NOTES = "ownernotes";
    private static final String TABLE_BARCODE = "barcode";
    private static final String TABLE_SERVICE = "service";
    private static final String TABLE_RECEIPT = "workreceipt";
    private static final String TABLE_NOTIFICATION ="notification";

    //USER Table Column Variable
    public static final String USERS_COLUMN_ID = "id";
    public static final String USERS_COLUMN_USERNAME = "username";
    public static final String USERS_COLUMN_PASSWORD = "password";

    //Notification Table Column Variable
    public static final String NOTIFICATION_COLUMN_ID = "id";
    public static final String NOTIFICATION_COLUMN_REQ ="notificationrequirements";
    public static final String NOTIFICATION_COLUMN_DATE ="ndate";
    public static final String NOTIFICATION_COLUMN_TIME ="time";

    //Profile Table Column Variable
    public static final String PROFILES_COLUMN_ID = "id";
    public static final String PROFILES_COLUMN_USERID = "user_id";
    public static final String PROFILES_COLUMN_NOTIFICATION_ID ="notificationID";
    public static final String PROFILES_COLUMN_SERVICE_ID ="serviceEntryID";
    public static final String PROFILES_COLUMN_MAKE = "make";
    public static final String PROFILES_COLUMN_MODEL = "model";
    public static final String PROFILES_COLUMN_YEAR = "year";
    public static final String PROFILES_COLUMN_MILEAGE = "mileage";
    public static final String PROFILES_COLUMN_COLOR = "color";
    public static final String PROFILES_COLUMN_PDATE = "purchaseDate";
    public static final String PROFILES_COLUMN_IMAGE = "profileimage";

    //Service Entry Table Column Variable
    public static final String SERVICE_COLUMN_ID = "id";
    public static final String SERVICE_COLUMN_VID = "vehicleprofile_id";
    public static final String SERVICE_COLUMN_CID = "cardate_id";
    public static final String SERVICE_COLUMN_OID = "owner_id";
    public static final String SERVICE_COLUMN_PID="part_id";
    public static final String SERVICE_COLUMN_WID="recept_id";

    //Car Data Table Column Variable
    public static final String CARDATA_COLUMN_ID = "id";
    public static final String CARDATA_COLUMN_DATE ="cdate";
    public static final String CARDATA_COLUMN_MILEAGE="mileage";

    //Owner's Notes Table Column Variable
    public static final String NOTES_COLUMN_ID = "id";
    public static final String NOTES_COLUMN_TIME = "time";
    public static final String NOTES_COLUMN_TITLE = "title";
    public static final String NOTES_COLUMN_NOTES = "notes";
    public static final String NOTES_COLUMN_PRODUCTS ="productsUsed";

    //Work Receipt Column Variable
    public static final String RECEIPT_COLUMN_ID ="id";
    public static final String RECEIPT_COLUMN_IMAGE ="receiptimage";
    public static final String RECEIPT_COLUMN_COST ="cost";

    //Barcode Table Column Variable
    public static final String BARCODE_COLUMN_ID = "id";
    public static final String BARCODE_COLUMN_DESC = "description";
    public static final String BARCODE_COLUMN_PART = "partname";


    private DBOpenHelper openHelper;
    private SQLiteDatabase sqlDb;

    //User table SQL create statement
    private static final String USER_CREATE_ENTRIES = "CREATE TABLE " +
            TABLE_USERS + " (" + USERS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USERS_COLUMN_USERNAME + " TEXT UNIQUE NOT NULL, " + USERS_COLUMN_PASSWORD + " INTEGER NOT NULL" + ")";

    //Notification table SQL create statement
    private static final String NOTIFICATION_CREATE_ENTRIES = "CREATE TABLE " +
            TABLE_NOTIFICATION + " (" + NOTIFICATION_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NOTIFICATION_COLUMN_REQ + " TEXT NULL, " + NOTIFICATION_COLUMN_DATE + " TEXT NULL, " +
            NOTIFICATION_COLUMN_TIME + " TEXT NULL)";

    //Profile table SQL create statement
    private static final String PROFILE_CREATE_ENTRIES = "CREATE TABLE " +
            TABLE_PROFILES + " (" + PROFILES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PROFILES_COLUMN_USERID + " INTEGER NOT NULL AUTOINCREMENT, " + PROFILES_COLUMN_NOTIFICATION_ID +
            " INTEGER NOT NULL AUTOINCREMENT, " + PROFILES_COLUMN_SERVICE_ID + " INTEGER NOT NULL AUTOINCREMENT, " +
            PROFILES_COLUMN_MAKE + " TEXT NOT NULL" +
            PROFILES_COLUMN_MODEL + " TEXT NOT NULL, " + PROFILES_COLUMN_YEAR + " INTEGER NOT NULL, " +
            PROFILES_COLUMN_MILEAGE + " INTEGER NOT NULL, " + PROFILES_COLUMN_COLOR + " TEXT NOT NULL, " +
            PROFILES_COLUMN_PDATE + " TEXT NOT NULL, " + PROFILES_COLUMN_IMAGE + " BLOB NULL)";
           /* +" FOREIGN KEY (" + PROFILES_COLUMN_USERID + ")REFERENCES " +
            TABLE_USERS +"(" + USERS_COLUMN_ID + ")" +
            " FOREIGN KEY (" + PROFILES_COLUMN_NOTIFICATION_ID + ") REFERENCES " +
            TABLE_NOTIFICATION +"("+NOTIFICATION_COLUMN_ID+")"+
            "FOREIGN KEY (" + PROFILES_COLUMN_SERVICE_ID +") REFERENCES " +
            TABLE_SERVICE +"("+SERVICE_COLUMN_ID+"))";*/

    //Service Entry SQL Create Statement
    private static final String SERVICE_CREATE_ENTRIES = "CREATE TABLE " +
            TABLE_SERVICE + " (" + SERVICE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            SERVICE_COLUMN_VID + " INT NOT NULL AUTOINCREMENT, " +
            SERVICE_COLUMN_CID + " INT NOT NULL AUTOINCREMENT, " +
            SERVICE_COLUMN_OID + " INT NOT NULL AUTOINCREMENT, " +
            SERVICE_COLUMN_PID + " INT NOT NULL AUTOINCREMENT, " +
            SERVICE_COLUMN_WID + " INT NOT NULL AUTOINCREMENT, " +
            " FOREIGN KEY (" + SERVICE_COLUMN_VID + ") REFERENCES " +
            TABLE_USERS +"(" + USERS_COLUMN_ID + ")" +
            " FOREIGN KEY (" + SERVICE_COLUMN_CID + ") REFERENCES " +
            TABLE_CARDATA +"(" + CARDATA_COLUMN_ID + ")" +
            " FOREIGN KEY (" + SERVICE_COLUMN_OID + ") REFERENCES " +
            TABLE_NOTES +"(" + NOTES_COLUMN_ID + ")" +
            " FOREIGN KEY (" + SERVICE_COLUMN_PID + ") REFERENCES " +
            TABLE_BARCODE +"(" + BARCODE_COLUMN_ID + ")" +
            " FOREIGN KEY (" + SERVICE_COLUMN_WID + ") REFERENCES " +
            TABLE_RECEIPT +"(" + RECEIPT_COLUMN_ID + ")" + ")";

    //Car Data SQL statement
    private static final String CARDATA_CREATE_ENTRIES = "CREATE TABLE " +
            TABLE_CARDATA + " (" + CARDATA_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CARDATA_COLUMN_DATE + " TEXT, " +
            CARDATA_COLUMN_MILEAGE + " INTEGER)";

    //Owner's Notes SQL Create Statement
    private static final String NOTES_CREATE_ENTRIES = "CREATE TABLE " +
            TABLE_NOTES + " (" + NOTES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENET, " +
            NOTES_COLUMN_TIME + " TEXT NULL, " + NOTES_COLUMN_TITLE + " TEXT NULL, " +
            NOTES_COLUMN_NOTES + " TEXT NULL" + NOTES_COLUMN_PRODUCTS + " TEXT NULL)";

    //Work Receipt SQL Create statements
    private static final String RECEIPT_CREATE_ENTRIES = "CREATE TABLE " +
            TABLE_RECEIPT + " (" + RECEIPT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            RECEIPT_COLUMN_IMAGE + " BLOB NULL, " + RECEIPT_COLUMN_COST + " REAL NULL" + ")";


    //Barcode SQL Create Statement
    private static final String BARCODE_CREATE_ENTRIES = "CREATE TABLE " +
            TABLE_BARCODE + " (" + BARCODE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENET, " +
            BARCODE_COLUMN_DESC + " TEXT NULL, " + BARCODE_COLUMN_PART + " TEXT NULL)";


    public DBHandler(Context context) {
        openHelper = new DBOpenHelper(context);
        sqlDb = openHelper.getWritableDatabase();
    }


    //Insert data into user table
    public void saveUserRecords(String username, int password) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(USERS_COLUMN_USERNAME,username);
        contentValues.put(USERS_COLUMN_PASSWORD, password);

        sqlDb.insert(TABLE_USERS, null, contentValues);
    }

    //Insert data into Vehicle Profile Table
    public void saveVPRecords (String make,String model,int year, int mileage, String color, String purchasedate)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PROFILES_COLUMN_MAKE,make);
        contentValues.put(PROFILES_COLUMN_MODEL,model);
        contentValues.put(PROFILES_COLUMN_YEAR,year);
        contentValues.put(PROFILES_COLUMN_MILEAGE,mileage);
        contentValues.put(PROFILES_COLUMN_COLOR, color);
        contentValues.put(PROFILES_COLUMN_PDATE, purchasedate);

        sqlDb.insert(TABLE_PROFILES,null,contentValues);
    }

    //Insert data into Notification Table
    public void saveNotifRecords (String date, String time, String text) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOTIFICATION_COLUMN_DATE,date);
        contentValues.put(NOTIFICATION_COLUMN_TIME, time);
        contentValues.put(NOTIFICATION_COLUMN_REQ, text);

        sqlDb.insert(TABLE_NOTIFICATION, null, contentValues);
    }

    //Insert Service Entry data to their correct tables
    public void saveServiceRecords (String date,int mileage,double cost,String productsUsed, String notes, String time)
    {
        ContentValues cvCar = new ContentValues();
        cvCar.put(CARDATA_COLUMN_DATE,date);
        cvCar.put(CARDATA_COLUMN_MILEAGE,mileage);

        ContentValues cvWork = new ContentValues();
        cvWork.put(RECEIPT_COLUMN_COST,cost);

        ContentValues cvOwner = new ContentValues();
        cvOwner.put(NOTES_COLUMN_PRODUCTS,productsUsed);
        cvOwner.put(NOTES_COLUMN_TIME,time);
        cvOwner.put(NOTES_COLUMN_NOTES,notes);

        sqlDb.insert(TABLE_CARDATA, null, cvCar);
        sqlDb.insert(TABLE_RECEIPT, null, cvWork);
        sqlDb.insert(TABLE_NOTES, null, cvOwner);

    }

    //Update Service Entry table
    //Insert Service Entry data to their correct tables
    public void updateServiceRecords (String date,int mileage,double cost,String productsUsed, String notes, String time)
    {
        ContentValues cvCar = new ContentValues();
        cvCar.put(CARDATA_COLUMN_DATE,date);
        cvCar.put(CARDATA_COLUMN_MILEAGE,mileage);

        ContentValues cvWork = new ContentValues();
        cvWork.put(RECEIPT_COLUMN_COST,cost);

        ContentValues cvOwner = new ContentValues();
        cvOwner.put(NOTES_COLUMN_PRODUCTS,productsUsed);
        cvOwner.put(NOTES_COLUMN_TIME,time);
        cvOwner.put(NOTES_COLUMN_NOTES,notes);

        sqlDb.update(TABLE_CARDATA, cvCar, CARDATA_COLUMN_ID + "=1", null);
        sqlDb.update(TABLE_RECEIPT, cvWork, RECEIPT_COLUMN_ID + "=1", null);
        sqlDb.update(TABLE_NOTES, cvOwner, NOTES_COLUMN_ID + "=1", null);

    }

    //Update Vehicle Profile
    public void updateProfile (String make,String model,int year, int mileage, String color, String purchasedate) {

        SQLiteDatabase db = openHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(PROFILES_COLUMN_MAKE,make);
        contentValues.put(PROFILES_COLUMN_MODEL,model);
        contentValues.put(PROFILES_COLUMN_YEAR,year);
        contentValues.put(PROFILES_COLUMN_MILEAGE,mileage);
        contentValues.put(PROFILES_COLUMN_COLOR, color);
        contentValues.put(PROFILES_COLUMN_PDATE, purchasedate);

        //db.update(TABLE_PROFILES,contentValues,"id=1",null);

        sqlDb.update(TABLE_PROFILES, contentValues, PROFILES_COLUMN_ID +"=" + 1, null);
    }

    public ServiceEntry selectServiceRecords() {
        String query1 = "SELECT " + CARDATA_COLUMN_DATE +
                ", " + CARDATA_COLUMN_MILEAGE + " FROM " + TABLE_CARDATA;

        String query2 = "SELECT " + RECEIPT_COLUMN_COST + " FROM " + TABLE_RECEIPT;

        String query3 = "SELECT " + NOTES_COLUMN_PRODUCTS +
                ", " + NOTES_COLUMN_TIME + ", " + NOTES_COLUMN_NOTES
                + " FROM " + TABLE_NOTES;

        SQLiteDatabase db = openHelper.getWritableDatabase();

        Cursor cursor1 = db.rawQuery(query1, null);
        Cursor cursor2 = db.rawQuery(query2,null);
        Cursor cursor3 = db.rawQuery(query3,null);

        ServiceEntry se = new ServiceEntry();

        if (cursor1.moveToFirst()) {
            cursor1.moveToFirst();
            se.setDate(cursor1.getString(0));
            se.setMileage(Integer.parseInt(cursor1.getString(1)));
            cursor1.close();
        }

        if (cursor2.moveToFirst()) {
            cursor2.moveToFirst();
            se.setCost(Double.parseDouble(cursor2.getString(0)));
        }

        if (cursor3.moveToFirst()) {
            cursor3.moveToFirst();
            se.setProducts(cursor3.getString(0));
            se.setTime(cursor3.getString(1));
            se.setNotes(cursor3.getString(2));
        }
        else {
            se=null;
        }

        db.close();
        return se;
    }


    //get fetch the all data not needed
    public Cursor getAllRecords() {


        //Does not work only works when selecting from user table
        //Cursor cursor = sqlDb.rawQuery("select * from " + TABLE_PROFILES, null);

        Cursor cursor = sqlDb.rawQuery("select * from " + TABLE_USERS, null);

        return cursor;

        //return sqlDb.rawQuery("select * from " + TABLE_PROFILES, null);

    }

    //Find username in the database from User Table
    public User findUsername(String username) {
        String query = "Select * FROM " + TABLE_USERS + " WHERE " + USERS_COLUMN_USERNAME + " =  \"" + username + "\"";

        SQLiteDatabase db = openHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        User user = new User();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            //user.setID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(USERS_COLUMN_ID))));
            user.setUsername(cursor.getString(cursor.getColumnIndex(USERS_COLUMN_USERNAME)));
            user.setPassword(Integer.parseInt(cursor.getString(cursor.getColumnIndex(USERS_COLUMN_PASSWORD))));
            cursor.close();
        } else {
            user = null;
        }
        db.close();
        return user;
    }

    //Find username in the database from User Table
    public Profile findProfile() {
        String query = "Select * FROM " + TABLE_PROFILES + " WHERE " + PROFILES_COLUMN_ID + " = " + 1;

        SQLiteDatabase db = openHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

       // User user = new User();
        Profile profile = new Profile();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();

            //user.setID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(USERS_COLUMN_ID))));
            //user.setUsername(cursor.getString(cursor.getColumnIndex(USERS_COLUMN_USERNAME)));
            //user.setPassword(Integer.parseInt(cursor.getString(cursor.getColumnIndex(USERS_COLUMN_PASSWORD))));

            profile.SetMake(cursor.getString(cursor.getColumnIndex(PROFILES_COLUMN_MAKE)));
            profile.SetModel(cursor.getString(cursor.getColumnIndex(PROFILES_COLUMN_MODEL)));
            profile.SetMileage(Integer.parseInt(cursor.getString(cursor.getColumnIndex(PROFILES_COLUMN_MILEAGE))));
            profile.SetYear(Integer.parseInt(cursor.getString(cursor.getColumnIndex(PROFILES_COLUMN_YEAR))));
            profile.SetColor(cursor.getString(cursor.getColumnIndex(PROFILES_COLUMN_COLOR)));
            profile.SetPurchaseDate(cursor.getString(cursor.getColumnIndex(PROFILES_COLUMN_PDATE)));

            cursor.close();
        } else {
            //user = null;
            profile = null;
        }
        db.close();
        //return user;
        return profile;
    }


    //Select notification from Notif Table
    public Notif selectNotif() {
        String query = "SELECT * FROM " + TABLE_NOTIFICATION;

        SQLiteDatabase db = openHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Notif notif = new Notif();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            cursor.getString(0);
            notif.setText(cursor.getString(1));
            notif.setDate(cursor.getString(2));
            notif.setTime(cursor.getString(3));
            cursor.close();
        } else {
            notif = null;
        }
        db.close();
        return notif;
    }

    public Cursor getProfile() {
        String query = "Select * FROM " + TABLE_PROFILES;

        SQLiteDatabase db = openHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }


    //Returns number of entries in Profile table
    public int profileCount() {
        SQLiteDatabase db = openHelper.getWritableDatabase();
        long count = DatabaseUtils.queryNumEntries(db,TABLE_PROFILES);
        int num = (int) count;

        return num;
    }

    public boolean deleteProfileEntry(String model) {

        boolean result = false;

        String query = "Select " + PROFILES_COLUMN_MODEL + " FROM " + TABLE_PROFILES + " WHERE " + PROFILES_COLUMN_MODEL + " =  \"" + model + "\"";

        SQLiteDatabase db = openHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Profile profile = new Profile();

        if (cursor.moveToFirst()) {
            profile.SetModel(cursor.getString(0));
            db.delete(TABLE_PROFILES, PROFILES_COLUMN_MODEL + " = ?",
                    new String[] { String.valueOf(profile.GetModel()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public void deleteServiceEntry(String date) {

        boolean result = false;

        String query = "DELETE * FROM " + TABLE_CARDATA + "," + TABLE_NOTES + ", " +
                TABLE_BARCODE + ", " + TABLE_RECEIPT + ", " + TABLE_SERVICE + " WHERE " +
                CARDATA_COLUMN_ID + " =1, " + NOTES_COLUMN_ID + "=1, " + BARCODE_COLUMN_ID +
                "=1, " + RECEIPT_COLUMN_ID + "=1";
        SQLiteDatabase db = openHelper.getWritableDatabase();

        db.rawQuery(query, null);
    }


    // inner class
    private class DBOpenHelper extends SQLiteOpenHelper {

        public DBOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            // TODO Auto-generated constructor stub

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            //System.out.println("create statement"+SQL_CREATE_ENTRIES);

            try
            {
                db.execSQL(USER_CREATE_ENTRIES);
                db.execSQL(PROFILE_CREATE_ENTRIES);
                db.execSQL(CARDATA_CREATE_ENTRIES);
                db.execSQL(NOTES_CREATE_ENTRIES);
                db.execSQL(BARCODE_CREATE_ENTRIES);
                db.execSQL(RECEIPT_CREATE_ENTRIES);
                db.execSQL(SERVICE_CREATE_ENTRIES);
                db.execSQL(NOTES_CREATE_ENTRIES);
                db.execSQL(NOTIFICATION_CREATE_ENTRIES);

            }catch(SQLiteException sql)
            {
                sql.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILES);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARDATA);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_BARCODE);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECEIPT);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATION);
            onCreate(db);
        }
    }

}






/*
public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public FeedReaderContract() {}

    public boolean deleteUser(String username) {

        boolean result = false;

        String query = "Select * FROM " + TABLE_USERS + " WHERE " + USERS_COLUMN_USERNAME + " =  \"" + username + "\"";

        SQLiteDatabase db = openHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        User user = new User();

        if (cursor.moveToFirst()) {
            user.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_USERS, USERS_COLUMN_ID + " = ?",
                    new String[] { String.valueOf(user.getID()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    /* Inner class that defines the table contents */
//public static abstract class FeedEntry implements BaseColumns {
       /* public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
    }


private static final String TEXT_TYPE = " TEXT";
private static final String COMMA_SEP = ",";
private static final String SQL_CREATE_ENTRIES =
        "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                FeedEntry._ID + " INTEGER PRIMARY KEY," +
                FeedEntry.COLUMN_NAME_USERNAME + TEXT_TYPE + COMMA_SEP +
                FeedEntry.COLUMN_NAME_PASSWORD + TEXT_TYPE + COMMA_SEP +
        " )";

private static final String SQL_DELETE_ENTRIES =
        "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME; }

public class FeedReaderDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}


}*/