package eng.abeerali.it.roomnotepad.room_package;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import eng.abeerali.it.roomnotepad.NoteDatabase;
import eng.abeerali.it.roomnotepad.NoteEntity;
import eng.abeerali.it.roomnotepad.R;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NotesAdapter.OnNoteItemClick {

    private TextView textViewMsg;
    private RecyclerView recyclerView;
    private NoteDatabase noteDatabase;
    private List<NoteEntity> notes = new ArrayList<>();
    private int pos;
    private NotesAdapter notesAdapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        textViewMsg = (TextView) findViewById(R.id.tv__empty);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        noteDatabase = NoteDatabase.getInstance(this);
        notes = new ArrayList<>();
        notes = noteDatabase.getNoteDao().getAll();
        setSupportActionBar(toolbar);
        setTitle("Notes");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, HomeActivity.class), 100);

            }
        });
        Log.e("dddddddddddddddd", " " + notes.size());

        if (notes.size() > 0)
            textViewMsg.setVisibility(View.GONE);

        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        notesAdapter = new NotesAdapter(notes, MainActivity.this);
        recyclerView.setAdapter(notesAdapter);
//        new RetrieveTask(this).execute();
    }

    public void onNoteClick(final int pos) {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Select option ")
                .setItems(new String[]{"Delete", "Update"}
                        , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                switch (i) {
                                    case 0: // delete
                                        noteDatabase.getNoteDao().delete(notes.get(pos));
                                        notes.remove(pos);
                                        listVisibility();
                                        break;
                                    case 1:
                                        MainActivity.this.pos = pos;
                                        startActivityForResult(new Intent(MainActivity.this
                                                        , HomeActivity.class).putExtra("note", notes.get(pos))
                                                , 100);
                                        break;
                                }

                            }
                        }).show();
    }

    class RetrieveTask extends AsyncTask<Void, Void, List<NoteEntity>> {

        WeakReference<MainActivity> weakReference;

        public RetrieveTask(MainActivity context) {
            weakReference = new WeakReference<>(context);
        }

        @Override
        protected List<NoteEntity> doInBackground(Void... voids) {
            if (weakReference != null)
                return weakReference.get().noteDatabase.getNoteDao().getAll();
            else
                return null;
        }

        @Override
        protected void onPostExecute(List<NoteEntity> list) {
            if (notes != null && notes.size() > 0) {
                weakReference.get().notes.clear();
                weakReference.get().notes.addAll(notes);
                // hides empty text view
                weakReference.get().textViewMsg.setVisibility(View.GONE);
                weakReference.get().notesAdapter.notifyDataSetChanged();
            }
//            notesAdapter = new NotesAdapter( notes,MainActivity.this);
//            recyclerView.setAdapter(notesAdapter);
//            super.onPostExecute(list);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100 && resultCode > 0) {
            if (resultCode == 1) {
                notes.add((NoteEntity) data.getSerializableExtra("note"));
            } else if (resultCode == 2) {
                notes.set(pos, (NoteEntity) data.getSerializableExtra("note"));
            }
            listVisibility();
        }
//        super.onActivityResult(requestCode, resultCode, data);
    }

    private void listVisibility() {
        int emptyMsgVisibility = View.GONE;
        if (notes.size() > 0) {
            if (textViewMsg.getVisibility() == View.GONE)
                emptyMsgVisibility = View.VISIBLE;
        }

        textViewMsg.setVisibility(emptyMsgVisibility);
        notesAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        noteDatabase.cleanUp();
        super.onDestroy();
    }
}
