package com.example.noq;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.database.Cursor;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	
	// Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "usersManager";
 
    // Users table name
    private static final String TABLE_USERS = "users";
 
    // Users Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
	private static final String KEY_NRIC = "nric";
    private static final String KEY_CONTACT_NO = "contact_number";
	private static final String KEY_EMAIL = "email";
    private static final String KEY_VEH_NO = "veh_number";
    private static final String KEY_IU_NO = "iu_number";
    private static final String KEY_PASSWORD = "password";	

	public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
	 
	@Override
	public void onCreate(SQLiteDatabase db) {
		 String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
	                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
	                + KEY_CONTACT_NO + " TEXT" + ")";
	        db.execSQL(CREATE_USERS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        
        // Create tables again
        onCreate(db);
	}
	  /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */	
	
    // Adding new user
    void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_ID, 1 + getUsersCount());
		values.put(KEY_NAME, user.getName()); 
		values.put(KEY_NRIC, user.getNRIC());
        values.put(KEY_CONTACT_NO, user.getContact()); 
		values.put(KEY_EMAIL, user.getEmail());
		values.put(KEY_VEH_NO, user.getVehNo());
		values.put(KEY_IU_NO, user.getIU());
		values.put(KEY_PASSWORD, user.getPassword());
 
        // Inserting Row
        db.insert(TABLE_USERS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single user
    User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_USERS, new String[] 
			{ KEY_ID, KEY_NAME, KEY_NRIC, KEY_CONTACT_NO, KEY_EMAIL, KEY_VEH_NO, KEY_IU_NO, KEY_PASSWORD }, 
				KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        User user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), 
			cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));

        return user;
    }
   
    // Getting all users
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setName(cursor.getString(1));
				user.setNRIC(cursor.getString(2));
                user.setContact(cursor.getString(3));
                user.setEmail(cursor.getString(4));
				user.setVehNo(cursor.getString(5));
				user.setIU(cursor.getString(6));
				user.setPassword(cursor.getString(7));
                userList.add(user);
            } while (cursor.moveToNext());
        }
 
        return userList;
    }
 
    // Updating single user
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_ID, user.getId());
		values.put(KEY_NAME, user.getName()); 
		values.put(KEY_NRIC, user.getNRIC());
        values.put(KEY_CONTACT_NO, user.getContact()); 
		values.put(KEY_EMAIL, user.getEmail());
		values.put(KEY_VEH_NO, user.getVehNo());
		values.put(KEY_IU_NO, user.getIU());
		values.put(KEY_PASSWORD, user.getPassword());
 
        // updating row
        return db.update(TABLE_USERS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(user.getId()) });
    }
 
    // Deleting single user
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_ID + " = ?",
                new String[] { String.valueOf(user.getId()) });
        db.close();
    }
 
 
    // Getting users Count
    public int getUsersCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }
}
