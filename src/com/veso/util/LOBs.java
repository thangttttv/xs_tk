package com.veso.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;

/**
 * 
 */
public class LOBs {

	public LOBs() {
	}

	public static void writeClob(Clob myClob, String src) throws IOException,
			SQLException {
		Writer writer = null;

		// writer = ( (weblogic.jdbc.common.OracleClob)
		// myClob).getCharacterOutputStream();
		// writer = ( (oracle.sql.CLOB) myClob).getCharacterOutputStream();

		writer.write(src);
		writer.flush();
		writer.close();
	}

	public static String readClob(Clob myClob) throws IOException, SQLException {
		Reader rd = myClob.getCharacterStream();
		StringBuffer sbuf = new StringBuffer();
		char[] line = new char[255];
		int i = 0;
		do {
			i = rd.read(line);
			if (i != -1) {
				String newline = new String(line, 0, i);
				sbuf.append(newline);
			}
		} while (i != -1);
		return sbuf.toString();
	}

	public static void writeBlob(Blob blob, byte[] src) throws IOException,
			SQLException {
		OutputStream os = null;
		// JDBC 2.0
		// os = blob.getBinaryOutputStream(); //get the output stream from the
		// Blob to insert it
		// JDBC 3.0
		os = blob.setBinaryStream(0); // get the output stream from the Blob
										// to insert it

		os.write(src);
		os.flush();
		os.close();
	}

	public static String readBlob(Blob myBlob) throws IOException, SQLException {
		InputStream is = myBlob.getBinaryStream();
		StringBuffer sbuf = new StringBuffer();
		byte[] line = new byte[1024];
		int i = 0;

		do {
			i = is.read(line);
			if (i != -1) {
				String newline = new String(line, 0, i);
				sbuf.append(newline);

			}
		} while (i != -1);
		return sbuf.toString();
	}

	public static byte[] readByteBlob(Blob myBlob) throws IOException,
			SQLException {
		InputStream is = myBlob.getBinaryStream();
		java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
		byte[] line = new byte[1024];
		int i = 0;
		do {
			i = is.read(line, 0, line.length);

			if (i != -1) {
				bos.write(line, 0, i);
			}
		} while (i != -1);

		byte[] return_value = null;
		return_value = bos.toByteArray();
		return return_value;
	}
}
