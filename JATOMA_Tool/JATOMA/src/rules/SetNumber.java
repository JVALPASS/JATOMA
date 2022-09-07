package rules;

import java.util.Set;

import edu.uci.ics.jung.graph.Graph;
public class SetNumber implements Rule {
	private Graph  _graph;
	private Set<Set<String>> _partition;
	private int _setNumber;
	public SetNumber(int setNumber){
		_setNumber = setNumber;
		} 
	@Override
	public void setGraph(Graph  graph) {
		_graph = graph;
		} 
	@Override 
	public void setPartition(Set<Set<String>> partition) { 
		_partition = partition; 
		} 
	@Override 
	public boolean check() throws Exception{ 
		System.out.println("partizione: "+_partition.size()+" "+_setNumber);
		if(_partition == null) 
			throw new Exception("Partition not found");
		if(_partition.size()>_setNumber) 
			return false;
		return true; 
	} 
}