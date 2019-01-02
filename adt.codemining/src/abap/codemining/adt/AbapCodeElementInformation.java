package abap.codemining.adt;

import abap.codemining.method.MethodParam;

public class AbapCodeElementInformation {
	private String visibility; 
	private String level; 
	private String name; 
	private MethodParam returnParameter; 

	public AbapCodeElementInformation(String visibility, String level, String name, MethodParam returnParameter) 
	{
		this.visibility = visibility;
		this.level = level; 
		this.name = name; 
		this.returnParameter = returnParameter; 
	}

	public String getVisibility() {
		return visibility; 
	}

	public String getLevel() {
		return level; 
	}
	
	public String getName() 
	{
		return name; 
	}

	public MethodParam getReturnValue() {
		return returnParameter; 
	}

}
