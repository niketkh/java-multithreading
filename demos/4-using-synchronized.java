public class App {

	private int count = 0;

	public static void main(String args[]) {
		App app = new App();
		app.doWork();
	}

	public synchronized void increment() {
		count++;
	}

	public void doWork() {

		// Increment operation is not a single step operation
		// Without any synchronization, the value of count may or may not be 20000
		// To solve this problem, we can either use AtomicInteger class instead of int along with volatile keyword
		// Or we can also solve the problem using synchronized keyword

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				for(int i=0; i<10000; i++) {
					// count++;
					increment();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				for(int i=0; i<10000; i++) {
					// count++;
					increment();
				}
			}
		});

		t1.start();
		t2.start();

		try {
			// Wait until t1 is finished
			t1.join();

			// Wait until t2 is finished
			t2.join();
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Count: " + count);
	}
}