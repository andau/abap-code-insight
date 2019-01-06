package abap.codemining.method;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.eclipse.jface.text.IDocument;

import abap.codemining.element.IAbapElement;

public class AbapMethodDefinitionExtractor {

	boolean publicSector = false;

	public AbapMethodInformation getMethodInformation(IDocument doc) {

		String content = setContentToLower(doc);

		Set<IAbapElement> abapElements = getMethodBodies(content);
		return new AbapMethodInformation(abapElements);
	}

	Set<IAbapElement> getMethodBodies(String content) {
		Set<IAbapElement> abapElements = new HashSet<>();

		Scanner scanner = new Scanner(content);

		AbapElementExtractor abapmethodBodyExtractor = new AbapElementExtractor();

		int linenumber = 0;
		while (scanner.hasNextLine()) {

			linenumber++;

			String line = scanner.nextLine(); 
			
			IAbapElement abapElement = abapmethodBodyExtractor.extractMethodBody(line, linenumber);

			if (abapElement != null) {
				abapElements.add(abapElement);
			}

			abapElement = abapmethodBodyExtractor.extractClassBody(line, linenumber);

			if (abapElement != null) {
				abapElements.add(abapElement);
			}

			abapElement = abapmethodBodyExtractor.extractClassDef(line, linenumber);

			if (abapElement != null) {
				abapElements.add(abapElement);
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
