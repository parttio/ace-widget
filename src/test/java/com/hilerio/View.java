package com.hilerio;

import com.hilerio.ace.AceEditor;
import com.hilerio.ace.AceMode;
import com.hilerio.ace.AceTheme;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class View extends VerticalLayout {

	public View() {
		
		setSizeFull();

		AceEditor aceEditor = new AceEditor();

		aceEditor.setValue("TEST");
		aceEditor.setTheme(AceTheme.terminal);
		aceEditor.setMode(AceMode.java);
		aceEditor.setFontSize(20);
		aceEditor.setHeight("100%");
		aceEditor.setWidth("100%");
//		aceEditor.setReadOnly(false);
//		aceEditor.setBasePath();
//		aceEditor.setHighlightActiveLine(false);
//		aceEditor.setShowInvisibles(true);
//		aceEditor.setShowGutter(false);
//		aceEditor.setShowPrintMargin(false);

//		aceEditor.addValueChangeListener(e -> {
//			System.out.println(aceEditor.getValue());
//			System.out.println("*****");
//		});
//
//		aceEditor.setSofttabs(false);
//		aceEditor.setTabSize(25);
//		aceEditor.setWrap(false);
//		aceEditor.setMinlines(2);
//		aceEditor.setMaxlines(10);
//		aceEditor.setPlaceholder("DEMO");
//
//		aceEditor.addFocusListener(e -> {
//		 System.out.println("Focus");
//		});
		
		add(aceEditor, new Button("demo", e -> {
			System.out.println(aceEditor.getValue());
		}));
		
		expand(aceEditor);
	}
}
