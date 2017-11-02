package echoserver;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class EchoClient {
  
  public static void main(String[] args) {
    
    String hostname;

    if (args.length == 0) {
      hostname = "localhost";
    }

    else {
      hostname = args[0];
    }

    try {
      Socket socket = new Socket(hostname, 6013);
	 
      InputStream sin = System.in;
      OutputStream sout = System.out;
      
      InputStream streamIn = socket.getInputStream();
      OutputStream streamOut = socket.getOutputStream();
	 	 
      int nextByte;
      while ((nextByte = sin.read()) != -1) {
        streamOut.write(nextByte);
	nextByte = streamIn.read();
	sout.write(nextByte);
      } 

      sin.close();
      sout.close();

      streamIn.close();
      streamOut.close();

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
