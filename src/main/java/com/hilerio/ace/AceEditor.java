package com.hilerio.ace;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.vaadin.flow.component.AbstractSinglePropertyField;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Focusable;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.template.Id;

/**
 * @author Sergio Alberto Hilerio
 * @author David Dodlek
 */
@SuppressWarnings("serial")
@Tag("ace-widget")
@NpmPackage(value = "@f0rce/ace-widget", version = "1.0.2")
@JsModule("./@f0rce/ace-widget/ace-widget.js")
public class AceEditor extends AbstractSinglePropertyField<AceEditor, String> implements Focusable<AceEditor> {

	@Id("editor")
	private Div editor;

	private AceTheme editorTheme = AceTheme.eclipse;
	private AceMode editorMode = AceMode.javascript;
	private String fontSize = "14px";
	private boolean softTabs = true;
	private int tabSize = 4;
	private boolean wrap = false;
	private int minLines = 15;
	private int maxLines = Integer.MAX_VALUE;
	private String basePath = "";
	private boolean autoComplete = false;
	private boolean initialFocus = false;
	private String placeHolder = "";
	private boolean readOnly = false;
	private boolean printMargin = false;
	private boolean showInvisibles = false;
	private boolean showGutter = true;
	private boolean hightlightActiveLine = true;
	private boolean displayIndentGuides = false;
	private boolean highlightSelectedWord = false;
	private int[] selection = new int[] { 0, 0, 0, 0 };
	private int[] cursorPosition = new int[] { 0, 0 };
	private boolean useWorker = false;
	private boolean liveAutocompletion = false;
	private boolean enableSnippets = true;
	private String[] customAutoCompletion = new String[0];
	private List<AceMarker> markers = new ArrayList<>();

	public AceEditor() {
		super("value", "", false);
		super.addListener(BlurChanged.class, this::updateText);

		setWidth("100%");
		setHeight("300px");
	};

	@Override
	protected void onAttach(AttachEvent attachEvent) {
		super.onAttach(attachEvent);
		refresh();
	}

	// Updates the Text and selection after the Blur event has been fired (Keyboard
	// lost focus)
	private void updateText(BlurChanged event) {
		setValue(event.getTxtValue());
		updateSelection(event);
	};

	// Updates the private variables to ensure that client and server are up to date
	private void updateSelection(BlurChanged event) {
		String[] split = event.getSelectionValue().split("\\|");
		int rowStart = Integer.parseInt(split[0]);
		int from = Integer.parseInt(split[1]);
		int rowEnd = Integer.parseInt(split[2]);
		int to = Integer.parseInt(split[3]);

		this.cursorPosition = new int[] { rowEnd, to };
		this.selection = new int[] { rowStart, from, rowEnd, to };
	};

	// Refreshes the whole editor because sometimes some settings are lost
	private void refresh() {
		getElement().callJsFunction("refreshEditor");
	};

	/**
	 * Sets the mode(language) of the editor.
	 *
	 * @param mode AceMode
	 */
	public void setMode(AceMode mode) {
		getElement().setProperty("mode", mode.toString());
		this.editorMode = mode;
	};

	/**
	 * Returns the current set mode for the editor.
	 * 
	 * @return AceMode
	 */
	public AceMode getMode() {
		return this.editorMode;
	};

	/**
	 * Sets the theme (style) of the editor.
	 *
	 * @param theme AceTheme
	 */
	public void setTheme(AceTheme theme) {
		getElement().setProperty("theme", theme.toString());
	};

	/**
	 * Returns the current set theme for the editor.
	 * 
	 * @return AceTheme
	 */
	public AceTheme getTheme() {
		return this.editorTheme;
	};

	/**
	 * Cleans the value contained in the editor.
	 */
	public void clear() {
		getElement().setProperty("value", "");
	};

	/**
	 * Sets value for the editor.
	 * 
	 * @param value String
	 */
	public void setValue(String value) {
		getElement().setProperty("value", value);
		this.blur(); // To force the exchange between client and server.
	};

	/**
	 * Sets font-size for the editor in pixels.
	 * 
	 * @param value int
	 */
	public void setFontSize(int value) {
		getElement().setProperty("fontSize", value + "px");
		this.fontSize = value + "px";
	};

	/**
	 * Returns the current set font-size of the editor in pixels.
	 * 
	 * @return String
	 */
	public String getFontSize() {
		return this.fontSize;
	};

	/**
	 * Sets softtabs for the editor.
	 * 
	 * @param value boolean
	 */
	public void setSofttabs(boolean value) {
		getElement().setProperty("softtabs", value);
		this.softTabs = value;
	};

	/**
	 * Returns if softtabs are currently enabled/disabled for the editor.
	 * 
	 * @return boolean
	 */
	public boolean isSofttabs() {
		return this.softTabs;
	};

	/**
	 * Sets tab-size for the editor.
	 * 
	 * @param value int
	 */
	public void setTabSize(int value) {
		getElement().setProperty("tabSize", String.valueOf(value));
		this.tabSize = value;
	};

	/**
	 * Returns the current set tab-size for the editor.
	 * 
	 * @return int
	 */
	public int getTabSize() {
		return this.tabSize;
	};

	/**
	 * Sets wrap for the editor.
	 * 
	 * @param wrap boolean
	 */
	public void setWrap(boolean wrap) {
		getElement().setProperty("wrap", wrap);
		this.wrap = wrap;
	};

	/**
	 * Returns if wrap is enabled/disabled for the editor.
	 * 
	 * @return boolean
	 */
	public boolean isWrap() {
		return this.wrap;
	};

	/**
	 * Sets AutoComplete for the editor.
	 * 
	 * @param value boolean
	 */
	public void setAutoComplete(boolean value) {
		getElement().setProperty("enableAutocompletion", value);
		this.autoComplete = value;
	};

	/**
	 * Returns if autocomplete is enabled/disabled for the editor.
	 * 
	 * @return boolean
	 */
	public boolean isAutoComplete() {
		return this.autoComplete;
	};

	/**
	 * Sets minlines for the editor.
	 * 
	 * @param minlines int
	 */
	public void setMinlines(int minlines) {
		getElement().setProperty("minlines", String.valueOf(minlines));
		this.minLines = minlines;
	};

	/**
	 * Returns the minimum set lines for the editor.
	 * 
	 * @return int
	 */
	public int getMinLines() {
		return this.minLines;
	};

	/**
	 * Sets maxlines for the editor.
	 * 
	 * @param maxlines int
	 */
	public void setMaxlines(int maxlines) {
		getElement().setProperty("maxlines", String.valueOf(maxlines));
		this.maxLines = maxlines;
	};

	/**
	 * Return the maximum lines set for the editor.
	 * 
	 * @return int
	 */
	public int getMaxLines() {
		return this.maxLines;
	};

	/**
	 * Sets initialFocus for the editor.
	 * 
	 * @param value boolean
	 */
	public void setInitialFocus(boolean value) {
		getElement().setProperty("initialFocus", value);
		this.initialFocus = value;
	};

	/**
	 * Returns if initial focus is enabled/disabled for the editor.
	 * 
	 * @return boolean
	 */
	public boolean isInitialFocus() {
		return this.initialFocus;
	};

	/**
	 * Sets placeholder for the editor.
	 * 
	 * @param value String
	 */
	public void setPlaceholder(String value) {
		getElement().setProperty("placeholder", value);
		this.placeHolder = value;
	};

	/**
	 * Returns the placeholder set for the editor.
	 * 
	 * @return String
	 */
	public String getPlaceholder() {
		return this.placeHolder;
	};

	/**
	 * Sets readOnly for the editor.
	 * 
	 * @param value boolean
	 */
	public void setReadOnly(boolean value) {
		getElement().setProperty("readonly", value);
		this.readOnly = value;
	};

	/**
	 * Returns if readOnly is enabled/disabled for the editor.
	 * 
	 * @return boolean
	 */
	public boolean isReadOnly() {
		return this.readOnly;
	};

	/**
	 * Sets height in px/pixel or percent.
	 * 
	 * @param height String
	 */
	public void setHeight(String height) {
		getElement().getStyle().set("height", height);
	};

	/**
	 * Returns the height set for the editor in px/pixel or percent.
	 * 
	 * @return String
	 */
	public String getHeight() {
		return getElement().getStyle().get("height");
	};

	/**
	 * Sets the height to 100%.
	 */
	public void setHeightFull() {
		getElement().getStyle().set("height", "100%");
	};

	/**
	 * Sets max-height in px/pixel or percent.
	 * 
	 * @param height String
	 */
	public void setMaxHeight(String height) {
		getElement().getStyle().set("max-height", height);
	};

	/**
	 * Returns the max-height set for the editor in px/pixel or percent.
	 * 
	 * @return String
	 */
	public String getMaxHeight() {
		return getElement().getStyle().get("max-height");
	};

	/**
	 * Sets min-height in px/pixel or percent.
	 * 
	 * @param height String
	 */
	public void setMinHeight(String height) {
		getElement().getStyle().set("min-height", height);
	};

	/**
	 * Returns the min-height set for the editor in px/pixel or percent.
	 * 
	 * @return String
	 */
	public String getMinHeight() {
		return getElement().getStyle().get("min-height");
	};

	/**
	 * Sets width in px/pixel or percent.
	 * 
	 * @param width String
	 */
	public void setWidth(String width) {
		getElement().getStyle().set("width", width);
	};

	/**
	 * Sets the width to 100%.
	 */
	public void setWidthFull() {
		getElement().getStyle().set("width", "100%");
	};

	/**
	 * Returns the width set for the editor in px/pixel or percent.
	 * 
	 * @return String
	 */
	public String getWidth() {
		return getElement().getStyle().get("width");
	};

	/**
	 * Sets max-width in px/pixel or percent.
	 * 
	 * @param width String
	 */
	public void setMaxWidth(String width) {
		getElement().getStyle().set("max-width", width);
	};

	/**
	 * Returns the max-width set for the editor in px/pixel or percent.
	 * 
	 * @return String
	 */
	public String getMaxWidth() {
		return getElement().getStyle().get("max-width");
	};

	/**
	 * Sets min-width in px/pixel or percent.
	 * 
	 * @param width String
	 */
	public void setMinWidth(String width) {
		getElement().getStyle().set("min-width", width);
	};

	/**
	 * Returns the min-width set for the editor in px/pixel or percent.
	 * 
	 * @return String
	 */
	public String getMinWidth() {
		return getElement().getStyle().get("min-width");
	};

	/**
	 * Sets the height and width to 100%.
	 */
	public void setSizeFull() {
		getElement().getStyle().set("width", "100%");
		getElement().getStyle().set("height", "100%");
	};

	/**
	 * Sets BasePath / BaseUrl.
	 * 
	 * @param baseurl String
	 */
	public void setBasePath(String baseurl) {
		getElement().setProperty("baseUrl", baseurl);
		this.basePath = baseurl;

	};

	/**
	 * Return the current BasePath / BaseUrl.
	 * 
	 * @return String
	 */
	public String getBasePath() {
		return this.basePath;
	};

	/**
	 * Sets showPrintMargin for the editor.
	 * 
	 * @param value boolean
	 */
	public void setShowPrintMargin(boolean value) {
		getElement().setProperty("showPrintMargin", value);
		this.printMargin = value;
	};

	/**
	 * Returns if showPrintMargin is enabled/disabled for the editor.
	 * 
	 * @return boolean
	 */
	public boolean isShowPrintMargin() {
		return this.printMargin;
	};

	/**
	 * Sets showInvisibles for the editor.
	 * 
	 * @param value boolean
	 */
	public void setShowInvisibles(boolean value) {
		getElement().setProperty("showInvisibles", value);
		this.showInvisibles = value;
	};

	/**
	 * Returns if showInvisibles is enabled/disabled for the editor.
	 * 
	 * @return boolean
	 */
	public boolean isShowInvisibles() {
		return this.showInvisibles;
	};

	/**
	 * Sets showGutter for the editor.
	 * 
	 * @param value boolean
	 */
	public void setShowGutter(boolean value) {
		getElement().setProperty("showGutter", value);
		this.showGutter = value;
	};

	/**
	 * Returns if showGutter is enabled/disabled for the editor.
	 * 
	 * @return boolean
	 */
	public boolean isShowGutter() {
		return this.showGutter;
	};

	/**
	 * Sets highlightActiveLine for the editor.
	 * 
	 * @param value boolean
	 */
	public void setHighlightActiveLine(boolean value) {
		getElement().setProperty("highlightActiveLine", value);
		this.hightlightActiveLine = value;
	};

	/**
	 * Returns if hightlightActiveLine is enabled/disabled for the editor.
	 * 
	 * @return boolean
	 */
	public boolean isHightlightActiveLine() {
		return this.hightlightActiveLine;
	};

	/**
	 * Sets displayIndentGuides for the editor.
	 * 
	 * @param value boolean
	 */
	public void setDisplayIndentGuides(boolean value) {
		getElement().setProperty("displayIndentGuides", value);
		this.displayIndentGuides = value;
	};

	/**
	 * Returns if displayIndentGuides is enabled/disabled for the editor.
	 * 
	 * @return boolean
	 */
	public boolean isDisplayIndentGuides() {
		return this.displayIndentGuides;
	};

	/**
	 * Sets highlightSelectedWord for the editor.
	 * 
	 * @param value boolean
	 */
	public void setHighlightSelectedWord(boolean value) {
		getElement().setProperty("highlightSelectedWord", value);
		this.highlightSelectedWord = value;
	};

	/**
	 * Returns if hightlightSelectedWord is enabled/disabled for the editor.
	 * 
	 * @return boolean
	 */
	public boolean isHightlightSelectedWord() {
		return this.highlightSelectedWord;
	};

	/**
	 * Sets selection for the editor.
	 * 
	 * @param from int
	 * @param to   int
	 */
	public void setSelection(int from, int to) {
		from = Math.abs(from);
		to = Math.abs(to);
		if (to < from) {
			int tmp = from;
			from = to;
			to = tmp;
		}
		getElement().setProperty("selection", 0 + "|" + from + "|" + 0 + "|" + to + "|" + UUID.randomUUID().toString());
		this.selection = new int[] { 0, from, 0, to };
		this.cursorPosition = new int[] { 0, to };
	};

	/**
	 * Sets selection for the editor and optionally also sets the focus.
	 * 
	 * @param from  int
	 * @param to    int
	 * @param focus boolean
	 */
	public void setSelection(int from, int to, boolean focus) {
		setSelection(from, to);
		if (focus) {
			this.focus();
		}
	};

	/**
	 * Sets selection for the editor.
	 * 
	 * @param rowStart int
	 * @param from     int
	 * @param rowEnd   int
	 * @param to       int
	 */
	public void setSelection(int rowStart, int from, int rowEnd, int to) {
		rowStart = Math.abs(rowStart);
		from = Math.abs(from);
		rowEnd = Math.abs(rowEnd);
		to = Math.abs(to);
		if (to < from) {
			int tmp = from;
			from = to;
			to = tmp;
		}
		if (rowEnd < rowStart) {
			int tmp = rowStart;
			rowStart = rowEnd;
			rowEnd = tmp;
		}
		getElement().setProperty("selection",
				rowStart + "|" + from + "|" + rowEnd + "|" + to + "|" + UUID.randomUUID().toString());
		this.selection = new int[] { rowStart, from, rowEnd, to };
		this.cursorPosition = new int[] { rowEnd, to };
	};

	/**
	 * Sets selection for the editor and optionally also sets the focus.
	 * 
	 * @param rowStart int
	 * @param from     int
	 * @param rowEnd   int
	 * @param to       int
	 * @param focus    boolean
	 */
	public void setSelection(int rowStart, int from, int rowEnd, int to, boolean focus) {
		setSelection(rowStart, from, rowEnd, to);
		if (focus) {
			this.focus();
		}
	};

	/**
	 * Returns an int array of the current selection where the first index
	 * represents "rowStart", the second index "from", the third index "rowEnd" and
	 * the forth index "to".
	 * 
	 * @return int[]
	 */
	public int[] getSelection() {
		return this.selection;
	};

	/**
	 * Sets useWorker for the editor.
	 * 
	 * @param value boolean
	 */
	public void setUseWorker(boolean value) {
		getElement().setProperty("useWorker", value);
		this.useWorker = value;
	};

	/**
	 * Returns if useWorker is enabled/disabled for the editor.
	 * 
	 * @return boolean
	 */
	public boolean isUseWorker() {
		return this.useWorker;
	};

	/**
	 * Sets cursorPosition for the editor.
	 * 
	 * @param pos int
	 */
	public void setCursorPosition(int pos) {
		pos = Math.abs(pos);
		if (pos > getValue().length() - 1) {
			pos = getValue().length() - 1;
		}
		getElement().setProperty("selection", 0 + "|" + pos + "|" + 0 + "|" + pos + "|" + UUID.randomUUID().toString());
		this.cursorPosition = new int[] { 0, pos };
		this.selection = new int[] { 0, pos, 0, pos };
	};

	/**
	 * Sets cursorPosition for the editor and optionally also sets the focus.
	 * 
	 * @param pos   int
	 * @param focus boolean
	 */
	public void setCursorPosition(int pos, boolean focus) {
		setCursorPosition(pos);
		if (focus) {
			this.focus();
		}
	};

	/**
	 * Returns the current set cursor position in the editor where the first index
	 * represents the line and the second index represents the position.
	 * 
	 * @return int[]
	 */
	public int[] getCursorPosition() {
		return this.cursorPosition;
	};

	/**
	 * Sets liveAutocompletion for the editor.
	 * 
	 * @param value boolean
	 */
	public void setLiveAutocompletion(boolean value) {
		getElement().setProperty("enableLiveAutocompletion", value);
		this.liveAutocompletion = value;
	};

	/**
	 * Returns if live autocompletion is enabled/disabled for the editor.
	 * 
	 * @return boolean
	 */
	public boolean isLiveAutocompletion() {
		return this.liveAutocompletion;
	};

	/**
	 * Sets enableSnippets for the editor.
	 * 
	 * @param value boolean
	 */
	public void setEnableSnippets(boolean value) {
		getElement().setProperty("enableSnippets", value);
		this.enableSnippets = value;
	};

	/**
	 * Returns if snippets are enabled/disabled for the editor.
	 * 
	 * @return boolean
	 */
	public boolean isEnableSnippets() {
		return this.enableSnippets;
	};

	/**
	 * Sets a custom autocompletion list for the editor.
	 * 
	 * @param wordList String[]
	 */
	public void setCustomAutoCompletion(String[] wordList) {
		if (wordList.length == 0) {
			this.customAutoCompletion = new String[0];
			return;
		}
		getElement().setProperty("customAutoCompletion", "|" + String.join(",", wordList));
		this.customAutoCompletion = wordList;
	};

	/**
	 * Sets a custom autocompletion list for the editor and sets the category aswell
	 * (default: "").
	 * 
	 * @param wordList String[]
	 * @param category String
	 */
	public void setCustomAutoCompletion(String[] wordList, String category) {
		if (wordList.length == 0) {
			this.customAutoCompletion = new String[0];
			return;
		}
		getElement().setProperty("customAutoCompletion", category + "|" + String.join(",", wordList));
		this.customAutoCompletion = wordList;
	};

	/**
	 * Returns a String array of the current custom autocompletion for the editor.
	 * 
	 * @return String[]
	 */
	public String[] getCustomAutoCompletion() {
		return this.customAutoCompletion;
	};

	/**
	 * Removes the custom autocompletion list set with setCustomAutoCompletiton()
	 * and replaces it with the default one.
	 */
	public void disableCustomAutoCompletion() {
		getElement().setProperty("customAutoCompletion", "|");
	};

	/**
	 * Adds text to a specific position of the editor.
	 * 
	 * @param text     String
	 * @param position int
	 */
	public void addTextAtPosition(String text, int position) {
		String currentVal = this.getValue();
		StringBuilder newVal = new StringBuilder();
		if (position > currentVal.length()) {
			position = currentVal.length() - 1;
		}
		for (int x = 0; x < currentVal.length(); x++) {
			newVal.append(currentVal.charAt(x));
			if (x == position) {
				newVal.append(text);
			}
		}
		this.setValue(newVal.toString());
		this.selection = new int[] { 0, this.getValue().length() - 1, 0, this.getValue().length() - 1 };
		this.cursorPosition = new int[] { 0, this.getValue().length() - 1 };
	};

	/**
	 * Adds text at the current position of the editor.<br>
	 * <br>
	 * <b>First Priority:</b> If there is a selection, the selection will be
	 * replaced.<br>
	 * <b>Second Priority:</b> If the cursor position has been set, the text will be
	 * added at the position of the cursor.<br>
	 * <b>Third/Last Priority:</b> If no text is selected and the cursor has not
	 * been set, the text will be added to the end of the text.<br>
	 * <br>
	 * 
	 * @param text String
	 */
	public void addTextAtCurrentPosition(String text) {
		String currentVal = this.getValue();
		if (this.selection != new int[] { 0, 0, 0, 0 }) {
			int from = this.selection[1];
			int to = this.selection[3];
			String toReplace = currentVal.substring(from, to);
			String newVal = currentVal.replace(toReplace, text);
			this.setValue(newVal);
			this.selection = new int[] { 0, 0, 0, 0 };
			this.cursorPosition = new int[] { 0, currentVal.length() - 1 };
		} else if (this.cursorPosition != new int[] { 0, 0 }) {
			addTextAtPosition(text, this.cursorPosition[1]);
			this.cursorPosition = new int[] { 0, currentVal.length() - 1 };
			this.selection = new int[] { 0, 0, 0, 0 };
		} else {
			addTextAtPosition(text, currentVal.length() - 1);
			this.cursorPosition = new int[] { 0, currentVal.length() - 1 };
			this.selection = new int[] { 0, 0, 0, 0 };
		}
	};

	/**
	 * Adds a marker to the editor at the current selection. If the returned string
	 * is null, there is no current selection. Use any addMarkerAtSelection() method
	 * instead. If the marker is not visible make sure that
	 * {@link #setHighlightActiveLine(boolean) setHightlightActiveLine()} and
	 * {@link #setHighlightSelectedWord(boolean) setHighlightSelectedWord} are set
	 * to false.
	 * 
	 * @param color AceMarkerColor
	 * 
	 * @return String
	 */
	public String addMarkerAtCurrentSelection(AceMarkerColor color) {
		if (this.selection != new int[] { 0, 0, 0, 0 }) {
			int[] currentSelection = this.selection;

			AceMarker marker = new AceMarker(currentSelection[0], currentSelection[1], currentSelection[2],
					currentSelection[3], color);

			getElement().setProperty("marker", marker.getRowStart() + "|" + marker.getFrom() + "|" + marker.getRowEnd()
					+ "|" + marker.getTo() + "|" + marker.getAceMarkerColor().toString() + "|" + marker.getID());
			this.markers.add(marker);
			return marker.getID();
		}
		return null;
	};

	/**
	 * Adds a marker to the editor at the current selection. If the returned string
	 * is null, there is no current selection. Use any addMarkerAtSelection() method
	 * instead. If the marker is not visible make sure that
	 * {@link #setHighlightActiveLine(boolean) setHightlightActiveLine()} and
	 * {@link #setHighlightSelectedWord(boolean) setHighlightSelectedWord} are set
	 * to false.
	 * 
	 * @param color AceMarkerColor
	 * @param alias String
	 * 
	 * @return String
	 */
	public String addMarkerAtCurrentSelection(AceMarkerColor color, String alias) {
		if (this.selection != new int[] { 0, 0, 0, 0 }) {
			int[] currentSelection = this.selection;

			AceMarker marker = new AceMarker(currentSelection[0], currentSelection[1], currentSelection[2],
					currentSelection[3], color, alias);

			getElement().setProperty("marker", marker.getRowStart() + "|" + marker.getFrom() + "|" + marker.getRowEnd()
					+ "|" + marker.getTo() + "|" + marker.getAceMarkerColor().toString() + "|" + marker.getID());
			this.markers.add(marker);
			return marker.getID();
		}
		return null;
	};

	/**
	 * Adds a marker to the editor. If the marker is not visible make sure that
	 * {@link #setHighlightActiveLine(boolean) setHightlightActiveLine()} and
	 * {@link #setHighlightSelectedWord(boolean) setHighlightSelectedWord} are set
	 * to false.
	 * 
	 * @param from  int
	 * @param to    int
	 * @param color AceMarkerColor
	 * 
	 * @return String
	 */
	public String addMarkerAtSelection(int from, int to, AceMarkerColor color) {
		from = Math.abs(from);
		to = Math.abs(to);
		if (to < from) {
			int tmp = from;
			from = to;
			to = tmp;
		}

		AceMarker marker = new AceMarker(from, to, color);

		getElement().setProperty("marker", 0 + "|" + marker.getFrom() + "|" + 0 + "|" + marker.getTo() + "|"
				+ marker.getAceMarkerColor().toString() + "|" + marker.getID());
		this.markers.add(marker);
		return marker.getID();
	};

	/**
	 * Adds a marker to the editor. If the marker is not visible make sure that
	 * {@link #setHighlightActiveLine(boolean) setHightlightActiveLine()} and
	 * {@link #setHighlightSelectedWord(boolean) setHighlightSelectedWord} are set
	 * to false.
	 * 
	 * @param rowStart int
	 * @param from     int
	 * @param rowEnd   int
	 * @param to       int
	 * @param color    AceMarkerColor
	 * 
	 * @return String
	 */
	public String addMarkerAtSelection(int rowStart, int from, int rowEnd, int to, AceMarkerColor color) {
		rowStart = Math.abs(rowStart);
		from = Math.abs(from);
		rowEnd = Math.abs(rowEnd);
		to = Math.abs(to);
		if (to < from) {
			int tmp = from;
			from = to;
			to = tmp;
		}
		if (rowEnd < rowStart) {
			int tmp = rowStart;
			rowStart = rowEnd;
			rowEnd = tmp;
		}

		AceMarker marker = new AceMarker(rowStart, from, rowEnd, to, color);

		getElement().setProperty("marker", marker.getRowStart() + "|" + marker.getFrom() + "|" + marker.getRowEnd()
				+ "|" + marker.getTo() + "|" + marker.getAceMarkerColor().toString() + "|" + marker.getID());
		this.markers.add(marker);
		return marker.getID();
	};

	/**
	 * Adds a marker to the editor. If the marker is not visible make sure that
	 * {@link #setHighlightActiveLine(boolean) setHightlightActiveLine()} and
	 * {@link #setHighlightSelectedWord(boolean) setHighlightSelectedWord} are set
	 * to false.
	 * 
	 * @param from  int
	 * @param to    int
	 * @param color AceMarkerColor
	 * @param alias String
	 *
	 * @return String
	 */
	public String addMarkerAtSelection(int from, int to, AceMarkerColor color, String alias) {
		from = Math.abs(from);
		to = Math.abs(to);
		if (to < from) {
			int tmp = from;
			from = to;
			to = tmp;
		}

		AceMarker marker = new AceMarker(from, to, color, alias);

		getElement().setProperty("marker", 0 + "|" + marker.getFrom() + "|" + 0 + "|" + marker.getTo() + "|"
				+ marker.getAceMarkerColor().toString() + "|" + marker.getID());
		this.markers.add(marker);
		return marker.getID();
	};

	/**
	 * Adds a marker to the editor. If the marker is not visible make sure that
	 * {@link #setHighlightActiveLine(boolean) setHightlightActiveLine()} and
	 * {@link #setHighlightSelectedWord(boolean) setHighlightSelectedWord} are set
	 * to false.
	 * 
	 * @param rowStart int
	 * @param from     int
	 * @param rowEnd   int
	 * @param to       int
	 * @param color    AceMarkerColor
	 * @param alias    String
	 * 
	 * @return String
	 */
	public String addMarkerAtSelection(int rowStart, int from, int rowEnd, int to, AceMarkerColor color, String alias) {
		rowStart = Math.abs(rowStart);
		from = Math.abs(from);
		rowEnd = Math.abs(rowEnd);
		to = Math.abs(to);
		if (to < from) {
			int tmp = from;
			from = to;
			to = tmp;
		}
		if (rowEnd < rowStart) {
			int tmp = rowStart;
			rowStart = rowEnd;
			rowEnd = tmp;
		}

		AceMarker marker = new AceMarker(rowStart, from, rowEnd, to, color, alias);

		getElement().setProperty("marker", marker.getRowStart() + "|" + marker.getFrom() + "|" + marker.getRowEnd()
				+ "|" + marker.getTo() + "|" + marker.getAceMarkerColor().toString() + "|" + marker.getID());
		this.markers.add(marker);
		return marker.getID();
	};

	/**
	 * Returns a list of all current active markers. If the list is empty, no marker
	 * is set at the moment.
	 * 
	 * @return a List of all AceMarkers
	 */
	public List<AceMarker> getAllMarkers() {
		return this.markers;
	};

	/**
	 * Removes a specific marker from the editor.<br>
	 * <br>
	 * You can get all the active markers with {@link #getAllMarkers()
	 * getAllMarkers()}.
	 * 
	 * @param marker AceMarker
	 */
	public void removeMarker(AceMarker marker) {
		for (AceMarker mar : markers) {
			if (mar.getID().equals(marker.getID())) {
				getElement().setProperty("rmMarker", marker.getID());
				markers.remove(marker);
			}
		}
	};

	/**
	 * Removes a specific marker from the editor by id.<br>
	 * <br>
	 * You can get all the active markers with {@link #getAllMarkers()
	 * getAllMarkers()}.
	 * 
	 * @param id String
	 */
	public void removeMarkerByID(String id) {
		for (AceMarker mar : markers) {
			if (mar.getID().equals(id)) {
				getElement().setProperty("rmMarker", id);
				markers.remove(mar);
			}
		}
	};

	/**
	 * Removes a specific marker from the editor by alias.<br>
	 * <br>
	 * You can get all the active markers with {@link #getAllMarkers()
	 * getAllMarkers()}.
	 * 
	 * @param alias String
	 */
	public void removeMarkerByAlias(String alias) {
		for (AceMarker mar : markers) {
			if (mar.getAlias().equals(alias)) {
				getElement().setProperty("rmMarker", mar.getID());
				markers.remove(mar);
			}
		}
	};

	/**
	 * Removes every marker from the editor.
	 */
	public void removeAllMarkers() {
		getElement().setProperty("rmMarker", "all" + UUID.randomUUID().toString());
		this.markers = new ArrayList<>();
	};
}