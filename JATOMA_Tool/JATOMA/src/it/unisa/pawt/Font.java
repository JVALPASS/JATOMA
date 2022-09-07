package it.unisa.pawt;

import java.text.AttributedCharacterIterator;
import java.util.Map;

public class Font {
	private String name;
	private int style;
	private int size;
	public static final  int BOLD = 1;
	public static final  int CENTER_BASELINE = 1;
	public static final  int LAYOUT_RIGHT_TO_LEFT = 1;
	public static final  int TYPE1_FONT = 1;
	public static final int ITALIC = 2;
	public static final int LAYOUT_NO_START_CONTEXT = 2;
	public static final int  HANGING_BASELINE = 2;
	public static final int LAYOUT_NO_LIMIT_CONTEXT = 4;
	public static final int LAYOUT_LEFT_TO_RIGHT = 0;
	public static final int PLAIN = 0;
	public static final int ROMAN_BASELINE = 0;
	public static final int TRUETYPE_FONT = 0;
	public Font(Font font) {
		name = font.getName();
		size = font.getSize();
		style = font.getStyle();
	}
	
	public Font(Map<? extends 
			AttributedCharacterIterator.Attribute,?> attributes) 
	{}
	
	public Font(String name, int style, int size) {
		this.name = name;
		this.style = style;
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStyle() {
		return style;
	}

	public void setStyle(int style) {
		this.style = style;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	
}