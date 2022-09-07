package it.unisa.javada.visitor;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jface.text.Document;

import graph.ClassGraph;
import it.unisa.javada.Utils;

public class VertexVisitor<E> extends ASTVisitor {

	CompilationUnit _compilation;
	Document _document;
	ClassGraph _graph;

	public VertexVisitor(CompilationUnit compilation, Document document, ClassGraph g) {
		_compilation = compilation;
		_document = document;
		_graph = g;	
	}
	
	@Override
	public boolean visit(ClassInstanceCreation node){
		String code = node.getParent().toString(); //codice sorgente
		try{
			String name = code.substring(0, code.indexOf('='));
			//aggiungo il nuovo vertice al grafo, rimuovendo eventuali spazi dal nome
			_graph.addVertex(name.replaceAll("\\s", ""));
			Utils.print("Aggiunto vertice: "+name);
		} catch (Exception e){
			return true;
		}
		return true;
	}
	
	@Override
	public void endVisit(MethodDeclaration node) {
		String name = node.resolveBinding().getMethodDeclaration().getName();
		int parametersNumber = node.resolveBinding().getParameterTypes().length;
		if(parametersNumber > 0)
			_graph.addMethod(name, parametersNumber);
	}
	
	

}
