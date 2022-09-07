package it.unisa.pawt;

public class Dimension {
	
	private int _width;
	private int _height;
	
	public Dimension() {}
	
	public Dimension(Dimension d) {
		_width=d._width;
		_height=d._height;
	}
	
	public Dimension(int width, int height) {
		_width=width;
		_height=height;
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
