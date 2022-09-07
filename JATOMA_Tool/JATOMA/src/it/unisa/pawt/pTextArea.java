package it.unisa.pawt;

import javax.accessibility.AccessibleContext;
import org.w3c.dom.Document;
import it.unisa.javada.Utils;

/**
 * Created by CARMINE on 01/09/2018.
 */

public class pTextArea extends pComponent {
	
	int _rows;
	int _columns;
	String _text = "";
	static int count = 1; //to avoid same id title
	
	public pTextArea() {
		this(null, "", 0, 0);
	}
	
	public pTextArea(Document doc) {
		this(null, "", 0, 0);
	}
	
	public pTextArea(Document doc, String text, int rows, int columns) {
		_text = text;
		_rows = rows;
		_columns = columns;
		_type = "pTextArea";
		_title = "textarea"+getCount();
	}
	
	public pTextArea(int rows, int columns) {
		this(null, "", rows, columns);
	}
	
	public pTextArea(String text, int rows, int columns) {
		this(null, text, rows, columns);
	}
	
	public pTextArea(String text) {
		this(null, text, 0, 0);
	}
	
	public void append(String str) {
		_text = _text+str;
	}
	
	protected Document createDefaultModel() {
		Utils.parsingWarning();
		return null;
	}
	
	public AccessibleContext getAccessibleContext() {
		Utils.parsingWarning();
		return null;
	}
	
	public int getColumns() {
		return _columns;
	}
	
	protected int getColumnWidth() {
		Utils.parsingWarning();
		return 0;
	}
	
	public int getLineCount() {
		if(_text!=null) {
			String[] lines = _text.split("\r\n|\r|\n");
			return lines.length;
		}
		else
			return 0;
	}
	
	public int getLineEndOffset(int line) {
		Utils.parsingWarning();
		return 0;
	}
	
	public int getLineOfOffset(int offset) {
		Utils.parsingWarning();
		return 0;
	}
	
	public int getLineStartOffset(int line) {
		Utils.parsingWarning();
		return 0;
	}
	
	public boolean getLineWrap() {
		Utils.parsingWarning();
		return false;
	}
	
	public Dimension getPreferredScrollableViewportSize() {
		Utils.parsingWarning();
		return null;
	}
	
	public Dimension getPreferredSize() {
		Utils.parsingWarning();
		return null;
	}
	
	protected int getRowHeight() {
		Utils.parsingWarning();
		return 0;
	}
	
	public int getRows() {
		return _rows;
	}
	
	public boolean getScrollableTracksViewportWidth() {
		Utils.parsingWarning();
		return false;
	}
	
	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
		Utils.parsingWarning();
		return 0;
	}
	
	public int getTabSize() {
		Utils.parsingWarning();
		return 0;
	}
	
	public String getUIClassID() {
		Utils.parsingWarning();
		return "";
	}
	
	public boolean getWrapStyleWord() {
		Utils.parsingWarning();
		return false;
	}
	
	public void insert(String str, int pos) {
		StringBuilder s = new StringBuilder(_text);
		s.insert(pos, str);
		_text=s.toString();
	}
	
	protected String paramString() {
		return _title;
	}
	
	public void replaceRange(String str, int start, int end) {
		StringBuilder s = new StringBuilder(_text);
		s.replace(start, end, str);
		_text=s.toString();
	}
	
	public void setColumns(int columns) {
		_columns=columns;
	}
	
	public void setFont(Font f) {
		Utils.parsingWarning();
	}
	
	public void setLineWrap(boolean wrap) {
		Utils.parsingWarning();
	}
	
	public void setRows(int rows) {
		_rows=rows;
	}
	
	public void setTabSize(int size) {
		Utils.parsingWarning();
	}
	
	public void setWrapStyleWord(boolean word) {
		Utils.parsingWarning();
	}
	
	public void setText(String text) {
		_text = text;
	}
	
	public String getText() {
		return _text;
	}
	
	public void setBackground(Color c) {
		
	}
	
	protected int getCount() {
		return count++;
	}
	
	public void create() {
		parserHTML.addTextArea(_text, _title, _rows, _columns);
	}

}
