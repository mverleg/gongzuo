package formfields;

import java.util.Date;
import javax.annotation.Nonnull;

public class DateField extends FormField<Date> {

    DateField(Mode mode, String identifier, String label) {
        super(mode, identifier, label);
    }

    @Override
    @Nonnull public String renderAsHtml() {
        // todo
        return "(render not implemented)\n";
    }

    @Override
    @Nonnull public ValidationResult<Date, String> validate() {
        // todo
        return ValidationResult.Failure("(validation not implemented)");
    }
}
