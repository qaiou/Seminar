package model;
import java.util.ArrayList;

public class Coordinator extends User {
    private ArrayList<Session> session = new ArrayList<Session>();

    public Coordinator(String id, String role, String name, String password) {
        super(id, role, name, password);
    }

}
