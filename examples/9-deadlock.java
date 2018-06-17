class A {
    synchronized void foo(B b) {
        String name = Thread.currentThread().getName();
        
        System.out.println(name + " entered A.foo");
        
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException e) {
            System.out.println("Interrupted");
        }
        
        System.out.println(name + " trying to call B.last()");
        b.last();
    }
    
    synchronized void last() {
        System.out.println("Inside A.last");
    }
}

class B {
    
    synchronized void bar(A a) {
        String name = Thread.currentThread().getName();
        
        System.out.println(name + " entered B.bar");
        
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException e) {
            System.out.println("Interrupted");
        }
        
        System.out.println(name + " trying to call A.last()");
        a.last();
    }
    
    synchronized void last() {
        System.out.println("Inside B.last");
    }
}

class Deadlock implements Runnable {
    A a = new A();
    B b = new B();
    
    Deadlock() {
        Thread.currentThread().setName("Main Thread");
        Thread t = new Thread(this, "Racing Thread");
        t.start();
        
        // Get lock on a in Main Thread
        a.foo(b);
        System.out.println("Back in main thread");
    }
    
    public void run() {
        b.bar(a);
        System.out.println("Back in racing thread");
    }
    
    public static void main(String args[]) {
        new Deadlock();
    }
}


// Output:
/*

Main Thread entered A.foo
Racing Thread entered B.bar
Main Thread trying to call B.last()
Racing Thread trying to call A.last()

*/


