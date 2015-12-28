package elitetechsolutions.newpocketcar;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.GregorianCalendar;

public class Notifications extends AppCompatActivity {


    TextView text,notes;
    EditText ndate,ntime,ntext;
    EditText timetext;
    Button setAlarmButton;


    public static final String DateKey = "namekey";
    public static final String NotesKey = "NotesKey";
    int testtime;
    public static final String MyPREFERENCES = "MyPrefs" ;

    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        //setAlarmButton = (Button) findViewById(R.id.timeButton);
        //alarmEnterTime = (EditText) findViewById(R.id.editTextAlarmTime);

        //active = (LinearLayout) findViewById(R.id.notifView);
        text = (TextView) findViewById(R.id.notifText);
        notes = (TextView) findViewById(R.id.notifNotes);
        timetext = (EditText) findViewById(R.id.timeText);
        setAlarmButton = (Button) findViewById(R.id.saveNotificationButton);

        ndate = (EditText) findViewById(R.id.dateText);
        ntext = (EditText) findViewById(R.id.NotificationText);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        text.setText(sharedPreferences.getString(DateKey, ""));
        notes.setText(sharedPreferences.getString(NotesKey,""));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Read values from the "savedInstanceState"-object and put them in your textview

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save the values you need from your textview into "outState"-object
        super.onSaveInstanceState(outState);


    }
    //Save information to notification database
    public void Save (View view) {

        Toast("Notification saved");

        this.finish();
    }

    public void Delete (View view) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Deleting Notification")
                .setMessage("Are you sure you want to delete this notification?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Code to delete specific service entry

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        text.setText("");
                        notes.setText("");
                        editor.putString(DateKey,"");
                        editor.putString(NotesKey,"");
                        editor.commit();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }


    public void setAlarm(View view){


        String theDate = ndate.getText().toString();
        String theNoficationText = ntext.getText().toString();


        notes.setText(theNoficationText);
        text.setText(theDate);



        testtime = Integer.parseInt(timetext.getText().toString());


        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(DateKey,theDate);
        editor.putString(NotesKey,theNoficationText);
        editor.commit();

        Long alertTime = new GregorianCalendar().getTimeInMillis()+testtime * 1000;
        Intent alertIntent = new Intent(this, AlertReceiver.class);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, alertTime, PendingIntent.getBroadcast(this, 1, alertIntent, PendingIntent.FLAG_UPDATE_CURRENT));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Display text to the screen
    public void Toast (CharSequence text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context,text,duration);
        toast.show();
    }




}
