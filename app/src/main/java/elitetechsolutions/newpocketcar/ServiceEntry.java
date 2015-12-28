package elitetechsolutions.newpocketcar;

/**
 * Created by Yasia on 12/15/2015.
 */
public class ServiceEntry {

    //Member Variables

    private String _date;
    private int _mileage;
    private double _cost;
    private String _time,_products,_notes;

    //Constructors
    public ServiceEntry() {

    }

    public ServiceEntry(String date,int mileage,double cost, String time, String products, String notes)
    {
        this._date = date;
        this._mileage = mileage;
        this._cost = cost;
        this._time = time;
        this._products = products;
        this._notes = notes;
    }

    //Set Methods
    public void setDate(String date) {
        this._date = date;
    }

    public void setMileage(int mileage) {
        this._mileage = mileage;
    }

    public void setCost(double cost) {
        this._cost = cost;
    }

    public void setTime(String time) {
        this._time = time;
    }

    public void setProducts(String products) {
        this._products = products;
    }

    public void setNotes(String notes) {
        this._notes = notes;
    }

    //Get Methods

    public String getDate() { return this._date; }
    public int getMileage() { return this._mileage; }
    public double getCost() { return this._cost; }
    public String getTime() { return this._time; }
    public String getProducts() { return this._products; }
    public String getNotes() { return this._notes; }

}
