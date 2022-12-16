package semaphore;

import java.util.concurrent.Semaphore;

public class CountThread implements Runnable {

    CommonResource res;
    Semaphore sem;
    String name;
    String potion;
    Shelf shelf;

    CountThread(CommonResource res, Semaphore sem, String name, String potion, Shelf shelf) {
        this.res = res;
        this.sem = sem;
        this.name = name;
        this.potion = potion;
        this.shelf = shelf;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " ждёт доступ к котлу");
            sem.acquire();
            System.out.println(name + " получила(а) доступ к котлу");
            res.x = 1;
            for (int i = 0; i < 5; i++) {
                System.out.println(this.name + ": " + res.x);
                res.x++;
                Thread.sleep(100);
            }
            shelf.putOnShelf(potion);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());;
        }
        System.out.println(name + " освобождает доступ к котлу");
        System.out.println(name + " сварил(а) " + potion);
        System.out.println(name + " положила(а) " + potion + " на полку");
        sem.release();
    }
}
