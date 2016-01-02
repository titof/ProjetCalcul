import java.util.Map;

public class Condition implements Expression
{
	//ATTRIBUTS
	private String If;
	private String Then;
	private String Else;
	
	public Condition(String If, String Then, String Else)
	{
		this.If=If;
		this.Then=Then;
		this.Else=Else;
	}
	
	
	public int interpret(Map<String, Expression> variables) 
	{
		if(If)
		{
			return Then;
		}
		else
		{
			return Else;
		}
	}

}
