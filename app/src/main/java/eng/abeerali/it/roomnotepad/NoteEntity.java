package eng.abeerali.it.roomnotepad;

import java.io.Serializable;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Note")
public class NoteEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long note_id;

    @ColumnInfo(name = "note_content")
    private String content;

    private String title;
    private String date;

    public NoteEntity( String content, String title, String date) {
        this.content = content;
        this.title = title;
        this.date = date;//new Date(System.currentTimeMillis())
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getNote_id() {
        return note_id;
    }

    public void setNote_id(long note_id) {
        this.note_id = note_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof NoteEntity))
            return false;

        NoteEntity noteEntity = (NoteEntity) obj;
        if (note_id != noteEntity.note_id)
            return false;

        return title != null?title.equals(noteEntity.title) : noteEntity.title == null;
    }

    @Override
    public int hashCode() {
        long result = note_id;
        result = 31 * result+(title != null?title.hashCode():0);
        return (int) result;
    }

    @Override
    public String toString() {
        return "Note{" +
                "note_id=" + note_id +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
