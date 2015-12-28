package elitetechsolutions.newpocketcar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WelcomePage extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        //loginbutton=(Button) findViewById(R.id.loginButton);
    }


    public void Login(View view) {

        DBHandler dbHandler = new DBHandler(this);

        EditText userBox = (EditText) findViewById(R.id.uField);
        EditText passBox = (EditText) findViewById(R.id.pField);

        String username =
                userBox.getText().toString();

        int password = passBox.getText().toString().hashCode();

        //Toast(username);

        User user = new User();

        //Checks to see if username entered is in database
       user = dbHandler.findUsername(username);

        if (user!=null) {
           int passCheck = user.getPassword();
            if (passCheck!=password)
                {Toast("Password invalid"); }
            else {
                Toast("Login successful");
                Intent intent = new Intent(this,VehicleProfiles.class);
                startActivity(intent); }
        }
        else {
            Toast("Username does not exist!");
        }

    }


    //Opens the registration page
    public void Register(View view) {
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }

    //Display text to the screen
    public void Toast (CharSequence text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context,text,duration);
        toast.show();
    }


}
