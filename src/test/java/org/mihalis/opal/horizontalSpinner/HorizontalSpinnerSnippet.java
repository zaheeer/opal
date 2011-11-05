/*******************************************************************************
 * Copyright (c) 2011 Laurent CARON.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Laurent CARON (laurent.caron@gmail.com) - initial API and implementation
 *******************************************************************************/
package org.mihalis.opal.horizontalSpinner;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.ToolTip;

/**
 * A simple snipper for the Horizontal Spinner widget
 * 
 */
public class HorizontalSpinnerSnippet {

	public static void main(final String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(2, true));

		createSpinnerGroup(shell);
		createHorizontalSpinnerGroup(shell);

		shell.pack();
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();

	}

	private static void createSpinnerGroup(final Shell shell) {
		final Group group = new Group(shell, SWT.NONE);
		group.setLayout(new GridLayout(1, false));

		final Label lbl1 = new Label(group, SWT.NONE);
		lbl1.setText("Simple vertical spinner :");
		final Spinner spinner1 = new Spinner(group, SWT.BORDER);
		spinner1.setMinimum(0);
		spinner1.setMaximum(1000);
		spinner1.setSelection(500);
		spinner1.setIncrement(1);
		spinner1.setPageIncrement(100);

		final Label lbl2 = new Label(group, SWT.NONE);
		lbl2.setText("Floating point values in Spinner :");
		final Spinner spinner2 = new Spinner(group, SWT.NONE);
		// allow 3 decimal places
		spinner2.setDigits(3);
		// set the minimum value to 0.001
		spinner2.setMinimum(1);
		// set the maximum value to 20
		spinner2.setMaximum(20000);
		// set the increment value to 0.010
		spinner2.setIncrement(10);
		// set the seletion to 3.456
		spinner2.setSelection(3456);
		spinner2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				final int selection = spinner2.getSelection();
				final int digits = spinner2.getDigits();
				System.out.println("Selection is " + selection / Math.pow(10, digits));
			}
		});

		final Label lbl3 = new Label(group, SWT.NONE);
		lbl3.setText("Validate input in a spinner widget :");
		final Spinner spinner3 = new Spinner(group, SWT.BORDER);
		spinner3.setValues(0, -100, 100, 0, 1, 10);
		spinner3.setLayoutData(new GridData(200, SWT.DEFAULT));
		final ToolTip toolTip = new ToolTip(shell, SWT.BALLOON | SWT.ICON_WARNING);
		spinner3.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(final ModifyEvent e) {
				final String string = spinner3.getText();
				String message = null;
				try {
					final int value = Integer.parseInt(string);
					final int maximum = spinner3.getMaximum();
					final int minimum = spinner3.getMinimum();
					if (value > maximum) {
						message = "Current input is greater than the maximum limit (" + maximum + ")";
					} else if (value < minimum) {
						message = "Current input is less than the minimum limit (" + minimum + ")";
					}
				} catch (final Exception ex) {
					message = "Current input is not numeric";
				}
				if (message != null) {
					spinner3.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
					final Rectangle rect = spinner3.getBounds();
					final GC gc = new GC(spinner3);
					final Point pt = gc.textExtent(string);
					gc.dispose();
					toolTip.setLocation(shell.getDisplay().map(shell, null, rect.x + pt.x, rect.y + rect.height));
					toolTip.setMessage(message);
					toolTip.setVisible(true);
				} else {
					toolTip.setVisible(false);
					spinner3.setForeground(null);
				}
			}
		});

	}

	private static void createHorizontalSpinnerGroup(final Shell shell) {
		final Group group = new Group(shell, SWT.NONE);
		group.setLayout(new GridLayout(1, false));

		final Label lbl1 = new Label(group, SWT.NONE);
		lbl1.setText("Simple horizontal spinner :");
		final HorizontalSpinner spinner1 = new HorizontalSpinner(group, SWT.BORDER);
		spinner1.setMinimum(0);
		spinner1.setMaximum(1000);
		spinner1.setSelection(500);
		spinner1.setIncrement(1);
		spinner1.setPageIncrement(100);

		final Label lbl2 = new Label(group, SWT.NONE);
		lbl2.setText("Floating point values in Spinner :");
		final HorizontalSpinner spinner2 = new HorizontalSpinner(group, SWT.NONE);
		// allow 3 decimal places
		spinner2.setDigits(3);
		// set the minimum value to 0.001
		spinner2.setMinimum(1);
		// set the maximum value to 20
		spinner2.setMaximum(20000);
		// set the increment value to 0.010
		spinner2.setIncrement(10);
		// set the seletion to 3.456
		spinner2.setSelection(3456);
		spinner2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				final int selection = spinner2.getSelection();
				final int digits = spinner2.getDigits();
				System.out.println("Selection is " + selection / Math.pow(10, digits));
			}
		});

		final Label lbl3 = new Label(group, SWT.NONE);
		lbl3.setText("Validate input in a spinner widget :");
		final HorizontalSpinner spinner3 = new HorizontalSpinner(group, SWT.BORDER);
		spinner3.setValues(0, -100, 100, 0, 1, 10);
		spinner3.setLayoutData(new GridData(200, SWT.DEFAULT));
		final ToolTip toolTip = new ToolTip(shell, SWT.BALLOON | SWT.ICON_WARNING);
		spinner3.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(final ModifyEvent e) {
				final String string = spinner3.getText();
				String message = null;
				try {
					final int value = Integer.parseInt(string);
					final int maximum = spinner3.getMaximum();
					final int minimum = spinner3.getMinimum();
					if (value > maximum) {
						message = "Current input is greater than the maximum limit (" + maximum + ")";
					} else if (value < minimum) {
						message = "Current input is less than the minimum limit (" + minimum + ")";
					}
				} catch (final Exception ex) {
					message = "Current input is not numeric";
				}
				if (message != null) {
					spinner3.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));
					final Rectangle rect = spinner3.getBounds();
					final GC gc = new GC(spinner3);
					final Point pt = gc.textExtent(string);
					gc.dispose();
					toolTip.setLocation(shell.getDisplay().map(group, null, rect.x + pt.x, rect.y + rect.height));
					toolTip.setMessage(message);
					toolTip.setVisible(true);
				} else {
					toolTip.setVisible(false);
					spinner3.setForeground(null);
				}
			}
		});
	}
}