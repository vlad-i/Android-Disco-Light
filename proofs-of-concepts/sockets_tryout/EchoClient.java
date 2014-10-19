import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class EchoClient {
    public static void main(String[] args) throws IOException,
	    ClassNotFoundException {

	if (args.length != 2) {
	    System.err
		    .println("Usage: java EchoClient <host name> <port number>");
	    System.exit(1);
	}

	String hostName = args[0];
	int portNumber = Integer.parseInt(args[1]);

	try (Socket echoSocket = new Socket(hostName, portNumber);
		ObjectInputStream objInputStream = new ObjectInputStream(
			echoSocket.getInputStream());
		ObjectOutputStream objOutput = new ObjectOutputStream(
			echoSocket.getOutputStream());
		BufferedReader stdIn = new BufferedReader(
			new InputStreamReader(System.in));) {
	    String userInput;

	    while ((userInput = stdIn.readLine()) != null) {
		ArrayList<String> list = new ArrayList<String>();
		list.add(userInput);
		objOutput.writeObject(list);
		Object secondList = objInputStream.readObject();
		System.out.println("echo: " + secondList + " of type "
			+ secondList.getClass());
	    }
	} catch (UnknownHostException e) {
	    System.err.println("Don't know about host " + hostName);
	    System.exit(1);
	} catch (IOException e) {
	    System.err.println("Couldn't get I/O for the connection to "
		    + hostName);
	    System.exit(1);
	}
    }
}
