// App.java
public class App {
	public static void main(String args[]) {
		(new Worker()).main();
	}
}


// Worker.java

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Worker {

	private Random random = new Random();
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	private List<Integer> l1 = new ArrayList<>();
	private List<Integer> l2 = new ArrayList<>();

	// If we use synchronized keyword on methods
	// then we would not be able to parallelize stageOne and stageTwo
	// As Worker object has only one lock
	// To solve this we use synchronized code blocks on different objects
	public void stageOne() {
		synchronized(lock1) {
			try {
				Thread.sleep(1);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
			l1.add(random.nextInt(100));
		}
	}

	public synchronized void StageTwo() {
		synchronized(lock2) {
			try {
				Thread.sleep(1);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
			l2.add(random.nextInt(100));
		}
	}

	public void process() {
		for(int i=0; i<1000; i++) {
			stageOne();
			stageTwo();
		}
	}

	public void main() {
		System.out.prinln("Main");

		long start = System.currentTimeMillis();

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				process();
			}
		});


		Thread t2 = new Thread(new Runnable() {
			public void run() {
				process();
			}
		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		System.out.println("Time taken: " + (end-start));
		System.out.pritln("List 1: " + l1.size() + "; List 2: " + l2.size());
	}
}