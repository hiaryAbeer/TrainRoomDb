package eng.abeerali.it.roomnotepad.data_binding;

import androidx.appcompat.app.AppCompatActivity;
import eng.abeerali.it.roomnotepad.R;
import eng.abeerali.it.roomnotepad.databinding.ActivityDataBindingMainBinding;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DataBindingMain extends AppCompatActivity {

    ActivityDataBindingMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityDataBindingMainBinding.inflate(getLayoutInflater());//This method inflates the layout and binds the object to it returning reference to the object.
        setContentView(mainBinding.getRoot());//R.layout.activity_data_binding_main);

//        ActivityDataBindingMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding_main);

        UserModel userModel = UserModel.getValidStub();
        mainBinding.setRefernce(userModel);

        mainBinding.setClickHandler(new ClickHandler());

    }

    public class ClickHandler {
        public void handleClick(View view) {
            Toast.makeText(DataBindingMain.this, "Button clicked " + view.getId(), Toast.LENGTH_SHORT).show();
        }
        public void handleClick() {
            Toast.makeText(DataBindingMain.this, "Button clicked ", Toast.LENGTH_SHORT).show();
        }
    }

}
