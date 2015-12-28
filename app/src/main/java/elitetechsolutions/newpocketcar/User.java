package elitetechsolutions.newpocketcar;

/**
 * Created by Yasia on 10/31/2015.
 */
public class User {

    //Member Variables
    private int _id,_password;
    private String _username;

    //Constructors
    public User() {

    }


    public User(String username, int password) {
        this._username = username;
        this._password = password;
    }


    //Get/Set Methods
    public void setID(int id) {
        this._id = id;
    }

    public int getID() {
        return this._id;
    }

    public void setUsername(String username) {
        this._username = username;
    }

    public String getUsername() {
        return this._username;
    }

    public void setPassword(int password) {
        this._password = password;
    }

    public int getPassword() {
        return this._password;
    }
}
