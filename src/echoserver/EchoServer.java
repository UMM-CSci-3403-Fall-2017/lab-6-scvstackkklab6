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
      ServerSocket listener = new ServerSocket(50000);
      System.out.println("EchoServer is listening on port 50000.");

      // Connect to a client
      while (true) { 
	 System.out.println("Waiting for client.");

         Socket client = listener.accept();
	 System.out.println("######--Connection established with client @" + client.getInetAddress().toString() + "--######");
	 
	 InputStream in = client.getInputStream();
	 OutputStream out = client.getOutputStream();
         byte[] buffer = new byte[4096];

	 int count;
	 while ((count = in.read(buffer)) > 0) {
	   out.write(buffer, 0, count);
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
