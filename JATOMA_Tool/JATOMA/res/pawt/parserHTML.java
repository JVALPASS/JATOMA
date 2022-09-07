package it.unisa.pawt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.LineNumberInputStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.FormattableFlags;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class parserHTML {
	private static Document doc;
	private static Element head;
	private static Element body;
	//private static Element diiv;
	private static HashMap<String, Element> frames = new HashMap<String, Element>();
	private static HashMap<String, Element> elements = new HashMap<String, Element>();
	private static Set<Set<String>> _partition;
	public static String buttonEvent="app.fire(id)";
	public static String close="dialog.close();";
	public static String comboEvent = "app.firee(id,document.getElementById(id).value)";
	public static String textEvent="app.insert(id,document.getElementById(id).value)";
	private static Element header;
	private static Element tag_div;
	private static Element span_menu;
	private static ArrayList<Element> listaElementi = new ArrayList<Element>();
	private static ArrayList<Element> listaOption = new ArrayList<Element>();
	public static String tableCSS = "css/tableCSS.css";
	public static PrintWriter pw;
	public parserHTML(){
	}
	
	public static void addmenu(ArrayList<pMenu> bar){
					
			tag_div = doc.createElement("div");
			body.appendChild(tag_div);
			tag_div.setAttribute("id", "myNav");
			tag_div.setAttribute("class", "frame overlay");
			
			
			Element a = doc.createElement("a");
			a.appendChild(doc.createTextNode("x"));
			tag_div.appendChild(a);
			a.setAttribute("href", "javascript:void(0)");
			a.setAttribute("class", "closebtn");
			a.setAttribute("onclick", "closeNav()");
			
			
			span_menu = doc.createElement("span");
			span_menu.setAttribute("class", "overlay-content");
			
			tag_div.appendChild(span_menu);
			
			//Element li = doc.createElement("li");
			//ul.appendChild(li);
			
			parse_menu(bar);
			
			/*
				Element viewport = doc.createElement("META");
				viewport.setAttribute("name", "viewport");
				viewport.setAttribute("content", "user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width, height=device-height, target-densitydpi=device-dpi");
				head.appendChild(viewport);
								
				body = doc.createElement("BODY");
				body.setAttribute("onhashchange", "updateFooter();");
				body.setAttribute("onload", "defaultFrame();");
				rootElement.appendChild(body);
			*/
			
	}
	
	
	public static void parse_menu(ArrayList<pMenu> bar){
		int i;
		
		
		Element ul = doc.createElement("ul"); //System.out.println("<ul>"); 
		span_menu.appendChild(ul);
		for(i=0;i<bar.size();i++){
				
				Element li = doc.createElement("li"); //System.out.println("<li>"); 
				ul.appendChild(li);
				li.appendChild(doc.createTextNode("- " + bar.get(i).getName()  + ": "));
				li.setAttribute("onclick", "insert_menu(this)");
				li.setAttribute("class", "colorMenu");
				li.setAttribute("style", "color: #ecf503;");
				//System.out.println(); 
				if(bar.get(i).has_item())
					parse_menu_item(li, bar.get(i).getArray_menu());
				
				//System.out.println("</li>");	
		}
		//System.out.println("</ul>"); 
	}
	
	
	
	private static void parse_menu_item(Element tag_li, ArrayList<pMenuItem> pmenui){
		int i;
	
		Element ul = doc.createElement("ul"); //System.out.println("<ul>"); 
		tag_li.appendChild(ul);
		ul.setAttribute("hidden", "true");
		for(i=0;i<pmenui.size();i++){
				
				Element li = doc.createElement("li"); //System.out.println("<li>"); 
				ul.appendChild(li);
				li.appendChild(doc.createTextNode(pmenui.get(i).getName()) ); //System.out.println(pmenui.get(i).getName());
				li.setAttribute("id", pmenui.get(i).getName() );	
				li.setAttribute("onclick", "app.fire(id)");
				li.setAttribute("class", "subnav");
				if(pmenui.get(i).has_item())
					parse_menu_item(li, pmenui.get(i).getArray_menu());
				
				//System.out.println("</li>"); 
				
		}
		//System.out.println("</ul>"); 
	}
	
	public static void  printDom(ComponentNode cn){
		System.out.println(cn.getData());
		ArrayList<ComponentNode> childs= cn.get_children();
		System.out.println(childs);
		for(ComponentNode aNode:childs){
			
			printDom(aNode);
			
		}
		System.out.println("/"+cn.getData());
		
	}
	


	
	public static void parse(pComponent c){
		
		if(c instanceof pFrame){
			for(pComponent component : ((pFrame) c).getComponents()) //tutti i pComponent del pFrame
				parse(component);
		} else if(c instanceof pPanel){ //AGGIUNGO I PANNELLI, con all'interno i pComponent
			for(pComponent component : ((pPanel) c).getComponents())
				parse(component);
		} else if(c instanceof pComboBox){ 
			for(pComponent component : ((pComboBox) c).getComponents()){
				c.create();
				parse(component);
			}
			
		}else if(c instanceof pDialog){ 
//			System.out.println(c.getClass().getName());
			
			c.create();
			
			for(pComponent component : ((pDialog) c).getComponents()){
				parse(component);
			}
		}else{
			
			if((c instanceof pMenuBar)||(c instanceof pMenuItem)||(c instanceof pMenu)){
				return;
			}
			
			System.out.println("creating: "+c.getName());
			//System.out.println("parent: " +c.getRoot().getName()+"  "+ c.getRoot().getClass().getName());
			c.create(); //chiamo il metodo create di ogni pComponent dell'applicazione. 
			
		}
	}


	//CREO HTML -> HEAD -> BODY, INIZIALIZZO IL SET _PARTITION
	public static void createPage(String title, Set<Set<String>> partition){
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("HTML");
			head = doc.createElement("HEAD");
			doc.appendChild(rootElement);
			addCss("css/index.css");
			addCss("css/ionic.css");
			addCss("css/ionicons.min.css");
			addCss("css/menu/menulaterale.css");
			addCss(tableCSS);
			
			rootElement.appendChild(head);
			
			Element viewport = doc.createElement("META");
			viewport.setAttribute("name", "viewport");
			viewport.setAttribute("content", "user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width, height=device-height");
			head.appendChild(viewport);
			
			
			Element title1 = doc.createElement("TITLE");
			title1.appendChild(doc.createTextNode(title));
			head.appendChild(title1);
			
			body = doc.createElement("BODY");
			body.setAttribute("onhashchange", "updateFooter();");
			body.setAttribute("onload", "defaultFrame();");
			rootElement.appendChild(body);
			//_partition = partition;
			File f = new File(tableCSS);
			if(f.exists()) f.delete();
		
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	public static void createPage(String title){
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("HTML");
			head = doc.createElement("HEAD");
			doc.appendChild(rootElement);
			addCss("css/index.css");
			addCss("css/ionic.css");
			addCss("css/ionicons.min.css");
			addCss("css/menu/menulaterale.css");
			addCss(tableCSS);
			
			rootElement.appendChild(head);
			
			Element viewport = doc.createElement("META");
			viewport.setAttribute("name", "viewport");
			viewport.setAttribute("content", "user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width, height=device-height");
			head.appendChild(viewport);
			
			
			Element title1 = doc.createElement("TITLE");
			title1.appendChild(doc.createTextNode(title));
			head.appendChild(title1);
			
			body = doc.createElement("BODY");
			body.setAttribute("onhashchange", "updateFooter();");
			body.setAttribute("onload", "defaultFrame();");
			rootElement.appendChild(body);
			
			//diiv= doc.createElement("div");
			//diiv.setAttribute("id", nome);
			//body.appendChild(diiv);
			File f = new File(tableCSS);
			if(f.exists()) f.delete();
		
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	
	public static Element addFrame(String title){
		Element frame = doc.createElement("DIV");
		frame.setAttribute("id", title);
		frame.setAttribute("class", "frame");
		
		Element div_menu = doc.createElement("div");
		frame.appendChild(div_menu);
		div_menu.setAttribute("class", "scritta_menu");
		div_menu.setAttribute("onclick", "openNav()");
		//div_menu.appendChild(doc.createTextNode("||| MENU"));
		div_menu.appendChild(doc.createTextNode(""));
		return frame;
	}
	
	
	
	//AGGIUNGO L'HEADER DELLA BARRA - RUBRICA - DOVE DOVREBBE ANDARE IL MENU
	public static void addHeader(String title){
		/*
		SAMPLE IONIC CODE
		<div id="title" class="bar bar-header bar-positive">
    		<h1 class="title">Java AWT Calc</h1>
		</div>
		*/
		Element header = doc.createElement("DIV");
		header.setAttribute("id", "title");
		header.setAttribute("class", "bar bar-header bar-positive");
		
		Element h1 = doc.createElement("H1");
		h1.setAttribute("class", "title");
		
		h1.appendChild(doc.createTextNode(title));
		header.appendChild(h1);
		body.appendChild(header);
	}
	
	private static void addFooter(){
		Element footer = doc.createElement("DIV");
		Element framesBullets = doc.createElement("DIV");
		Element buttonLeft = doc.createElement("BUTTON");
		Element buttonRight = doc.createElement("BUTTON");
		footer.setAttribute("id", "footer");
		buttonLeft.setAttribute("id","buttonLeft");
		buttonRight.setAttribute("id","buttonRight");
		framesBullets.setAttribute("id", "framesBullets");
		framesBullets.setAttribute("class", "title" );
		footer.setAttribute("class", "bar bar-positive bar-footer");
		buttonLeft.setAttribute("class", "hide button button-clear icon-left ion-android-arrow-dropleft-circle");
		buttonRight.setAttribute("class", "hide button button-clear icon-right ion-android-arrow-dropright-circle");
		footer.appendChild(buttonLeft);
		footer.appendChild(framesBullets);
		footer.appendChild(buttonRight);
		body.appendChild(footer);
	}

	private static void addScript(String path){
		Element script= doc.createElement("SCRIPT");
		script.setAttribute("type", "text/javascript");
		script.setAttribute("src", path);
		body.appendChild(script);
	}

	private static void addCss(String path){
		Element css = doc.createElement("LINK");
		css.setAttribute("rel", "stylesheet");
		css.setAttribute("type", "text/css");
		css.setAttribute("href", path);
		head.appendChild(css);
		
	}
	
	public static void addButton(String name){
		Element button = doc.createElement("BUTTON");
		button.setAttribute("type", "button");
		button.setAttribute("class", "button");
		button.setAttribute("id", name);
		button.appendChild(doc.createTextNode(name));
		button.setAttribute("onClick", buttonEvent);
		
//		if(root != null)
//			root.appendChild(button);
//		else 
//			System.out.println("root null");
		
		elements.put(name, button);
		
	}
	
	
	public static Element addComboBox(String name) {
		Element c = doc.createElement("SELECT");
		c.setAttribute("id", name);
		c.setAttribute("onchange", comboEvent);
		int k= listaOption.size();
		int j=0;
		for(int i=0;i<k-j;i++){
			Element popzione= listaOption.get(i);
			
			c.appendChild(popzione);
			j=j+2;
		}
		
		elements.put(name, c);
		
		
		return c;
	}
	public static void add_opzione(pOption pOption){
		String title=pOption._title;
		String value= pOption._value;
		Element option = doc.createElement("option");
		option.setAttribute("value", value);
		option.setAttribute("id", value);
		option.appendChild(doc.createTextNode(value));
		listaOption.add(option);
		
		
	}

	public static void addpOption(String value) {
		Element option = doc.createElement("option");
		option.setAttribute("value", value);
		option.setAttribute("id", value);
		option.appendChild(doc.createTextNode(value));
		elements.put(value, option);
	}
	
	public static void addRadioButton(String name) {
		
		Element button = doc.createElement("INPUT");
        button.setAttribute("type", "radio");
        button.setAttribute("name", "name");
        button.setAttribute("value", "value");
        button.setAttribute("id", name);
        button.appendChild(doc.createTextNode(name));
        button.setAttribute("onClick", buttonEvent);
        elements.put(name, button);
    }
	public static void addCheckBox(String name) {
		Element button = doc.createElement("input");
		button.setAttribute("type", "checkbox");
		button.setAttribute("name", "name");
		button.setAttribute("value", "value");
		button.setAttribute("id", name);
		button.appendChild(doc.createTextNode(name));
		button.setAttribute("onClick", buttonEvent);
		elements.put(name, button);
	}
	
	public static void addProgressBar(String name, int min, int max, int orientation) {
		Element progressBar = doc.createElement("PROGRESS");
		progressBar.setAttribute("max", Integer.toString(max));
		progressBar.setAttribute("value", Integer.toString(min));
		progressBar.setAttribute("id", name);
		if(orientation==1) {
			//make vertical progress bar
		}
		progressBar.appendChild(doc.createTextNode(name));
		elements.put(name, progressBar);
	}
	
	public static void addTextArea(String name, Integer rows, Integer columns, Element root) {
		Element list = doc.createElement("DIV");
		list.setAttribute("class", "areaditesto");
		Element textArea = doc.createElement("TEXTAREA");
		if(rows!=0 || columns!=0) {
			textArea.setAttribute("rows", Integer.toString(rows));
			textArea.setAttribute("cols", Integer.toString(columns));
		}
		textArea.setAttribute("id", "textarea");
		textArea.setAttribute("style", "border:1px solid black; width:300px; height:150px;");
		textArea.appendChild(doc.createTextNode(name));
		list.appendChild(textArea);
		if(root != null)
			root.appendChild(list);
		else 
//			System.out.println("root null");
		elements.put("textarea", textArea);
	}
	
	
	public static void addPasswordField(String title, String text, Element root) {
		Element passwordField = doc.createElement("INPUT");
		passwordField.setAttribute("type", "password");
		passwordField.setAttribute("placeholder", "password");
		passwordField.setAttribute("id", title);
		passwordField.setAttribute("onkeyup", textEvent);
		passwordField.setAttribute("value", text);
		elements.put(title, passwordField);
	}
	
	public static Element addDialog(String title) {
		
//		System.out.println("dialog creata");
		Element dialog=doc.createElement("Dialog");
		dialog.setAttribute("id", "dialog");
//		if(root != null)
//			root.appendChild(dialog);
//		else 
//			System.out.println("root null");
		for(int i=0;i<listaElementi.size();i++){
			dialog.appendChild(listaElementi.get(i));
		}
		Element button= doc.createElement("BUTTON");
		button.setAttribute("type", "button");
		button.setAttribute("class", "button");
		button.setAttribute("id", "chiudi");
		button.appendChild(doc.createTextNode("chiudi"));
		button.setAttribute("onClick", close);
		dialog.appendChild(button);
//		add_component(dialog);
		
		elements.put(title, dialog);
	
		return dialog;
	}
	public static void aggiungi(pComponent component){
		String type = component._type; 
		String title= component._title;
		String s="";
		String event="";
		if(type=="pTextField"){
			type="INPUT";
			s="onkeyup";
			event=textEvent;	
		}
		else if(type=="pButton"){
			type="BUTTON";
			 s="onClick";
			 event=buttonEvent;
			 }
		else if (type=="pLabel"){
			type="LABEL";
			s="onKeyup";
			event="textEvent";}
		
		Element e=doc.createElement(type);
		e.setAttribute("type", type);
		e.setAttribute("class", type);
		e.setAttribute("id", title);
		e.appendChild(doc.createTextNode(title));
		e.setAttribute(s, event);
		listaElementi.add(e);
		
	}
	
	public static void add_Component(Element dialog){
				Iterator setsIterator = _partition.iterator();
				while(setsIterator.hasNext()){
					Set<String> set = (Set<String>) setsIterator.next();
					Iterator setIterator = set.iterator();
					while(setIterator.hasNext()){
						String key = (String) setIterator.next();
						Element element = elements.get(key);
						if(element!=null){
								dialog.appendChild(element);
						}
						
						
					}
					
					
				}
	}
	

				
	
	public static void addLabel(String name,Element root){
		Element list = doc.createElement("DIV");
		
		Element label = doc.createElement("LABEL");
		
		label.setAttribute("id", name);
		label.setAttribute("class", "label");
		
		label.appendChild(doc.createTextNode(name));
		list.appendChild(label);
		if(root != null)
			root.appendChild(label);
		else 
//			System.out.println("root null");
		
		elements.put(name, label);
	}
	public static void addTable(String title, pTable table){
		Element divHTML = doc.createElement("DIV");
		divHTML.setAttribute("class", "divTab");
		Element tableHTML = doc.createElement("TABLE");
		tableHTML.setAttribute("id", title);
		tableHTML.setAttribute("class", "tab");
		tableHTML.setAttribute("border", "1");
		create_headerTable(tableHTML, table);
		create_rows(tableHTML, table);
		create_tableCSS(table, title);
		divHTML.appendChild(tableHTML);
		elements.put(title, divHTML);
	}
	private static void create_tableCSS(pTable table, String title)
	{
		try {
			File f = new File(tableCSS);
			FileOutputStream fos = new FileOutputStream(f, true);
			if(!f.exists())
			{	
				f.createNewFile();
			}
			pw = new PrintWriter(fos);
			
			//table
			writeCssProperty("#"+table._title,"border-collapse", "collapse");
			writeCssProperty("#"+table._title,"width", "100%");
			writeCssProperty("."+table._title+"-column", "color", table.foreground);
			writeCssProperty("."+table._title+"-column", "border-color", table.gridColor);
			writeCssProperty("."+ table._title+"-column", "background-color", table.background);
			writeCssProperty("."+ table._title+"-row", "height", ""+table.getRowHeight()+"px");
			writeCssProperty("."+table._title+"-column", "border-width", "1px");
			writeCssProperty("."+table._title+"-column", "border-style", "solid");
			writeCssProperty("."+table._title+"-column", "padding-top", ""+table.getRowMargin()+"px");
			writeCssProperty("."+table._title+"-column", "padding-bottom", ""+table.getRowMargin()+"px");
			writeCssProperty("."+table._title+"-column", "padding-left", ""+table.getColumnModel().getColumnMargin()+"px");
			writeCssProperty("."+table._title+"-column", "padding-right", ""+table.getColumnModel().getColumnMargin()+"px");

			//table-select
			writeCssProperty("."+table._title+"-column-selected", "border-width", "1px");
			writeCssProperty("."+table._title+"-column-selected", "border-style", "solid");
			writeCssProperty("."+table._title+"-column-selected", "color", table.selectionForeground);
			writeCssProperty("."+table._title+"-column-selected", "border-color", table.gridColor);
			writeCssProperty("."+ table._title+"-column-selected", "background-color", table.selectionBackground);
			writeCssProperty("."+ table._title+"-row-selected", "height", ""+table.getRowHeight()+"px");
			writeCssProperty("."+table._title+"-column-selected", "padding-top", ""+table.getRowMargin()+"px");
			writeCssProperty("."+table._title+"-column-selected", "padding-bottom", ""+table.getRowMargin()+"px");
			writeCssProperty("."+table._title+"-column-selected", "padding-left", ""+table.getColumnModel().getColumnMargin()+"px");
			writeCssProperty("."+table._title+"-column-selected", "padding-right", ""+table.getColumnModel().getColumnMargin()+"px");

			//header
			writeCssProperty("."+table._title+"-TabHeader", "border-width", "1px");
			writeCssProperty("."+table._title+"-TabHeader", "border-style", "SOLID");
			writeCssProperty("."+table._title+"-TabHeader", "border-color", "BLACK");
			writeCssProperty("."+table._title+"-TabHeader", "background-color", "ALICEBLUE");
			writeCssProperty("."+table._title+"-TabHeader", "text-align", "CENTER");
			writeCssProperty("."+table._title+"-TabHeader", "font-weight", "BOLD");
			writeCssProperty("."+table._title+"-TabHeader", "padding", "5px");
			
		
			pw.close();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void writeCssProperty(String selector, String property, String newValue){
		pw.append(""+selector+"{"+property+":"+newValue+"}");
		pw.println();
	}

	private static void create_headerTable(Element tableHTML, pTable table)
	{
		int num_colonne = table.getColumnCount();
		boolean flagHeader = false;
		Element trHeader = null;
		for(int k=0; k<num_colonne; k++)
		{
			if(k==0) {
				trHeader = doc.createElement("TR");
				trHeader.setAttribute("id", table._title+"-rowHeader");
				flagHeader= true;
			}
			Element th = doc.createElement("TH");
			th.setAttribute("id", table._title+"-colHeader"+k);
			th.setAttribute("class",  table._title+"-tabHeader");
			String colonnaNome = table.getColumnName(k);
			if(colonnaNome == null) th.appendChild(doc.createTextNode(""));
			else th.appendChild(doc.createTextNode(colonnaNome));
			trHeader.appendChild(th);
		}

		if(flagHeader == true) tableHTML.appendChild(trHeader);
	}

	private static void create_rows(Element tableHTML, pTable table)
	{
		int num_righe = table.getRowCount();
		int num_colonne = table.getColumnCount();
		for(int i=0; i<num_righe; i++)
		{
			Element tr = doc.createElement("TR");
			tr.setAttribute("id", table._title+"-row"+i);
			tr.setAttribute("class",  table._title+"-row");

			for(int j=0; j<num_colonne; j++)
			{
				Element td = doc.createElement("TD");
				td.setAttribute("id", table._title+"-col"+i+","+j);
				td.setAttribute("class",  table._title+"-column");
				String str = (String) table.getValueAt(i, j);
				if(str == null) td.appendChild(doc.createTextNode(""));
				else td.appendChild(doc.createTextNode(str));
				tr.appendChild(td);
			}

			tableHTML.appendChild(tr);
		}

	}
	
	
	public static void addRadioButton(String name,Element root) {
		
		System.out.println("Radio button creato");
		Element button = doc.createElement("INPUT");
        button.setAttribute("type", "radio");
        button.setAttribute("name", "name");
        button.setAttribute("value", "value");
        button.setAttribute("id", name);
        button.appendChild(doc.createTextNode(name));
        button.setAttribute("onClick", buttonEvent);
        if(root != null)
			root.appendChild(button);
		else 
//			System.out.println("root null");
		
        elements.put(name, button);
        
    }
	
	
	public static void addTextField(int size, String title, Element root){
		Element list = doc.createElement("DIV");
		list.setAttribute("class", "areaditesto");
		Element label = doc.createElement("LABEL");
		Element textField = doc.createElement("INPUT");
				
		textField.setAttribute("type", "text");
		textField.setAttribute("placeholder", title);
		textField.setAttribute("id", title);
		textField.setAttribute("onkeyup", textEvent);
		
		
		label.appendChild(textField);
		list.appendChild(label);
		if(root != null)
			root.appendChild(list);
		else 
//			System.out.println("root null");
		
		elements.put(title, textField);
	}
	
	public static void writePage(String fl, Set<Set<String>> partition) throws URISyntaxException{
		//individuo i vari insiemi
		_partition = partition;
		Iterator setsIterator = _partition.iterator();
		int id = 0;
		int co = 0;
		while(setsIterator.hasNext()){
			Set<String> set = (Set<String>) setsIterator.next();
			Element frame = addFrame("frame"+id);
			Iterator setIterator = set.iterator();
//			System.out.println(co+++")"+frame);
			int con = 0;
			while(setIterator.hasNext()){
				String key = (String) setIterator.next();
				Element element = elements.get(key);
				//System.out.println("Elemento: "+element.getTagName());
				if(element != null){
//					System.out.println("\t"+con+++")"+element);
						frame.appendChild(element);
						
					
						for(int j=0;j<listaElementi.size();j++){
						
						if(element.getTagName().equals(listaElementi.get(j).getTagName())){
							frame.removeChild(element);
							
							
						}
						frame.appendChild(element);
						
							
						}
					}
				}
			
			//controllo che il frame da aggiungere non sia vuoto
			if(frame.hasChildNodes()){
				frames.put("frame"+id, frame);
				id++;
			}
		}

		//creazione dei frame
		Set<Entry<String, Element>> f = frames.entrySet();
		Iterator i = f.iterator();
		while(i.hasNext()){
			Entry<String, Element> entry = (Entry<String, Element>) i.next();
			body.appendChild(entry.getValue());
		}
		
		addFooter();
		
		//AGGIUNTA DEGLI SCRIPT
		addScript("cordova.js");
		addScript("js/hammer.min.js");
		addScript("js/index.js");
		
		addScript("js/cordova.js"); 
		
		addScript("js/ionic.bundle.min.js");
		addScript("js/menu/menulaterale.js");
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer;

		try {
			transformer = transformerFactory.newTransformer();
		    transformer.setOutputProperty(OutputKeys.METHOD, "html");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(fl);
			transformer.transform(source, result);
		} catch (TransformerException exception) {
			exception.printStackTrace();
		}
	}
	
	public static ArrayList<String> getWidgets(pFrame frame){
		ArrayList<pComponent> c= frame.getComponents();
		ArrayList<String> s= new ArrayList<String>();
		for(int i=0; i<c.size(); i++){
			s.add(c.get(i).getName());
			//System.out.println("Widget in gw: "+s.get(i));
		}
		return s;
	}

	

	

	
}


