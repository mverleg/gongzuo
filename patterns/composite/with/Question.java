package composite.with;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import composite.util.IO;

/**
 * Yes/No question.
 */
public class Question implements QuestionnaireItem {
    @NotNull final String text;
    @Nullable final QuestionnaireItem yes;
    @Nullable final QuestionnaireItem no;

    public Question(String text, QuestionnaireItem yes, QuestionnaireItem no) {
        this.text = text;
        this.yes = yes;
        this.no = no;
    }

    public void perform(IO io) {
        io.show(text);
        if ("yes".equals(io.ask(new String[]{"yes", "no"}))) {
            yes.perform(io);
        } else {
            no.perform(io);
        }
    }
}
