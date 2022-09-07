package it.unisa.pawt;

public class Rectangle {
	
	private int _x;
	private int _y;
	private int _width;
	private int _height;
	
	public Rectangle() {
		this(0, 0, 0, 0);
	}
	
	public Rectangle(Dimension d){
		this(0, 0, d.get_width(), d.get_height());
	}
	
	public Rectangle(int x, int y, int width, int height) {
		_x=x;
		_y=y;
		_width=width;
		_height=height;
	}
	
	public Rectangle(int width, int height) {
		this(0, 0, width, height);
	}
	
	public Rectangle(Rectangle r) {
		this(r.get_x(), r.get_y(), r.get_width(), r.get_height());
	}

	public int get_x() {
		return _x;
	}

	public void set_x(int _x) {
		this._x = _x;
	}

	public int get_y() {
		return _y;
	}

	public void set_y(int _y) {
		this._y = _y;
	}

	public int get_width() {
		return _width;
	}

	public void set_width(int _width) {
		this._width = _width;
	}

	public int get_height() {
		return _height;
	}

	public void set_height(int _height) {
		this._height = _height;
	}
	
	

}
