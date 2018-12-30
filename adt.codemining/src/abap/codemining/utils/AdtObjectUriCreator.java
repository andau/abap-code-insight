package abap.codemining.utils;

import java.net.URI;
import java.net.URISyntaxException;

import com.sap.adt.tools.core.IAdtObjectReference;

public class AdtObjectUriCreator {

	static final String URI_START_PARAM_IDENTIFIER = "#start";
	static final int URI_TEST_START_CHARACTER = 9;

	private final IAdtObjectReference adtObject;

	public AdtObjectUriCreator(IAdtObjectReference adtObject) {
		this.adtObject = adtObject;
	}

	public URI createUriForLine(int linenumber) throws URISyntaxException {
		return new URI(String.format("%s%s=%s,%s", adtObject.getUri().toString(),
				AdtObjectUriCreator.URI_START_PARAM_IDENTIFIER, linenumber,
				AdtObjectUriCreator.URI_TEST_START_CHARACTER));
	}

}
