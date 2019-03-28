package abap.codemining.element;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.eclipse.jface.text.IDocument;

import abap.codemining.element.domain.IAbapElement;
import abap.codemining.element.extractor.CdsViewElementExtractor;
import abap.codemining.element.extractor.IAbapElementExtractor;
import abap.codemining.feature.FeatureFacade;
import abap.codemining.plugin.AbapCodeInsightPluginHelper;

public class CdsElementParser implements IAbapElementParser {

	FeatureFacade featureFacade;

	public CdsElementParser(FeatureFacade featureFacade) {
		this.featureFacade = featureFacade;
		registerPreferencePropertyChangeListener();
	}

	private void registerPreferencePropertyChangeListener() {
		final AbapCodeInsightPluginHelper abapCodeInsightHelper = new AbapCodeInsightPluginHelper();
		abapCodeInsightHelper.getPreferenceStore().addPropertyChangeListener(event -> {
			featureFacade = new FeatureFacade();
		});
	}

	@Override
	public AbapElementInformation getElementInformation(IDocument doc) {

		final String content = setContentToLower(doc);

		final Set<IAbapElement> abapElements = getCdsViewElements(content);
		return new AbapElementInformation(abapElements);
	}

	Set<IAbapElement> getCdsViewElements(String content) {
		final Set<IAbapElement> abapElements = new HashSet<>();

		final Scanner scanner = new Scanner(content);

		final List<IAbapElementExtractor> elementExtractors = new ArrayList<>();
		elementExtractors.add(new CdsViewElementExtractor(featureFacade.getCdsMiningFeature()));

		int linenumber = 0;
		while (scanner.hasNextLine()) {

			linenumber++;

			final String line = scanner.nextLine();

			for (final IAbapElementExtractor elementExtractor : elementExtractors) {
				final IAbapElement abapElement = elementExtractor.extractFromLine(line, linenumber);
				if (abapElement != null) {
					abapElements.add(abapElement);
				}
			}

		}

		scanner.close();

		return abapElements;
	}

	private String setContentToLower(IDocument doc) {
		String content = doc.get();
		content = content.toLowerCase().trim();
		return content;
	}

}