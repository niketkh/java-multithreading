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

class MultiThreadDemo {
    public static void main(String args[]) {
        new NewThread("One");
        new NewThread("Two");
        new NewThread("Three");
        
        try {
            // Wait for other threads to end
            Thread.sleep(2000);
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
Three: 5
One: 4
Two: 4
Three: 4
One: 3
Two: 3
Three: 3
One: 2
Two: 2
Three: 2
One: 1
Two: 1
Three: 1
One exiting
Two exiting
Three exiting
Main thread exiting

*/