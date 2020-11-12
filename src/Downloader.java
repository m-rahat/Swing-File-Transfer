import java.util.*;
import java.net.*;
import java.io.*;

public class Downloader {

	public void download(String ip, String p, String path) throws UnknownHostException, IOException {
		/*
		 * Open up a socket, then get the byte array using a buffered input stream then
		 * use a file output stream to write out the file
		 */
		
	    String hostName = ip;
//		private final static String hostName = "localhost";
		int port = Integer.parseInt(p);
		String fileOutput = path;
		
		
		long start = System.currentTimeMillis();
		Socket socket = new Socket(hostName, port);
		System.out.println("Connected to Server");
		BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
		System.out.println("Transferring file...");
		byte[] byteArray = bis.readAllBytes();
		FileOutputStream fos = new FileOutputStream(fileOutput);
		fos.write(byteArray);
		System.out.println("Transfer Complete");
		long end = System.currentTimeMillis();
		System.out.println("Download time: " + (end - start) / 1000);
		socket.close();
	}
}
