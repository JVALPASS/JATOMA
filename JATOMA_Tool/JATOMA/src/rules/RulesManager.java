package rules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import edu.uci.ics.jung.graph.Graph;

public class RulesManager{
		ArrayList<Rule> _rules;

Graph _graph;
Set<Set<String>> _partition;
public RulesManager(Graph graph, Set<Set<String>> partition){
	_rules = new ArrayList<Rule>();
	_graph = graph;
	_partition = partition;
	} 
public RulesManager(Graph graph){ 
	_rules = new ArrayList<Rule>();
	_graph = graph;
	_partition = firstPartition(graph);
	}
public void addRule(Rule r){ 
	if(!_rules.contains(r)) {
		r.setGraph(_graph);
		r.setPartition(_partition);
		_rules.add(r);
		}
	}
public boolean check() { 
	for(Rule rule : _rules){
		try 
		{ 
			if(!rule.check()) 
				return false; }
		catch (Exception e){
			e.printStackTrace(); 
			return false;
			} 
		}
	return true; 
	} 
public void update(Set<Set<String>> partition)
{ 
	_partition = partition; 
	for(Rule rule : _rules) 
		rule.setPartition(partition); 
		
	}
private Set<Set<String>> firstPartition(Graph g){ 
	Set<Set<String>> toReturn = new HashSet<Set<String>>();
	Set<String> firstPartition = new HashSet<String>();
	Collection c = g.getVertices(); 
	Iterator iterator = c.iterator(); 
	while(iterator.hasNext()) 
		firstPartition.add((String) iterator.next()); 
	toReturn.add(firstPartition);
	return toReturn; 
	} 
}


