package eng.abeerali.it.roomnotepad;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {NoteEntity.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    public static final String TABLE_NAME_NOTE = "Note";
    public static final String DB_NAME = "notesdb.db";
    public abstract NoteDao getNoteDao();

    private static NoteDatabase noteDatabase;

    public static NoteDatabase getInstance(Context context) {
        if (noteDatabase == null)
            noteDatabase = buildDatabaseInstance(context);
        return noteDatabase;
    }

    private static NoteDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context, NoteDatabase.class,DB_NAME).allowMainThreadQueries().build();
    }

    public void cleanUp() {
        noteDatabase = null;
    }

}
