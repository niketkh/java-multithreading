// Create a second thread
class NewThread extends Thread {
    NewThread() {
        // Create a new, second thread
        super("Demo Thread");
        System.out.println("Child Thread: " + this);
        // Start the thread
        start();
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