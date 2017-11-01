package echoserver;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class EchoClient {
  
  public static void main(String[] args) {
    String hostname;

    if (args.length == 0) {
      System.out.println("Error: Forgot to supply hostname argument.");
      return;
    }
    
    else {
       try {
         hostname = args[0];
	 Socket socket = new Socket(hostname, 50000);
	 
	 InputStream in = socket.getInputStream();
	 OutputStream out = socket.getOutputStream();
	 byte[] buffer = new byte[4096];
	 
	 int count;
	 while ((count = in.read(buffer)) > 0) {
	   out.write(buffer, 0, count);
	 } 

	 in.close();
	 out.close();

	 try {
	    socket.close();
	  } catch (IOException e) {
	    System.out.println("Error closing socket @" + socket.getInetAddress().toString() + ".");
	    System.out.println(e);
	  }

        } catch (IOException e) {
	  System.out.println("Error.");
	  System.out.println(e);
	}
    }
  } 
}
