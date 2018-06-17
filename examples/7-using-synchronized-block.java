class CallMe {
	
	void call(String msg) {
		System.out.print("[" + msg);
		try {
			Thread.sleep(200);
		}
		catch(InterruptedException e) {
			System.out.println("Interrupted");
		}
		System.out.println("]");
	}
}

class Caller implements Runnable {
	String msg;
	CallMe target;
	Thread t;

	public Caller(CallMe target, String msg) {
		this.target = target;
		this.msg = msg;
		t = new Thread(this);
		t.start();
	}

	public void run() {
		// Without synchronized msg would be printed without the closing square brackets
		// synchronized block prevents other threads from entering call while another thread is using it
		synchronized(target) {
			target.call(msg);
		}
	}
}

class Main {
	public static void main(String args[]) {
		CallMe target = new CallMe();
		Caller ob1 = new Caller(target, "Hello");
		Caller ob2 = new Caller(target, "Synchronized");
		Caller ob3 = new Caller(target, "World");

		// Wait for threads to terminate
		try {
			ob1.t.join();
			ob2.t.join();
			ob3.t.join();
		}
		catch(InterruptedException e) {
			System.out.println("Interrupted");
		}
	}
}



// Output:
/*

[Hello]
[World]
[Synchronized]

*/