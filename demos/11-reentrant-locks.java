import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * java.util.concurrent.locks.Lock This is the base interface for Lock API. 
 * It provides all the features of synchronized keyword with additional ways 
 * to create different Conditions for locking, providing timeout for thread 
 * to wait for lock. Some of the important methods are Lock.lock() to
 * acquire the lock, Lock.unlock() to release the lock, Lock.tryLock() to 
 * wait for lock for a certain period of time,
 * Lock.newCondition() to create the Condition etc.
 * 
 * java.util.concurrent.locks.ReentrantLock is the most widely used 
 * implementation class of Lock interface. This class implements the
 * Lock interface in similar way as synchronized keyword. 
 * 
 * java.util.concurrent.locks.Condition objects are similar to Object 
 * wait-notify model with additional feature to create different sets of wait. 
 * A Condition object is always created by Lock object. 
 * Some of the important methods are
 * Condition.await() that is similar to Object.wait()
 * Condition.signal() and Condition.signalAll()
 * that are similar to Object.notify() and Object.notifyAll() methods.
 */

class Runner {

    private int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    private void increment() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public void firstThread() throws InterruptedException {
        lock.lock();
        System.out.println("Waiting ....");
        cond.await();
        System.out.println("Woken up!");
        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    public void secondThread() throws InterruptedException {
        Thread.sleep(1000);
        lock.lock();
        System.out.println("Press the return key!");
        new Scanner(System.in).nextLine();
        System.out.println("Got return key!");
        cond.signal();
        try {
            increment();
        } finally {
            //should be written to unlock Thread whenever signal() is called
            lock.unlock();
        }
    }

    public void finished() {
        System.out.println("Count is: " + count);
    }
}

class App {

    public static void main(String[] args) throws Exception {
        final Runner runner = new Runner();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    runner.firstThread();
                } catch (InterruptedException ignored) {
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    runner.secondThread();
                } catch (InterruptedException ignored) {
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        runner.finished();
    }

}