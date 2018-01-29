package decorator.whyuse.delegation;

import java.io.File;

import decorator.whyuse.inkotlin.MyBufferedReaderKotlin;
import decorator.shared.MyFileReader;
import decorator.shared.MySequenceReader;

public class DemoDelegate {
    public static void main(String[] args) {
        decorator.whyuse.inkotlin.MyBufferedReaderKotlin rdr = new decorator.whyuse.inkotlin.MyBufferedReaderKotlin(new MyFileReader(new File("shared/demo.txt")));
        for (int k = 0; k < 250; k++) {
            System.out.println(k + ": " + rdr.readLine());
        }
        System.out.println("Those were the first 250 lines of " + rdr.getSourceName());
        // It also works for other readers!
        decorator.whyuse.inkotlin.MyBufferedReaderKotlin seqrdr = new MyBufferedReaderKotlin(new MySequenceReader());
        // But this doesn't work (not polymorphic)...
        // Utils.functionForMyReaders(rdr);
    }
}
