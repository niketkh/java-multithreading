// Suspending and Resuming a thread the modern way
class NewThread implements Runnable {
    String name;
    Thread t;
    boolean suspendFlag;
    
    NewThread(String name) {
        this.name = name;
        t = new Thread(this, name);
        System.out.println("New Thread: " + t);
        // Start the thread
        t.start();
    }
    
    // Entry point for thread
    public void run() {
        try {
            for(int i=15; i>0; i--) {
                System.out.println(name + ": " + i);
                Thread.sleep(100);
                synchronized(this) {
                    while(suspendFlag) {
                        wait();
                    }
                }
            }
        }
        catch(InterruptedException e) {
            System.out.println(name + " Interruped");
        }
        System.out.println(name + " exiting");
    }
    
    synchronized void suspendThread() {
        suspendFlag = true;
    }
    
    synchronized void resumeThread() {
        suspendFlag = false;
        notify();
    }
}

class Main {
    public static void main(String args[]) {
        NewThread ob1 = new NewThread("One");
        NewThread ob2 = new NewThread("Two");
        
        try {
            Thread.sleep(500);
            ob1.suspendThread();
            System.out.println("Suspending One");
            Thread.sleep(500);
            ob1.resumeThread();
            System.out.println("Resuming One");
            ob2.suspendThread();
            System.out.println("Suspending Two");
            Thread.sleep(500);
            ob2.resumeThread();
            System.out.println("Resuming Two");
            
            // Wait for threads to finish
            ob1.t.join();
            ob2.t.join();
        }
        catch(InterruptedException e) {
            System.out.println("Main Interrupted");
        }
        
        System.out.println("Main exiting");
    }
}



// Output:
/*

New Thread: Thread[One,5,main]
New Thread: Thread[Two,5,main]
One: 15
Two: 15
One: 14
Two: 14
One: 13
Two: 13
One: 12
Two: 12
One: 11
Two: 11
Suspending One
Two: 10
Two: 9
Two: 8
Two: 7
Two: 6
Resuming One
Suspending Two
One: 10
One: 9
One: 8
One: 7
One: 6
Resuming Two
Two: 5
One: 5
Two: 4
One: 4
Two: 3
One: 3
Two: 2
One: 2
Two: 1
One: 1
Two exiting
One exiting
Main exiting

*/




