import java.util.*;
import java.net.*;
import java.io.*;

public class Uploader {
	

	
	public void upload(String filePath, String p) throws IOException {
		/*
		 * Take a file, read it into a byte array using the File Input Stream
		 * Then send that byte array using a Buffered Output Stream
		 * Done
		 */
		String fileToSend = filePath;
		int port = Integer.parseInt(p);
		
		ServerSocket serverSocket = new ServerSocket(port);
		Socket clientSocket = serverSocket.accept();
		System.out.println("Connected to IP: " + clientSocket.getInetAddress());
		BufferedOutputStream bos = new BufferedOutputStream(clientSocket.getOutputStream());
		
		File file = new File(fileToSend);
		FileInputStream fis = new FileInputStream(file);
		byte[] byteArray = fis.readAllBytes();
		long start = System.currentTimeMillis();
		System.out.println("Transferring Files...");
		bos.write(byteArray);
		long end = System.currentTimeMillis();
		System.out.println("Writing/Uploading time " + (end - start)/1000);
		System.out.println("Transfer Complete");
		bos.flush();
		bos.close();
	}
	/*
	public static void main(String [] args) throws IOException {
		//Dealing with connection stuff here

		ServerSocket serverSocket = new ServerSocket(port);
		Socket clientSocket = serverSocket.accept();
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(clientSocket.getOutputStream());
		
		//Creating a new file
		File file = new File(fileToSend);
		//This line below takes the file to an input stream
		FileInputStream fis = new FileInputStream(file);
		byte[] byteArray = new byte[(int) file.length()];
//		System.out.println(byteArray.length);
		//Make new buffered input stream with the file input stream inside
		BufferedInputStream bis = new BufferedInputStream(fis);
		//line below will read the input stream and into the byte array
		bis.read(byteArray);
		
		//Sending the file
		bufferedOutputStream.write(byteArray);
		bufferedOutputStream.flush();
		bufferedOutputStream.close();
	}
	*/
}
