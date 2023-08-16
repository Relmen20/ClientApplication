package StudyClasses.Threads;

class EggRunnable implements Runnable {
    private Thread tr;
    private String egg = "Egg";

    EggRunnable() {
        tr = new Thread(this, "EggThread");
        tr.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            RunnableImplement.vote = egg;
            System.out.printf("\nThread {%s} ---> %s", tr.getName(), egg);
        }
    }

    public Thread getTr() {
        return tr;
    }
}

class ChickenRunnable implements Runnable {

    private String threadName;
    private String chicken = "Chicken";

    ChickenRunnable(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            RunnableImplement.vote = chicken;
            System.out.printf("\nThread {%s} ---> %s", threadName, chicken);
        }
    }
}


public class RunnableImplement {

    public volatile static String vote;

    public static void main(String[] args) {

        String threadName = "chicken";

        Thread tr = new Thread(new ChickenRunnable(threadName), threadName);

        System.out.println("-----------Start------------");
        EggRunnable trEgg = new EggRunnable();
        tr.start();

        try {
            trEgg.getTr().join();
        } catch (InterruptedException e) {
        }

        if (tr.isAlive()) {
            try {
                tr.join();
            } catch (InterruptedException e) {
            }
        }
        System.out.printf("\n------------End-------------\n" + "Winner ---> {%s}", vote);
    }
}
