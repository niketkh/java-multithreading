// Create multiple threads
class NewThread implements Runnable {
    String name; 
    Thread t;
    
    NewThread(String threadName) {
        name = threadName;
        t = new Thread(this, name);
        System.out.println("New Thread: " + t);
        // Start the thread
        t.start();
    }
    
    // This is the entry point for the thread
    public void run() {
        try {
            for(int i=5; i>0; i--) {
                System.out.println(name + ": " + i);
                Thread.sleep(100);
            }
        }
        catch(InterruptedException e) {
            System.out.println(name + " Interrupted");
        }
        
        System.out.println(name + " exiting");
    }
}

class DemoJoin {
    public static void main(String args[]) {
        NewThread ob1 = new NewThread("One");
        NewThread ob2 = new NewThread("Two");
        NewThread ob3 = new NewThread("Three");

        System.out.println("Thread One is alive: " + ob1.t.isAlive());
        System.out.println("Thread Two is alive: " + ob2.t.isAlive());
        System.out.println("Thread Three is alive: " + ob3.t.isAlive());
        
        try {
            System.out.println("Waiting for threads to finish");
            ob1.t.join();
            ob2.t.join();
            ob3.t.join();
        }
        catch(InterruptedException e) {
            System.out.println("Main Thread Interrupted");
        }
        System.out.println("Main thread exiting");
    }
}



// Output:
/*

New Thread: Thread[One,5,main]
New Thread: Thread[Two,5,main]
One: 5
New Thread: Thread[Three,5,main]
Two: 5
Thread One is alive: true
Thread Two is alive: true
Three: 5
Thread Three is alive: true
Waiting for threads to finish
One: 4
Two: 4
Three: 4
Two: 3
One: 3
Three: 3
One: 2
Three: 2
Two: 2
One: 1
Three: 1
Two: 1
One exiting
Two exiting
Three exiting
Thread One is alive: false
Thread Two is alive: false
Thread Three is alive: false
Main thread exiting

*/