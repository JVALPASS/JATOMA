package it.unisa.pawt;
import org.apache.cordova.CordovaWebView;

public class pLabel extends pComponent{
	String text;
	ActionListener _actionListener;
	Font f;
	//String _value;
	
	public pLabel(String title){
		_title = title;
		_type = "pLabel";
		text= title;
		f = null;
	}
	
	public void addActionListener(ActionListener actionListener) {
		 this._actionListener = actionListener;
		
	}
	
	public void fire() {
		if(_actionListener != null){
			this._actionListener.actionPerformed(new ActionEvent(this, _title));
		}
	}

	public void setRoot(pComponent root){
		super.setRoot(root);
	}
	
	public void setText(String value) {

				String _value = value;


				try {
					CordovaWebView wv = Constants.view;
					if(wv != null){
						wv.sendJavascript("app.setta('"+_title+"','"+_value+"')");
				//Log.d("sono in if", "appsetta : " + _title);
						}	
				} catch(Exception e) {}
			}
	
	public void setVisible(boolean value) {
		
	}
	public void setFont(Font f) {
		this.f = f;
	}//renderlo dinamico
}