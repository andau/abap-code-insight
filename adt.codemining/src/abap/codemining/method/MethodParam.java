package abap.codemining.method;

import abap.codemining.utils.StringUtils;

public class MethodParam {
      private String paramName;
	private String paramType;
	private MethodParamType type;

	public MethodParam(String paramName, String paramType, MethodParamType type) 
      {
    	  this.paramName = paramName; 
    	  this.paramType = paramType; 
    	  this.type = type; 
      }

	public String getParamName() {
		return paramName;
	}

	public String getParamType() {
		return paramType;
	}

	public MethodParamType getType() {
		return type;
	}
	
	public String getLabel() 
	{
	     return paramName.equals(StringUtils.EMPTY) ? paramType : getFormattedLabel(); 
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