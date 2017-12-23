package formfields;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

abstract public class FormField<T> implements HtmlRenderable {

    protected String rawValue;
    protected T cleanValue;
    @Nonnull protected final String identifier;  // todo: make sure it's a valid html id
    @Nullable protected final String label;
    @Nonnull protected Mode mode = Mode.OPTIONAL;
    @Nonnull protected Status validationStatus = Status.UNVALIDATED;

    FormField(Mode mode, String identifier, String label) {
        this.mode = mode;
        this.identifier = identifier;
        this.label = label;
    }

    public void bind(@Nonnull String value) {
        rawValue = value;
    }

    @Nonnull public abstract ValidationResult<T, String> validate();

    public String getUnvalidatedValue() {
        return rawValue;
    }

    public final String getIdentifier() {
        return identifier;
    }

    public final String getLabel() {
        return label;
    }

    public void hide() {
        mode = Mode.HIDDEN;
    }

    public void makeReadonly() {
        mode = Mode.READONLY;
    }

    public void makeEditable() {
        mode = Mode.OPTIONAL;
    }

    public void makeRequired() {
        mode = Mode.REQUIRED;
    }

    public boolean isEditable() {
        return mode == Mode.OPTIONAL || mode == Mode.REQUIRED;
    }

    public boolean isVisible() {
        return mode != Mode.HIDDEN;
    }

    public boolean isRequired() {
        return mode == Mode.REQUIRED;
    }

    public boolean hasBeenChecked() {
        return validationStatus != Status.UNVALIDATED;
    }

    public boolean isValid() {
        return validationStatus != Status.VALID;
    }

    public boolean isInvalid() {
        return validationStatus != Status.INVALID;
    }
}
