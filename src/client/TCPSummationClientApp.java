package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class TCPSummationClientApp {

	public static void main(String[] args) {

		System.out.println("\n\tExecuting TCPSummationClientApp");

		// Sample data
		int number1 = 25;
		int number2 = 50;
		int number3 = 75;

		try {

			// 1. Define server information
			int serverPortNo = 8087;
			InetAddress serverAddress = InetAddress.getLocalHost();

			// 2. Connect to the server
			Socket socket = new Socket(serverAddress, serverPortNo);

			// 3. Send data to the server
			OutputStream outStream = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(outStream);
			dos.writeInt(number1);
			dos.writeInt(number2);
			dos.writeInt(number3);

			// 4. Process response from the server
			InputStream inStream = socket.getInputStream();
			DataInputStream dis = new DataInputStream(inStream);
			int resultSum = dis.readInt();
			int resultMult = dis.readInt();

			// 5. Display the result
			System.out.println("\tSending to the server: " + number1 + ", " + number2 + ", " + number3);
			System.out.println("\tReceived total summation from server: " + resultSum);
			System.out.println("\tReceived total multiplication from server: " + resultMult);

			// Close all the closables
			dis.close();
			dos.close();
			socket.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		System.out.println("\tEnd of execution at TCPSummationClientApp");
	}
}