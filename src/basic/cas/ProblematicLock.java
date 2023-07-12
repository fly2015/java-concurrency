package basic.cas;

public class ProblematicLock {
    private volatile boolean locked = false;

    public synchronized void lock() {
/*        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        while(this.locked) {
            // busy wait - until this.locked == false
            System.out.println(Thread.currentThread().getName() + " doing something in locked status");
        }

        this.locked = true;
        System.out.println(Thread.currentThread().getName() + "Locked");
    }

    public void unlock() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.locked = false;
        System.out.println(Thread.currentThread().getName() + " Unlocked");
    }


    public static void main(String[] args) throws InterruptedException {
        ProblematicLock problematicLock = new ProblematicLock();
        Thread th0 = new Thread(() -> problematicLock.lock());

        Thread th1 = new Thread(() -> problematicLock.lock());
        Thread th2 = new Thread(() ->
        {
            problematicLock.unlock();
        });
        th0.start();
        th1.start();
        th2.start();

        boolean interrupted;
        while (interrupted = th0.isInterrupted() && th1.isInterrupted() && th2.isInterrupted())
        {
            System.out.println("Doing");
        }
        System.out.println("Exit");
    }
}
