
public abstract class User {
    private String userId, password, email;

    public User(String n, String pw, String e){
        userId= n;
        password = pw;
        email = e;
    }
    public abstract String getRole();
}
