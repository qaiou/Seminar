
public class Evaluator extends User{

    public Evaluator(String n, String pw, String e){
        super(n, pw, e);

    }
    
    public String getRole(){
        return "Evaluator";
    }
}
