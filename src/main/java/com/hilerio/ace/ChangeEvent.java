package com.hilerio.ace;

/**
 * Sergio Alberto Hilerio.
 */

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;

@DomEvent("editor-content")
public class ChangeEvent extends ComponentEvent<AceEditor> {

    private String value;

    public ChangeEvent(AceEditor source, boolean fromClient, @EventData("event.detail.value") String value) {
        super(source, fromClient);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}