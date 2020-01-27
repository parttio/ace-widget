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
		aceEditor.setTheme(AceTheme.monokai);
		aceEditor.setMode(AceMode.xml);
		//aceEditor.setHeight("500px");
		//aceEditor.setWidth("500px");
		aceEditor.setFontSize(20);
		aceEditor.setReadOnly(false);
		
		aceEditor.addValueChangeListener(e ->{
			//System.out.println(e.getValue());
			System.out.println("*****");
		});

		// aceEditor.setSofttabs(false);
		// aceEditor.setTabSize(25);
		// aceEditor.setWrap(false);
		// aceEditor.setMinlines(2);
		// aceEditor.setMaxlines(10);
		// aceEditor.setPlaceholder("DEMO");

		// aceEditor.addFocusListener(e -> {
		// System.out.println("Focus");
		// });

		add(aceEditor, new Button("demo", e->{System.out.println(aceEditor.getValue());}));

	}
}
