package decorator.whyuse.inheritance;

import java.io.File;

import decorator.shared.Utils;

public class DemoInheritance {
    public static void main(String[] args) {
        MyBufferedReaderInheritance rdr = new MyBufferedReaderInheritance(new File("shared/demo.txt"));
        for (int k = 0; k < 250; k++) {
            System.out.println(k + ": " + rdr.readLine());
        }
        System.out.println("Those were the first 250 lines of " + rdr.getSourceName());
        Utils.functionForMyReaders(rdr);
        // But what if we have a network reader...?
        // new MyBufferedSequenceReader(new File("shared/demo.txt"))
    }
}

