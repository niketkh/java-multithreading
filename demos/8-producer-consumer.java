/*

 * Producer-Consumer is the situation where one or more threads are producing
 * data items and adding them to a shared data store of some kind while one or
 * more other threads process those items, removing them from the data store.

*/

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class App {
    
    /**
     * Thread safe implementation of {@link java.util.Queue} data structure so
     * you do not need to worry about synchronization.
     * More specifically {@link java.util.concurrent.BlockingQueue}
     * implementations are thread-safe. All queuing methods are atomic in nature
     * and use internal locks or other forms of concurrency control. If
     * BlockingQueue is not used queue is shared data structure either
     * {@code synchronized} or {@code wait() notify()} should be used.
     * Java 1.5 introduced a new concurrency library {@link java.util.concurrent}
     * which was designed to provide a higher level abstraction over
     * the wait/notify mechanism.
     */
    public static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
    
    public static void main(String args[]) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    producer();
                } catch (InterruptedException ignored) {}
            }
        });
        
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    consumer();
                } catch (InterruptedException ignored) {}
            }
        });
        
        t1.start();
        t2.start();
        
        // Pause for 1 seconds before force quitting the app 
        // As we are looping infinitely
        Thread.sleep(1000);
        System.exit(0);
    }
    
    public static void producer() throws InterruptedException {
        Random random = new Random();
        
        while(true) {
            // If queue is full waits
            queue.put(random.nextInt(100));
        }
    }
    
    public static void consumer() throws InterruptedException {
        while(true) {
            Thread.sleep(100);
            // If queue is empty waits
            Integer value = queue.take();
            System.out.println("Taken value: " + value);
            System.out.println("Queue size: " + queue.size());
        }
    }
}




// Output:

/*

Taken value: 30
Queue size: 10
Taken value: 52
Queue size: 10
Taken value: 46
Queue size: 10
Taken value: 74
Queue size: 10
Taken value: 69
Queue size: 10
Taken value: 87
Queue size: 10
Taken value: 64
Queue size: 10
Taken value: 26
Queue size: 10
Taken value: 19
Queue size: 10
Taken value: 48
Queue size: 9

*/