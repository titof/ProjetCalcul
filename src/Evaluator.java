import java.util.Map;
import java.util.Stack;

class Evaluator implements Expression {
	
    private Expression syntaxTree;
 
    public Evaluator(String expression) {
        Stack<Expression> expressionStack = new Stack<Expression>();
        for (String token : expression.split(" ")) {
            switch(token)
            {
            	case "+":
            		Expression addExpression = new Plus(expressionStack.pop(), expressionStack.pop());
                    expressionStack.push( addExpression );
                    break;
                    
            	case "-":
            		// it's necessary remove first the right operand from the stack
                    Expression right = expressionStack.pop();
                    // ..and after the left one
                    Expression left = expressionStack.pop();
                    Expression subExpression = new Minus(left, right);
                    expressionStack.push( subExpression );
                    
            	case "if":
            		//Il faut v�rifier que la condition est bien form�e
            		Expression right = expressionStack.pop();
            		//v�rifier qu'il y a then et else
            		
            		break;
            	
            	default:
            		expressionStack.push ( new Variable(token) );
            }
        	/**
        	 * Version if else de Cl�ment
        	 * 
        	//v�rifie s'il y a un + dans l'�quation
        	if  (token.equals("+")) {
                Expression addExpression = new Plus(expressionStack.pop(), expressionStack.pop());
                expressionStack.push( addExpression );
            }
        	//v�rifie s'il y a un - dans l'�quation
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
        }**/
        syntaxTree = expressionStack.pop();
    }
 
    public int interpret(Map<String,Expression> context) {
        return syntaxTree.interpret(context);
    }
}