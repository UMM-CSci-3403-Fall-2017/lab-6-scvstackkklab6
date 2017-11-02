package echoserver;

import java.net.*;
import java.io.*;

/*
 * EchoServer uses a tcp connection to communicate with a client 
 * on the specified port. The server reads client input, and
 * writes it back to the client.
 */

public class EchoServer {

  // Run the server
  public static void main(String[] args) {
    try {
      ServerSocket listener = new ServerSocket(6013);
      System.out.println("EchoServer is listening on port 6013.");

      // Connect to a client
      while (true) { 
         System.out.println("Waiting for client.");

         Socket client = listener.accept();
	 System.out.println("######--Connection established with client @" + client.getInetAddress().toString() + "--######");
	 
	 InputStream in = client.getInputStream();
	 OutputStream out = client.getOutputStream();

	 int nextByte;
	 while ((nextByte = in.read()) != -1) {
	   out.write(nextByte);
	 }

	 in.close();
	 out.close();

	 try {
	   client.close();
	 } catch (IOException e) {
	   System.out.println("Error closing client socket @" + client.getInetAddress().toString() + ".");
	   System.out.println(e);
	 }
      }	
    } catch (IOException e) {
      System.out.println("Error.");
      System.out.println(e);
    }
  }
}
