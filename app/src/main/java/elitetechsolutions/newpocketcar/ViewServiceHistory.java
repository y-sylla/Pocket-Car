package elitetechsolutions.newpocketcar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewServiceHistory extends AppCompatActivity {

    LinearLayout serviceView;
    TextView info, notes, carName;

    EditText ServiceCarName;
    EditText ServiceDate;
    EditText ServiceMileage;
    EditText ServiceCost;
    EditText ServiceTimeConsumed;
    EditText ProductsUsed;
    EditText OwnersNotes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_service_history);

        serviceView = (LinearLayout) findViewById(R.id.entryView);
        info = (TextView) findViewById(R.id.serviceText);
        notes = (TextView) findViewById(R.id.serviceNotes);
        carName = (TextView) findViewById(R.id.carName);

        ServiceCarName = (EditText) findViewById(R.id.ServiceCarNameET);
        ServiceDate = (EditText) findViewById(R.id.ServiceDateET);
        ServiceMileage = (EditText) findViewById(R.id.ServiceMileageET);
        ServiceCost = (EditText) findViewById(R.id.ServiceCostET);
        ServiceTimeConsumed = (EditText) findViewById(R.id.ServiceTCET);
        ProductsUsed = (EditText) findViewById(R.id.ServiceProductUsed);
        OwnersNotes = (EditText) findViewById(R.id.ServiceOwnerNotesET);

        SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        String dateService = (mSharedPreferences.getString("dateService",""));
        String CarMake = (mSharedPreferences.getString("makeField",""));
        String Model = (mSharedPreferences.getString("modelField",""));
        String mileageService = (mSharedPreferences.getString("mileService",""));
        String costService = (mSharedPreferences.getString("costService",""));
        String timeConsumedService = (mSharedPreferences.getString("timeService",""));
        String productsUsedService = (mSharedPreferences.getString("productService",""));
        String ownersNotesService = (mSharedPreferences.getString("ownerService",""));

        ServiceDate.setText(dateService,TextView.BufferType.EDITABLE);
        ServiceCarName.setText(CarMake +" "+ Model);
        ServiceMileage.setText(mileageService);
        ServiceCost.setText(costService);
        ServiceTimeConsumed.setText(timeConsumedService);
        ProductsUsed.setText(productsUsedService);
        OwnersNotes.setText(ownersNotesService);





    }

}
