package abap.codemining.label;

import java.io.IOException;
import java.net.URI;

import com.sap.adt.tools.core.model.util.ServiceNotAvailableException;
import com.sap.adt.tools.core.project.IAbapProject;

public interface IMiningLabelBuilder {

	boolean showRef();

	boolean showSignature();

	String buildSignatureLabel(IAbapProject abapProject, URI uri, String doc) throws MiningLabelBuildingException;

	String buildReferencesLabel(IAbapProject abapProject, URI uri, String doc)
			throws ServiceNotAvailableException, IOException;

}
