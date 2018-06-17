// Without the use of any interthread communication there is no way to synchronize Producer and Consumer

// Use wait and notify to ensure Producer only produces when value is consumed
// and Consumer consumes only when value is produced

class Q {
    private int n;
    private boolean valueSet;
    
    public synchronized int get() {
        while(!valueSet) {
            try {
                wait();
            }
            catch(InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
        System.out.println("Got: " + n);
        valueSet = false;
        notify();
        return n;
    }
    
    public synchronized void put(int n) {
        while(valueSet) {
            try {
                wait();
            }
            catch(InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
        this.n = n;
        valueSet = true;
        System.out.println("Put: " + n);
        notify();
    }
}

class Producer implements Runnable {
    Q q;
    
    Producer(Q q) {
        this.q = q;
        new Thread(this, "Producer").start();
    }
    
    public void run() {
        int n = 0;
        for(int i=0; i<10; i++) {
            q.put(n++);
        }
    }
}

class Consumer implements Runnable {
    Q q;
    
    Consumer(Q q) {
        this.q = q;
        new Thread(this, "Consumer").start();
    }
    
    public void run() {
        for(int i=0; i<10; i++) {
            q.get();
        }
    }
}

class Main {
    public static void main(String args[]) {
        Q q = new Q();
        new Producer(q);
        new Consumer(q);
    }
}


// Output:
/*

Put: 0
Got: 0
Put: 1
Got: 1
Put: 2
Got: 2
Put: 3
Got: 3
Put: 4
Got: 4
Put: 5
Got: 5
Put: 6
Got: 6
Put: 7
Got: 7
Put: 8
Got: 8
Put: 9
Got: 9

*/












