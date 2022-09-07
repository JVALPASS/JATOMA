package it.unisa.javada;

import java.lang.Process;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;


import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Assignment.Operator;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.text.edits.TextEdit;
import it.unisa.javada.LocalException;
import it.unisa.javada.FileManager;

import it.unisa.javada.Utils;

public class Parser {

	private String _javaVersion = JavaCore.VERSION_1_8;
	private String[] _classpath;
	private String _classpathSeparator;

	public Parser(int javaVersion) {
		Utils.print("File parsing.");
		_classpathSeparator = System.getProperty("path.separator");
		_classpath = java.lang.System.getProperty("java.class.path").split(_classpathSeparator);

		switch (javaVersion) {
		case 1:
			_javaVersion = JavaCore.VERSION_1_1;
			break;
		case 2:
			_javaVersion = JavaCore.VERSION_1_2;
			break;
		case 3:
			_javaVersion = JavaCore.VERSION_1_3;
			break;
		case 4:
			_javaVersion = JavaCore.VERSION_1_4;
			break;
		case 5:
			_javaVersion = JavaCore.VERSION_1_5;
			break;
		case 6:
			_javaVersion = JavaCore.VERSION_1_6;
			break;
		case 7:
			_javaVersion = JavaCore.VERSION_1_7;
			break;
		case 8:
			_javaVersion = JavaCore.VERSION_1_8;
			break;
		default:
			_javaVersion = JavaCore.VERSION_1_8;
			break;
		}
	}

	public void addClasspath(String path) {
		if (FileManager.fileExists(path) || FileManager.directoryExists(path)) {
			String[] nClasspath = new String[_classpath.length + 1];
			System.arraycopy(_classpath, 0, nClasspath, 0, _classpath.length);
			nClasspath[_classpath.length] = new File(path).getAbsolutePath();
			_classpath = new String[nClasspath.length];
			System.arraycopy(nClasspath, 0, _classpath, 0, nClasspath.length);
		}
	}

	public void addClasspaths(String path) {
		if (FileManager.directoryExists(path)) {
			File[] dirContents = new File(path).listFiles(new JarFilenameFilter());
			String[] nClasspath = new String[_classpath.length + dirContents.length];
			System.arraycopy(_classpath, 0, nClasspath, 0, _classpath.length);

			int i = 0;
			for (File f : dirContents) {
				nClasspath[_classpath.length + (i++)] = f.getAbsolutePath();
			}

			_classpath = new String[nClasspath.length];
			System.arraycopy(nClasspath, 0, _classpath, 0, nClasspath.length);

			addClasspath(path);
		}
	}

	public String getClasspath(boolean print) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < _classpath.length; i++) {
			if (i == (_classpath.length - 1))
				sb.append(_classpath[i]);
			else {
				sb.append(_classpath[i] + _classpathSeparator);
				if (print) {
					sb.append("\n");
				}

			}
		}

		return sb.toString();
	}

	public void print() {
		Utils.print("Classpath.");
		Utils.print(this.getClasspath(true));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void parse(String projectPath, String project, String filePath, String fileName, String outputPath) throws LocalException {

		Utils.print("Parsing file:" + fileName + ".java");

		try {
			
			String str = FileManager.readFileToString(projectPath + File.separator + project + File.separator + filePath +
					File.separator + fileName + ".java");

			ASTParser parser = ASTParser.newParser(AST.JLS8);
			parser.setSource(str.toCharArray());

			parser.setResolveBindings(true);
			parser.setBindingsRecovery(true);

			parser.setKind(ASTParser.K_COMPILATION_UNIT);
			parser.setUnitName(project + File.separator + fileName + ".java");
			parser.setEnvironment(_classpath, 
					new String[] { projectPath + File.separator + project + File.separator + filePath }, new String[] { "UTF8" }, true);

			Map<?, ?> options = JavaCore.getOptions();
			JavaCore.setComplianceOptions(_javaVersion, options);
			parser.setCompilerOptions(options);

			Document document = new Document(str);

			final CompilationUnit compilation = (CompilationUnit) parser.createAST(null);

			final AST ast = compilation.getAST();
			final ASTRewrite rewriter = ASTRewrite.create(ast);
			compilation.recordModifications();

			ListRewrite listRewrite;
			Block block;
			Statement statement;

		
			// Add a new import
			/*
			ImportDeclaration id = ast.newImportDeclaration();
			id.setName(ast.newName(new String[] { "it", "unisa","pawt" }));
			id.setOnDemand(true);
			listRewrite = rewriter.getListRewrite(compilation, CompilationUnit.IMPORTS_PROPERTY);
			listRewrite.insertLast(id, null);
            */
			
			// Change a specific import
			List<ImportDeclaration> imports = compilation.imports();
			
			for (ImportDeclaration i : imports) {
				Name oldImport = i.getName();
				
				
				if(oldImport.toString().matches(".*J(Button|Frame|Panel|Menu|ButtonGroup|CheckBox|ComboBox|Dialog|Label"
						+ "|MenuBar|MenuItem|Option|PasswordField|ProgressBar|RadioButton|ScrollPane|Table|TextArea|TextField|Component)")) {
					int index = oldImport.toString().lastIndexOf("J");
					String type = oldImport.toString().substring(index+1);
					Name newImport = ast.newName(new String[] { "it", "unisa", "pawt", "p" + type });
					rewriter.replace(oldImport, newImport, null);
					
				}
				
				if(oldImport.toString().contains("java.awt.event.")) {
					  int index = oldImport.toString().indexOf(".event");
					String type = oldImport.toString().substring(index + 7);
				//	System.out.println("type: "+ type);
					Name newImport = ast.newName(new String[] { "it", "unisa", "pawt",  type });
					rewriter.replace(oldImport, newImport, null);
				
				}
				
				//cambio l'eventuale import della classe Color
				if(oldImport.toString().equals("java.awt.Color")) {
					Name newImport = ast.newName(new String[] { "it", "unisa", "pawt",  "Color" });
					rewriter.replace(oldImport, newImport, null);
				}	
				if(oldImport.toString().equals("java.awt.Font")) {
					Name newImport = ast.newName(new String[] { "it", "unisa", "pawt",  "Font" });
					rewriter.replace(oldImport, newImport, null);
				}
				if(oldImport.toString().equals("java.awt.TextField")) {
					Name newImport = ast.newName(new String[] { "it", "unisa", "pawt", "pTextField"});
					rewriter.replace(oldImport, newImport, null);
				}
				if(oldImport.toString().equals("java.awt.Component")) {
					Name newImport = ast.newName(new String[] { "it", "unisa", "pawt",  "pComponent" });
					rewriter.replace(oldImport, newImport, null);
				}
				
				if(oldImport.toString().equals("java.awt.RadioButton")) {
					Name newImport = ast.newName(new String[] { "it", "unisa", "pawt",  "pRadioButton" });
					rewriter.replace(oldImport, newImport, null);
				}
				if(oldImport.toString().equals("java.awt.Font")) {
					Name newImport = ast.newName(new String[] { "it", "unisa", "pawt",  "Font" });
					rewriter.replace(oldImport, newImport, null);
				}
				if(oldImport.toString().equals("java.awt.ScrollPane")) {
					Name newImport = ast.newName(new String[] { "it", "unisa", "pawt",  "pScrollPane"  });
					rewriter.replace(oldImport, newImport, null);
				}
				if(oldImport.toString().equals("java.awt.Table")) {
					Name newImport = ast.newName(new String[] { "it", "unisa", "pawt",  "pTable" });
					rewriter.replace(oldImport, newImport, null);
				}
				if(oldImport.toString().equals("java.awt.TextArea")) {
					Name newImport = ast.newName(new String[] { "it", "unisa", "pawt",  "pTextArea" });
					rewriter.replace(oldImport, newImport, null);
				}

				if(oldImport.toString().equals("java.awt.MenuItem")) {
					Name newImport = ast.newName(new String[] { "it", "unisa", "pawt", "pMenuItem" });
					rewriter.replace(oldImport, newImport, null);
				}
				if(oldImport.toString().equals("java.awt.Option")) {
					Name newImport = ast.newName(new String[] { "it", "unisa", "pawt",  "pOption" });
					rewriter.replace(oldImport, newImport, null);
				}
				if(oldImport.toString().equals("java.awt.PasswordField")) {
					Name newImport = ast.newName(new String[] { "it", "unisa", "pawt",  "pPasswordField" });
					rewriter.replace(oldImport, newImport, null);
				}
				if(oldImport.toString().equals("java.awt.ProgressBar")) {
					Name newImport = ast.newName(new String[] { "it", "unisa", "pawt",  "pProgressBar"  });
					rewriter.replace(oldImport, newImport, null);
				}
	
				if(oldImport.toString().equals("java.awt.ComboBox")) {
					Name newImport = ast.newName(new String[] { "it", "unisa", "pawt",  "pComboBox"  });
					rewriter.replace(oldImport, newImport, null);
				}
				if(oldImport.toString().equals("java.awt.Dialog")) {
					Name newImport = ast.newName(new String[] { "it", "unisa", "pawt",  "pDialog"  });
					rewriter.replace(oldImport, newImport, null);
				}
				if(oldImport.toString().equals("java.awt.Label")) {
					Name newImport = ast.newName(new String[] { "it", "unisa", "pawt",  "pLabel"  });
					rewriter.replace(oldImport, newImport, null);
				}
				if(oldImport.toString().equals("java.awt.MenuBar")) {
					Name newImport = ast.newName(new String[] { "it", "unisa", "pawt", "pMenuBar" });
					rewriter.replace(oldImport, newImport, null);
				}

				if(oldImport.toString().equals("java.awt.Panel")) {
					Name newImport = ast.newName(new String[] { "it", "unisa", "pawt",  "pPanel"});
					rewriter.replace(oldImport, newImport, null);
				}
				if(oldImport.toString().equals("java.awt.Menu")) {
					Name newImport = ast.newName(new String[] { "it", "unisa", "pawt",  "pMenu" });
					rewriter.replace(oldImport, newImport, null);
				}
				if(oldImport.toString().equals("java.awt.ButtonGroup")) {
					Name newImport = ast.newName(new String[] { "it", "unisa", "pawt",  "ButtonGroup"  });
					rewriter.replace(oldImport, newImport, null);
				}
				if(oldImport.toString().equals("java.awt.CheckBox")) {
					Name newImport = ast.newName(new String[] { "it", "unisa", "pawt",  "pCheckBox" });
					rewriter.replace(oldImport, newImport, null);
				}
				if(oldImport.toString().equals("java.awt.Button")) {
					Name newImport = ast.newName(new String[] { "it", "unisa", "pawt", "pButton"});
					rewriter.replace(oldImport, newImport, null);
				}
				if(oldImport.toString().equals("java.awt.Frame")) {
					Name newImport = ast.newName(new String[] { "it", "unisa", "pawt",  "pFrame"});
					rewriter.replace(oldImport, newImport, null);
				}
				if(oldImport.toString().equals("java.awt.TextField")) {
					Name newImport = ast.newName(new String[] { "it", "unisa", "pawt",  "pTextField"  });
					rewriter.replace(oldImport, newImport, null);
				}
				if(oldImport.toString().equals("java.awt.FlowLayout")) {
					Name newImport = ast.newName(new String[] { "it", "unisa", "pawt",  "FlowLayout"});
					rewriter.replace(oldImport, newImport, null);
				}
				if(oldImport.toString().equals("java.io.File")) {
					Name newImport = ast.newName(new String[] { "it", "unisa", "javada",  "pFile" });
					rewriter.replace(oldImport, newImport, null);
				}
				
				if(oldImport.toString().equals("javax.swing.ButtonGroup")) {
					Name newImport = ast.newName(new String[] { "it", "unisa", "pawt",  "ButtonGroup" });
					rewriter.replace(oldImport, newImport, null);
				}
				
				if(oldImport.toString().equals("javax.swing.table.DefaultTableModel")) {
					Name newImport = ast.newName(new String[] { "it", "unisa", "table",  "DefaultTableModel" });
					rewriter.replace(oldImport, newImport, null);
				}
				
				if(i.isOnDemand() && (oldImport.toString().equals("javax.swing") || oldImport.toString().equals("java.awt"))) {
					  System.out.println("Entrata 6 Name import: " + oldImport);
					/*ImportDeclaration id = ast.newImportDeclaration();
					id.setName(ast.newName(new String[] { "it", "unisa","pawt" }));
					id.setOnDemand(true);
					listRewrite = rewriter.getListRewrite(compilation, CompilationUnit.IMPORTS_PROPERTY);
					listRewrite.insertLast(id, null);*/
					
					//int index = oldImport.toString();
					//String type = oldImport.toString().substring(index + 7);
				//	System.out.println("type: "+ type);
					Name newImport = ast.newName(new String[] { "it", "unisa", "pawt" });
					rewriter.replace(oldImport, newImport, null);
				
				}
				
				
			}			// Aggiungo l'import pComponent
			
			            
			ImportDeclaration id = ast.newImportDeclaration();
			id.setName(ast.newName(new String[] { "it", "unisa","pawt","pComponent" }));
			id.setOnDemand(false);
			listRewrite = rewriter.getListRewrite(compilation, CompilationUnit.IMPORTS_PROPERTY);
			listRewrite.insertLast(id, null);
			TypeDeclaration type = (TypeDeclaration) compilation.types().get(0);
			
			
			compilation.accept(new ASTVisitor() {
				 
				//boolean first = true;
				
				public boolean visit(SimpleType node) {
					
					String JType = node.getName().toString();
					
					Assignment as = null;
					//ExpressionStatement es2 = null;
					boolean first = true;
					
					if((JType.equals("JFrame")||JType.equals("Frame")) && first && node.getParent() instanceof ClassInstanceCreation ) {						
						first = false;
						ClassInstanceCreation classinstance = (ClassInstanceCreation) node.getParent();
					
						if(classinstance.getParent() instanceof Assignment) {
						
							as = (Assignment) classinstance.getParent();
							String variableName = as.getLeftHandSide().getStructuralProperty(SimpleName.IDENTIFIER_PROPERTY).toString();
							Assignment assignment = ast.newAssignment();
						    assignment.setLeftHandSide(ast.newSimpleName("mf1"));
						    assignment.setOperator(Operator.ASSIGN);
						    assignment.setRightHandSide(ast.newSimpleName(variableName));
						    ExpressionStatement es = ast.newExpressionStatement(assignment);
						    Block block =  (Block) as.getParent().getParent();
						    
						    ListRewrite listRewrite = rewriter.getListRewrite(block, Block.STATEMENTS_PROPERTY);
							listRewrite.insertAfter(es, as.getParent(), null);
						
						}
						
						else if(classinstance.getParent() instanceof ExpressionStatement) {
							//es = (ExpressionStatement) classinstance.getParent();
						
							Assignment assignment = ast.newAssignment();
						    assignment.setLeftHandSide(ast.newSimpleName("mf1"));
						    assignment.setOperator(Operator.ASSIGN);
						    
						    ClassInstanceCreation cic = (ClassInstanceCreation) rewriter.createCopyTarget(classinstance);
						    assignment.setRightHandSide(cic);
						    ExpressionStatement es = ast.newExpressionStatement(assignment);
						    
						    
						    Block block =  (Block) classinstance.getParent().getParent();
						    
						    
						 //   ListRewrite lr = rewriter.getListRewrite(block, Block.STATEMENTS_PROPERTY);
						   // lr.insertFirst(es, null);
						    
						 
						    ListRewrite listRewrite = rewriter.getListRewrite(block, Block.STATEMENTS_PROPERTY);
							listRewrite.insertAfter(es, classinstance.getParent(), null);
							listRewrite.remove(classinstance.getParent(), null);
							
						
						}
						
						else if(classinstance.getParent() instanceof VariableDeclarationFragment) {
							
						
							VariableDeclarationFragment vdf = (VariableDeclarationFragment) classinstance.getParent();
							
							String variableName = vdf.getName().toString();
							Assignment assignment = ast.newAssignment();
						    assignment.setLeftHandSide(ast.newSimpleName("mf1"));
						    assignment.setOperator(Operator.ASSIGN);
						    assignment.setRightHandSide(ast.newSimpleName(variableName));
						    ExpressionStatement es = ast.newExpressionStatement(assignment);
						    Block block =  (Block) classinstance.getParent().getParent().getParent();
						    
						    ListRewrite listRewrite = rewriter.getListRewrite(block, Block.STATEMENTS_PROPERTY);
							listRewrite.insertAfter(es, classinstance.getParent().getParent(), null);
							
							
						}
				
							
						
					}
					
					
					if(JType.matches(".*J(TextField|Panel|Button|Frame|CheckBox|ButtonGroup|ComboBox|Component|Dialog|"
							+ "Label|Menu|MenuBar|MenuItem|Option|PasswordField|ProgressBar|RadioButton|ScrollPane|TextArea|Table)")) 
					{
						String type = JType.substring(JType.lastIndexOf("J") + 1);
						rewriter.set(node, SimpleType.NAME_PROPERTY, ast.newSimpleName("p" + type), null);
					}
					
					if(JType.equals("File")) {
						rewriter.set(node, SimpleType.NAME_PROPERTY, ast.newSimpleName("pFile"), null);
					}
					if(JType.matches("(TextField|Panel|Button|Frame|CheckBox|ButtonGroup|ComboBox|Component|Dialog|"
							+ "Label|Menu|MenuBar|MenuItem|Option|PasswordField|ProgressBar|RadioButton|ScrollPane|TextArea|Table)")) 
					{

						String type = JType;
						rewriter.set(node, SimpleType.NAME_PROPERTY, ast.newSimpleName("p" + type), null);
					}
					
					return false; // do not continue 
				}
				
			
			});
	 
		
			

			// Add a new field variable
			
			VariableDeclarationFragment fragment = ast.newVariableDeclarationFragment();
			fragment.setName(ast.newSimpleName("mf1"));
			fragment.setInitializer(ast.newNullLiteral());

			FieldDeclaration fieldDeclaration = ast.newFieldDeclaration(fragment);
			fieldDeclaration.setType(ast.newSimpleType(ast.newSimpleName("pComponent")));
			fieldDeclaration.modifiers().add(ast.newModifier(ModifierKeyword.PRIVATE_KEYWORD));
			listRewrite = rewriter.getListRewrite(type, TypeDeclaration.BODY_DECLARATIONS_PROPERTY);
			listRewrite.insertFirst(fieldDeclaration, null);
			
			// Add a new method
			MethodDeclaration methodDeclaration = ast.newMethodDeclaration();
			methodDeclaration.setConstructor(false);
			List modifiers = methodDeclaration.modifiers();
			modifiers.add(ast.newModifier(ModifierKeyword.PUBLIC_KEYWORD));

			methodDeclaration.setName(ast.newSimpleName("getTopComponent"));
			methodDeclaration.setReturnType2(ast.newSimpleType(ast.newSimpleName("pComponent")));
			block = ast.newBlock();

			listRewrite = rewriter.getListRewrite(block, Block.STATEMENTS_PROPERTY);
			statement = (Statement) rewriter.createStringPlaceholder("//Do not remove!", ASTNode.EMPTY_STATEMENT);
			listRewrite.insertLast(statement, null);
			
			listRewrite = rewriter.getListRewrite(block, Block.STATEMENTS_PROPERTY);
			statement = (Statement) rewriter.createStringPlaceholder("return mf1;", ASTNode.RETURN_STATEMENT);
			listRewrite.insertLast(statement, null);
			
			methodDeclaration.setBody(block);

			listRewrite = rewriter.getListRewrite(type, TypeDeclaration.BODY_DECLARATIONS_PROPERTY);
			listRewrite.insertLast(methodDeclaration, null);

			try {
				
				TextEdit edits = rewriter.rewriteAST(document, null);
				edits.apply(document);

				String writeFilePath = outputPath + File.separator + fileName + ".java";
						//outputPath + File.separator + project + File.separator + fileName;
				
				String writeDirectoryPath = writeFilePath.substring(0, writeFilePath.lastIndexOf(File.separator));
				
				
				
				if(!FileManager.directoryExists(outputPath + File.separator + project))
					FileUtils.copyDirectory(new File(projectPath + project), new File(outputPath + File.separator + project));
				FileManager.writeDirectory(writeDirectoryPath);
				
				//FileManager.writeFile(writeFilePath, document.get());
				String ParsedFileDirectory = outputPath + File.separator + project + File.separator + filePath;
						//"src\\it\\unisa\\run\\mini";
				String ParsedFilePath = ParsedFileDirectory + File.separator + fileName + ".java";
								
			
				FileManager.writeFile(ParsedFilePath, document.get());
				
				
				FileUtils.copyDirectory(new File(".\\res\\bin\\it\\unisa\\pawt"), new File(outputPath + File.separator + project + File.separator + "bin\\it\\unisa\\pawt"));
				FileUtils.copyDirectory(new File(".\\res\\src\\it\\unisa\\pawt"), new File(outputPath + File.separator + project + File.separator + "src\\it\\unisa\\pawt"));
				
				FileUtils.copyDirectory(new File(".\\res\\bin\\it\\unisa\\javada"), new File(outputPath + File.separator + project + File.separator + "bin\\it\\unisa\\javada"));
				FileUtils.copyDirectory(new File(".\\res\\src\\it\\unisa\\javada"), new File(outputPath + File.separator + project + File.separator + "src\\it\\unisa\\javada"));
				
				FileUtils.copyDirectory(new File(".\\res\\bin\\it\\unisa\\table"), new File(outputPath + File.separator + project + File.separator + "bin\\it\\unisa\\table"));
				FileUtils.copyDirectory(new File(".\\res\\src\\it\\unisa\\table"), new File(outputPath + File.separator + project + File.separator + "src\\it\\unisa\\table"));
				
				 String os = System.getProperty("os.name");
				  String env;
				  
				  if(os.toLowerCase().startsWith("window")) 
					  env = "powershell.exe ";
				  else env = "//bin//bash";		
				
				String command = 
						  
				env + " cd " + outputPath + File.separator + project + File.separator + "src" + ";"+
						"javac "
				+ "-cp . it\\unisa\\pawt\\*.java it\\unisa\\javada\\*java " + filePath.substring(4) + "\\*.java  -d " + outputPath + File.separator + project
				+ "\\bin";
				
				//javac -classpath "[jarname with specified path]" [java filename]
				  		
				  // Executing the command
				
				  Process powerShellProcess = Runtime.getRuntime().exec(command);
				  // Getting the results
				  powerShellProcess.getOutputStream().close();
				
				  String line;
				 
				  BufferedReader stdout = new BufferedReader(new InputStreamReader(
				    powerShellProcess.getInputStream()));
				 
				  while ((line = stdout.readLine()) != null) {
					  System.out.println(line);
				  }
				  stdout.close();
				  
				  BufferedReader stderr = new BufferedReader(new InputStreamReader(powerShellProcess.getErrorStream()));
				  
				  while ((line = stderr.readLine()) != null) {
					  System.out.println(line);
				  }
				  
				  stderr.close();
				 
				  Utils.print("Parsing executed.");
				
				

			} catch (BadLocationException ble) {

			}
		} catch (IOException ioe) {
			throw new LocalException("Error parsing file '" + fileName + ".java" + "': " + ioe.getMessage());
		}
	}
	

}
