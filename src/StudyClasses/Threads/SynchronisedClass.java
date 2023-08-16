package StudyClasses.Threads;

class SomeParam {
    int smtInt = 0;
}

class Asynchronous implements Runnable {

    private SomeParam smt;
    private String threadName;

    public Asynchronous(SomeParam smt, String threadName) {
        this.smt = smt;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        synchronized (smt){
            for (int i = 1; i < 6; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                smt.smtInt += 1;
                System.out.printf("%s with {priority: %d} change smt to ---> %d\n", threadName, Thread.currentThread().getPriority(), smt.smtInt);
            }
        }
    }
}

public class SynchronisedClass {

    public static void main(String[] args) {

        SomeParam smt = new SomeParam();

        for (int i = 1; i < 4; i++) {
            String name = "Thread " + i;
            Thread tr = new Thread(new Asynchronous(smt, name));
            if(i == 3){
                tr.setPriority(Thread.MIN_PRIORITY);
                try{
                    tr.join();
                }catch(InterruptedException e){}
            }else{
                tr.setPriority(Thread.MAX_PRIORITY);
            }
            tr.start();
        }
    }
}
