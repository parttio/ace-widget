package com.hilerio;

import com.hilerio.ace.AceEditor;
import com.hilerio.ace.AceMode;
import com.hilerio.ace.AceTheme;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route("")
public class View extends Div {

	public View() {

		AceEditor aceEditor = new AceEditor();
		aceEditor.setValue("TEST");
		aceEditor.setMode(AceMode.pgsql);
		aceEditor.setSizeFull();

		add(aceEditor);
	}
}
