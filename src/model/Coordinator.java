package model;
import java.util.ArrayList;

public class Coordinator extends User {
    private ArrayList<Session> session = new ArrayList<Session>();

    public Coordinator(String n, String pw, String e){
        super(n, pw, e);

    }

    public String getRole(){
        return "Coordinator";
    }

}
