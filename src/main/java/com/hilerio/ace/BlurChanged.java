package com.hilerio.ace;

/**
 * @author David Dodlek
 */

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;

@DomEvent("editor-content")
public class BlurChanged extends ComponentEvent<AceEditor> {

	private String value;

	public BlurChanged(AceEditor source, boolean fromClient, @EventData("event.detail.value") String value) {
		super(source, fromClient);
		source.getElement().setProperty("selection", "-|-"); // Reset the selection so the selection can be set to the same index again
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
