package test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import argparser.ArgParser;
import argparser.BooleanHolder;
import it.unisa.javada.Parser;
import it.unisa.javada.FileManager;
import it.unisa.javada.LocalException;


public class BuilderMain {

	public static void main(String[] args) throws IOException {
			
			String[] params= new String[3];
			params[0] = "C:\\Users\\Valerio\\eclipse-workspace\\";//path dell'app da miniaturizzare
			params[1] = "EsercizioFinale2";//cartella dell'app da miniaturizzare
			params[2] = "C:\\Users\\Valerio\\eclipse-workspace\\Finale";//path dell'app data in output
			
			ArgParser parser = new ArgParser("java test.BuilderMain");
			BooleanHolder emulate = new BooleanHolder();
			parser.addOption("-path %sX3 #Java project path", params);
			parser.addOption("-emulate %v #Run emulator at the end", emulate);
			parser.matchAllArgs(args);
			
			//Path root del progetto da parsare
			String projectPath = params[0];
			
			//Nome del progetto
			String project = params[1];	
			
			//Path destinazione del progetto miniaturizzato
			String outputPath = params[2];
			
			int javaVersion = 8;
							
			CordovaProjectCreator cpc = new CordovaProjectCreator();
				
			cpc.createCordovaProject(project, project, outputPath);
			
			Parser p = new Parser(javaVersion);
			//Path dei sorgenti del progetto da parsare
			String filePath = FileManager.getSrcDirName(projectPath+File.separator+project);
			
			List<String> classes = FileManager.scanProject(projectPath+File.separator+project+File.separator+filePath);
			HTMLGenerator hg = new HTMLGenerator();
			ArrayList<Set<Set<String>>> partizioni = new ArrayList<>();

			try {
				
				for(int i=0; i<classes.size(); i++) {
					p.parse(projectPath, project, filePath, FileManager.trim(classes.get(i)), outputPath + "\\parsed");
					//partizioni.add(hg.partition(projectPath, project, filePath, FileManager.trim(classes.get(i))));
				}
					
					
			} catch (LocalException e1) {
				e1.printStackTrace();
			}	
			String projectinput = outputPath + "\\parsed" + File.separator + project;			
			String projectoutput = outputPath + File.separator + project;
						 
			
			try {
				hg.createFileHTML(projectinput,filePath,projectoutput,partizioni);
				
				FileUtils.copyDirectory(new File(".\\res\\assetPlugin\\"), 
						new File(projectoutput + "\\www"));
					
					FileUtils.copyDirectory(new File(".\\res\\assetPlugin\\"), 
						new File(projectoutput + "\\platforms\\android\\assets\\www"));
			
					FileUtils.copyDirectory(new File(".\\res\\assetPlugin\\"), 
						new File(projectoutput + "\\platforms\\android\\app\\src\\main\\assets\\www"));
			
					FileUtils.copyDirectory(new File(".\\res\\pawt"), 
						new File(projectoutput + "\\platforms\\android\\app\\src\\main\\java\\it\\unisa\\pawt"));
			
					FileUtils.copyDirectory(new File(".\\res\\table"), 
						new File(projectoutput + "\\platforms\\android\\app\\src\\main\\java\\it\\unisa\\table"));
					
					FileUtils.copyDirectory(new File(".\\res\\javada"), 
							new File(projectoutput + "\\platforms\\android\\app\\src\\main\\java\\it\\unisa\\javada"));
					
					FileUtils.copyDirectory(new File(outputPath + File.separator + project + "\\platforms\\android" + File.separator + filePath),
						new File(projectoutput + "\\platforms\\android\\app\\src\\main\\java\\it\\unisa" + File.separator + project.toLowerCase()));
					
					FileUtils.copyDirectory(new File(outputPath + File.separator + project + "\\platforms\\android\\res\\raw"),
							new File(projectoutput + "\\platforms\\android\\app\\src\\main\\res\\raw"));
				
				FileUtils.copyDirectory(new File(projectoutput+"\\platforms\\android\\app\\src\\main\\res"), 
						new File(projectoutput+"\\platforms\\android\\res"));
				
				FileUtils.copyFile(new File(projectoutput+"\\platforms\\android\\app\\src\\main\\AndroidManifest.xml"), 
						new File(projectoutput+"\\platforms\\android\\AndroidManifest.xml"));
				
				FileUtils.copyDirectory(new File(projectoutput+"\\platforms\\android\\app\\src\\main\\java\\it\\unisa\\run"), 
						new File(projectoutput+"\\platforms\\android\\src\\it\\unisa\\run"));
				
				FileUtils.copyDirectory(new File(projectoutput+"\\platforms\\android\\src\\it\\unisa"), 
						new File(projectoutput+"\\platforms\\android\\app\\src\\main\\java\\it\\unisa"));
				
				FileUtils.copyDirectory(new File(projectoutput+"\\platforms\\android\\assets\\www"),
						new File(projectoutput+"\\platforms\\android\\app\\src\\main\\assets\\www"));
				
				
			} catch (InstantiationException | IOException | URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			if(emulate.value)
				cpc.runCordovaProject(projectoutput);
	}
}