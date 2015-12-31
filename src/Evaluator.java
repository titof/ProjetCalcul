import java.util.Map;
import java.util.Stack;

class Evaluator implements Expression {
	
    private Expression syntaxTree;
 
    public Evaluator(String expression) {
        Stack<Expression> expressionStack = new Stack<Expression>();
        for (String token : expression.split(" ")) {
            //vérifie s'il y a un + dans l'équation
        	if  (token.equals("+")) {
                Expression addExpression = new Plus(expressionStack.pop(), expressionStack.pop());
                expressionStack.push( addExpression );
            }
        	//vérifie s'il y a un - dans l'équation
            else if (token.equals("-")) {
                // it's necessary remove first the right operand from the stack
                Expression right = expressionStack.pop();
                // ..and after the left one
                Expression left = expressionStack.pop();
                Expression subExpression = new Minus(left, right);
                expressionStack.push( subExpression );
            }
            else                        
                expressionStack.push( new Variable(token) );
        }
        syntaxTree = expressionStack.pop();
    }
 
    public int interpret(Map<String,Expression> context) {
        return syntaxTree.interpret(context);
    }
}