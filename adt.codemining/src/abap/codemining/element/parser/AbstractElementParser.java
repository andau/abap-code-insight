package abap.codemining.element.parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.eclipse.jface.text.IDocument;

import abap.codemining.element.AbapElementInformation;
import abap.codemining.element.IAbapElementParser;
import abap.codemining.element.domain.IAbapElement;
import abap.codemining.element.extractor.IAbapElementExtractor;
import abap.codemining.feature.FeatureFacade;

public abstract class AbstractElementParser implements IAbapElementParser {

	FeatureFacade featureFacade;

	final List<IAbapElementExtractor> elementExtractors = new ArrayList<>();

	public AbstractElementParser(FeatureFacade featureFacade) {
		this.featureFacade = featureFacade;
		initializeElementExtractors();
	}

	@Override
	public AbapElementInformation getElementInformation(IDocument doc) {

		final String content = setContentToLower(doc);

		final Set<IAbapElement> abapElements = getReportElements(content);
		return new AbapElementInformation(abapElements);
	}

	protected abstract void initializeElementExtractors();

	Set<IAbapElement> getReportElements(String content) {
		final Set<IAbapElement> abapElements = new HashSet<>();

		final Scanner scanner = new Scanner(content);

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
