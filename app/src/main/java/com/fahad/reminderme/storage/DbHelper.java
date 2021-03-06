package com.fahad.reminderme.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fahad.reminderme.model.Todo;

import java.util.ArrayList;

import timber.log.Timber;

/**
 * Created by moodi on 26/01/2018.
 */

public class DbHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "Todo.db";
    private static final String TABLE_NAME = "todo_table";
    private static final int VERSION = 1;
    private static final String ID = "id"; // 0
    private static final String TITLE = "title"; // 1
    private static final String START_TIME = "start_time"; // 2

    private Context context;

    public DbHelper(Context context) {

        super(context, DATABASE_NAME, null, VERSION);

        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TITLE + " TEXT,"
                + START_TIME + " TEXT"
                + ")"
        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insert(Todo todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(TITLE, todo.getTitle());
        cv.put(START_TIME, todo.getTime());

        return db.insert(TABLE_NAME, null, cv);

    }


    public ArrayList<Todo> getAll() {

        ArrayList<Todo> todoList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "
                        + TABLE_NAME
                , null);

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                String title = cursor.getString(1);
                String time = cursor.getString(2);

                Todo todo = new Todo(title, time, id);
                todoList.add(todo);
            } while (cursor.moveToNext());
        }

        return todoList;
    }


    public void delete(String id) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME
                , ID
                        + " = "
                        + id
                , null);

    }


    public Todo getById(String itemId) {
        Todo todo = null;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = '" + itemId + "' "
                , null);
        Timber.d("in getting data by id ");

        if (cursor != null)
            cursor.moveToFirst();

        String id = cursor.getString(0);
        String title = cursor.getString(1);
        String time = cursor.getString(2);
        todo = new Todo(title, time, id);
        return todo;
    }


    public int updateById(String id, Todo todo) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ID, id);
        cv.put(TITLE, todo.getTitle());
        cv.put(START_TIME, todo.getTime());

        return db.update(TABLE_NAME, cv, "ID = ?", new String[]{id});
    }


}
