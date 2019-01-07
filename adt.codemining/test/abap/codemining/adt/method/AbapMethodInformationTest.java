package abap.codemining.adt.method;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import abap.codemining.method.MethodParam;
import abap.codemining.utils.StringUtils;

public class AbapMethodInformationTest {

	private static final String TEST_VISIBILITY = "public";
	private static final String TEST_LEVEL = "static";
	private static final String TEST_NAME = "test_abap_codeelement";
	AbapMethodInformation cut = Mockito.mock(AbapMethodInformation.class);
	private final MethodParam returnParameter = null;
	private final Collection<MethodParam> impParameters = new ArrayList<>();
	private final Collection<MethodParam> expParameters = new ArrayList<>();

	@Before
	public void before() {
		cut = new AbapMethodInformation(TEST_VISIBILITY, TEST_LEVEL, TEST_NAME, returnParameter, impParameters,
				expParameters);
	}

	@Test
	public void testGetMethods() {
		String testLabel = TEST_VISIBILITY + StringUtils.SPACE + TEST_LEVEL + StringUtils.SPACE + "void"
				+ StringUtils.SPACE + TEST_NAME + StringUtils.SPACE + "()";
		assertEquals(testLabel, cut.getSignatureLabel());
	}

}
