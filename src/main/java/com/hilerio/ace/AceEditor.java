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
	 * @param value String
	 */
	public void setValue(String value) {
		getElement().setProperty("value", value);
	}

	/**
	 * Sets font-size for the editor in pixels.
	 * 
	 * @param value Integer
	 */
	public void setFontSize(Integer value) {
		getElement().setAttribute("font-size", value + "px");
	}

	/**
	 * Sets softtabs for the editor.
	 * 
	 * @param value Boolean
	 */
	public void setSofttabs(Boolean value) {
		getElement().setAttribute("softtabs", String.valueOf(value));
	}

	/**
	 * Sets tab-size for the editor.
	 * 
	 * @param value Integer
	 */
	public void setTabSize(Integer value) {
		getElement().setAttribute("tab-size", String.valueOf(value));
	}

	/**
	 * Sets wrap for the editor.
	 * 
	 * @param wrap Boolean
	 */
	public void setWrap(Boolean wrap) {
		getElement().setAttribute("wrap", wrap);
	}

	/**
	 * Sets AutoComplete for the editor.
	 * 
	 * @param value Boolean
	 */
	public void setAutoComplete(Boolean value) {
		getElement().setProperty("autoComplete", String.valueOf(value));
	}

	/**
	 * Sets minlines for the editor.
	 * 
	 * @param minlines Integer
	 */
	public void setMinlines(Integer minlines) {
		getElement().setAttribute("minlines", String.valueOf(minlines));
	}

	/**
	 * Sets maxlines for the editor.
	 * 
	 * @param maxlines Integer
	 */
	public void setMaxlines(Integer maxlines) {
		getElement().setAttribute("maxlines", String.valueOf(maxlines));
	}

	/**
	 * Sets initialFocus for the editor.
	 * 
	 * @param value Boolean
	 */
	public void setInitialFocus(Boolean value) {
		getElement().setAttribute("initialFocus", value);
	}

	/**
	 * Sets placeholder for the editor.
	 * 
	 * @param value String
	 */
	public void setPlaceholder(String value) {
		getElement().setAttribute("placeholder", value);
	}

	/**
	 * Sets readOnly for the editor.
	 * 
	 * @param value Boolean
	 */ 
	public void setReadOnly(Boolean value) {
		getElement().setAttribute("readonly", value);
	}

	/**
	 * Sets height in px/pixel or percent
	 * 
	 * @param height String
	 */
	public void setHeight(String height) {
		getElement().getStyle().set("max-height", height);
	};

	/**
	 * Sets width in px/pixel or percent
	 * 
	 * @param width String
	 */
	public void setWidth(String width) {
		getElement().getStyle().set("max-width", width);
	};

	/**
	 * Sets BasePath / BaseUrl
	 * 
	 * @param baseurl String
	 */
	public void setBasePath(String baseurl) {
		getElement().setProperty("baseUrl", baseurl);
	}

	/**
	 * Sets showPrintMargin for the editor.
	 * 
	 * @param value Boolean
	 */
	public void setShowPrintMargin(Boolean value) {
		getElement().setProperty("showPrintMargin", value);
	}

	/**
	 * Sets showInvisibles for the editor.
	 * 
	 * @param value Boolean
	 */
	public void setShowInvisibles(Boolean value) {
		getElement().setProperty("showInvisibles", value);
	}

	/**
	 * Sets showGutter for the editor.
	 * 
	 * @param value Boolean
	 */
	public void setShowGutter(Boolean value) {
		getElement().setProperty("showGutter", value);
	}

	/**
	 * Sets highlightActiveLine for the editor.
	 * 
	 * @param value Boolean
	 */
	public void setHighlightActiveLine(Boolean value) {
		getElement().setProperty("highlightActiveLine", value);
	}

	/**
	 * Sets displayIndentGuides for the editor.
	 * 
	 * @param value Boolean
	 */
	public void setDisplayIndentGuides(Boolean value) {
		getElement().setProperty("displayIndentGuides", value);
	}

	/**
	 * Sets highlightSelectedWord for the editor.
	 * 
	 * @param value Boolean
	 */
	public void setHighlightSelectedWord(Boolean value) {
		getElement().setProperty("highlightSelectedWord", value);
	}

	/**
	 * Sets selection for the editor.
	 * 
	 * @param from Integer
	 * @param to   Integer
	 */
	public void setSelection(Integer from, Integer to) {
		getElement().setProperty("selection", from + "|" + to);
	}

	/**
	 * Sets useWorker for the editor.
	 * 
	 * @param value Boolean
	 */
	public void setUseWorker(Boolean value) {
		getElement().setProperty("useWorker", value);
	}

	/**
	 * Sets cursorPosition for the editor.
	 * 
	 * @param pos Integer
	 */
	public void setCursorPosition(Integer pos) {
		getElement().setProperty("selection", pos + "|" + pos);
	}
	
	/**
	 * Sets liveAutocompletion for the editor.
	 * 
	 * @param value Boolean
	 */
	public void setLiveAutocompletion(Boolean value) {
		getElement().setProperty("enableLiveAutocompletion", value);
	}

}