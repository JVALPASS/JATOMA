package graph;
import java.util.ArrayList;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import it.unisa.javada.Utils;

public class ClassGraph {
	private DirectedGraph<String, DefaultEdge> g;
	private ArrayList<Method> methods;
	
	public ClassGraph(){
		g = new DefaultDirectedGraph<>(DefaultEdge.class);
		methods = new ArrayList<Method>();
	}
	
	public DirectedGraph getGraph(){
		return g;
	}

	public ArrayList<Method> getMethods(){
		return methods;
	}
	
	public boolean contains(String v){
		return g.containsVertex(v);
	}
	
	public void addVertex(String v){
		g.addVertex(v);
	}
	
	public void addEdge(String v1, String v2){
		if(g.containsVertex(v1) && g.containsVertex(v2))
			g.addEdge(v1,v2);
	}
	
	public void addMethod(String name, int params){
		for(Method m : methods)
			if(m.getName().equals(name) && m.getParametersCount() == params) return;
		methods.add(new Method(name, params));
	}
	
	public void removeIsolatedVertices(){
		ArrayList<String> toRemove = new ArrayList<String>();
		//Colleziono i vertici da rimuovere
		for(String vertex : g.vertexSet()){
			if(g.edgesOf(vertex).isEmpty()){
				toRemove.add(vertex);
				Utils.print("Il vertice  \""+vertex+"\" è un vertice isolato. Rimosso dal grafo.");
			}
		}
		//Rimuovo i vertici
		for(String vertex : toRemove) g.removeVertex(vertex);
	}
}
