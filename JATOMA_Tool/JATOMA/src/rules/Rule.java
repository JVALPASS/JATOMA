package rules;

import java.util.Set;

import edu.uci.ics.jung.graph.Graph;
public interface Rule {
	public void setPartition(Set<Set<String>> partition); 
	public void setGraph(Graph  graph);
	public boolean check() throws Exception; 
	}
