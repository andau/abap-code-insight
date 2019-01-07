package abap.codemining.method;

import static org.junit.Assert.*;

import org.junit.Test;

import abap.codemining.utils.StringUtils;


public class MethodParamTest {


	private static final String PARAMETER_NAME = "PARAMETER_NAME";
	private static final String TYPE_REF_TO = "TYPE REF TO";
	private static final String TYPE = "TYPE";
	private static final String CLASS_TYPE = "ZCL_TEST";
	private static final String SIMPLE_TYPE = "STRING";
	

	
	@Test
	public void testMethodParamTypeRefTo() {
		String parameterValue = TYPE_REF_TO + StringUtils.SPACE + CLASS_TYPE; 
		MethodParam cut = new MethodParam(PARAMETER_NAME, parameterValue , MethodParamType.RETURNING); 
		assertEquals(PARAMETER_NAME, cut.getParamName());
		assertEquals(parameterValue, cut.getParamObjectType()); 
		assertEquals(MethodParamType.RETURNING, cut.getMethodParamType()); 
		assertEquals(PARAMETER_NAME.toLowerCase() +":"+ CLASS_TYPE, cut.getLabel()); 
	}

	@Test
	public void testMethodParamSimpleType() {
		String parameterValue = TYPE + StringUtils.SPACE + SIMPLE_TYPE; 
		MethodParam cut = new MethodParam(PARAMETER_NAME, parameterValue , MethodParamType.RETURNING); 
		assertEquals(PARAMETER_NAME, cut.getParamName());
		assertEquals(parameterValue, cut.getParamObjectType()); 
		assertEquals(MethodParamType.RETURNING, cut.getMethodParamType()); 
		assertEquals(PARAMETER_NAME.toLowerCase() + ":"  + SIMPLE_TYPE, cut.getLabel()); 
	}

}
