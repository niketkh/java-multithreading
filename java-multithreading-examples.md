# Java Multithreading 

### Examples
* [Getting reference to current thread using `currentThread()`](examples/1-using-currentThread)
* [Creating a Thread - Implementing Runnable Interface](examples/2-implementing-runnable.java)
* [Creating a Thread - Extending Thread Class](examples/3-extending-thread.java)
* [Creating Multiple Threads](examples/4-multiple-threads.java)
* [Using `join()`](examples/5-using-join.java)
* [Synchronization using synchronized methods](examples/6-using-synchronized-methods.java)
* [Synchronization using synchronized block](examples/7-using-synchronized-block.java)
* [Interthread Communication - Using `wait()`, `notify()`](examples/8-interthread-communication.java)
* [Deadlock Example](examples/9-deadlock.java)
* [Custom Suspend and Resume Methods](examples/10-suspend-resume.java)


### Topics
* Creating a Thread
* Creating Multiple Threads
* [Thread Priorities](topics/thread-priorities.md)
* [Synchronization](topics/synchronization.md)
* Interthread Communication
* Deadlock
* Suspending, Resuming and Stopping Threads
* Thread Lifecycle


Methods of Thread Class | Description
---|---
`setName()` | Set thread's name
`getName()` | Get thread's name
`setPriority()` | Set thread's priority
`getPriority()` | Get thread's priority
`currentThread()` | Obtain a reference to current thread
`start()` | Start a thread by calling its run method
`run()` | Entry point for the thread
`sleep()` | Suspend a thread for a period of time
`isAlive()` | Check if a thread is still running
`join()` | Wait for thread to terminate
`wait()` |
`notify()` |
`notifyAll()` |
`suspend()` | **Deprecated**
`resume()` | **Deprecated**
`stop()` | **Deprecated**


States of Thread | Description
---|---
BLOCKED | A thread that has suspended execution because it is waiting to acquire a lock
NEW | A thread that has not begun execution 
RUNNABLE | A thread that either is currently executing or will execute when it gains access to CPU
TERMINATED | A thread that has completed execution
TIMED_WAITING | A thread that has suspended execution for a specified period of time, such as when it has called `sleep()`. This state is also entered when a timeout version of `wait()` or `join()` is called.
WAITING | A thread that has suspended execution because it is waiting for some action to occur. For example, it is waiting because of a call to a non-timeout version of `wait()` or `join()`
