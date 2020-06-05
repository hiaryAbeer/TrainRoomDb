package eng.abeerali.it.roomnotepad;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import static eng.abeerali.it.roomnotepad.NoteDatabase.TABLE_NAME_NOTE;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM Note")
    List<NoteEntity> getAll();

    /*
     * Insert the object in database
     * @param note, object to be inserted
     */
    @Insert
    long insert(NoteEntity noteEntity);

    /*
     * update the object in database
     * @param note, object to be updated
     */
    @Update
    void update(NoteEntity noteEntity);

    /*
     * delete the object from database
     * @param note, object to be deleted
     */
    @Delete
    void delete(NoteEntity noteEntity);

    /*
     * delete the object from database
     * @param note, object to be deleted
     */
    @Delete
    void delete(NoteEntity... noteEntities);// Note... is varargs, here note is an array
}
