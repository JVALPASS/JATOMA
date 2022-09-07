package it.unisa.pawt;

public interface LayoutManager {
	
	public void addLayoutComponent(String name, pComponent comp);
	public void layoutContainer(Container parent);
	public Dimension minimumLayoutSize(Container parent);
	public Dimension preferredLayoutSize(Container parent);
	public void removeLayoutComponent(pComponent comp);

}
