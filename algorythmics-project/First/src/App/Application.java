package App;

public class Application {
    public static void main(String args[]) {
	Shared shared = new Shared();
	Read reading = new Read(shared);
	Thread read = new Thread(reading);

	Average calc = new Average(shared);
	Thread calculate = new Thread(calc);
	read.start();
	calculate.start();
    }

}
