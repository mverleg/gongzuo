package src.main.java.composite.with;

import org.jetbrains.annotations.NotNull;

import src.main.java.composite.util.IO;

/**
 * Answer to a {@link Question}
 */
public class Answer implements QuestionnaireItem {
    @NotNull final String text;

    public Answer(String text) {
        this.text = text;
    }

    public void perform(IO io) {
        io.show(text);
    }
}
