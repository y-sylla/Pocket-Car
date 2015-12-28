package elitetechsolutions.newpocketcar;

/**
 * Created by Yasia on 12/12/2015.
 */
public class Profile {

    //Member Variables
    private int _id,_year,_mileage,_userID, _notifID, _serviceID;
    private String _make,_model,_color,_purchasedate,_image;
    /** Hi */

    //Constructors
    public Profile() {

    }

    public Profile(String make, String model, int year, String color, int mileage, String purchasedate)
    {
        this._color = color;
        this._year = year;
        this._make = make;
        this._mileage = mileage;
        this._model = model;
        this._purchasedate = purchasedate;
        this._image = null;
    }

    //Set Methods
    public void setID(int id) { this._id = id; }
    public void SetMake(String make) { this._make = make; }
    public void SetModel(String model) { this._model = model; }
    public void SetYear(int year) { this._year = year; }
    public void SetColor(String color) { this._color = color; }
    public void SetMileage(int mileage) { this._mileage = mileage;}
    public void SetPurchaseDate(String purchaseDate) { this._purchasedate = purchaseDate; }
    public void setUserID(int id) { this._userID = id; }
    public void setNotifID(int id) { this._notifID = id ; }
    public void setServiceID(int id) { this._serviceID = id; }
    public void setImage(String image) { this._image = image; }

    //Get Methods
    public int getID() { return this._id; }
    public String GetMake() { return _make; }
    public String GetModel() { return  _model; }
    public String GetColor() { return _color; }
    public String GetPurchaseDate() { return _purchasedate; }
    public int GetYear() { return _year; }
    public int GetMileage() { return _mileage; }
    public int getUserID() { return _userID; }
    public int getNotifID() { return _notifID; }
    public int getServiceID() { return _serviceID; }
    public String getImage() { return _image; }

}
