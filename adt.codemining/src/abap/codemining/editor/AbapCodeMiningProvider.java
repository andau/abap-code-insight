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
import org.eclipse.ui.texteditor.ITextEditor;

import abap.codemining.general.AbapEditorCodeMining;
import abap.codemining.plugin.AbapCodeMiningPluginHelper;

public class AbapCodeMiningProvider extends AbstractCodeMiningProvider {

	AbapCodeMiningPluginHelper abapCodeMiningPluginHelper;

	public AbapCodeMiningProvider() {
		abapCodeMiningPluginHelper = new AbapCodeMiningPluginHelper();
	}

	@Override
	public CompletableFuture<List<? extends ICodeMining>> provideCodeMinings(ITextViewer viewer,
			IProgressMonitor monitor) {
		return CompletableFuture.supplyAsync(() -> {
			monitor.isCanceled();
			ITextEditor textEditor = super.getAdapter(ITextEditor.class);

			addViewerToReconciler(viewer);

			try {
				List<ICodeMining> minings = new ArrayList<>();
				collectMinings(textEditor, minings);
				monitor.isCanceled();
				return minings;

			} catch (JavaModelException e) {
				// TODO: what should we done when there are some errors?
			}

			return Collections.emptyList();
		});
	}

	private void addViewerToReconciler(ITextViewer viewer) {
		//CodeMiningReconciler codeMiningReconciler = abapCodeMiningPluginHelper.getCodeMiningReconciler();
		//codeMiningReconciler.install(viewer);

	}

	private void collectMinings(ITextEditor textEditor, List<ICodeMining> minings) throws JavaModelException {

		if (textEditor.getTitle().contains("ZCL")) {

			AbapEditorCodeMining abapClassCodeMining = new AbapEditorCodeMining(textEditor);
			abapClassCodeMining.evaluateCodeMinings(minings, this);
		}
	}
}