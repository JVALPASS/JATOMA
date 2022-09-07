package graph;
import java.util.Set;
import edu.uci.ics.jung.algorithms.cluster.EdgeBetweennessClusterer;
import edu.uci.ics.jung.graph.Graph;
import it.unisa.javada.Utils;
import rules.RulesManager; 

public class GraphPartitioner { 
	public static Set<Set<String>>execute(Graph graph,RulesManager rulesManager)
			throws Exception{ 
		int edgeCount = graph.getEdgeCount(); 
		if(edgeCount==0) 
			throw new Exception("Nel grafo non ci sono archi"); 
		if(!rulesManager.check()) 
			throw new Exception("Vincoli insoddisfacibili"); 
		EdgeBetweennessClusterer edgeBetweennessClusterer; 
		edgeBetweennessClusterer = new EdgeBetweennessClusterer(1); 
		Set<Set<String>> partition; 
		partition = edgeBetweennessClusterer.apply(graph);
		Utils.print("archi = "+graph.getEdges()+" num archi = "+graph.getEdgeCount()
		+" vertici = "+graph.getVertices()+" partizione = "+partition);
		rulesManager.update(partition); 
		if(!rulesManager.check()) { 
			edgeBetweennessClusterer = new EdgeBetweennessClusterer(0); 
			partition = edgeBetweennessClusterer.apply(graph);
		}
		else {
			for(int i=2; i<edgeCount; i++){ 
				Utils.print("archi = "+graph.getEdges()
				+" num archi = "+graph.getEdgeCount()
				+" vertici = "+graph.getVertices()+" partizione = "+partition);

				edgeBetweennessClusterer = new EdgeBetweennessClusterer(i); 
				Set<Set<String>> newPartition; 
				newPartition = edgeBetweennessClusterer.apply(graph); 
				rulesManager.update(newPartition); 
				if(!rulesManager.check()){ 
					edgeBetweennessClusterer = new EdgeBetweennessClusterer(i-1); 
					edgeBetweennessClusterer.apply(graph); 
					Utils.print("vincolo non  soddisfatto");
					break; 
				} 
				else  partition = newPartition; 
			}
		}
		int edgesRemoved; 
		edgesRemoved = edgeBetweennessClusterer.getEdgesRemoved().size(); 
		for(int i=0; i<edgesRemoved; i++){ 
			String edge = ""; 
			edge = edgeBetweennessClusterer.getEdgesRemoved().get(i).toString();
			graph.removeEdge(edge); 
		} 
		return partition; 
	} 
}