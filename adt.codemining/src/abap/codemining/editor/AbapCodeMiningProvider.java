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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.codemining.AbstractCodeMiningProvider;
import org.eclipse.jface.text.codemining.CodeMiningReconciler;
import org.eclipse.jface.text.codemining.ICodeMining;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.texteditor.ITextEditor;

import com.sap.adt.tools.core.IAdtObjectReference;

import abap.codemining.element.AbapClassElementParser;
import abap.codemining.element.CdsViewElementParser;
import abap.codemining.feature.FeatureFacade;
import abap.codemining.general.AbapEditorCodeMining;
import abap.codemining.plugin.AbapCodeMiningPluginHelper;

public class AbapCodeMiningProvider extends AbstractCodeMiningProvider {

	AbapCodeMiningPluginHelper abapCodeMiningPluginHelper;
	private FeatureFacade featureFacade;
	private EditorFacade textEditorFacade;

	public AbapCodeMiningProvider() {
		abapCodeMiningPluginHelper = new AbapCodeMiningPluginHelper();
		featureFacade = new FeatureFacade();
		registerPreferencePropertyChangeListener();
	}

	@Override
	public CompletableFuture<List<? extends ICodeMining>> provideCodeMinings(ITextViewer viewer,
			IProgressMonitor monitor) {
		return CompletableFuture.supplyAsync(() -> {
			monitor.isCanceled();
			ITextEditor textEditor = super.getAdapter(ITextEditor.class);

			CodeMiningReconciler codeMiningReconciler = new CodeMiningReconciler();
			codeMiningReconciler.install(viewer);

			try {

				List<ICodeMining> minings = new ArrayList<>();
				collectMinings(textEditor, viewer, minings);
				return minings;

			} catch (JavaModelException e) {
				// TODO: what should we done when there are some errors?
			}

			return Collections.emptyList();
		});
	}

	private void registerPreferencePropertyChangeListener() {

		abapCodeMiningPluginHelper.getPreferenceStore().addPropertyChangeListener(event -> {
			featureFacade = new FeatureFacade();
		});

	}

	private void addViewerToReconciler(ITextViewer viewer) {
		if (featureFacade.getReconcilerFeature().isUpdateEnabled()) {
			CodeMiningReconciler codeMiningReconciler = abapCodeMiningPluginHelper.getCodeMiningReconciler();
			codeMiningReconciler.install(viewer);
		}
	}

	private void collectMinings(ITextEditor textEditor, ITextViewer viewer, List<ICodeMining> minings)
			throws JavaModelException {

		textEditorFacade = new EditorFacade(textEditor); 
		IAdtObjectReference adtObject = textEditorFacade.getAdtObject();

		if (textEditorFacade.getAbapProject() != null) {

			IAdtObjectReference adtObjectReference = textEditorFacade.getAdtObject();
			switch (adtObjectReference.getType()) {
			case "CLAS/I":
				AbapEditorCodeMining abapClassCodeMining = new AbapEditorCodeMining(textEditor, viewer, new AbapClassElementParser(featureFacade));
				abapClassCodeMining.evaluateCodeMinings(minings, this);
				break;
			case "DDLS/DF": 
				AbapEditorCodeMining cdsClassCodeMining = new AbapEditorCodeMining(textEditor, viewer, new CdsViewElementParser(featureFacade));
				cdsClassCodeMining.evaluateCodeMinings(minings, this);
			default:
				break;
			}

		}
	}
}