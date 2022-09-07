package it.unisa.javada;

import java.io.File;
import android.os.Environment;
import it.unisa.run.AWTPlugin;

public class pFile extends File{
	
	public pFile(String filename) {
		
		super(Environment.getExternalStorageDirectory(), (filename.replace(":", "")).replace("\\", "/"));
		
		AWTPlugin.getInstance().requestExternalStoragePermission();

		
	}

}
