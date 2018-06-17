
import java.util.concurrent.*;

/**
 * Till Java 1.4, threads could be implemented by either implementing
 * {@link java.lang.Runnable} or extending java.lang.Thread.
 * This was quite simple, but had a serious limitation;
 * They have a run method that cannot return values. In order to side-step this,
 * most programmers use side-effects (writing to a file etc.) to mimic returning
 * values to the invoker of the thread. Java 5 introduces the
 * java.util.concurrent.Callable interface, that allows users to
 * return values from a thread.
 * 
 * Runnable vs Callable :
 *
 * Runnable Introduced in Java 1.0. Callable<T> Introduced in Java 1.5 as
 * part of java.util.concurrent library.
 *
 * Runnable cannot be parametrized. Callable is a parametrized type whose type
 * parameter indicates the return type of its run method Classes implementing.
 *
 * Runnable needs to implement run() method, classes implementing Callable needs
 * to implement call() method.
 *
 * Runnable.run() returns no value, Callable.call() returns a value of Type T.
 * 
 * Runnable can not throw checked exceptions, Callable can throw checked
 * exceptions.
 */

public class App {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();

        //anonymous call of Callable
        Future<Integer> future = executor.submit(new Callable<Integer>() {

            @Override
            //return value is Integer
            public Integer call() throws TimeoutException {
                Random random = new Random();
                int duration = random.nextInt(4000);
                if (duration > 2000) {
                    throw new TimeoutException ("Sleeping for too long.");
                }

                System.out.println("Starting ...");
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException ignored) {}
                System.out.println("Finished.");
                return duration;
            }
        });

        executor.shutdown();
//        executor.awaitTermination(1, TimeUnit.DAYS);
        try {
            //get returned value from call()
            System.out.println("Result is: " + future.get());

        } catch (InterruptedException ignored) {
        } catch (ExecutionException e) {
            TimeoutException ex = (TimeoutException) e.getCause();
            System.out.println(ex.getMessage());
        }
    }

}