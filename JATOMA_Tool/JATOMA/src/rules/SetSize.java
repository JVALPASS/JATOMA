package rules;
	import edu.uci.ics.jung.graph.Graph;
	import java.util.Set;

	public class SetSize implements Rule
	{ 
		private Set<Set<String>> _partition; 
		private Graph  _graph;
		int _numberOfElements;
		public SetSize(int numberOfElements){
			_numberOfElements = numberOfElements;
			} 
		@Override
		public void setPartition(Set<Set<String>> partition) {
			_partition = partition; 
			}
		@Override 
		public void setGraph(Graph  graph) { 
			_graph = graph; 
			}
		@Override 
		public boolean check() throws Exception { 
			for(Set<String> set : _partition) 
				if(set.size() > _numberOfElements) 
					return false; 
			return true; 
			} 
		}
		
		
	

