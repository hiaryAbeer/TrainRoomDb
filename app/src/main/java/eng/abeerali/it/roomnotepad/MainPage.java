package eng.abeerali.it.roomnotepad;

import androidx.appcompat.app.AppCompatActivity;
import eng.abeerali.it.roomnotepad.data_binding.DataBindingMain;
import eng.abeerali.it.roomnotepad.room_package.HomeActivity;
import eng.abeerali.it.roomnotepad.room_package.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPage extends AppCompatActivity {

    private Button room, dBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        room = findViewById(R.id.main_room);
        dBinding = findViewById(R.id.main_data_binding);

        room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPage.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        dBinding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPage.this, DataBindingMain.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }
}
