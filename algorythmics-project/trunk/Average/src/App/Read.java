package App;

import java.util.Scanner;

public class Read implements Runnable {
    private Shared shared;

    Read(Shared elements) {
	this.shared = elements;
    }

    @Override
    public void run() {
	Scanner stream;
	stream = new Scanner(System.in);
	while (stream.hasNext()) {
	    try {
		shared.setElem(Double.parseDouble(stream.next()));
	    } catch (NumberFormatException e) {
		break;
	    }
	}
	shared.stop();// stops Average thread
	stream.close();
    }
}
