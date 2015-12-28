package elitetechsolutions.newpocketcar;

/**
 * Created by Yasia on 12/15/2015.
 */
public class Notif {

    //Member Variables
    private int _id;
    private String _date, _text,_time;


    //Constructors
    public Notif() {
        _date = "";
        _text="";
        _time="";
    }

    public Notif(String text, String date, String time) {
        this._date = date;
        this._text = text;
        this._time = time;
    }

    //Set Methods
    public void setDate(String date) {
        this._date = date;
    }

    public void setText(String text) {
        this._text = text;
    }

    public void setTime(String time) { this._time = time; }

    //Get Methods
    public String getDate() {
        return this._date;
    }

    public String getText() {
        return this._text;
    }

    public String getTime() { return this._time;}

}
