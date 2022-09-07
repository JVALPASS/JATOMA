package graph;

public class Method {
	private String methodName;
	private int parametersCount;
	
	public Method(String name, int parameterscount){
		parametersCount = parameterscount;
		methodName = name; 
	}
	
	public String getName(){
		return methodName;
	}
	
	public int getParametersCount(){
		return parametersCount;
	}
}
