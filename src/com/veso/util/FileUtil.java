/*******************************************************************************
 * File name: FileUtil.java
 * @author Nguyen Thien Phuong 
 * Copyright (c) 2007 iNET Media Co .Ltd 
 * Created on Jun 30, 2007 10:33:28 AM
 ******************************************************************************/

package com.veso.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
    /*
     * Reads a file from the hard drive.
     *
     */
    public static String readFile(String filename)
    {
	    File f = new File(filename);
	    String tmp = null;
	    try {
    	    int size = (int) f.length();
    	    FileInputStream in = new FileInputStream(f);
    	    int bread = 0;
    	    byte[] data = new byte[size];
    	    while (bread < size)
        		    bread += in.read(data, bread, size-bread);
    	    tmp = new String(data, 0);
    	    in.close();
	    } catch(IOException e) {
	    }
        return tmp;
    }
 
    public static void createFile(String filename) throws IOException {
    	File f = new File(filename);
    	f.createNewFile();
    }
    
    /*
     * Save the file on the hard disk. Returns falure;
     * unsuccessful.
     */
    public static boolean saveFile(String filename, String buffer)
    {
	    try {
		    File f = new File(filename);
    	    FileOutputStream out = new FileOutputStream(f);
    	    byte[] data = new byte[buffer.length()+1];
    	    buffer.getBytes(0, buffer.length(), data, 0);
    	    out.write(data, 0, buffer.length());
    	    out.close();
	    } catch(IOException e) {
	    	e.printStackTrace();
		    return false;
	    }
	    return true;
    }
    
    public static void main(String[] args) {
		try {
			FileUtil.createFile("C:\\test.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
