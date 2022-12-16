package semaphore;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Shelf {
    ArrayList<String> shelf;

    Shelf() {
        this.shelf = new ArrayList<>();
    }

    public void putOnShelf(String potion) {
        shelf.add(potion);
    }

    public String showShelf() {
       String result = "Полка: ";
       for (int i = 0; i < shelf.size(); i++) {
            result += shelf.get(i) + " | ";
       }
       return result;
    }

    public void resToFile() {
        try(FileOutputStream fos = new FileOutputStream("C:\\Users\\1288947\\IdeaProjects\\tasks\\src\\main\\java\\semaphore\\res.txt")) {
            byte[] buffer = showShelf().getBytes();
            fos.write(buffer, 0, buffer.length);

        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
