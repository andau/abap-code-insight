package abap.codemining.preferences;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import abap.codemining.utils.StringUtils;

public class PreferencesUiHelper {

	final static Color HEADER_COLOR = new Color(Display.getCurrent(), 0, 0, 255);
	final static Color BIG_HEADER_COLOR = new Color(Display.getCurrent(), 0, 255, 0);

	public void addHeaderLabelWithSpaceBefore(Composite fieldEditorParent, String headerText) {
		addEmptyLabel(fieldEditorParent);
		addHeaderLabel(fieldEditorParent, headerText, HEADER_COLOR);
	}

	public void addBigHeaderLabelWithoutSpaceBefore(Composite fieldEditorParent, String headerText) {
		addBigHeaderLabel(fieldEditorParent, headerText, BIG_HEADER_COLOR);
	}

	public void addEmptyLabel(Composite fieldEditorParent) {
		addHeaderLabel(fieldEditorParent, StringUtils.EMPTY, HEADER_COLOR);
	}

	private void addHeaderLabel(Composite fieldEditorParent, String headerText, Color color) {
		final Label headerLabel = new Label(fieldEditorParent, SWT.NONE);
		headerLabel.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 3, 1));
		headerLabel.setText(headerText);
		headerLabel.setForeground(color);

	}

	private void addBigHeaderLabel(Composite fieldEditorParent, String headerText, Color color) {
		final Label headerLabel = new Label(fieldEditorParent, SWT.NONE);
		headerLabel.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 3, 1));
		headerLabel.setText(headerText);
		headerLabel.setFont(new Font(null, "Tahoma", 14, 0));
		headerLabel.setForeground(color);

	}

}
