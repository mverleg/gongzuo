package decorator.whyuse.inheritance;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

import decorator.whyuse.shared.MyFileReader;

public class MyBufferedReaderInheritance extends MyFileReader {

    final private Queue<String> lines = new LinkedList<String>();

    public MyBufferedReaderInheritance(File in) {
        super(in);
    }

    @Override
    public String readLine() {
        if (lines.size() == 0) {
            for (int k = 0; k < 100; k++) {
                lines.add(super.readLine());
            }
        }
        return lines.remove();
    }
}
