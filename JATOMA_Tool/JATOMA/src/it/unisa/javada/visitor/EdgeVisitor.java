package it.unisa.javada.visitor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jface.text.Document;

import graph.ClassGraph;
import graph.Method;

public class EdgeVisitor extends ASTVisitor {

	CompilationUnit _compilation;
	Document _document;
	ClassGraph _graph;
	Set<String> names = new HashSet<String>();

	public EdgeVisitor(CompilationUnit compilation, Document document, ClassGraph g) {
		_compilation = compilation;
		_document = document;
		_graph = g;
	}
	
	/*
	 * Ogni edge è composto dai due vertici (fromVertex, toVertex).
	 * fromVertex è il l'oggetto su cui viene invocato il metodo
	 * toVertex è l'oggeto (o anche più di uno) che viene passato come parametro al metodo
	 * es: a.somma(b,c) -> (a,b),(a,c)
	 * Ulteriori controlli vengono fatti anche per eventi: si va a ricercare l'invocazione del metodo tra le righe in questione estraendone gli argomenti.
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.MethodInvocation)
	 */
	@Override
	public boolean visit(MethodInvocation node) {
		if(node.getExpression() == null) return true;
		String fromVertex = node.getExpression().toString();
		List<Expression> arg = node.arguments();
		//Se l'oggetto fromVertex non interagisce con altri oggetti, la visita termina. Controllo i possibili archi per ognuno di questi argomenti
		while(!arg.isEmpty()){
			String code = arg.remove(0).toString();
			if(_graph.contains(code)){
				/* Caso più semplice, ovvero quando l'argomento è proprio il nome dell'oggetto
				 * es: fromVertex.metodo(argomento);
				 * in tal caso expression = argomento, lascio che il grafo aggiunga tale arco (se necessario)
				 */
				_graph.addEdge(fromVertex, code);
			}else if(code.indexOf('.') != -1 && !code.contains("ActionListener")){
				 String vertex = code.substring(0, code.indexOf('.'));
				 _graph.addEdge(fromVertex, vertex);
			} else {
				for(Method method : _graph.getMethods()){
					searchInteractions(fromVertex, code, method);
				}
			}
		}
		return true;
	}
	
	private void searchInteractions(String fromVertex, String code, Method method){
		System.out.println("from: "+fromVertex);
		System.out.println("code nel punto 0: "+code);
		//se non trovo il nome del metodo inutile proseguire
		if(!code.contains(method.getName()+"(") || method.getParametersCount() == 0) return;
		//elimino tutto ciò che sta a sinistra del metodo
		code = code.substring(code.indexOf(method.getName()+"("));
		System.out.println("code nel punto 1: "+code);
		//controllo eventuali istruzioni aggiuntive
		if(code.indexOf(';') != code.length() && code.indexOf(';') != -1){
			searchInteractions(fromVertex, code.substring(code.indexOf(';')+1), method);
			code = code.substring(0, code.indexOf(';'));
			System.out.println("code nel punto 2: "+code);
		}
		//shifto la stringa eliminando il nome del metodo e la prima parentesi
		code = code.substring(method.getName().length()+1);
		System.out.println("code nel punto 3: "+code);
		//controllo se ci sono più di argomenti
		if(method.getParametersCount() == 1){ //unico parametro 
			if(code.contains(".")) code = code.substring(0, code.indexOf('.'));
			else code = code.substring(0, code.indexOf(')'));
			System.out.println("code nel punto 4: "+code);
		} else{
			//devo trovare i parametri
			String[] vertices = code.split(",");
			for(String vertex : vertices){
				if(vertex.contains(".")) vertex = vertex.trim().substring(0, vertex.indexOf('.'));
				else if(vertex.indexOf(')') != -1) vertex = vertex.trim().substring(0, vertex.indexOf(')'));
				_graph.addEdge(fromVertex, vertex);
			}
			return;
		}
		_graph.addEdge(fromVertex, code);
	}
}
