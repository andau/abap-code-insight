package abap.codemining.utils;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.sap.adt.tools.core.IAdtObjectReference;

public class AdtObjectUriCreatorTest {

	private static final int TEST_LINENUMBER = 36;
	private static final String ADT_OBJECT_TEST_URI = "adtObjectTestUri";
	private final IAdtObjectReference adtObject = Mockito.mock(IAdtObjectReference.class);

	@Before
	public void before() {
		Mockito.when(adtObject.getUri()).thenReturn(URI.create(ADT_OBJECT_TEST_URI));
	}

	@Test
	public void testUriCreator() throws URISyntaxException {
		AdtObjectUriCreator cut = new AdtObjectUriCreator(adtObject);
		URI uriForLine = cut.createUriForLine(TEST_LINENUMBER);

		String compareUri = String.format("%s%s=%s,%s", ADT_OBJECT_TEST_URI,
				AdtObjectUriCreator.URI_START_PARAM_IDENTIFIER, TEST_LINENUMBER,
				AdtObjectUriCreator.URI_TEST_START_CHARACTER);
		assertEquals(compareUri, uriForLine.toString());

	}

}
