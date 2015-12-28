package elitetechsolutions.newpocketcar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class VehicleProfiles extends AppCompatActivity {


    TextView name, mileage, color, purchased;
    ImageView car;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_profiles);

        LinearLayout parent = (LinearLayout) findViewById(R.id.parent);

        DBHandler dbHandler = new DBHandler(this);

        //int count = dbHandler.profileCount();

        final SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String CarMake = (mSharedPreferences.getString("makeField",""));
        String Model = (mSharedPreferences.getString("modelField",""));
        String Mileage = (mSharedPreferences.getString("mileageBox",""));
        String PurchaseDate = (mSharedPreferences.getString("dateBox",""));
        String ColorCar = (mSharedPreferences.getString("colorBox",""));


        //HARDCODED VALUES
        name = (TextView) findViewById(R.id.vehicleName);
        mileage = (TextView) findViewById(R.id.mileage);
        color = (TextView) findViewById(R.id.color);
        purchased = (TextView) findViewById(R.id.purchased);
        car = (ImageView) findViewById(R.id.carpic);


        String Volkswagen = "Volkswagen";

        if(CarMake.equals(Volkswagen)) {
            car.setImageResource(R.drawable.red);
        }
        else if(CarMake.equals("Nissan")){
            car.setImageResource(R.mipmap.ic_gtr);
        }


        name.setText(CarMake+" "+Model);
        mileage.setText("Mileage: " + Mileage);
        color.setText("Color: "+ ColorCar);
        purchased.setText("Purchased: " + PurchaseDate);

    }


    public void NewEntry (View view) {
        Intent intent = new Intent(this,NewServiceEntry.class);
        startActivity(intent);
    }

    public void ViewHistory (View view) {
        Intent intent = new Intent(this,ViewServiceHistory.class);
        startActivity(intent);
    }

    public void NewVehicle (View view) {
        Intent intent = new Intent(this,NewVehicleProfile.class);
        startActivity(intent);
    }

    public void EditService (View view) {
        Intent intent = new Intent(this,EditServiceEntry.class);
        startActivity(intent);
    }

    public void Logout (View view) {
        Toast("Logged out");
        Intent intent = new Intent(this,WelcomePage.class);
        startActivity(intent);
    }

    public void EditVehicle (View view) {
        Intent intent = new Intent(this,EditVehicleProfile.class);
        startActivity(intent);
    }

    public void Notifications (View view) {
        Intent intent = new Intent(this,Notifications.class);
        startActivity(intent);
    }

    //Display text to the screen
    public void Toast (CharSequence text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context,text,duration);
        toast.show();
    }
    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

}
