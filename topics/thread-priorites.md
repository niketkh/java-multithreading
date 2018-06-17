# Thread Priorities

Thread priorities are used by the thread scheduler to decide when each thread should be allowed to run. In theory, over a given period of time, higher priority threads get more CPU time than lower priority threads. In practice, the amount of CPU time that a thread gets often depends on several factors besides its priority. (For example, how an operating system implements multitasking can affect the relative availablity of CPU time.) A higher priority thread can also preempt a lower priority one. For instance, when a lower priority thread is running and a higher priority thread resumes (from sleeping or waiting on I/O, for example), it will preempt the lower priority thread.

In theory, threads of equal priority should get equal access to the CPU. But you need to be careful. Remember, Java is designed to work in a wide range of environments. Some of those environments implement multitasking fundamentally defferent than others. For safety, threads that share the same priority should yield control once in a while. This ensures that all threads have a chance to run under a nonpreemptive operating system. In practice, even in nonpreemptive environments, most threads still get a chance to run, because most threads inevitably encounter some blocking situation, such as waiting for I/O. When this happens, the blocked thread is suspended and other threads can run. But, if you want smooth multithreaded execution, you are better off not relying on this. Also, some types of tasks are CPU-intensive. Such threads dominate CPU. For these types of threads, you want to yield control occasionally so that other threads can run.

To set a thread's priority, use `setPriority()` method, which is a member of **Thread**. 

`final void setPriority(int level)`

Here, level specifies the new priority setting for calling thread. The value of level must be within the range **MIN_PRIORITY** and **MAX_PRIORITY**. Currently, these values are 1 and 10, respectively. To return a thread to default priority, specify **NORM_PRIORITY**, which is currently 5. These priorities are defined as **static final** variables within **Thread**.

You can obtain the current priority by calling `getPriority()` method of **Thread**.

`final int getPriority()`


