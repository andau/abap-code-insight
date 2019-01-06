package abap.codemining.adt;

import java.util.Collection;

import abap.codemining.method.MethodParam;

public class AbapCodeElementInformation {
	private String visibility; 
	private String level; 
	private String name; 
	private MethodParam returnParameter; 
	private Collection<MethodParam> impParameters; 
	private Collection<MethodParam> expParameters; 

	public AbapCodeElementInformation(String visibility, String level, String name, MethodParam returnParameter, Collection<MethodParam> impParameters, Collection<MethodParam> expParameters) 
	{
		this.visibility = visibility;
		this.level = level; 
		this.name = name; 
		this.returnParameter = returnParameter;
		this.impParameters = impParameters; 
		this.expParameters = expParameters; 
		
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

	public Collection<MethodParam> getImpParameters() {
		return impParameters;
	}

	public Collection<MethodParam> getExpParameters() {
		return expParameters;
	}



}
