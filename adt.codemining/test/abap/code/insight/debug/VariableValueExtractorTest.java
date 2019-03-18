package abap.code.insight.debug;

import static org.junit.Assert.assertEquals;

import org.eclipse.debug.core.DebugException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.sap.adt.debugger.variables.IAbapVariable;
import com.sap.adt.debugger.variables.IAbapVariableValue;

public class VariableValueExtractorTest {

	VariableValueExtractor cut = new VariableValueExtractor();
	IAbapVariable[] variables;
	IAbapVariable abapVariable = Mockito.mock(IAbapVariable.class);
	final IAbapVariableValue variableValue = Mockito.mock(IAbapVariableValue.class);
	final IAbapVariable childVariable = Mockito.mock(IAbapVariable.class);

	@Before
	public void before() throws DebugException {
		variables = new IAbapVariable[1];
		variables[0] = abapVariable;
		Mockito.when(abapVariable.getValue()).thenReturn(variableValue);
		Mockito.when(variableValue.getVariables()).thenReturn(new IAbapVariable[] { childVariable });
	}

	@Test
	public void testExtractMeVariable() throws DebugException {
		Mockito.when(abapVariable.getName()).thenReturn(VariableValueExtractor.DEBUG_VARIABLES_LOCALS);

		assertEquals(1, cut.extract(variables).size());
	}

}
