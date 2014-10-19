import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException,
	    ClassNotFoundException {

	if (args.length != 1) {
	    System.err.println("Usage: java EchoServer <port number>");
	    System.exit(1);
	}

	int portNumber = Integer.parseInt(args[0]);

	try (ServerSocket serverSocket = new ServerSocket(portNumber);
		Socket clientSocket = serverSocket.accept();
		ObjectOutputStream objOut = new ObjectOutputStream(
			clientSocket.getOutputStream());
		ObjectInputStream objIn = new ObjectInputStream(
			clientSocket.getInputStream());) {
	    Object inputObject;
	    while ((inputObject = objIn.readObject()) != null) {
		objOut.writeObject(inputObject);
	    }
	    System.out.println("Server ended.");
	} catch (IOException e) {
	    System.out
		    .println("Exception caught when trying to listen on port "
			    + portNumber + " or listening for a connection");
	    System.out.println(e.getMessage());
	}
    }
}
