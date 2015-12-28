package elitetechsolutions.newpocketcar;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SelectServiceEntry extends AppCompatActivity {

    LinearLayout serviceView;
    TextView info, notes, carName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_service_entry);

        serviceView = (LinearLayout) findViewById(R.id.entryView);
        info = (TextView) findViewById(R.id.serviceText);
        notes = (TextView) findViewById(R.id.serviceNotes);
        carName = (TextView) findViewById(R.id.carName);

        DBHandler dbHandler = new DBHandler(this);

        Profile profile = dbHandler.findProfile();

       /* ServiceEntry serviceEntry = new ServiceEntry();

        carName.setText(profile.GetYear() + " " + profile.GetMake()
                + " " + profile.GetModel());

        info.setText("Date: " + serviceEntry.getDate() + "\n"
                + "Mileage: " + serviceEntry.getMileage() + "\n"
                + "Cost: " + serviceEntry.getCost() + "\n"
                + "Time: " + serviceEntry.getTime() + "\n"
                + "Products: " + serviceEntry.getProducts() + "\n");

        notes.setText("Notes: \n" + serviceEntry.getNotes()); */
    }

    public void Edit(View view) {
        Intent intent = new Intent(this,EditServiceEntry.class);
        startActivity(intent);
    }

}
