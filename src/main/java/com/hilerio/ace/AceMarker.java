package com.hilerio.ace;

import java.util.UUID;

/**
 * @author David Dodlek
 */

public class AceMarker {

	private String id;
	private int rowStart;
	private int from;
	private int rowEnd;
	private int to;
	private AceMarkerColor aceMarkerColor;
	private String alias = "";

	public AceMarker(int from, int to, AceMarkerColor aceMarker) {
		this.rowStart = 0;
		this.from = from;
		this.rowEnd = 0;
		this.to = to;
		this.aceMarkerColor = aceMarker;
		this.id = UUID.randomUUID().toString();
	}

	public AceMarker(int from, int to, AceMarkerColor aceMarker, String alias) {
		this.rowStart = 0;
		this.from = from;
		this.rowEnd = 0;
		this.to = to;
		this.aceMarkerColor = aceMarker;
		this.id = UUID.randomUUID().toString();
		this.alias = alias;
	}

	public AceMarker(int rowStart, int from, int rowEnd, int to, AceMarkerColor aceMarker) {
		this.rowStart = rowStart;
		this.from = from;
		this.rowEnd = rowEnd;
		this.to = to;
		this.aceMarkerColor = aceMarker;
		this.id = UUID.randomUUID().toString();
	}

	public AceMarker(int rowStart, int from, int rowEnd, int to, AceMarkerColor aceMarker, String alias) {
		this.rowStart = rowStart;
		this.from = from;
		this.rowEnd = rowEnd;
		this.to = to;
		this.aceMarkerColor = aceMarker;
		this.id = UUID.randomUUID().toString();
		this.alias = alias;
	}

	public String getID() {
		return this.id;
	}

	public int getRowStart() {
		return this.rowStart;
	}

	public int getFrom() {
		return this.from;
	}

	public int getRowEnd() {
		return this.rowEnd;
	}

	public int getTo() {
		return this.to;
	}

	public AceMarkerColor getAceMarkerColor() {
		return this.aceMarkerColor;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Override
	public String toString() {
		return "AceMarker [id=" + id + ", rowStart=" + rowStart + ", from=" + from + ", rowEnd=" + rowEnd + ", to=" + to
				+ ", aceMarkerColor=" + aceMarkerColor + ", alias=" + alias + "]";
	}
}
