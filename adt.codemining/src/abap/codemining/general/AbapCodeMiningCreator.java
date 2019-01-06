package abap.codemining.general;

import java.net.URI;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.codemining.ICodeMining;
import org.eclipse.jface.text.codemining.ICodeMiningProvider;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.NewSearchUI;

import com.sap.adt.ris.search.ui.usagereferences.AdtRisUsageReferencesSearchQuery;
import com.sap.adt.ris.search.ui.usagereferences.AdtRisUsageReferencesSearchQueryParameters;
import com.sap.adt.ris.search.usagereferences.AdtRisUsageReferencesSearchServiceFactory;
import com.sap.adt.tools.core.model.util.ServiceNotAvailableException;

public class AbapCodeMiningCreator {

	public ICodeMining createRef(int linenumber, IDocument document, ICodeMiningProvider provider, String miningLabel,
			ISearchQuery usageReferencesQuery) throws BadLocationException {
		return new AbapHeaderCodeMining(linenumber, document, provider, miningLabel,
				e -> NewSearchUI.runQueryInBackground(usageReferencesQuery));
	}
	public ICodeMining create(int linenumber, IDocument document, ICodeMiningProvider provider, String label) throws BadLocationException {
		return new AbapHeaderCodeMining(linenumber, document, provider, label, null);
	}

	public ISearchQuery createUsageReferencQuery(IProject project, URI uri) throws ServiceNotAvailableException {
		AdtRisUsageReferencesSearchServiceFactory.createUsageReferencesSearchService(project,
				new NullProgressMonitor());
		AdtRisUsageReferencesSearchQueryParameters parameters = new AdtRisUsageReferencesSearchQueryParameters(project,
				uri);
		return new AdtRisUsageReferencesSearchQuery(parameters);
	}

}
