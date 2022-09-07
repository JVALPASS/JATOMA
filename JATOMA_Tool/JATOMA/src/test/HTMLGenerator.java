package test;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jface.text.Document;
import org.jgrapht.graph.DefaultEdge;

import edu.uci.ics.jung.graph.OrderedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import graph.ClassGraph;
import graph.GraphPartitioner;
import it.unisa.pawt.pFrame;
import it.unisa.pawt.parserHTML;
import rules.RulesManager;
import rules.SameSet;
import rules.SetNumber;
import it.unisa.javada.FileManager;
import it.unisa.javada.Utils;
import it.unisa.javada.visitor.EdgeVisitor;
import it.unisa.javada.visitor.VertexVisitor;

public class HTMLGenerator {
	
	
	public void createFileHTML(String project_input, String filePath, String output_project,ArrayList<Set<Set<String>>> partizioni) throws IOException, URISyntaxException, InstantiationException {
			
			Utils.print("Generating html files.");
			//String path = "C:\\Users\\antonio\\eclipse-workspace\\ProgettoClassi\\ProgettoClassi\\src\\";
			//String path2="C:\\Users\\Antonio\\AndroidStudioProjects\\android\\build\\intermediates\\classes\\debug\\";
		//	String path2 = //"C:\\Users\\antonio\\hello\\www";
			//		  "C:\\Users\\antonio\\eclipse-workspace\\ProgettoClassi\\ProgettoClassi\\bin\\";
			
			String path = project_input + "\\src\\";
			String path2 = project_input + "\\bin\\";
		//	System.out.println("path: " + path); 
			//System.out.println("path2: " + path2);
			File percorso1= new File(path);
			File percorso2= new File(path2);
			
			if(percorso1.exists() && percorso2.exists()){
			
			List<String> classes = FileManager.scanProject(path);
			//System.out.println(classes);
			ArrayList<String> nomiPath= new ArrayList<String>();
			ArrayList<String> nomi= new ArrayList<String>();
			
			for (int i=0; i<classes.size();i++){
				
				String nome = classes.get(i);
				boolean q = false;
				
				for(int j=0; j<nome.length();j++){
					if(nome.charAt(j)=='\\'){
						q=true;
					}
				}
				
				if(q==true){
					
				String z = nome.replaceAll("\\\\",".");
				int k= nome.lastIndexOf("\\")+1;
				int kk=nome.lastIndexOf(".");
				nomi.add(nome.substring(k, kk));
				nomiPath.add(z.substring(0,kk));
				/*int k= nome.lastIndexOf(".")+1;
				String nome2= nome.substring(k);
				nomi.add(nome2);*/
				
				}else if(q==false){
					int kkk=nome.lastIndexOf(".");
					nomi.add(nome.substring(0,kkk));
					nomiPath.add(nome.substring(0,kkk));
				}
			}
			
			for(int i=0; i<nomiPath.size(); i++){
		
					try{
						
						File file = new File(path2);
						URL url = file.toURI().toURL();
						URL[] urls = new URL[]{url};
						ClassLoader cl = new URLClassLoader(urls);
						
						
						Class  cls = cl.loadClass(nomiPath.get(i));
						Method[] metodi = cls.getMethods();
						boolean valore=false;
						
						for(Method m: metodi){
							if(m.getName().equals("getTopComponent")){
								valore=true;
							}
								
						}
						
						if(valore==true){
							//Operazione ciao = (Operazione) cls.newInstance();
							Object obj = cls.newInstance();
							
							Method method = obj.getClass().getDeclaredMethod("getTopComponent");
							//Method method2 = ciao.getClass().getDeclaredMethod("getFrame", null);
							method.setAccessible(true);
							//method2.setAccessible(true);
							pFrame frame = (pFrame) method.invoke(obj);
							//ArrayList<String> a= (ArrayList<String>) method2.invoke(ciao, null);
							//Set<Set<String>> partition= testPartition(a);
							
							String pagina = output_project + "\\www\\" + nomi.get(i)+".html";
						//	String pagina= "C:\\Users\\antonio\\hello\\www\\"+nomi.get(i)+".html";
							parserHTML.setPath(output_project + "\\platforms\\android\\app\\src\\main\\assets\\www\\");
							parserHTML.createPage(pagina);
							parserHTML.addHeader(nomi.get(i));
							parserHTML.parse(frame);
							ArrayList<String> widgets= parserHTML.getWidgets(frame);
							
							Set<Set<String>> orderedPartition = testPartition(widgets);
							
							/* for(Set<String> set : partition){ 
								 System.out.println("--->SET di "+set.size()+" VERTICI");
								 for(String stri : set) {
									 System.out.print(stri+", "); 
								 } 
							 } */
							
							
							
							/*for(int s = 0;s<partizioni.size();s++) {
								Set<Set<String>> neworderedPartition = this.orderPartition(partizioni.get(s), orderedPartition);// avviene qui la partizione 
								parserHTML.writePage(pagina,neworderedPartition);
							}*/ //precedente partizionamento
							
							
							parserHTML.writePage(pagina,orderedPartition);
							
							
							
							FileUtils.copyFileToDirectory(new File(pagina), new File(output_project + "\\platforms\\android\\assets\\www\\"));
							
							/*if(!FileManager.directoryExists(output_project + "\\platforms\\android\\res\\raw")) {
								FileManager.createDirectory(output_project + "\\platforms\\android\\res\\raw");
							}*/
							
							if(!FileManager.directoryExists(output_project + "\\platforms\\android\\res\\raw")) {
								File f=new File(output_project + "\\platforms\\android\\res\\raw");
								f.mkdirs();
							}
							
							File f= new File(output_project + "\\platforms\\android\\res\\raw\\form.txt");
							
							if (f.exists()){
								boolean x=true;
								FileReader fff= new FileReader(f);
								Scanner s= new Scanner(fff);
								while(s.hasNext()){
									
									if((nomiPath.get(i)+"-"+frame._title).equals(s.nextLine())){
										x=false;
									}
								}
								
								fff.close();
								s.close();
								
								if(x==true){
									FileWriter scrivi= new FileWriter(f,true);
									PrintWriter fos= new PrintWriter(scrivi);
									fos.println((nomiPath.get(i)+"-"+frame._title));
									fos.close();
							
								}
							}
					
							else if (f.createNewFile()){
								FileWriter scrivi= new FileWriter(f,true);
								PrintWriter fos= new PrintWriter(scrivi);
								fos.println(nomiPath.get(i)+"-"+frame._title);
								fos.close();
						
							}
							
							
							
							//copia file java in out
								String pathClassi= nomiPath.get(i).replaceAll("\\.","\\\\");
								
							
							 	Path source = Paths.get(path+pathClassi+".java");
							   // Path target = Paths.get(output_project + "\\platforms\\android\\src\\it\\unisa\\run\\mini\\" + nomi.get(i) + ".java");
							  //  Files.copy(source, target, REPLACE_EXISTING);
							    FileUtils.copyFile(new File(source.toUri()),new File(output_project + "\\platforms\\android\\" + filePath + "\\"  + nomi.get(i) + ".java"));
							    Utils.print("Done.");
						}
					
						} 
						catch(ClassNotFoundException e)  {
							e.printStackTrace();
						} 
						catch(FileNotFoundException e)  {
							e.printStackTrace();
						}	 
						catch (NoSuchMethodException e) {
							e.printStackTrace();
						} 
						catch (IllegalArgumentException e) {
							e.printStackTrace();
						} 
						catch (IllegalAccessException e) {
							e.printStackTrace();
						} 
						catch (InvocationTargetException e) {
							e.printStackTrace();
						}
			
			}
			
			}
			
			else{
				System.out.println("Path non trovate");
			}
	
}
	private static Set<Set<String>> testPartition(ArrayList<String> a){
		Set<Set<String>> partition = new LinkedHashSet<Set<String>>();
		Set<String> firstSet = new LinkedHashSet<String>();
		
		for(int i=0; i<a.size();i++){
			firstSet.add(a.get(i));
		}
		
		partition.add(firstSet);
		
		
		return partition;
	}
	public  Set<Set<String>> partition(String projectPath, String project, String filePath, String fileName) throws IOException{
		String _classpathSeparator = System.getProperty("path.separator");
		String[] _classpath = java.lang.System.getProperty("java.class.path").split(_classpathSeparator);
	    String _javaVersion = JavaCore.VERSION_1_8;
		String stringa = FileManager.readFileToString(projectPath + File.separator + project + File.separator + filePath +File.separator + fileName + ".java");

		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setSource(stringa.toCharArray());

		parser.setResolveBindings(true);
		parser.setBindingsRecovery(true);

		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setUnitName(project + File.separator + fileName + ".java");
		parser.setEnvironment(_classpath, 
				new String[] { projectPath + File.separator + project + File.separator + filePath }, new String[] { "UTF8" }, true);

		Map<?, ?> options = JavaCore.getOptions();
		JavaCore.setComplianceOptions(_javaVersion, options);
		parser.setCompilerOptions((Map<String, String>) options);

		Document document = new Document(stringa);

		final CompilationUnit compilation = (CompilationUnit) parser.createAST(null);

		final AST ast = compilation.getAST();
		final ASTRewrite rewriter = ASTRewrite.create(ast);
		compilation.recordModifications();
		ClassGraph cG = new ClassGraph();
		VertexVisitor vertexVisitor = new VertexVisitor(compilation, document, cG);
		compilation.accept(vertexVisitor);
		EdgeVisitor edgeVisitor  = new EdgeVisitor(compilation, document, cG);
		compilation.accept(edgeVisitor);
		cG.removeIsolatedVertices();
		System.out.println(cG.getGraph().edgeSet());
		System.out.println(cG.getGraph().vertexSet());
		OrderedSparseMultigraph<String,String> graph = new OrderedSparseMultigraph<String,String>();//OrderedSparseMultigraph
        Set<DefaultEdge> archi = new LinkedHashSet<DefaultEdge>();
        Set<String> vertici = new LinkedHashSet<String>();
        vertici = cG.getGraph().vertexSet();
        archi = cG.getGraph().edgeSet();
        for(String s:vertici) {
      		graph.addVertex(s);

        }
        for(DefaultEdge s:archi) {
        	int inizioS1 = 1;
        	int fineS1 =  s.toString().indexOf(":")-1;
        	String vertice1 = s.toString().substring(inizioS1, fineS1);
        	int inizioS2 = s.toString().indexOf(":")+2;
        	int fineS2 =  s.toString().length()-1;
        	String vertice2 = s.toString().substring(inizioS2, fineS2);
    		graph.addEdge(s.toString(),vertice1,vertice2,EdgeType.DIRECTED);
      		System.out.println("vertice 1 "+vertice1);
      		System.out.println("vertice 2 "+vertice2);

        }
        
  		System.out.println("vertici finale "+graph.getVertices());
		System.out.println("archi finale "+graph.getEdges());

		
         RulesManager rulesManager = new RulesManager(graph); 
         rulesManager.addRule(new SetNumber(1)); 

         
		 Set<Set<String>> partition = null;
		try {
			 partition = GraphPartitioner.execute(graph, rulesManager);  //Stampo il contenuto di ogni blocco (set) 
			 for(Set<String> sett : partition){ 
				 System.out.println("--->SET finale di "+sett.size()+" VERTICI");
				 System.out.println();
				 for(String stri : sett) {
					 System.out.print(stri+", "); 
				 } 
			 } 
	      } catch (Exception e) { 
			 System.out.println("Eccezione: "+e.getMessage()); 
			 e.printStackTrace(); 
		 }
		return partition;
		
	}
	
	/*Questo metodo serve che se vine effettuata la partzione tramite il metodo partition,
	 *   questo permette l'ordinamento delle partizioni restituite
	 */
	public  Set<Set<String>> orderPartition(Set<Set<String>> disorderedPartition,Set<Set<String>> orderedPartition){
		int blocco = 0;
		Hashtable<String,Integer> t = new Hashtable<>();
		for(Set<String> sett : orderedPartition){ 
			 System.out.println("--->SET di "+sett.size()+" VERTICI");
			 for(String stri : sett) {
					 System.out.println(stri+", "); 
			 } 
		 } 
		
		for(Set<String> sett : disorderedPartition){ 
			 System.out.println("--->SET disordinato di "+sett.size()+" VERTICI");
			 for(String stri : sett) {
					 System.out.println(stri+", "); 
			 } 
		 }
		
		
		for(Set<String> set : disorderedPartition){ 
			 for(String stri : set) {
				 t.put(stri, blocco);
			 } 
			 blocco++;
		 }
		int blocchi = disorderedPartition.size();
		System.out.println(blocchi);
		

		ArrayList<Set<String>> newOrdered =  new ArrayList<Set<String>>(); 
		for(int i = 0;i<blocchi;i++) {
			newOrdered.add(i,new LinkedHashSet<String>());
		}
	
		for(Set<String> set : orderedPartition){ 
			 for(String stri : set) {
					System.out.println("sssss "+stri);
				 if(t.containsKey(stri)== true) {
					 int partizione = t.get(stri);
					 Set<String> a;
					 System.out.println("entarto nel primo if partzione "+partizione);
					 if(!newOrdered.get(partizione).isEmpty()) {
	                     System.out.println("partizione non vuota");
						 a = newOrdered.get(partizione);
					}
					 else {
	                     System.out.println("partizione  vuota");
						 a = new LinkedHashSet<String>();
					 }
					 a.add(stri);
					 newOrdered.remove(partizione);
					 newOrdered.add(partizione,a);

				 }
			 }
		 }
		
		for(Set<String> set : newOrdered){ 
			 System.out.println("--->SET ordinato passato di "+set.size()+" VERTICI");
			 for(String stri : set) {
				 System.out.print(stri+", "); 
			 } 
		}
		
		Set<Set<String>> setOrderedPartition =  new LinkedHashSet<Set<String>>(); 
		for(int i = 0;i < newOrdered.size(); i++) {
			if(newOrdered.get(i).size()!=0) {
				Set<String> insieme = new LinkedHashSet<String>();
				for(String set :newOrdered.get(i)) {
					insieme.add(set);
				}
				setOrderedPartition.add(insieme);
			}
		}
		for(Set<String> set : setOrderedPartition){ 
			 System.out.println("--->SET ordinatoo finale di "+set.size()+" VERTICI");
			 for(String stri : set) {
				 System.out.print(stri+", "); 
			 } 
		}
		return setOrderedPartition;
		
	}

	

}

