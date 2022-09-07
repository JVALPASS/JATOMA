package it.unisa.javada.visitor;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTNode;

public class  VisitorNode {
	private ArrayList<ASTNode> nodi;
	public VisitorNode() {
		nodi = new  ArrayList<>();
	}
	public void visit(ASTNode astNode) {
		if(astNode != null) {
			nodi.add(astNode);
			visit(astNode.getParent()) ;
		}
	}
	public ArrayList<ASTNode> getNodi(){
		return nodi;
	}
}
