/********************************************************************************
 * File name: InetImage.java
 * 
 * @author tienpq
 * 
 * Copyright (c) 2007 iNET Media Co .Ltd
 * Create date Apr 11, 2007
 ********************************************************************************/

package com.veso.util;

import java.awt.Image;

import javax.swing.ImageIcon;

public class InetImage {
    
    private String pathImage = "";
    Image image = null;
    
    
    public InetImage(String pathImage){
        this.pathImage = pathImage;
    }
    
    public int getWidth(){
        
        int width = -1;
        
	    image = new ImageIcon(pathImage).getImage();		    
	    width = image.getWidth(null);
	    
		return width;
    }
    
    public int getHeight(){
        int heigth = -1;
        
	    image = new ImageIcon(pathImage).getImage();		    
	    heigth = image.getHeight(null);
	    
		return heigth;
    }
    
    public static void main(String[] args) {
		InetImage img = new InetImage("C:\\Documents and Settings\\tienpq.INET0\\My Documents\\My Pictures\\AiThanh.jpg");
		Logger.writeln(String.valueOf(img.getWidth()));
		Logger.writeln(String.valueOf(img.getHeight()));
    }
}
