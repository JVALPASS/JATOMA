package it.unisa.javada;

import java.nio.CharBuffer;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import it.unisa.javada.Constants;

public class Utils {

	private static boolean _debug = false;
	
	public static void setDebug(boolean debug) {
		_debug = debug;
	}
	
	public static String spaces( int spaces ) {
		  return CharBuffer.allocate( spaces ).toString().replace( '\0', ' ' );
		}
	
	public static void print(String message) {
		
		boolean multiple = message.contains("\n");
		String format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").format(new Timestamp(new java.util.Date().getTime()));
		String base = "["+format+"] "+Constants.appAcro;
		int length = base.length();
		
		if(multiple) {
			String[] messages = message.split("\n");
			System.out.println(base + "::");
			for(String s : messages) {
				System.out.println(spaces(length+3)+s);
			}
			//System.out.println(base + ":]");
			
		} else
			System.out.println(base + ":: " + message);
	}

	public static void printRaw(String message) {		
			System.out.println(message);
	}	
	
	public static void print(Exception e) {
		print("ERROR: "+e.getMessage());
		if(_debug)
			e.printStackTrace();
	}

	private static ArrayList<String> _list = new ArrayList<String>();
	
	public static void resetList() {
		_list.clear();
	}

	public static boolean addToList(String item) {
		if(!_list.contains(item)) {
			_list.add(item);
			return true;
		}	
		return false;
	}	
	
	public static void printList() {
		for(String str: _list) {
			printRaw(str);
		}
	}
	
	public static void parsingWarning() {
		System.out.println("Warning. Invoked method in pawt: "+
	    	       Thread.currentThread().getStackTrace()[2].getMethodName()+
	    	       "\nThis method cannot be parsed yet. Could be available in future developments.");
	}
	
}
