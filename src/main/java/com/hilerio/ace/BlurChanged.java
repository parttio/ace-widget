package com.hilerio.ace;

/**
 * @author David Dodlek
 */

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;

@DomEvent("editor-content")
public class BlurChanged extends ComponentEvent<AceEditor> {

	private String txtValue;
	private String selectionValue;

	public BlurChanged(AceEditor source, boolean fromClient, @EventData("event.detail.value") String txtValue, @EventData("event.detail.selection") String selectionValue) {
		super(source, fromClient);
		this.txtValue = txtValue;
		this.selectionValue = selectionValue;
	}

	public String getTxtValue() {
		return txtValue;
	}
	
	public String getSelectionValue() {
		return selectionValue;
	}

}
