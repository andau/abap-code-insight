package abap.codemining.method;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import abap.codemining.element.AbapClassBody;
import abap.codemining.element.AbapMethodBody;

public class AbapElementExtractorTest {

	private static final String TEST_METHOD_NAME = "test_method_name";
	private static final String TEST_CLASS_NAME = "test_class_name";
	AbapElementExtractor cut;

	@Before
	public void before() {
		cut = new AbapElementExtractor();
	}

	@Test
	public void testMethodBody() {
		String line = "method " + TEST_METHOD_NAME + ".";
		int linenumber = 3;
		AbapMethodBody methodBody = cut.extractMethodBody(line, linenumber);
		assertEquals(linenumber, methodBody.getLinenumber());
		assertEquals(TEST_METHOD_NAME, methodBody.getElementname());
		assertEquals(22, methodBody.getStartindex());

	}

	@Test
	public void testClassBody() {
		String line = "class " + TEST_CLASS_NAME + " implementation.";
		int linenumber = 3;
		AbapClassBody classBody = cut.extractClassBody(line, linenumber);
		assertEquals(linenumber, classBody.getLinenumber());
		assertEquals(TEST_CLASS_NAME, classBody.getElementname());
		assertEquals(20, classBody.getStartindex());

	}

}
