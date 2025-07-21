package com.cliniterra.util;
/**
 * @author Aayush Shrestha
 */
import java.io.File;
import java.io.IOException;

import jakarta.servlet.http.Part;

public class ImageUtil {
	
	
	public String getImageName(Part part) {
		
		//get the content-disposition header from part
		
		
		
		String contentDis = part.getHeader("content-disposition");// form-data ; name = 'profilepic'; filename='prof.jpg'
		
		String[] item = contentDis.split(";"); 
		
		String imageName = null; 
		
		//finds the file name and assigns it to image name
		for (String s: item) {
			if(s.trim().startsWith("filename")) {
				imageName = s.substring(s.indexOf("=") + 2, s.length()-1);
			}
		}
		
		if (imageName == null || imageName.isEmpty()) {
			// Assign a default file name if none was provided
			imageName = "download.png";
		}
		
		return imageName;
		
	}
	
	public boolean uploadImage(Part part, String rootPath, String saveFolder) {
		
		String savePath = getSavePath(saveFolder);
		File fileSaveDir = new File(savePath);
		
		if (!fileSaveDir.exists()) { //if doesnt exists
			if(!fileSaveDir.mkdir()) {
				return false; // cant make dir
			}
		}
		
		try {
			String imageName = getImageName(part);
			
			String filepath = savePath + "/" + imageName;
			
			part.write(filepath); 
			return true;
		}
		catch (IOException e) {

		    System.err.println("Error uploading image: " + e.getMessage());
			e.printStackTrace(); // Log the exception
			return false; // Upload failed
		}
	}
	
	public String getSavePath(String saveFolder) {
		return "C:/Users/ayush/eclipse-workspace/Cliniterra/src/main/webapp/resources/images/" + saveFolder + "/";
	}
}
