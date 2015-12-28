package elitetechsolutions.newpocketcar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class NewServiceEntry extends AppCompatActivity {

    private TextView car;
    EditText dateBoxService;
    EditText mileBox;
    EditText costBox;
    EditText timeBox;
    EditText productBox;
    EditText ownerBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_service_entry);

        car=(TextView) findViewById(R.id.carName);
        car.setText("2012 Volkswagon Golf");

        dateBoxService = (EditText) findViewById(R.id.dateField);
        mileBox = (EditText) findViewById(R.id.mileageField);
        costBox = (EditText) findViewById(R.id.costField);
        timeBox = (EditText) findViewById(R.id.timeField);
        productBox = (EditText) findViewById(R.id.productField);
        ownerBox = (EditText) findViewById(R.id.noteField);


    }

    public void saveService(View view){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("dateService", dateBoxService.getText().toString());
        editor.putString("mileService", mileBox.getText().toString());
        editor.putString("costService", costBox.getText().toString());
        editor.putString("timeService", timeBox.getText().toString());
        editor.putString("productService", productBox.getText().toString());
        editor.putString("ownerService", ownerBox.getText().toString());
        editor.commit();
        // this.finish();
        Intent intent = new Intent(this,VehicleProfiles.class);
        startActivity(intent);
    }



    public void TakePicture(View view) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(intent);

    }

    public void Create (View view) {

        //Set edit text
        EditText dateBox = (EditText) findViewById(R.id.dateField);
        EditText mileBox = (EditText) findViewById(R.id.mileageField);
        EditText costBox = (EditText) findViewById(R.id.costField);
        EditText timeBox = (EditText) findViewById(R.id.timeField);
        EditText productBox = (EditText) findViewById(R.id.productField);
        EditText ownerBox = (EditText) findViewById(R.id.noteField);

        //Gets text from edit text to save to values
        String date = dateBox.getText().toString();
        int mileage = Integer.parseInt(mileBox.getText().toString());
        double cost = Double.parseDouble(costBox.getText().toString());
        String time = timeBox.getText().toString();
        String productsUsed = productBox.getText().toString();
        String ownersNotes = ownerBox.getText().toString();

        //Enter values to the database
        DBHandler dbHandler = new DBHandler(this);

        dbHandler.saveServiceRecords(date,mileage,cost,productsUsed,ownersNotes,time);

        Toast("Service Entry Saved");

        this.finish();
    }

    public void Barcode(View v) {
        IntentIntegrator integrator = new IntentIntegrator(NewServiceEntry.this);
        integrator.addExtra("SCAN_WIDTH", 640);
        integrator.addExtra("SCAN_HEIGHT", 480);
        integrator.addExtra("SCAN_MODE", "PRODUCT_MODE");
        //customize the prompt message before scanning
        integrator.addExtra("PROMPT_MESSAGE", "Scanner Start!");
        integrator.initiateScan(IntentIntegrator.PRODUCT_CODE_TYPES);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (result != null) {
            String contents = result.getContents();
            if (contents != null) {
                //showDialog(R.string.result_succeeded, result.toString());
            } else {
                //showDialog(R.string.result_failed, getString(R.string.result_failed_why));
            }
        }
    }

    //Display text to the screen
    public void Toast (CharSequence text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context,text,duration);
        toast.show();
    }


}
