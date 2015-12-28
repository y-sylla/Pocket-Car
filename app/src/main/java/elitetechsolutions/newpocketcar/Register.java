package elitetechsolutions.newpocketcar;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText userBox;
    EditText passwordBox,confirmBox;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userBox = (EditText) findViewById(R.id.cuField);
        passwordBox = (EditText) findViewById(R.id.cpField);
        confirmBox = (EditText) findViewById(R.id.confirmField);
    }

    public void newUser (View view) {

        DBHandler dbHandler = new DBHandler(this);

        String password =
                passwordBox.getText().toString();

        String username =
                userBox.getText().toString();

        String confirm =
                confirmBox.getText().toString();

        int pw = password.hashCode();

        //Check username length
        if (username.length() <4 || username.length() >16) {
            Toast("Username must be between 4-16 characters");
        }

        //Check password length
        if (password.length() <8 || password.length() >20) {
            Toast("Password must be between 8-20 characters");
        }

        //Check if password and password confirmation are the same
        if (password.equals(confirm))
        {
            dbHandler.saveUserRecords(username,pw);
            Toast("Account created");
            this.finish();
        }
        else {
            Toast("Passwords do not match!");
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