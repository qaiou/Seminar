
public class Coordinator extends User {

    public Coordinator(String n, String pw, String e){
        super(n, pw, e);

    }

    public String getRole(){
        return "Coordinator";
    }

}
