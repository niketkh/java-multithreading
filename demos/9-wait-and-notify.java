import java.util.Scanner;

/**
 * wait() tells the calling thread to give up the lock and go to sleep (not polling) until
 * some other thread enters the same lock and calls notify()
 * 
 * notify() wakes up the first thread that called wait() on
 * the same object. 
 */
class Processor {

    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer thread running ....");
            wait();
            System.out.println("Resumed.");
        }
    }

    public void consume() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);
        synchronized (this) {
            System.out.println("Waiting for return key.");
            scanner.nextLine();
            System.out.println("Return key pressed.");
            // calling notify() does not release the lock but 
            // only wakes up the thread that called wait on the same object
            notify();
            Thread.sleep(5000);
            System.out.println("Consumption done.");
        }
    }
}

class App {
    public static void main(String args[]) throws InterruptedException {
        final Processor processor = new Processor();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.produce();
                } catch (InterruptedException ignored) {}
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consume();
                } catch (InterruptedException ignored) {}
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}