package it.unisa.javada;

import java.util.List;

public class Process {
	private Parameters _params;
	private Project _project;
	private Parser _parser;

	public Process(String[] args) {
		try {
			Info(true);

			_params = new Parameters(args, this.getClass().getName());
			Utils.setDebug(_params.isDebug());
			_params.print();

			_project = new Project(_params.getProjectPath());
			_project.print();
			List<String> files = _project.getSourceFiles();
			for(String s: files) {
				Utils.print("Source file:"+s);
			}

			_parser = new Parser(_params.getJavaVersion());
			_parser.addClasspath(_project.getLibraryPath());
			_parser.addClasspath(_project.getBinaryPath());
			_parser.print();
			
			Utils.resetList();
			/*
			for (String s : files) {
				try {	
						//_parser.parseFile(_project.getProjectPath(), _project.getProjectName(), _project.getSourcePath(), s);
				} catch (LocalException e) {
					Utils.print(e);
				}			
			}*/
			Info(false);
		} catch (LocalException e) {
			Utils.print(e);
			System.exit(1);
		}
	}

	private void Info(boolean start) throws LocalException {
		if(start) {
			Utils.print("*** " + Constants.appName + " ***");
			Utils.print("*** " + Constants.appAcro + " ***");
		} else {
			Utils.print("*** End " + Constants.appAcro + " ***");
		}
	}

	public static void main(String[] args) throws Exception {
		new Process(args);
		System.exit(0);
	}
}
