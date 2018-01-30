package composite.without;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import composite.util.IO;

/**
 * Yes/No question.
 */
public class Question {
    @NotNull final String text;
    @Nullable final Question yesQuestion;
    @Nullable final Question noQuestion;
    @Nullable final Answer yesAnswer;
    @Nullable final Answer noAnswer;

    public Question(String text, Question yes, Question no) {
        this.text = text;
        yesQuestion = yes;
        noQuestion = no;
        yesAnswer = noAnswer = null;
    }

    public Question(String text, Answer yes, Question no) {
        this.text = text;
        yesQuestion = null;
        noQuestion = no;
        yesAnswer = yes;
        noAnswer = null;
    }

    public Question(String text, Question yes, Answer no) {
        this.text = text;
        yesQuestion = yes;
        noQuestion = null;
        yesAnswer = null;
        noAnswer = no;
    }

    public Question(String text, Answer yes, Answer no) {
        this.text = text;
        yesQuestion = noQuestion = null;
        yesAnswer = yes;
        noAnswer = no;
    }

    public void perform(IO io) {
        io.show(text);
        if ("yes".equals(io.ask(new String[]{"yes", "no"}))) {
            if (yesQuestion == null) {
                yesAnswer.display(io);
            } else {
                yesQuestion.perform(io);
            }
        } else {
            if (noQuestion == null) {
                noAnswer.display(io);
            } else {
                noQuestion.perform(io);
            }
        }
    }
}
