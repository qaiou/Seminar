package model;

public class User {
    private String id, role, name, password;

    public User(String id, String role, String name, String password) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.password = password;
    }

    public String getId() { return id; }
    public String getRole() { return role; }
    public String getUserId() { return name; }
    public String getPassword() { return password; }
}
