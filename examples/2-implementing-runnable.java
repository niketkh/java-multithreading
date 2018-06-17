// Create a second thread
class NewThread implements Runnable {
    
    Thread t;
    
    NewThread() {
        // Create a new, second thread
        t = new Thread(this, "Demo Thread");
        System.out.println("Child Thread: " + t);
        // Start the thread
        t.start();
    }
    
    // This is the entry point for the second thread
    public void run() {
        try {
            for(int i=5; i>0; i--) {
                System.out.println("Child Thread: " + i);
                Thread.sleep(100);
            }
        }
        catch(InterruptedException e) {
            System.out.println("Child thread Interrupted");
        }
        System.out.println("Exiting Child Thread");
    }
}

class ThreadDemo {
    public static void main(String args[]) {
        // Create a new thread
        new NewThread();
        
        try {
            for(int i=5; i>0; i--) {
                System.out.println("Main Thread: " + i);
                Thread.sleep(200);
            }
        }
        catch(InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }
        
        System.out.println("Exiting Main Thread");
    }
}


// Output:
/*

Child Thread: Thread[Demo Thread,5,main]
Main Thread: 5
Child Thread: 5
Child Thread: 4
Main Thread: 4
Child Thread: 3
Child Thread: 2
Main Thread: 3
Child Thread: 1
Exiting Child Thread
Main Thread: 2
Main Thread: 1
Exiting Main Thread

*/