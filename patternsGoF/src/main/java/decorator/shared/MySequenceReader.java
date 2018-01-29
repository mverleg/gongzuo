package decorator.shared;

public class MySequenceReader implements MyReader {

    private int index = 0;

    @Override
    public String readLine() {
        index++;
        return String.valueOf(index);
    }

    @Override
    public String getSourceName() {
        return "random!";
    }
}

