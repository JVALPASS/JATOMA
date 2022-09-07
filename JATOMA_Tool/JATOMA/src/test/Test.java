package test;
import it.unisa.javada.FileManager;
import it.unisa.pawt.*;

import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

import argparser.*;

import java.lang.reflect.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Test{

	public static void main(String[] args)throws URISyntaxException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, IOException{
		// TODO Auto-generated method stub
		//Desktop/tesi/Finale/DOMParser_2/src/test
	/*	
		String[] per= new String[2];
		
		ArgParser parser = new ArgParser("java test.Test");
		parser.addOption("-path %sX2 #Java project path", per);
		parser.matchAllArgs (args);
		
		String path=per[0];
		String path2= per[1];
	*/
		//String path="C:\\Users\\Antonio\\Desktop\\tesi\\ProgettoClassi\\ProgettoClassi\\src\\";
		
		String path = "C:\\Users\\antonio\\eclipse-workspace\\ProgettoClassi\\ProgettoClassi\\src\\";
		//String path2="C:\\Users\\Antonio\\AndroidStudioProjects\\android\\build\\intermediates\\classes\\debug\\";
		String path2 = //"C:\\Users\\antonio\\hello\\www";
					  "C:\\Users\\antonio\\eclipse-workspace\\ProgettoClassi\\ProgettoClassi\\bin\\";
		
		File percorso1= new File(path);
		File percorso2= new File(path2);
		
		if(percorso1.exists() && percorso2.exists()){
		
		List<String> classes = FileManager.scanProject(path);
		
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
				String pagina= "C:\\Users\\antonio\\hello\\www\\"+nomi.get(i)+".html";
				//String pagina= "C:\\Users\\Antonio\\Desktop\\"+nomi.get(i)+".html";
			
				parserHTML.createPage(pagina);
				parserHTML.addHeader(nomi.get(i));
				parserHTML.parse(frame);
				ArrayList<String> widgets= parserHTML.getWidgets(frame);
			
		
				Set<Set<String>> partition= testPartition(widgets);
				
				parserHTML.writePage(pagina,partition);
				
				File f= new File("C:\\Users\\antonio\\hello\\platforms\\android\\res\\raw\\form.txt");
		
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
				    Path target = Paths.get("C:\\Users\\antonio\\hello\\platforms\\android\\src\\it\\unisa\\run\\mini\\" + nomi.get(i) + ".java");
				    Files.copy(source, target, REPLACE_EXISTING);
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
	

		}



