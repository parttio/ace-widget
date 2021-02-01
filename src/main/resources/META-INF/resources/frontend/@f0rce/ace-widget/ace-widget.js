/**
@license MIT
Copyright 2021 David "F0rce" Dodlek
Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import { PolymerElement, html } from "@polymer/polymer/polymer-element.js";

import "ace-builds/src-noconflict/ace.js";
import "ace-builds/src-noconflict/ext-language_tools.js";
import "ace-builds/src-noconflict/snippets/snippets.js";

var editorFocus = function () {
  var _self = this;
  setTimeout(function () {
    if (!_self.isFocused()) _self.textInput.focus();
  });
  this.textInput.$focusScroll = "browser";
  this.textInput.focus();
};

class AceWidget extends PolymerElement {
  static get template() {
    return html`
      <style>
        :host {
          display: block;
          width: 100%;
          height: 100%;
        }
        #editor {
          border: 1px solid var(--lumo-contrast-20pct);
          border-radius: var(--lumo-border-radius);
          @apply --ace-widget-editor;
        }
        .ace_marker-layer .green {
          background-color: var(--lumo-success-color);
          color: var(--lumo-primary-contrast-color);
          position: absolute;
        }
        .ace_marker-layer .darkGrey {
          background-color: var(--lumo-shade-50pct);
          color: var(--lumo-primary-contrast-color);
          position: absolute;
        }
        .ace_marker-layer .red {
          background-color: var(--lumo-error-color);
          color: var(--lumo-primary-contrast-color);
          position: absolute;
        }
        .ace_marker-layer .blue {
          background-color: var(--lumo-primary-color);
          color: var(--lumo-primary-contrast-color);
          position: absolute;
        }
        .ace_marker-layer .orange {
          background-color: #ff9900;
          color: #555;
          position: absolute;
        }
      </style>
      <div id="editor"></div>
    `;
  }

  static get is() {
    return "ace-widget";
  }

  static get properties() {
    return {
      theme: {
        type: String,
        value: "eclipse",
        notify: true,
        observer: "themeChanged",
      },
      mode: {
        type: String,
        value: "javascript",
        notify: true,
        observer: "modeChanged",
      },
      value: {
        type: String,
        notify: true,
        observer: "valueChanged",
      },
      readonly: {
        type: Boolean,
        value: false,
        observer: "readonlyChanged",
      },
      softtabs: {
        type: Boolean,
        value: true,
        observer: "softtabsChanged",
      },
      wrap: {
        type: Boolean,
        value: false,
        observer: "wrapChanged",
      },
      fontSize: {
        type: String,
        value: "14px",
        observer: "fontSizeChanged",
      },
      tabSize: {
        type: Number,
        value: 4,
        observer: "tabSizeChanged",
      },
      snippets: {
        type: String,
        notify: true,
      },
      minlines: {
        type: Number,
        value: 15,
      },
      maxlines: {
        type: Number,
        value: { Infinity },
      },
      enableLiveAutocompletion: {
        type: Boolean,
        value: false,
      },
      enableAutocompletion: {
        type: Boolean,
        value: false,
      },
      enableSnippets: {
        type: Boolean,
        value: false,
      },
      initialFocus: {
        type: Boolean,
        value: false,
      },
      placeholder: {
        type: String,
        value: "",
      },
      verbose: {
        type: Boolean,
        value: false,
      },
      baseUrl: {
        type: String,
        value: "",
      },
      showPrintMargin: {
        type: Boolean,
        value: false,
      },
      showInvisibles: {
        type: Boolean,
        value: false,
      },
      showGutter: {
        type: Boolean,
        value: true,
      },
      highlightActiveLine: {
        type: Boolean,
        value: true,
        observer: "highlightActiveLineChanged",
      },
      displayIndentGuides: {
        type: Boolean,
        value: false,
      },
      highlightSelectedWord: {
        type: Boolean,
        value: false,
        observer: "highlightSelectedWordChanged",
      },
      selection: {
        type: String,
        value: "0|0|0|0|-",
        observer: "selectionChanged",
      },
      useWorker: {
        type: Boolean,
        value: false,
      },
      customAutoCompletion: {
        type: String,
        value: "|",
        observer: "customAutoCompletionChanged",
      },
      marker: {
        type: String,
        value: "-|-|-|-|-|-",
        observer: "markerChanged",
      },
      markerList: {
        type: Array,
        value: { markers: [] },
      },
      rmMarker: {
        type: String,
        value: "",
        observer: "rmMarkers",
      },
      firstRun: {
        type: Boolean,
        value: true,
      },
    };
  }

  /**
   * In order to statically import non ES mudules resources, you need to use `importPath`.
   * But in order to use `importPath`, for elements defined in ES modules, users should implement
   * `static get importMeta() { return import.meta; }`, and the default
   * implementation of `importPath` will  return `import.meta.url`'s path.
   * More info on @Polymer/lib/mixins/element-mixin.js`
   */

  async connectedCallback() {
    super.connectedCallback();

    let baseUrl =
      this.baseUrl || `${this.importPath}../../ace-builds/src-min-noconflict/`;

    // In non-minified mode, imports are parallelized, and sometimes `ext-language_tools.js` and
    // `snippets.js` arrive before `ace.js` is ready. I am adding some tests here with dynamic imports
    // to fix thaty
    if (!ace) {
      await import(`${baseUrl}ace.js`);
    }
    if (!ace.require("ace/ext/language_tools")) {
      await import(`${baseUrl}ext-language_tools.js`);
    }


    // console.debug("[ace-widget] connectedCallback")
    let div = this.$.editor;
    div.style.width = "100%";
    div.style.height = "100%";
    this.editor = ace.edit(div);
    this.editor.focus = editorFocus;
    this.editor.langTools = ace.require("ace/ext/language_tools");
    // this.init();

    this.dispatchEvent(
      new CustomEvent("editor-ready", {
        detail: { value: this.editor, oldValue: null },
      })
    );

    this.initializeEditor();
  }

  initializeEditor() { 
    let editor = this.editor;

    this.head = document.head;

    this.injectStyle("#ace_editor\\.css");

    let baseUrl =
      this.baseUrl || `${this.importPath}../../ace-builds/src-min-noconflict/`;

    ace.config.set("basePath", baseUrl);
    ace.config.set("modePath", baseUrl);
    ace.config.set("themePath", baseUrl);
    ace.config.set("workerPath", baseUrl);
    
    this.editorValue = "";
    this._selection = this.selection;
    editor.setOption("enableSnippets", this.enableSnippets);
    editor.setOption("enableBasicAutocompletion", this.enableAutocompletion);
    editor.setOption("enableLiveAutocompletion", this.enableLiveAutocompletion);

    editor.on("input", this._updatePlaceholder.bind(this));
    setTimeout(this._updatePlaceholder.bind(this), 100);
    editor.on("blur", this.editorBlurChangeAction.bind(this));
    editor.selection.on(
      "changeSelection",
      this.updateSelectionAction.bind(this)
    );
    this.session = editor.getSession();

    if (this.initialFocus) {
      editor.focus();
    }

    editor.$blockScrolling = Infinity;

    // Forcing a xyzChanged() call, because the initial one din't do anything as editor wasn't created yet
    this.themeChanged();
    this.readonlyChanged();
    this.wrapChanged();
    this.tabSizeChanged();
    this.modeChanged();
    this.softtabsChanged();
    this.fontSizeChanged();
    this.selectionChanged();
    this.customAutoCompletionChanged();
    this.markerChanged();
    this.highlightActiveLineChanged();
    this.highlightSelectedWordChanged();

    editor.setShowPrintMargin(this.showPrintMargin);
    editor.setOption("showGutter", this.showGutter);
    editor.setShowInvisibles(this.showInvisibles);
    editor.setDisplayIndentGuides(this.displayIndentGuides);
    editor.getSession().setUseWorker(this.useWorker);

    // Setting content

    // Trying to get content as HTML content
    let htmlContent = this.innerHTML.trim();
    // console.debug("[ace-widget] HTML content found", htmlContent);

    // If we have a value in the `value` attribute, we keep it, else we use the HTML content
    if (this.value === undefined) {
      this.value = htmlContent;
      // console.debug("[ace-widget] initializeEditor - using HTML content as value", this.value)
    } else {
      // Forcing a valueChanged() call, because the initial one din't do anything as editor wasn't created yet
      this.valueChanged();
    }

    // min and max lines
    editor.setOptions({
      minLines: this.minlines,
      maxLines: this.maxlines,
    });

    // snippets
    if (this.enableSnippets) {
      let snippetManager = ace.require("ace/snippets").snippetManager;
      snippetManager.register(this.snippets, "_");
    }

    if (this.verbose) {
      console.debug(
        "[ace-widget] After initializing: editor.getSession().getValue()",
        editor.getSession().getValue()
      );
    }

    this.firstRun = false;
  }

  refreshEditor() {
    if (this.firstRun) {
      return;
    }

    let editor = this.editor;

    editor.setOption("enableSnippets", this.enableSnippets);
    editor.setOption("enableBasicAutocompletion", this.enableAutocompletion);
    editor.setOption("enableLiveAutocompletion", this.enableLiveAutocompletion);
    editor.setOption("showGutter", this.showGutter);

    setTimeout(this._updatePlaceholder.bind(this), 5);

    editor.$blockScrolling = Infinity;

    // Forcing a xyzChanged() call, because the initial one din't do anything as editor wasn't created yet
    this.themeChanged();
    this.readonlyChanged();
    this.wrapChanged();
    this.tabSizeChanged();
    this.modeChanged();
    this.softtabsChanged();
    this.fontSizeChanged();
    this.selectionChanged();
    this.customAutoCompletionChanged();
    this.markerChanged();
    this.highlightActiveLineChanged();
    this.highlightSelectedWordChanged();

    editor.setShowPrintMargin(this.showPrintMargin);
    editor.setOption("showGutter", this.showGutter);
    editor.setShowInvisibles(this.showInvisibles);
    editor.setDisplayIndentGuides(this.displayIndentGuides);
    editor.getSession().setUseWorker(this.useWorker);
   
    editor.setOptions({
      minLines: this.minlines,
      maxLines: this.maxlines,
    });
  }

  fontSizeChanged() {
    if (this.editor == undefined) {
      return;
    }
    this.$.editor.style.fontSize = this.fontSize;
  }

  modeChanged() {
    if (this.editor == undefined) {
      return;
    }
    this.editor.getSession().setMode("ace/mode/" + this.mode);
  }

  softtabsChanged() {
    if (this.editor == undefined) {
      return;
    }
    this.editor.getSession().setUseSoftTabs(this.softtabs);
  }

  themeChanged() {
    if (this.editor == undefined) {
      return;
    }
    this.editor.setTheme("ace/theme/" + this.theme);
  }

  valueChanged() {
    // console.debug("[ace-widget] valueChanged - ",this.value)
    if (this.editor == undefined) {
      return;
    }
    if (this.editorValue != this.value) {
      this.editorValue = this.value;
      this.editor.clearSelection();
      this.editor.resize();
    }
  }

  readonlyChanged() {
    if (this.editor == undefined) {
      return;
    }
    this.editor.setReadOnly(this.readonly);
    this.editor.setHighlightActiveLine(!this.readonly);
    this.editor.setHighlightGutterLine(!this.readonly);
    this.editor.renderer.$cursorLayer.element.style.opacity = this.readonly
      ? 0
      : 1;
  }

  wrapChanged() {
    if (this.editor == undefined) {
      return;
    }
    this.editor.getSession().setUseWrapMode(this.wrap);
  }

  tabSizeChanged() {
    if (this.editor == undefined) {
      return;
    }
    if (this.tabSize) {
      this.editor.getSession().setTabSize(this.tabSize);
    }
  }

  selectionChanged() {
    if (this.editor == undefined) {
      return;
    }

    if (this.selection == "0|0|0|0|-") {
      return;
    }

    const selection = this.selection.split("|");
    const rowStart = parseInt(selection[0]);
    const from = parseInt(selection[1]);
    const rowEnd = parseInt(selection[2]);
    const to = parseInt(selection[3]);

    const Range = ace.require("ace/range").Range;
    this.editor.selection.setRange(new Range(rowStart, from, rowEnd, to));
  }

  markerChanged() {
    if (this.editor == undefined) {
      return;
    }

    if (this.marker == "-|-|-|-|-|-") {
      return;
    }

    const markerRaw = this.marker;
    const rawSplit = markerRaw.split("|");
    const markerRowStart = parseInt(rawSplit[0]);
    const markerFrom = parseInt(rawSplit[1]);
    const markerRowEnd = parseInt(rawSplit[2]);
    const markerTo = parseInt(rawSplit[3]);
    const markerColor = String(rawSplit[4]);
    const uuid = String(rawSplit[5]);

    const Range = ace.require("ace/range").Range;
    const _range = this.editor
      .getSession()
      .addMarker(
        new Range(markerRowStart, markerFrom, markerRowEnd, markerTo),
        markerColor,
        "text",
        false
      );
    this.markerList.markers.push({ uuid: uuid, rangeid: _range });
  }

  rmMarkers() {
    if (this.rmMarker == "") {
      return;
    }

    if (this.rmMarker.includes("all")) {
      for (var i in this.markerList.markers) {
        const del = this.markerList.markers[i].rangeid;
        this.editor.getSession().removeMarker(del);
        delete this.markerList.markers[i];
      }
    } else {
      for (var i in this.markerList.markers) {
        if (this.markerList.markers[i].uuid == this.rmMarker) {
          const del = this.markerList.markers[i].rangeid;
          this.editor.getSession().removeMarker(del);
          delete this.markerList.markers[i];
        }
      }
    }
  }

  highlightActiveLineChanged() {
    if (this.editor == undefined) {
      return;
    }

    this.editor.setHighlightActiveLine(this.highlightActiveLine);
  }

  highlightSelectedWordChanged() {
    if (this.editor == undefined) {
      return;
    }

    this.editor.setHighlightSelectedWord(this.highlightSelectedWord);
  }

  get editorValue() {
    if (this.editor == undefined) {
      return "";
    }
    return this.editor.getValue();
  }

  set editorValue(value) {
    if (this.editor == undefined || value === undefined) {
      return;
    }
    this._value = value;
    this.editor.setValue(value);
    // console.debug("[ace-widget] set editorValue", this.editorValue)
  }

  editorBlurChangeAction() {
    this.updateSelectionAction();
    this.dispatchEvent(
      new CustomEvent("editor-content", {
        detail: {
          value: this.editorValue,
          oldValue: this._value,
          selection: this._selection,
        },
      })
    );
    this.selection = this._selection;
  }

  focus() {
    this.editor.focus();
  }

  customAutoCompletionChanged() {
    if (this.editor == undefined) {
      return;
    }
    const rawString = String(this.customAutoCompletion);
    const rawSplit = rawString.split("|");
    if (rawSplit[1] == "") {
      this.editor.completers = [
        this.editor.langTools.snippetCompleter,
        this.editor.langTools.keyWordCompleter,
      ];
    } else {
      var staticWordCompleter = {
        getCompletions: function (editor, session, pos, prefix, callback) {
          const wordList = rawSplit[1].split(",");
          callback(
            null,
            wordList.map(function (word) {
              return {
                caption: word,
                value: word,
                meta: rawSplit[0],
              };
            })
          );
        },
      };
      this.editor.completers = [staticWordCompleter];
    }
  }

  _updatePlaceholder() {
    let editor = this.editor;
    var shouldShow = this.editorValue == "" ? true : false;
    var node = editor.renderer.emptyMessageNode;
    if (this.verbose) {
      console.debug("[ace-widget] _updatePlaceholder", {
        shouldShow: shouldShow,
        node: node,
      });
    }
    if (!shouldShow && node) {
      if (this.verbose) {
        console.debug("[ace-widget] _updatePlaceholder - shouldShow && !node");
      }
      editor.renderer.scroller.removeChild(editor.renderer.emptyMessageNode);
      editor.renderer.emptyMessageNode = null;
    } else if (shouldShow && !node) {
      node = editor.renderer.emptyMessageNode = document.createElement("div");
      node.textContent = this.placeholder;
      node.className = "ace_emptyMessage";
      node.style.padding = "0 9px";
      node.style.position = "absolute";
      node.style.zIndex = 9;
      node.style.opacity = 0.5;
      editor.renderer.scroller.appendChild(node);
    } else if (shouldShow && node) {
      editor.renderer.scroller.removeChild(editor.renderer.emptyMessageNode);
      editor.renderer.emptyMessageNode = null;
      node = editor.renderer.emptyMessageNode = document.createElement("div");
      node.textContent = this.placeholder;
      node.className = "ace_emptyMessage";
      node.style.padding = "0 9px";
      node.style.position = "absolute";
      node.style.zIndex = 9;
      node.style.opacity = 0.5;
      editor.renderer.scroller.appendChild(node);
    }
  }

  updateSelectionAction() {
    const range = this.editor.selection.getRange();
    const rowFrom = String(range.start.row);
    const from = String(range.start.column);
    const rowTo = String(range.end.row);
    const to = String(range.end.column);

    const set = rowFrom + "|" + from + "|" + rowTo + "|" + to + "|-";
    this._selection = set;
  }

  /**
   * Injects a style element into ace-widget's shadow root
   * @param {CSSSelector} selector for an element in the same shadow tree or document as `ace-widget`
   */
  injectStyle(selector) {
    const lightStyle =
      this.getRootNode().querySelector(selector) ||
      document.querySelector(selector);
    this.shadowRoot.appendChild(lightStyle.cloneNode(true));
  }
}

customElements.define(AceWidget.is, AceWidget);
