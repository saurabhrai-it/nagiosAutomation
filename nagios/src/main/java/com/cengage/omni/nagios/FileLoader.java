package com.cengage.omni.nagios;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileLoader {
	public static File getFileLocation()
	  {
		  JFileChooser chooser = new JFileChooser();
		    chooser.setCurrentDirectory(new java.io.File("C:\\"));
		    chooser.setDialogTitle("Select Folder");
		    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    chooser.setAcceptAllFileFilterUsed(false);
		    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		  	  return chooser.getSelectedFile();
		    } else {
		      return null;
		    }
	  }
	public static void getErrorMain()
	{
		JOptionPane.showMessageDialog(null, "Please select any folder.");
	}
}
