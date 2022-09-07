package rules;
import java.util.Set;

import edu.uci.ics.jung.graph.Graph;

public class SameSet  implements Rule{ 
	private Set<Set<String>> _partition;
	private Graph  _graph;
	private String _v1, _v2; 
	public SameSet(String v1, String v2){ 
		_v1 = v1; 
		_v2 = v2; 
		} 
	@Override 
	public void setPartition(Set<Set<String>> partition) { 
		_partition = partition; 
		} 
	@Override 
	public void setGraph(Graph graph) { 
		_graph = graph; 
		}
	@Override
	public boolean check() throws Exception {
		if(_partition == null) return true; 
		for(Set<String> set : _partition) 
			if(set.contains(_v1) && set.contains(_v2)) 
				return true;
		return false;
		}
	}
