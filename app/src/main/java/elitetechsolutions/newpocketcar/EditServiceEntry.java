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
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class EditServiceEntry extends AppCompatActivity {


    EditText EditDate;
    EditText EditMileage;
    EditText EditTimeComsumed;
    EditText EditCost;
    EditText EditOwnersNotes;
    EditText EditPartsUsed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service_entry);


        EditDate = (EditText) findViewById(R.id.dateField);
        EditMileage = (EditText) findViewById(R.id.mField);
        EditTimeComsumed = (EditText) findViewById(R.id.tField);
        EditCost = (EditText) findViewById(R.id.cField);
        EditOwnersNotes = (EditText) findViewById(R.id.oField);
        EditPartsUsed = (EditText) findViewById(R.id.puField);


       /// EditDate.setText(dateService);
        //EditMileage.setText(mileageService);
        //EditCost.setText(costService);
       // EditTimeComsumed.setText(timeConsumedService);
       // EditPartsUsed.setText(productsUsedService);
        //EditOwnersNotes.setText(ownersNotesService);

    }

    public void deleteService (View view) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Deleting Service Entry")
                .setMessage("Are you sure you want to delete this service entry?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DeleteService();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }


    public void DeleteService(){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("dateService", "");
        editor.putString("mileService","");
        editor.putString("costService", "");
        editor.putString("timeService", "");
        editor.putString("productService", "");
        editor.putString("ownerService","");
        editor.commit();
        Intent intent = new Intent(this,VehicleProfiles.class);
        startActivity(intent);
    }
    public void SaveService(View view) {

       SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("dateService", EditDate.getText().toString());
        editor.putString("mileService", EditMileage.getText().toString());
        editor.putString("costService", EditCost.getText().toString());
        editor.putString("timeService", EditTimeComsumed.getText().toString());
        editor.putString("productService", EditPartsUsed.getText().toString());
        editor.putString("ownerService", EditOwnersNotes.getText().toString());
        editor.commit();
     //   this.finish();
        Intent intent = new Intent(this,VehicleProfiles.class);
        startActivity(intent);
    }

    public void TakePicture(View view) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(intent);
    }

    public void Barcode(View v) {
        IntentIntegrator integrator = new IntentIntegrator(EditServiceEntry.this);
        integrator.addExtra("SCAN_WIDTH", 640);
        integrator.addExtra("SCAN_HEIGHT", 480);
        integrator.addExtra("SCAN_MODE", "PRODUCT_MODE");
        //customize the prompt message before scanning
        integrator.addExtra("PROMPT_MESSAGE", "Scanner Start!");
        integrator.initiateScan(IntentIntegrator.PRODUCT_CODE_TYPES);
    }

   // public void onActivityResult(int requestCode, int resultCode, Intent intent) {
   //     IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
   //     if (result != null) {
   //         String contents = result.getContents();
    //        if (contents != null) {
    //            //showDialog(R.string.result_succeeded, result.toString());
   //         } else {
    //            //showDialog(R.string.result_failed, getString(R.string.result_failed_why));
    //        }
    //    }
   // }

    public void deleteEntry() {
        DBHandler dbHandler = new DBHandler(this);

        dbHandler.deleteServiceEntry("2013-03-24");
        Toast("Service entry deleted");
    }

    //Display text to the screen
    public void Toast (CharSequence text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }


}
