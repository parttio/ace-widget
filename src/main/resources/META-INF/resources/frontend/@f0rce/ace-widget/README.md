[![Published on Vaadin  Directory](https://img.shields.io/badge/Vaadin%20Directory-published-00b4f0.svg)](https://vaadin.com/directory/component/ace-widget1)
[![Stars on vaadin.com/directory](https://img.shields.io/vaadin-directory/star/ace-widget1.svg)](https://vaadin.com/directory/component/ace-widget1)
# ace-widget backend #

Even <strong>more</strong> embeddable code editor
Custom Element - just one tag, and no JS needed to provide
[Ace](http://ace.c9.io/) - The High Performance Code Editor

> Originally based on [LostInBrittany's fork](https://github.com/LostInBrittany/ace-widget)
> of [PolymerLabs ace-element](https://github.com/PolymerLabs/ace-element)


## Install

Install the component using [Vaadin Directory](https://vaadin.com/directory/component/ace-widget1):

```xml
<dependency>
   <groupId>com.hilerio</groupId>
   <artifactId>ace-widget</artifactId>
   <version>--VERSION HERE--</version>
</dependency>

<repository>
   <id>vaadin-addons</id>
   <url>https://maven.vaadin.com/vaadin-addons</url>
</repository>
```

## Attributes

Attribute     | Type      | Default | Description
---           | ---       | ---     | ---
`theme`       | *String*  | ``      | `Editor#setTheme` at [Ace API](http://ace.c9.io/#nav=api&api=editor)
`mode`        | *String*  | ``      | `EditSession#setMode` at [Ace API](http://ace.c9.io/#nav=api&api=edit_session)
`font-size`   | *String*  | ``      | `Editor#setFontSize` at [Ace API](http://ace.c9.io/#nav=api&api=editor)
`softtabs`    | *Boolean* | ``      | `EditSession#setUseSoftTabs()` at [Ace API](http://ace.c9.io/#nav=api&api=edit_session)
`tab-size`    | *Boolean* | ``      | `Session#setTabSize()` at [Ace API](http://ace.c9.io/#nav=api&api=edit_session)
`readonly`    | *Boolean* | ``      | `Editor#setReadOnly()` at [Ace API](http://ace.c9.io/#nav=api&api=editor)
`wrap`        | *Boolean* | ``      | `Session#setWrapMode()` at [Ace API](http://ace.c9.io/#nav=api&api=edit_session)
`autoComplete` | *Object* | ``   | Callback for `langTools.addCompleter` like the example at [Ace API](https://github.com/ajaxorg/ace/wiki/How-to-enable-Autocomplete-in-the-Ace-editor)
`minlines`    | *Number*  | 15      | `Editor.setOptions({minlines: minlines})`
`maxlines`    | *Number*  | 30      | `Editor.setOptions({minlines: maxlines})`
`initialFocus`| *Boolean* | ``      | If true, `Editor.focus()` is called upon initialisation
`placeholder` | *String*  | ``      | A placeholder text to show when the editor is empty

## Properties

Name        |  Description
---         | ---
`editor`    | Ace [editor](http://ace.c9.io/#nav=api&api=editor) object.
`value`     | [editor.get-/setValue()](http://ace.c9.io/#nav=api&api=editor)

## Events

Name             |  Description
---              | ---
`editor-content` | Triggered when editor content gets changed
`editor-ready`   | Triggered once Ace editor instance is created.

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -m 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

## License

[MIT License](http://opensource.org/licenses/MIT)
