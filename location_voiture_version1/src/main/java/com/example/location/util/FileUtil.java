package com.example.location.util;

import java.io.File;

import javafx.stage.FileChooser;

public class FileUtil {
		public static File selectedFile;
	public static String getNameFromFileChooser(FileChooser fileChooser) {
		selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile.exists()) {
			return selectedFile.getName();
		}else {
			return null;
		}
	}
  

}
