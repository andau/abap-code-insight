package abap.codemining.element;

import org.eclipse.jface.text.IDocument;

public interface IAbapElementParser {

	AbapElementInformation getElementInformation(IDocument doc);

}
