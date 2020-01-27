package com.hilerio.ace;

/**
 * Sergio Alberto Hilerio.
 */

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;

@DomEvent("editor-content")
public class ChangeEvent
        extends ComponentEvent<AceEditor> {
    public ChangeEvent(AceEditor source,
                       boolean fromClient) {
        super(source, fromClient);
    }
}