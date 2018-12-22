/**
 *  Copyright (c) 2017 Angelo ZERR.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  Contributors:
 *     Angelo Zerr <angelo.zerr@gmail.com> - [CodeMining] Provide Java References/Implementation CodeMinings - Bug 529127
 */
package abap.codemining.editor;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.ui.javaeditor.EditorUtility;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.codemining.AbstractCodeMiningProvider;
import org.eclipse.jface.text.codemining.ICodeMining;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.texteditor.ITextEditor;

import abap.codemining.general.AbapClassCodeMining;
import abap.codemining.preferences.AbapPreferencesPropertyTester;
import abap.codemining.preferences.MyPreferenceConstants;

/**
 * Java code mining provider to show code minings by using {@link IJavaElement}:
 *
 * <ul>
 * <li>Show references</li>
 * <li>Show implementations</li>
 * </ul>
 *
 * @since 3.15
 *
 */
public class AbapElementCodeMiningProvider extends AbstractCodeMiningProvider {

	private ITextViewer fViewer;

	private ITypeRoot fUnit;

	private final boolean showReferences;

	private final boolean showReferencesOnClass;

	private final boolean showReferencesOnMethod;

	private final boolean showReferencesAtLeastOne;

	private final boolean showImplementations;

	private final boolean showImplementationsAtLeastOne;

	public AbapElementCodeMiningProvider() {
		showReferences = AbapPreferencesPropertyTester
				.isEnabled(MyPreferenceConstants.EDITOR_JAVA_CODEMINING_SHOW_REFERENCES);
		showReferencesOnClass = AbapPreferencesPropertyTester
				.isEnabled(MyPreferenceConstants.EDITOR_JAVA_CODEMINING_SHOW_CLASS_REFERENCES);
		showReferencesOnMethod = AbapPreferencesPropertyTester
				.isEnabled(MyPreferenceConstants.EDITOR_JAVA_CODEMINING_SHOW_METHOD_REFERENCES);
		showReferencesAtLeastOne = AbapPreferencesPropertyTester
				.isEnabled(MyPreferenceConstants.EDITOR_JAVA_CODEMINING_SHOW_REFERENCES_AT_LEAST_ONE);
		showImplementations = AbapPreferencesPropertyTester
				.isEnabled(MyPreferenceConstants.EDITOR_JAVA_CODEMINING_SHOW_IMPLEMENTATIONS);
		showImplementationsAtLeastOne = AbapPreferencesPropertyTester
				.isEnabled(MyPreferenceConstants.EDITOR_JAVA_CODEMINING_SHOW_IMPLEMENTATIONS_AT_LEAST_ONE);
	}

	@Override
	public CompletableFuture<List<? extends ICodeMining>> provideCodeMinings(ITextViewer viewer,
			IProgressMonitor monitor) {
		return CompletableFuture.supplyAsync(() -> {
			monitor.isCanceled();
			ITextEditor textEditor = super.getAdapter(ITextEditor.class);

			if (textEditor.getEditorInput() instanceof IFileEditorInput) {
				((IFileEditorInput) textEditor.getEditorInput()).getFile();
			}

			ITypeRoot unit = EditorUtility.getEditorInputJavaElement(textEditor, true);
			// if (unit == null) {
			// return Collections.emptyList();
			// }
			fViewer = viewer;
			fUnit = unit;
			try {
				IJavaElement[] children = unit != null ? unit.getChildren() : new IJavaElement[] {};
				List<ICodeMining> minings = new ArrayList<>();
				collectMinings(unit, textEditor, children, minings, viewer, monitor);
				monitor.isCanceled();
				return minings;
			} catch (JavaModelException e) {
				// TODO: what should we done when there are some errors?
			}
			return Collections.emptyList();
		});
	}

	/**
	 * Collect java code minings
	 *
	 * @param unit       the compilation unit
	 * @param textEditor the Java editor
	 * @param elements   the java elements to track
	 * @param minings    the current list of minings to update
	 * @param viewer     the viewer
	 * @param monitor    the monitor
	 * @throws JavaModelException
	 */
	private void collectMinings(ITypeRoot unit, ITextEditor textEditor, IJavaElement[] elements,
			List<ICodeMining> minings, ITextViewer viewer, IProgressMonitor monitor) throws JavaModelException {
		for (IJavaElement element : elements) {
			if (monitor.isCanceled()) {
				return;
			}
			if (element.getElementType() == IJavaElement.TYPE) {
				collectMinings(unit, textEditor, ((IType) element).getChildren(), minings, viewer, monitor);
			} else if (element.getElementType() != IJavaElement.METHOD) {
				continue;
			}
		}
		IDocument doc = textEditor.getDocumentProvider().getDocument(textEditor.getEditorInput());
		if (textEditor.getTitle().contains("ZCL")) {

			AbapClassCodeMining abapClassCodeMining = new AbapClassCodeMining();

			try {
				abapClassCodeMining.evaluateCodeMinings(minings, textEditor, viewer, this, doc);
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}