package eng.abeerali.it.roomnotepad.room_package;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import eng.abeerali.it.roomnotepad.NoteDatabase;
import eng.abeerali.it.roomnotepad.NoteEntity;
import eng.abeerali.it.roomnotepad.R;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.ref.WeakReference;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    private TextInputEditText et_title, et_content;
    private NoteDatabase noteDatabase;
    private NoteEntity note;
    private boolean update;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        et_title = findViewById(R.id.et_title);
        et_content = findViewById(R.id.et_content);
        button = findViewById(R.id.but_save);
        noteDatabase = NoteDatabase.getInstance(this);

        if ((note = (NoteEntity) getIntent().getSerializableExtra("note")) != null) {
            getSupportActionBar().setTitle("Update");
            update = true;
            button.setText("Update");
            et_content.setText(note.getContent());
            et_title.setText(note.getTitle());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (update) {
                    note.setTitle(et_title.getText().toString());
                    note.setContent(et_content.getText().toString());
                    noteDatabase.getNoteDao().update(note);
                    setResult(note, 2);

                } else {
                    // fetch data and create note object
                    note = new NoteEntity(et_content.getText().toString(), et_title.getText().toString(), "" + new Date(System.currentTimeMillis()));
                    new InsertTask(HomeActivity.this, note).execute();
                }
            }
        });
    }

    private void setResult(NoteEntity note, int flag) {
        setResult(flag, new Intent().putExtra("note", note));
        finish();
    }

    private static class InsertTask extends AsyncTask<Void, Void, Boolean> {

        private WeakReference<HomeActivity> reference;
        private NoteEntity noteEntity;

        public InsertTask(HomeActivity context, NoteEntity noteEntity) {
            reference = new WeakReference<>(context);
            this.noteEntity = noteEntity;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {

            long j = reference.get().noteDatabase.getNoteDao().insert(noteEntity);
            noteEntity.setNote_id(j);
            Log.e("ID ", "doInBackground: " + j);

            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean){
                reference.get().setResult(noteEntity,1);
                reference.get().finish();
            }
        }
    }

}
