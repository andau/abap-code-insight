package abap.codemining.adt.method;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.sap.adt.tools.abapsource.internal.sources.codeelementinformation.ICodeElement;
import com.sap.adt.tools.abapsource.sources.codeelementinformation.ICodeElementInformationBackendService;

import abap.codemining.adt.method.AbapMethodInformationService;

public class AbapMethodInformationServiceTest {

	private static final URI TEST_URI = URI.create("TEST/URI");
	private static final String TEST_DOC_CONTENT = "test doc content";
	AbapMethodInformationService cut;
	private final ICodeElementInformationBackendService codeElementInformationService = Mockito
			.mock(ICodeElementInformationBackendService.class);
	private final ICodeElement codeElement = Mockito.mock(ICodeElement.class);

	@Before
	public void before() {
		cut = new AbapMethodInformationService(codeElementInformationService);
		Mockito.when(codeElementInformationService.getCodeElementInformation(Mockito.any(), Mockito.any()))
				.thenReturn(codeElement);
	}

	@Test(expected = NullPointerException.class)
	public void test() {
		cut.getInfo(TEST_URI, TEST_DOC_CONTENT);
	}

}
