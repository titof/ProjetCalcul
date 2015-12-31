import java.util.Map;
import java.util.HashMap;

public class InterpreterExample {
    public static void main(String[] args) {
        
    	String expression = "w x z o + - +";
    	
        Evaluator sentence = new Evaluator(expression);
        Map<String,Expression> variables = new HashMap<String,Expression>();
        
        variables.put("w", new Number(1));
        variables.put("x", new Number(3));
        variables.put("z", new Number(2));
        variables.put("o", new Number(2));
        
        int result = sentence.interpret(variables);
        System.out.println(result);
    }
}

// w + x - z + o
// (w + x) - (z + o)

// 1 + 3 - 2 + 2		-> 4
// (1 + 3) - (2 + 2)	-> 0

//Ca devrait donner 2 mais ça donne 0