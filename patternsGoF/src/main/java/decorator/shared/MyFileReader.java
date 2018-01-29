package decorator.shared;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class MyFileReader implements MyReader {

    private Scanner youDoAllTheWorkOk;
    private File file;

    public MyFileReader(File in) {

        try {
            youDoAllTheWorkOk = new Scanner(new FileReader(in));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readLine() {
        return youDoAllTheWorkOk.nextLine();
    }

    @Override
    public String getSourceName() {
        return file.getName();
    }
}

