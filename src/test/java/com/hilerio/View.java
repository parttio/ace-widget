package com.hilerio;

import com.hilerio.ace.AceEditor;
import com.hilerio.ace.AceMode;
import com.hilerio.ace.AceTheme;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class View extends VerticalLayout {

	public View() {

		HorizontalLayout layoutComboBoxes = new HorizontalLayout();

		ComboBox<AceTheme> themesComboBox = new ComboBox<>();
		themesComboBox.setItems(AceTheme.values());
		themesComboBox.setLabel("Themes");

		ComboBox<AceMode> modesComboBox = new ComboBox<>();
		modesComboBox.setItems(AceMode.values());
		modesComboBox.setLabel("Modes");

		layoutComboBoxes.add(themesComboBox, modesComboBox);

		setSizeFull();

		AceEditor aceEditor = new AceEditor();

		aceEditor.setValue("TEST");
//		aceEditor.setTheme(AceTheme.github);
//		aceEditor.setMode(AceMode.java);
		aceEditor.setFontSize(20);
		aceEditor.setWidth("100%");
		aceEditor.setHeight("100%");
//		aceEditor.setReadOnly(false);
//		aceEditor.setBasePath();
//		aceEditor.setHighlightActiveLine(false);
//		aceEditor.setShowInvisibles(true);
//		aceEditor.setShowGutter(false);
//		aceEditor.setShowPrintMargin(false);
//		aceEditor.setDisplayIndentGuides(false);
//		aceEditor.setHighlightSelectedWord(false);
//		aceEditor.setUseWorker(false);
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

		add(layoutComboBoxes, aceEditor, new Button("demo", e -> {
//			System.out.println(aceEditor.getValue());

//			aceEditor.setCursorPosition(8);
//			aceEditor.setSelection(0, 5);
		}));

		expand(aceEditor);

		themesComboBox.addValueChangeListener(event -> {
			if (event.getValue() != null) {
				aceEditor.setTheme(event.getValue());
			}
		});

		modesComboBox.addValueChangeListener(event -> {
			if (event.getValue() != null) {
				aceEditor.setMode(event.getValue());
			}
		});
	}
}
