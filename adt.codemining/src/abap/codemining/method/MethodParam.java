package abap.codemining.method;

import abap.codemining.utils.StringUtils;

public class MethodParam {
      private String paramName;
	private String paramType;
	private MethodParamType methodParamType;

	public MethodParam(String paramName, String paramType, MethodParamType mehtodParamType) 
      {
    	  this.paramName = paramName; 
    	  this.paramType = paramType; 
    	  this.methodParamType = mehtodParamType; 
      }

	public String getParamName() {
		return paramName;
	}

	public String getParamObjectType() {
		return paramType;
	}

	public MethodParamType getMethodParamType() {
		return methodParamType;
	}
	
	public String getLabel() 
	{
	     return paramName.equals(StringUtils.EMPTY) ? paramType : getShortLabel(); 
	}
	
	private String getShortLabel() 
	{
		String shortParamType = paramType.replace("TYPE REF TO" + StringUtils.SPACE,""); 
		if (shortParamType.startsWith("TYPE")) 
		{
			shortParamType = shortParamType.substring("TYPE".length()+1);
		}
		return paramName.toLowerCase() + ":" + shortParamType; 
	}

	private String getFormattedLabel() {
		if (paramType.startsWith("TYPE REF TO"))
		{
			return paramName.toLowerCase() + StringUtils.SPACE + paramType.replace("TYPE REF TO", "type ref to"); 
		}
		else 
		{
			return paramName.toLowerCase() + StringUtils.SPACE + paramType.toLowerCase();  
		}
	}	

}