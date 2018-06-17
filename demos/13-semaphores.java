import java.util.concurrent.Semaphore;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Connection {

    private static Connection instance = new Connection();
    
    /*
    * Limit connections to 10
    * true in Semaphore() means whichever thread gets first in the waiting pool 
    * waiting to acquire a resource, is first to obtain the permit.
    * 
    * When you initialize the semaphore with Fairness, by setting its second
    * argument to true, it will treat the waiting threads like FIFO.
    * But, it doesn't have to be that way if you don't set on the fairness. the JVM
    * may schedule the waiting threads in some other manner that it sees best
    */
    private Semaphore sem = new Semaphore(10, true);
    private int connections = 0;

    private Connection() {
    }

    public static Connection getInstance() {
        return instance;
    }

    public void connect() {
        try {

            // get permit decrease the sem value, if 0 wait for release
            sem.acquire();

            //if doConnect throws and exception is still releases the permit
            //so we use a separate method here to increase the connections count
            doConnect();

        } catch (InterruptedException ignored) {
        } finally {
            //release permit, increase the sem value and activate waiting thread
            sem.release();
        }
    }

    public void doConnect() {
        synchronized (this) { //atomic
            connections++;
            System.out.println("Current connections (max 10 allowed): " + connections);
        }
        try {
            //do your job
            System.out.println("Working on connections " + Thread.currentThread().getName());
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {}
        //when exit doConnect method decrement number of connections
        synchronized (this) {//atomic
            connections--;
            System.out.println("I'm done " + Thread.currentThread().getName() + " Connection is released , connection count: " + connections);
        }
    }
}

class App {

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 200; i++) { 
            executor.submit(new Runnable() {
                public void run() {
                    Connection.getInstance().connect();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}