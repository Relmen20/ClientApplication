package StudyClasses.Threads;

import java.util.Random;

class Egg extends Thread
{
    @Override
    public void run()
    {
        for(int i = 0; i < 5; i++) {
            try {
                // Приостанавливаем поток
                sleep(newMain.getTimeSleep());
                System.out.println("Яйцо");
                newMain.check =  "Яйцо";
            }catch(InterruptedException e){}
        }
    }
}

class Chicken extends Thread
{
    @Override
    public void run()
    {
        for(int i = 0; i < 5; i++) {
            try {
                // Приостанавливаем поток
                sleep(newMain.getTimeSleep());
                System.out.println("Курица");
                newMain.check =  "Курица";
            }catch(InterruptedException e){}
        }
    }
}


public class newMain {
    public static volatile String check = "";
    public static int getTimeSleep()
    {
        final Random random = new Random();
        int tm = random.nextInt(1000);
        if (tm < 10)
            tm *= 100;
        else if (tm < 100)
            tm *= 10;
        return tm;
    }

    public static void main(String[] args) {
        Egg egg = new Egg (); // Создание потока
        Chicken chicken = new Chicken();
        System.out.println("Начинаем спор : кто появился первым ?");


        egg.start(); // Запуск потока
        chicken.start();

        try{
            egg.join();
        }catch (InterruptedException e){}

        if(chicken.isAlive()){
            try {
                chicken.join();
            }catch(InterruptedException e){}
        }


        System.out.printf("Победитель ---> %s", check);
    }
}
