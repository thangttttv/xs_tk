package com.veso.util;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.Hashtable;

public class WebReader {
	
	private DataInputStream in = null;

	private DataOutputStream out = null;

	// e.g: "HTTP/1.1 200 OK"
	private String requestType = null;

	// key in lowercase
	private Hashtable headers = null;

	// http body
	private byte[] content = null;

	public WebReader() {
		this.headers = new Hashtable();
	}

	// ---------------------------------------------------
	public String getRequestType() {
		return requestType;
	}

	public Hashtable getHeaders() {
		return headers;
	}

	public byte[] getContent() {
		return content;
	}

	public String getHeader(String name) {
		String key = (String) headers.get(name);
		return key;
	}

	// ---------------------------------------------------

	// Getting data from URL: http://server:port/uri/...
	public void doGet(String server, String uri, int port) {
		try {
			// Socket client = new Socket("localhost", 80);
			System.out.println("Prepare Connect via socket...");
			Socket client = new Socket(server, port);
			System.out.println("Connected via socket.");
			client.setKeepAlive(true);
			this.in = new DataInputStream(client.getInputStream());
			this.out = new DataOutputStream(client.getOutputStream());

			// out.writeBytes("GET /vn/logo/top_logo.jsp HTTP/1.0\r\n");
			// --> http://localhost/vn/xxx
			out.writeBytes("GET " + uri + " HTTP/1.0\r\n");

			/* Optional Parameters */
			out.writeBytes("Accept: image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*\r\n");
			out.writeBytes("Accept-Language: en-us\r\n");
			out.writeBytes("Accept-Encoding: gzip, deflate\r\n");
			out.writeBytes("Host: " + server + "\r\n");
			out.writeBytes("User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)\r\n");
			out.writeBytes("Connection: Keep-Alive\r\n");

			/* This Header is very important and can't be ignored */
			out.writeBytes("Content-Length: 0\r\n\r\n");
			out.flush();

			/*******************************************************************
			 * int ch; while ( (ch = in.read()) >= 0) { System.out.print( (char)
			 * ch); }
			 ******************************************************************/

			// /////////////////////////////
			parseRequest();
			// /////////////////////////////

			out.close();
			in.close();
			client.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	// ==========================================================================
	// ==========================================================================
	// From the input stream we extract the message and all the headers
	// (including Nokia HTTP extension headers)
	// Sequence:
	// 1. Request-Type =
	// POST /uri HTTP/1.1
	// GET /uri HTTP/1.1
	// 2. HTML Headers:
	// Content-Type: application/vnd.wap.mms-message
	// Content-Length: 22489
	// User-Agent: Java/1.4.1
	// Host: localhost:7000
	// Accept: text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2
	// Connection: keep-alive
	//
	// 3. MM Content (if POST request):...
	// IN : this.in
	// --------------------------------------------------------------------------
	private boolean parseRequest() {
		byte[] match = { 13, 10, 13, 10 };

		int pos, ch;
		int index = 0;
		String line = "";
		try {
			while ((ch = in.read()) >= 0) {
				// The HTTP headers and the message body are separated by
				// blank line (a sequence of CR>LF>). In hex mode,
				// this shows as a sequence '0D0A0D0A' or {13, 10, 13, 10}
				if (ch == match[index] && index <= 3) {
					index++;
				} else {
					index = 0;
				}
				if (index == 4) {
					content = readHTTPContent();
					// System.out.println("===================================");
					// System.out.println(new String(content));
					break;
				}

				// 2. Gets the request headers
				line += (char) ch;
				if ((line.length() > 2) && (ch == '\n')) {
					pos = line.indexOf(':');
					if (pos != -1) {
						// <header-name>:<header-value>\n
						String name = line.substring(0, pos); // .toLowerCase();
						String value = line.substring(pos + 1, line.length()).trim();
						this.setHeader(name, value);
					} else {
						// 1. Request Type
						// e.g: GET / HTTP/1.1 or GET /mmsc HTTP/1.1
						// <-> http://server/ or http://server/mmsc
						this.setRequestType(line.substring(0, line.length()).trim());
					}
					line = "";
				}
			}
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	// --------------------------------------------------------------------------
	// Store and print header to screen
	private void setHeader(String key, String value) {
		this.headers.put(key.toLowerCase(), value);
		// System.out.println(key + ": " + value);
	}

	// --------------------------------------------------------------------------
	private byte[] readHTTPContent() throws IOException {
		ByteArrayOutputStream baosContent = new ByteArrayOutputStream();
		int contentLength = 0;
		try {
			contentLength = Integer.parseInt((String) headers.get("content-length"));
		} catch (Exception ex) {
			contentLength = 1024 * 1024;
		}

		int bytesToRead = 0;
		int bytesRead = 0;
		int totalBytesRead = 0;
		int bufferSize = 1024;
		byte[] buffer = new byte[bufferSize];

		if (contentLength < bufferSize) {
			bytesToRead = contentLength;
		} else {
			bytesToRead = bufferSize;
		}
		do {
			try {
				bytesRead = in.read(buffer, 0, bytesToRead);
			} catch (InterruptedIOException e) {
				/* comms read timeout expired, no problem */
				System.out.println("Timeout reading from socket");
			}
			if (bytesRead == -1) {
				in.close();
				// throw new IOException("Connection was closed by client.");
				break;
			} else if (bytesRead > 0) {
				// ////////////////////////////////////
				baosContent.write(buffer, 0, bytesRead);
				// ////////////////////////////////////
				totalBytesRead += bytesRead;
			}
			// Left bytes to read
			if (contentLength - totalBytesRead > bufferSize) {
				bytesToRead = bufferSize;
			} else {
				bytesToRead = contentLength - totalBytesRead;
			}
		} while (totalBytesRead < contentLength);
		// /////////////////////////////////////
		return baosContent.toByteArray();
		// /////////////////////////////////////
	}

	private void setRequestType(String s) {
		this.requestType = new String(s);
		// System.out.println(s);
	}

	public static String fullUrl(String input, String sDomain, String sAction, String type) {
		if (StringTool.isEmptyOrNul(input) 
				|| StringTool.isEmptyOrNul(sDomain)) return "";
		String responseContent = input;
		
		String fullDomain = "http://" + sDomain;
		
		responseContent = StringTool.replaceString(responseContent, "src=\"/", "src=\"" + fullDomain + "/");
		responseContent = StringTool.replaceString(responseContent, "src='/", "src='" + fullDomain + "/");
		responseContent = StringTool.replaceString(responseContent, "href=\"/", "href=\"" + fullDomain + "/");
		responseContent = StringTool.replaceString(responseContent, "href='/", "href='" + fullDomain + "/");
		responseContent = StringTool.replaceString(responseContent, "href='" + fullDomain + "/", "?newsid=");
		responseContent = StringTool.replaceString(responseContent, "href=\"" + fullDomain + "", "href=\"?type="+(StringTool.isEmptyOrNul(type)?"":type)+"&act="+sAction+"&newsid=");
		responseContent = StringTool.replaceString(responseContent, "\"/", fullDomain + "/");
		
		return responseContent;
		
	}
	
	public static String filterContent(String data, String startTag, String endTag, String closeTag) {
		
		String sTemp = "";
		String result = "";
		try {
			int startPos = data.indexOf(startTag);
			int endPos = 0;
			
			if (startPos == 0) {
				return "";
			} else {
				sTemp = data.substring(startPos);
				endPos = sTemp.indexOf(endTag);
				if (endPos > 0) {
					sTemp = sTemp.substring(0, endPos);
				}
			}
			result = sTemp + closeTag;
		} catch (Exception e) {
			result = data;
		}
		
		return result;
	}
	
	public static String processImages(String data, String replStr) {
		if (StringTool.isEmptyOrNul(data)) return "";
		String sTemp1 = "";
		String oldData = data;
		StringBuffer result = new StringBuffer("");
		try {
			int pos = data.indexOf("src=\"");
			if (pos < 0) return data;
			while (true) {
				sTemp1 = data.substring(0, pos);
				data = data.substring(pos);
				result.append(sTemp1);
				if (!data.startsWith("src=\"http://")) {
					data = data.replaceFirst("src=\"", "src=\""+replStr);
				}
				pos = data.indexOf("src=", "src=\"".length());
				if (pos < 0) break;
			}
			result.append(data);
		} catch (Exception e) {
			result = new StringBuffer(oldData);
		}
		return result.toString();
	}
	
	public static String removeTagWith(String data, String startTagRemove, String endTag) {
		if (StringTool.isEmptyOrNul(data)) return "";
		if (StringTool.isEmptyOrNul(startTagRemove) || StringTool.isEmptyOrNul(endTag)) return data;
		String result = data;
		try {
			int pos1 = data.indexOf(startTagRemove);
			if (pos1 < 0) return data;
			int pos2 = data.indexOf(endTag, pos1);
			if (pos2 > pos1) {
				result = data.substring(0, pos1) + data.substring(pos2+endTag.length());
			}
		} catch (Exception e) {
		}
		return result;
	}
	
	public static void main(String args[]) {
		WebReader webReader = new WebReader();
		webReader.doGet("www.vnexpress.net", "/Vietnam/The-gioi/", 80);
		System.out.println(new String(webReader.getContent()));
	}

}
