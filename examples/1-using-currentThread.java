class CurrentThreadDemo {
    public static void main(String args[]) {
        // Get reference to current thread
        Thread t = Thread.currentThread();
        
        // Prints name of the thread, its priority, and the name of its group
        System.out.println("Current thread: " + t);
        
        // Change the name of the thread
        t.setName("My Thread");
        System.out.println("After name change: " + t);
        
        try {
            for(int i=5; i>0; i--) {
                System.out.println(i);
                Thread.sleep(1000);
            }
        }
        catch(InterruptedException e) {
            System.out.println("Main thread interrupted");
        }
    }
}


// Output:
/*

Current thread: Thread[main,5,main]
After name change: Thread[My Thread,5,main]
5
4
3

*/