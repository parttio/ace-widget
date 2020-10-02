package com.hilerio.ace;

import com.vaadin.flow.component.AbstractSinglePropertyField;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Focusable;
import com.vaadin.flow.component.Synchronize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.polymertemplate.Id;

/**
 * @author: Sergio Alberto Hilerio.
 */
@SuppressWarnings("serial")
@Tag("ace-widget")
@NpmPackage(value = "@granite-elements/ace-widget", version = "2.2.7-b1")
@JsModule("./@granite-elements/ace-widget/ace-widget.js")
public class AceEditor extends AbstractSinglePropertyField<AceEditor, String> implements Focusable<AceEditor> {

	@Id("editor")
	private Div editor;

	public AceEditor() {
		super("value", "", false);
	}

	@Override
	protected void onAttach(AttachEvent attachEvent) {
		super.onAttach(attachEvent);
		addListener(BlurChanged.class, this::updateText);
	}
	
	// Updates the Text after the Blur event has been fired (Keyboard lost focus)
	private void updateText(BlurChanged event) {
		setValue(event.getValue());
	}

	/**
	 * Sets the mode(language) of the editor.
	 *
	 * @param mode mode(language)
	 */
	public void setMode(AceMode mode) {
		getElement().setAttribute("mode", "ace/mode/" + mode);
	}

	/**
	 * Sets the theme (style) of the editor.
	 *
	 * @param theme theme(style)
	 */
	public void setTheme(AceTheme theme) {
		getElement().setAttribute("theme", "ace/theme/" + theme);
	}

	/**
	 * Cleans the value contained in the editor.
	 */
	public void clear() {
		getElement().setProperty("value", "");
	}

	/**
	 * Sets value for the editor.
	 * 
	 * @return
	 */
	public void setValue(String value) {
		getElement().setProperty("value", value);
	}

	/**
	 * Sets font-size for the editor in pixels.
	 */
	public void setFontSize(Integer value) {
		getElement().setAttribute("font-size", value + "px");
	}

	/**
	 * Sets softtabs for the editor.
	 */
	public void setSofttabs(boolean value) {
		getElement().setAttribute("softtabs", String.valueOf(value));
	}

	/**
	 * Sets tab-size for the editor.
	 */
	public void setTabSize(Integer value) {
		getElement().setAttribute("tab-size", String.valueOf(value));
	}

	/**
	 * Sets wrap for the editor.
	 */
	public void setWrap(Boolean wrap) {
		getElement().setAttribute("wrap", wrap);
	}

	// /**
	// * Sets AutoComplete for the editor.
	// */
	// public void setAutoComplete(Boolean value) {
	// getElement().setProperty("autoComplete", String.valueOf(value));
	// }

	/**
	 * Sets minlines for the editor.
	 */
	public void setMinlines(Integer minlines) {
		getElement().setAttribute("minlines", String.valueOf(minlines));
	}

	/**
	 * Sets maxlines for the editor.
	 */
	public void setMaxlines(Integer maxlines) {
		getElement().setAttribute("maxlines", String.valueOf(maxlines));
	}

	/**
	 * Sets initialFocus for the editor.
	 */
	public void setInitialFocus(Boolean value) {
		getElement().setAttribute("initialFocus", value);
	}

	/**
	 * Sets placeholder for the editor.
	 */
	public void setPlaceholder(String value) {
		getElement().setAttribute("placeholder", value);
	}

	/**
	 * Sets readOnly for the editor.
	 */
	public void setReadOnly(boolean value) {
		getElement().setAttribute("readonly", value);
	}

	/**
	 * Sets height in px
	 * 
	 * @param height
	 */
	public void setHeight(String height) {
		getElement().getStyle().set("min-height", height);
	};

	/**
	 * Sets width in px or pixels
	 * 
	 * @param width
	 */
	public void setWidth(String width) {
		getElement().getStyle().set("max-width", width);
	};
	
	/**
	 * Sets BasePath / BaseUrl
	 * 
	 * @param baseurl
	 */
	public void setBasePath(String baseurl) {
		getElement().setAttribute("baseUrl", baseurl);
	}
}
