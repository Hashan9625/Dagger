package com.dmhashanmd.dagger.contentProvider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

import android.util.Log
import com.dmhashanmd.dagger.BaseApplication
import com.dmhashanmd.dagger.db.MyDatabaseHelper
import com.dmhashanmd.dagger.model.Engine
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject


class CustomContentProvider : ContentProvider() {

    @Inject lateinit var engine: Engine

    companion object {
        const val AUTHORITY = "com.dmhashanmd.dagger.provider"
        const val TABLE_NAME = "users"
        val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$TABLE_NAME")

        private const val USERS = 1
        private const val USER_ID = 2

        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTHORITY, TABLE_NAME, USERS)
            addURI(AUTHORITY, "$TABLE_NAME/#", USER_ID)
        }
    }

    private lateinit var dbHelper: MyDatabaseHelper
    private lateinit var database: SQLiteDatabase

    override fun onCreate(): Boolean {
        dbHelper = MyDatabaseHelper(context!!)
        database = dbHelper.writableDatabase

        (context?.applicationContext as BaseApplication)
            .parentComponent
            .childComponentFactory()
            .create()
            .inject(this)

        val entryPoint = EntryPointAccessors.fromApplication(context!!, EntryPointMethod::class.java)

        Log.d("CustomContentProvider", "sub Perent : ${engine.name}")
        Log.d("CustomContentProvider", "entryPoint: ${entryPoint.getEngine().name}")
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return when (uriMatcher.match(uri)) {
            USERS -> database.query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder)
            USER_ID -> {
                val id = ContentUris.parseId(uri)
                database.query(TABLE_NAME, projection, "_id=?", arrayOf(id.toString()), null, null, sortOrder)
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val id = database.insert(TABLE_NAME, null, values)
        if (id > 0) {
            val returnUri = ContentUris.withAppendedId(CONTENT_URI, id)
            context?.contentResolver?.notifyChange(returnUri, null)
            return returnUri
        }
        throw IllegalArgumentException("Failed to insert row into $uri")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        val rowsUpdated = when (uriMatcher.match(uri)) {
            USERS -> database.update(TABLE_NAME, values, selection, selectionArgs)
            USER_ID -> {
                val id = ContentUris.parseId(uri)
                database.update(TABLE_NAME, values, "_id=?", arrayOf(id.toString()))
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
        if (rowsUpdated > 0) context?.contentResolver?.notifyChange(uri, null)
        return rowsUpdated
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val rowsDeleted = when (uriMatcher.match(uri)) {
            USERS -> database.delete(TABLE_NAME, selection, selectionArgs)
            USER_ID -> {
                val id = ContentUris.parseId(uri)
                database.delete(TABLE_NAME, "_id=?", arrayOf(id.toString()))
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
        if (rowsDeleted > 0) context?.contentResolver?.notifyChange(uri, null)
        return rowsDeleted
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher.match(uri)) {
            USERS -> "vnd.android.cursor.dir/vnd.$AUTHORITY.$TABLE_NAME"
            USER_ID -> "vnd.android.cursor.item/vnd.$AUTHORITY.$TABLE_NAME"
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }
}