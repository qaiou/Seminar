package model;

public abstract class User {
    private String id, name, password;

    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getId() { return id; }
    public  abstract String getRole() ;
    public String getName() { return name; }
    public String getPassword() { return password; }
}
