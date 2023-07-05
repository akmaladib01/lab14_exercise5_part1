package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import controller.NumberCalculator;

public class TCPSummationServerApp {

	public static void main(String[] args) {

		System.out.println("\n\tExecuting TCPSummationServerApp");

		try {

			// 1. Bind to a port
			int portNo = 8087;
			ServerSocket serverSocket = new ServerSocket(portNo);

			NumberCalculator calculator = new NumberCalculator();

			System.out.println("\tWaiting for request");

			// 2. Listen to requests
			while (true) {

				// 3. Accept request
				Socket clientRequest = serverSocket.accept();

				// 4. Process client request - read three numbers from client
				InputStream inStream = clientRequest.getInputStream();
				DataInputStream dis = new DataInputStream(inStream);
				int number1 = dis.readInt();
				int number2 = dis.readInt();
				int number3 = dis.readInt();
				System.out.println("\tFrom client: " + number1 + ", " + number2 + ", " + number3);

				// Further processing
				int totalMultiplication = calculator.numberMultiplication(number1, number2, number3);
				int totalSum = calculator.numberSum(number1, number2, number3);

				// 5. Respond to client - return total sum and total multiplication to client
				OutputStream outStream = clientRequest.getOutputStream();
				DataOutputStream dos = new DataOutputStream(outStream);
				dos.writeInt(totalSum);
				dos.writeInt(totalMultiplication);
				System.out.println("\tTo client, total summation of the numbers: " + totalSum);
				System.out.println("\tTo client, total multiplication of the numbers: " + totalMultiplication);

				System.out.println("\n\tWaiting for next request");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}