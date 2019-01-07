package abap.codemining.adt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.sap.adt.tools.abapsource.internal.sources.codeelementinformation.ICodeElement;
import com.sap.adt.tools.abapsource.internal.sources.codeelementinformation.ICodeElement.ICodeElementProperty;

import abap.codemining.adt.CodeElementWrapper.PropertyKey;
import abap.codemining.method.MethodParamType;
import abap.codemining.utils.StringUtils;

@SuppressWarnings("restriction")
public class CodeElementWrapperTest {

	private static final String ZCL_TEST_ABAP_TYPE = "ZCL_TEST_ABAP_TYPE";
	private static final String CODE_ELEMENT_VISIBILITY_PUBLIC = "public";
	private static final String CODE_ELEMENT_LEVEL_STATIC = "static";

	CodeElementWrapper cut;
	private final ICodeElement codeElement = Mockito.mock(ICodeElement.class);
	private final ICodeElementProperty codeElementProperty = Mockito.mock(ICodeElementProperty.class);
	private final List<ICodeElement> codeElementChildren = new ArrayList<>();

	@Before
	public void before() {
		cut = new CodeElementWrapper(codeElement);
	}

	@Test
	public void testGetVisibility() {

		assertEquals(StringUtils.EMPTY, cut.getVisibility());

		Mockito.when(codeElement.getProperty(PropertyKey.visibility.toString())).thenReturn(codeElementProperty);
		Mockito.when(codeElementProperty.getValue()).thenReturn(CODE_ELEMENT_VISIBILITY_PUBLIC);
		assertEquals(CODE_ELEMENT_VISIBILITY_PUBLIC, cut.getVisibility());

	}

	@Test
	public void testGetLevel() {

		assertEquals(StringUtils.EMPTY, cut.getLevel());

		Mockito.when(codeElement.getProperty(PropertyKey.level.toString())).thenReturn(codeElementProperty);
		Mockito.when(codeElementProperty.getValue()).thenReturn(CODE_ELEMENT_LEVEL_STATIC);
		assertEquals(CODE_ELEMENT_LEVEL_STATIC, cut.getLevel());

	}

	@Test
	public void testGetParameters() {
		assertNull(cut.getReturningParameter());

		Mockito.when(codeElement.getCodeElementChildren()).thenReturn(codeElementChildren);
		assertNull(cut.getReturningParameter());

		final ICodeElement returningChild = Mockito.mock(ICodeElement.class);
		codeElementChildren.add(returningChild);
		assertNull(cut.getReturningParameter());

		ICodeElementProperty paramTypeProperty = Mockito.mock(ICodeElementProperty.class);
		Mockito.when(returningChild.getProperty(PropertyKey.paramType.toString())).thenReturn(paramTypeProperty);
		Mockito.when(paramTypeProperty.getValue())
				.thenReturn(CodeElementWrapper.MethodParamDirection.returning.toString());

		ICodeElementProperty abapTypeProperty = Mockito.mock(ICodeElementProperty.class);
		Mockito.when(returningChild.getProperty(PropertyKey.abapType.toString())).thenReturn(abapTypeProperty);
		Mockito.when(abapTypeProperty.getValue()).thenReturn(ZCL_TEST_ABAP_TYPE);

		assertNotNull(cut.getReturningParameter());
		assertEquals(MethodParamType.RETURNING, cut.getReturningParameter().getMethodParamType());
		assertEquals(ZCL_TEST_ABAP_TYPE, cut.getReturningParameter().getParamObjectType());

		assertEquals(0, cut.getImportingParameters().size());
		Mockito.when(paramTypeProperty.getValue())
				.thenReturn(CodeElementWrapper.MethodParamDirection.importing.toString());
		assertEquals(1, cut.getImportingParameters().size());
		assertNull(cut.getReturningParameter());

		assertEquals(0, cut.getExportingParameters().size());
		Mockito.when(paramTypeProperty.getValue())
				.thenReturn(CodeElementWrapper.MethodParamDirection.exporting.toString());
		assertEquals(1, cut.getExportingParameters().size());
		assertEquals(0, cut.getImportingParameters().size());

	}

}
