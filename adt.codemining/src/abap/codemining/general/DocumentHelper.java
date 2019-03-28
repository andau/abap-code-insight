package abap.codemining.general;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;

import abap.codemining.utils.StringUtils;

public class DocumentHelper {

	public static String getLinetext(IDocument doc, int linenumber) {
		try {
			final IRegion lineInformation = doc.getLineInformation(linenumber);
			return doc.get(lineInformation.getOffset(), lineInformation.getLength());
		} catch (final BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return StringUtils.EMPTY;

	}

}
