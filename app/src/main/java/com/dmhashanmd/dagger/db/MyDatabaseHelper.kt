package com.dmhashanmd.dagger.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "myapp.db"
        const val DATABASE_VERSION = 1
        const val TABLE_USERS = "users"

        const val COLUMN_ID = "_id"   // Required for CursorAdapter & ContentProvider
        const val COLUMN_NAME = "name"
        const val COLUMN_EMAIL = "email"

        private const val CREATE_TABLE_USERS =
            "CREATE TABLE $TABLE_USERS (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_NAME TEXT NOT NULL, " +
                    "$COLUMN_EMAIL TEXT NOT NULL" +
                    ")"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_USERS)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }
}