# Java Multithreading

### Starting Threads
* There are two ways to start threads in Java
    - Extending Thread Class
    - Implementing Runnable Interface (Prefered)
* [Extending Thread Class Example](demos/1-extending-thread-class.java)
* [Implementing Runnable Interface Example](demos/2-implementing-runnable-interface.java)

### Basic Thread Synchronization
* `volatile` keyword is used to prevent from caching variables when they might be changed from other threads
* [Using `volatile` keyword Example](demos/3-using-volatile.java)

### The Synchronized Keyword
* Every object in java has an intrinsic lock or monitor lock (mutex)
* If you use `synchronized` keyword on a method, then before calling such a method you need to acquire the intrinsic lock
* Only one thread can acquire intrinsic lock at any time
* When you running something inside a snchronized block it is guaranteed to get the current state of variables
* [Using `synchronized` keyword example](demos/4-using-synchronized.java)

### Multiple Locks - Using Synchronized Code Blocks
* `synchronized` code blocks can be used on different objects to achieve synchronization when multiple locks is needed
* [Using `synchronized` code blocks Example](demos/5-using-synchronized-code-blocks.java)

### Thread Pools
* Thread Pools is a way of managing multiple threads
* Thread Pools is similar to having multiple workers in the factory
* [Thread Pools Example](demos/6-thread-pools.java)

### CountDown Latches
* CountDownLatch is thread safe class
* [CountDownLatch Example](demos/7-countdown-latch.java)

### Producer Consumer 
* Implementing Producer Consumer pattern using `ArrayBlockingQueue`
* [Producer Consumer Example](demos/8-producer-consumer.java)

### Wait and Notify
* [Wait and Notify Example](demos/9-wait-and-notify.java)

### Producer Consumer using `wait()`  `notify()`
* [Producer Consumer Example Using Wait and Notify](demos/10-producer-consumer.java)

### Re-entrant Locks
* [Re-entrant Locks Example](demos/11-reentrant-locks.java)


### Handling Deadlocks
* [Handling Deadlocks Example](demos/12-handling-deadlocks.java)

### Semaphores
* [Semaphore Example](demos/13-semaphores.java)

### Callable and Future
* [Callable and Future Example](demos/14-callable-and-future.java)

### Interrupting Threads
* [Interrupting Threads Example 1](demos/16-interrupting-threads.java)
* [Interrupting Threads Example 2](demos/15-interrupting-threads.java)

### References
* [Multithreading examples from complete reference](java-multithreading-examples.md)
* [PTR from Concurrency in Practice](java-concurrency-in-practice.md)
* [Multithreading Code Repo](https://github.com/Beerkay/JavaMultiThreading)
* [Mutex vs Semaphore](https://www.geeksforgeeks.org/mutex-vs-semaphore/)


