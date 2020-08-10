package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import Model.Contact;
import Utils.Utils;

public class Database extends SQLiteOpenHelper {


    public Database(@Nullable Context context) {
        super(context, Utils.DATABASE_NAME, null, Utils.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQL -- Structured Query Language
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + Utils.TABLE_NAME + "("
                + Utils.KEY_ID + " INTEGER PRIMARY KEY," + Utils.KEY_NAME + " TEXT,"
                + Utils.KEY_PHONE_NUMBER + " TEXT" + ")";

        //With this the database is created
        db.execSQL(CREATE_CONTACT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //if a new version is there than we need to drop the initial table and get a new one
        //dropping means deleting

        db.execSQL("DROP TABLE IF EXISTS " + Utils.TABLE_NAME);

        //create the table again
        onCreate(db);

    }

    //CRUD Operations -- Create Read Update Delete Operations

    //add Contact

    public void addContact(Contact contact){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();


        //we are inserting a column
        value.put(Utils.KEY_NAME, contact.getName());
        value.put(Utils.KEY_PHONE_NUMBER, contact.getPhoneNumber());

        //we put inside these things in database
        db.insert(Utils.TABLE_NAME,null, value);
        db.close();



    }

    //get contact
    public Contact getContact(int id){

     SQLiteDatabase db = this.getReadableDatabase();
     Cursor cursor = db.query(Utils.TABLE_NAME,new String[]{ Utils.KEY_ID, Utils.KEY_NAME, Utils.KEY_PHONE_NUMBER },Utils.KEY_ID + "=?"
             + new String[]{String.valueOf(id)}, null , null, null, null);

     if(cursor != null){
         cursor.moveToFirst();
     }
     Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
             cursor.getString(1), cursor.getString(2));

     return contact;
    }

    //get all contacts

    public List<Contact> getAllContact(){

        //instantiating the database
        SQLiteDatabase db = this.getReadableDatabase();

        //creating an arraylist of type contact so that we can put all the data there
        List<Contact> contactList = new ArrayList<>();

        //we have to retrieve the data
        String selectALL = "SELECT * FROM " + Utils.TABLE_NAME;
        //creating the cursor for it to read
        Cursor cursor = db.rawQuery(selectALL, null);

        //loop through our contacts
        if(cursor.moveToFirst()){
            do {

                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));

                //add contact
                contactList.add(contact);

            }while (cursor.moveToNext());
        }

        return contactList;


    }
}
