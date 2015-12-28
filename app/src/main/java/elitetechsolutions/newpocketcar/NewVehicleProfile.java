package elitetechsolutions.newpocketcar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewVehicleProfile extends AppCompatActivity {

    //Set edit text
    EditText makeBox;
    EditText modelBox;
    EditText yearBox;
    EditText mileageBox;
    EditText colorBox;
    EditText dateBox;
    public static final String makeName = "namekey";
    public static final String modelName = "NotesKey";
    public static final String carYear= "NotesKey";
    public static final String carMileage = "NotesKey";
    public static final String carColor = "NotesKey";
    public static final String carDate = "NotesKey";
    private static final int SELECT_PHOTO = 100;


    public static final String MyPREFERENCES = "MyPrefs" ;

    String makeNameET;
    String modelNameET;
    String carYearET;
    String carMileageET;
    String carColorET;
    String carDateET;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_vehicle_profile);

        makeBox = (EditText) findViewById(R.id.makeField);
        modelBox = (EditText) findViewById(R.id.modelField);
        yearBox = (EditText) findViewById(R.id.yearField);
        mileageBox = (EditText) findViewById(R.id.mileageField);
        colorBox = (EditText) findViewById(R.id.colorField);
        dateBox = (EditText) findViewById(R.id.dateField);


    }


    public void saveInfo(View view){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("makeField", makeBox.getText().toString());
        editor.putString("modelField", modelBox.getText().toString());
        editor.putString("yearField", yearBox.getText().toString());
        editor.putString("mileageBox", mileageBox.getText().toString());
        editor.putString("colorBox", colorBox.getText().toString());
        editor.putString("dateBox", dateBox.getText().toString());
        editor.commit();
       // this.finish();
        Intent intent = new Intent(this,VehicleProfiles.class);
        startActivity(intent);
    }

    public void Create (View view) {

        int year =0;
        int mileage=0;

        //Gets text from edit text to save to values
        String make = makeBox.getText().toString();
        String model = modelBox.getText().toString();
        year = Integer.parseInt(yearBox.getText().toString());
        mileage = Integer.parseInt(mileageBox.getText().toString());
        String color = colorBox.getText().toString();
        String date = dateBox.getText().toString();

        if (make.equals(null) || model.equals(null) ||color.equals(null) || date.equals(null) ) {
            Toast("Please enter all values!");
        }
        else if (year==0) {
            Toast("Please enter a year!");
        }
        else if (mileage==0) {
            Toast("Please enter mileage!");
        }

        else {

        //Enter values into database
        DBHandler dbHandler = new DBHandler(this);

        dbHandler.saveVPRecords(make,model,year,mileage,color,date);

        Toast("Vehicle Profile Created");

        this.finish(); }
    }

    //Display text to the screen
    public void Toast (CharSequence text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context,text,duration);
        toast.show();
    }


}
