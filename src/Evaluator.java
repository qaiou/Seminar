import java.util.*;

public class Evaluator extends User{
    private ArrayList<Evaluation> evaluation = new ArrayList<Evaluation>();

    public Evaluator(String n, String pw, String e){
        super(n, pw, e);

    }
    
    public String getRole(){
        return "Evaluator";
    }
}
