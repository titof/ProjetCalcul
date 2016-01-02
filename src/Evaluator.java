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
                    break;
                    
            	case "if":
            		//Il faut v�rifier que la condition est bien form�e
            		Expression right = expressionStack.pop();
            		Pattern p=Pattern.compile("(.*)then(.*)else(.*).*");
            		Matcher m=p.matcher(right);
            		String If = null;
            		String Then = null;
            		String Else = null;
            		while(m.find()) 
            		{
            			If = m.group(1).substring(m.group(1).indexOf('(')+1, m.group(1).indexOf(')'));
            			Then = m.group(2).substring(m.group(2).indexOf('(')+1, m.group(2).indexOf(')'));;
            			Else = m.group(3).substring(m.group(3).indexOf('(')+1, m.group(3).indexOf(')'));;
            		}
            		//Si la condition est bien �crite
            		if(If != null && Then != null && Else != null)
            		{
            			//Interpr�ter la condition
            			Expression condition = new Condition(If,Then,Else);
            			expressionStack.push( condition );
            		}
            		else
            		{
            			System.out.println("Erreur: condtion mal form�e");
            		}
            		
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