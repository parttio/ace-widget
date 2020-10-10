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
		
		setWidth("100%");
		setHeight("200px");
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
	 * @param value int
	 */
	public void setFontSize(int value) {
		getElement().setAttribute("font-size", value + "px");
	}

	/**
	 * Sets softtabs for the editor.
	 * 
	 * @param value boolean
	 */
	public void setSofttabs(boolean value) {
		getElement().setAttribute("softtabs", String.valueOf(value));
	}

	/**
	 * Sets tab-size for the editor.
	 * 
	 * @param value int
	 */
	public void setTabSize(int value) {
		getElement().setAttribute("tab-size", String.valueOf(value));
	}

	/**
	 * Sets wrap for the editor.
	 * 
	 * @param wrap boolean
	 */
	public void setWrap(boolean wrap) {
		getElement().setAttribute("wrap", wrap);
	}

	/**
	 * Sets AutoComplete for the editor.
	 * 
	 * @param value boolean
	 */
	public void setAutoComplete(boolean value) {
		getElement().setProperty("autoComplete", String.valueOf(value));
	}

	/**
	 * Sets minlines for the editor.
	 * 
	 * @param minlines int
	 */
	public void setMinlines(int minlines) {
		getElement().setAttribute("minlines", String.valueOf(minlines));
	}

	/**
	 * Sets maxlines for the editor.
	 * 
	 * @param maxlines int
	 */
	public void setMaxlines(int maxlines) {
		getElement().setAttribute("maxlines", String.valueOf(maxlines));
	}

	/**
	 * Sets initialFocus for the editor.
	 * 
	 * @param value boolean
	 */
	public void setInitialFocus(boolean value) {
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
	 * @param value boolean
	 */
	public void setReadOnly(boolean value) {
		getElement().setAttribute("readonly", value);
	}

	/**
	 * Sets height in px/pixel or percent
	 * 
	 * @param height String
	 */
	public void setHeight(String height) {
		getElement().getStyle().set("height", height);
	};

	/**
	 * Sets max-height in px/pixel or percent
	 * 
	 * @param height String
	 */
	public void setMaxHeight(String height) {
		getElement().getStyle().set("max-height", height);
	};

	/**
	 * Sets min-height in px/pixel or percent
	 * 
	 * @param height String
	 */
	public void setMinHeight(String height) {
		getElement().getStyle().set("min-height", height);
	};

	/**
	 * Sets width in px/pixel or percent
	 * 
	 * @param width String
	 */
	public void setWidth(String width) {
		getElement().getStyle().set("width", width);
	};

	/**
	 * Sets max-width in px/pixel or percent
	 * 
	 * @param width String
	 */
	public void setMaxWidth(String width) {
		getElement().getStyle().set("max-width", width);
	};

	/**
	 * Sets min-width in px/pixel or percent
	 * 
	 * @param width String
	 */
	public void setMinWidth(String width) {
		getElement().getStyle().set("min-width", width);
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
	 * @param value boolean
	 */
	public void setShowPrintMargin(boolean value) {
		getElement().setProperty("showPrintMargin", value);
	}

	/**
	 * Sets showInvisibles for the editor.
	 * 
	 * @param value boolean
	 */
	public void setShowInvisibles(boolean value) {
		getElement().setProperty("showInvisibles", value);
	}

	/**
	 * Sets showGutter for the editor.
	 * 
	 * @param value boolean
	 */
	public void setShowGutter(boolean value) {
		getElement().setProperty("showGutter", value);
	}

	/**
	 * Sets highlightActiveLine for the editor.
	 * 
	 * @param value boolean
	 */
	public void setHighlightActiveLine(boolean value) {
		getElement().setProperty("highlightActiveLine", value);
	}

	/**
	 * Sets displayIndentGuides for the editor.
	 * 
	 * @param value boolean
	 */
	public void setDisplayIndentGuides(boolean value) {
		getElement().setProperty("displayIndentGuides", value);
	}

	/**
	 * Sets highlightSelectedWord for the editor.
	 * 
	 * @param value boolean
	 */
	public void setHighlightSelectedWord(boolean value) {
		getElement().setProperty("highlightSelectedWord", value);
	}

	/**
	 * Sets selection for the editor.
	 * 
	 * @param from int
	 * @param to   int
	 */
	public void setSelection(int from, int to) {
		getElement().setProperty("selection", from + "|" + to);
	}

	/**
	 * Sets useWorker for the editor.
	 * 
	 * @param value boolean
	 */
	public void setUseWorker(boolean value) {
		getElement().setProperty("useWorker", value);
	}

	/**
	 * Sets cursorPosition for the editor.
	 * 
	 * @param pos int
	 */
	public void setCursorPosition(int pos) {
		getElement().setProperty("selection", pos + "|" + pos);
	}

	/**
	 * Sets liveAutocompletion for the editor.
	 * 
	 * @param value boolean
	 */
	public void setLiveAutocompletion(boolean value) {
		getElement().setProperty("enableLiveAutocompletion", value);
	}

}