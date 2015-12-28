package elitetechsolutions.newpocketcar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import elitetechsolutions.newpocketcar.R;

public class EditVehicleProfile extends AppCompatActivity {


    EditText makeBox;
    EditText modelBox;
    EditText yearBox;
    EditText mileageBox;
    EditText colorBox;
    EditText dateBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vehicle_profile);



        makeBox = (EditText) findViewById(R.id.makeField);
        modelBox = (EditText) findViewById(R.id.modelField);
        yearBox = (EditText) findViewById(R.id.yearField);
        mileageBox = (EditText) findViewById(R.id.mileageField);
        colorBox = (EditText) findViewById(R.id.colorField);
        dateBox = (EditText) findViewById(R.id.dateField);





    }

    public void SaveNewVehicle (View view) {
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
    public void setVehicleToZero (){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("makeField", "");
        editor.putString("modelField","");
        editor.putString("yearField", "");
        editor.putString("mileageBox","");
        editor.putString("colorBox", "");
        editor.putString("dateBox", "");
        editor.commit();
        // this.finish();
        Intent intent = new Intent(this,VehicleProfiles.class);
        startActivity(intent);
    }
    public void DeleteThis (View view) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Deleting Vehicle Profile")
                .setMessage("Are you sure you want to delete this vehicle profile?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setVehicleToZero();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    public void deleteEntry() {
        DBHandler dbHandler = new DBHandler(this);

        //Profile profile = new Profile();

        boolean result =  dbHandler.deleteProfileEntry("Golf");

        if (result!=true) {
            Toast("Delete failed");
        }
        else {
            Toast("Profile deleted");
        }
    }

    //Display text to the screen
    public void Toast (CharSequence text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
