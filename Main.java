package semaphore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {

    public static String getRandom(String[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
    // changing something

    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();
        Semaphore sem = new Semaphore(1);
        CommonResource res = new CommonResource();
        Shelf shelf = new Shelf();

        String data = "";
        String[] namesAndPotions;
        ArrayList<Thread> wizards = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream("C:\\Users\\1288947\\IdeaProjects\\tasks\\src\\main\\java\\semaphore\\namesandpotions.txt")) {

            byte[] byteData = new byte[fis.available()];
            byteData = fis.readAllBytes();
            data = new String(byteData);

            namesAndPotions = data.split("\r\n");
            System.out.println("У нас есть " + namesAndPotions.length + " волшебников (потоков)");
            String[] splitRes = new String[namesAndPotions.length * 2];

            for (int i = 0; i < namesAndPotions.length; i++) {
                splitRes = namesAndPotions[i].split(", ");
                String name = splitRes[0];
                String potion = splitRes[1];
                System.out.println("Мы можем создать поток с именем: " + name +" и зельем: " + potion);
                wizards.add(new Thread(new CountThread(res, sem, name, potion, shelf)));
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Thread t : wizards) {
            t.start();
        }

        for (Thread t : wizards) {
            t.join();
        }

        shelf.resToFile();

    }
}
