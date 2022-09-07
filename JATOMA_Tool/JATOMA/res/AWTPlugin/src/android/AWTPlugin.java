package it.unisa.run;

import it.unisa.pawt.*;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.Manifest;
import android.content.Context;
import android.util.Log;

import java.io.InputStream;

import java.util.ArrayList;

import java.util.Scanner;



public class AWTPlugin extends CordovaPlugin {

	static AWTPlugin instance;

	public static AWTPlugin getInstance() {
		if(instance == null) {
			instance = new AWTPlugin();
		}

		return instance;
	}

	private Object ciao;
	private static pFrame component;
	//-private Hashtable<String, pFrame> table = new Hashtable<String, pFrame>();
	private ArrayList<String> nameFrame;
	private ArrayList<String> nameClass;

	public AWTPlugin()
			//throws FileNotFoundException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException
	{

		AWTPlugin.instance = this;

		nameFrame = new ArrayList<String>();
		nameClass = new ArrayList<String>();


		/*BufferedReader reader = null;
		try {
			reader = new BufferedReader(
					new InputStreamReader(context.getAssets().open("form.txt"), "UTF-8"));

			String mLine;
			while ((mLine = reader.readLine()) != null) {
				righe.add(mLine);
			}

		}

		catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					System.out.println("IMPOSSIBILE CHIUDERE IL FILEREADER");
				}
			}
		}*/

	//}
		/*try {
			FileReader a = new FileReader("com.example.hello.form.txt");
			Scanner b = new Scanner(a);

			//estraggo dal file tutte le righe salvandole come stringhe in un ArrayLIst
			while (b.hasNext()) {
				String w = b.nextLine();
				righe.add(w);
			}
		}

		catch (FileNotFoundException e) {
			System.out.println("File non trovato!!!!!!!!!!!!!!!");

			e.printStackTrace();
		}*/

			String file = "res/raw/form.txt";
			InputStream is = this.getClass().getClassLoader().getResourceAsStream(file);
			Scanner b = new Scanner(is);

			//estraggo dal file tutte le righe salvandole come stringhe in un ArrayLIst
			while (b.hasNext()) {
				String w = b.nextLine();
				nameClass.add(w.substring(0,w.indexOf("-")));
				nameFrame.add(w.substring(w.indexOf("-")+1));
			}



/*		for(int i=0; i<righe.size();i++) {
			table.put(righe.get(i), istanzia(righe.get(i)));
		}*/

		/*ciao = new Hello();
		component = (pFrame) ((Hello) ciao).getTopComponent();*/
		//table.put("Hello", istanzia("com.example.hello.mini.Hello"));
		}
	public void setFrame(pFrame c){
		component=c;
		for(int i=0; i<nameClass.size(); i++) {
			if (nameFrame.get(i).equals(component.getName())) {
				viewPage(nameClass.get(i));
			}
		}
	}

	public void viewPage(String path) {

				String pagina = path.substring(path.lastIndexOf(".") + 1);
				CordovaWebView wv = Constants.view;
				wv.loadUrl("file:///android_asset/www/" + pagina + ".html");
		        Log.d("awtrun", "viewPage: ok");


		/*ArrayList<pComponent> p=component.getComponents();
		for(int i=0; i<p.size();i++) {
			if (p.get(i).getClass() != null && p.get(i).getClass() == pTextField.class) {
				pTextField a = (pTextField) p.get(i);
				if (a.getText() != null) {
					a.setText(a.getText());
				}
			}
		}*/


	}


	/* public pFrame istanzia(String nome){

		pFrame comp = null;
		try{
			Class<?> cls = Class.forName(nome);
			ciao = cls.newInstance();
			Method method = ciao.getClass().getDeclaredMethod("getTopComponent", null);
			method.setAccessible(true);
			comp = (pFrame) method.invoke(ciao, null);
		}

		catch(ClassNotFoundException e)  {
			e.printStackTrace();
		}
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}

		return comp;
	}*/
	
	public Context getContext(){
		return this.cordova.getActivity().getApplicationContext();

	}
	
	public void requestExternalStoragePermission(){

		String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
		cordova.requestPermissions(this, 0, permissions);

	}

	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		try {
			Constants.view = webView;
			Log.d("awtrun", "Plugin action call! : " + action);

			if (action.equals("insert")) {
				JSONObject arg_object = args.getJSONObject(0);
				String id = arg_object.getString("id");
				String value = arg_object.getString("element");
				//String nome = arg_object.getString("nome");
				//pFrame component = table.get(nome);
				component.change(id, value);



			}

			else if (action.equals("load")) {
				//String nome = arg_object.getString("nome");
				//pFrame component = table.get(nome);
				Log.d("awtrun", "sono in load");
				component.reload();
			}

			else if (action.equals("fire")) {
				JSONObject arg_object = args.getJSONObject(0);
				String id = arg_object.getString("id");
				//String nome = arg_object.getString("nome");
				//pFrame component = table.get(nome);
				Log.d("awtrun", component.getName());
				Log.d("awtrun", "ID: " + id);
				component.fire(id);
 			}

			else if (action.equals("firee")) {

				JSONObject arg_object = args.getJSONObject(0);

				String id = arg_object.getString("id");
				String value = arg_object.getString("element");
				//String nome = arg_object.getString("nome");
				//pFrame component = table.get(nome);
				component.fire(id, value);
			}

			else if (action.equals("fireRow")) {
				JSONObject arg_object = args.getJSONObject(0);
				String id = arg_object.getString("id");
				String row = arg_object.getString("row");
				//pFrame component = table.get(nome);
				component.fireRow(id, row);
			}
			else if (action.equals("fireCell")) {
				JSONObject arg_object = args.getJSONObject(0);
				String id = arg_object.getString("id");
				String row = arg_object.getString("row");
				String column = arg_object.getString("column");
				//pFrame component = table.get(nome);
				component.fireCell(id, row, column);
			}

			else if (action.equals("insertTable")) {
				JSONObject arg_object = args.getJSONObject(0);
				String id = arg_object.getString("id");
				String row = arg_object.getString("row");
				String column = arg_object.getString("column");
				String value = arg_object.getString("value");
				//pFrame component = table.get(nome);
				component.change(id, row, column, value);
			}

			else if (action.equals("start")){
				//String pagina2= righe.get(0).substring(righe.get(0).lastIndexOf(".")+1);
				//webView.loadUrl("file:///android_asset/www/"+pagina+".html");
				Log.d("awtrun", nameClass.get(0));
				String pagina = nameClass.get(0);
				Class<?> cls = Class.forName(pagina);
				ciao = cls.newInstance();
				/*Method method = ciao.getClass().getDeclaredMethod("getTopComponent", null);
				method.setAccessible(true);
				component = (pFrame) method.invoke(ciao, null);
				component.setVisible(pagina2);*/
							}


						return true;

		} catch (Exception e) {
			callbackContext.error(e.getMessage());
			return false;
		}
	}
}
