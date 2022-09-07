package test;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.commons.io.*;

import it.unisa.javada.FileManager;
import it.unisa.javada.Utils;


public class CordovaProjectCreator {
	
	String env;
	 
	public void createCordovaProject(String projectDirectory, String projectName, String projectOutput) throws IOException {
	
		 
		 if(!FileManager.directoryExists(projectOutput)) {
				FileManager.createDirectory(projectOutput);
			}
		 
		  String currentDirectory = System.getProperty("user.dir");
		  String os = System.getProperty("os.name");
		  
		  if(os.toLowerCase().startsWith("window")) 
			  env = "powershell.exe ";
		  else env = "//bin//bash";
		  
		  // Cordova installation check
		  
		  if(!checkCordova(env)) {
				Utils.print("Cordova installation not found, check if Cordova is installed correctly."
						+"\n View this page for documentation: 'https://cordova.apache.org/docs/en/latest/'");
				System.exit(1);
			}
		  
		  
		  String command = 
				  
				  env  + "cd " + projectOutput + ";"
		  		+ "cordova create " + projectDirectory + " it.unisa.run " + projectName +";"
		  		+ "cd " + projectDirectory + ";"
		  		+ "cordova platform add android;"
		  		+ "cordova plugin add " + currentDirectory + "\\res\\AWTPlugin";
		  		
		  
		  // Executing the command
		  
		  Process powerShellProcess = Runtime.getRuntime().exec(command);
		 
		  // Getting the results
		  
		  powerShellProcess.getOutputStream().close();
		
		  String line;
		  Utils.print("Executing Cordova Creator ");
		  BufferedReader stdout = new BufferedReader(new InputStreamReader(
		    powerShellProcess.getInputStream()));
		 
		  while ((line = stdout.readLine()) != null) {
			  Utils.print(line);
		  }
		  stdout.close();
		  
		 
		  
		  BufferedReader stderr = new BufferedReader(new InputStreamReader(powerShellProcess.getErrorStream()));
		  
		  while ((line = stderr.readLine()) != null) {
			  Utils.print(line);
		  }
		  
		  stderr.close();
		  Utils.print("Cordova project has been created successfully.");
	
	 }
	 
	
	 public boolean checkCordova(String env) {
		 Utils.print("Cordova installation checking...");
		 try {
			Process powerShellProcess = Runtime.getRuntime().exec(env+"cordova --version");
			BufferedReader stdout = new BufferedReader(new InputStreamReader(
				    powerShellProcess.getInputStream()));
			String version = stdout.readLine();
			stdout.close();
			if(version!=null) {
				Utils.print("Version found: "+version); //stampo la versione eventualmente installata
				return true;
			}
			else
				return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return false;
	 }
	 
	 
	 public void runCordovaProject(String projectDirectory) {
		 
		String command = env + "cd " + projectDirectory + ";"
						+ "cordova run --emulator";
		
		 try {
			Utils.print("Preparing Cordova app on an emulator...");
			Process powerShellProcess=Runtime.getRuntime().exec(command);
			String line;
			BufferedReader stdout = new BufferedReader(new InputStreamReader(
				powerShellProcess.getInputStream()));
			while ((line = stdout.readLine()) != null) {
				Utils.print(line);
			}
			stdout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	 

}
