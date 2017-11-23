package formfields;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Form implements Iterable<FormField>, HtmlRenderable {

    /** List of all the form fields in correct order, but without type information. */
    private final LinkedHashMap<String, FormField> allFieldsOrdered = new LinkedHashMap<String, FormField>();

    /** Map of each field with type information but without order. */
    private final Map<String, DateField> dateFields = new HashMap<String, DateField>(0);
    private final Map<String, EmailField> emailFields = new HashMap<String, EmailField>(0);

    /** Bookkeeping fields. */
    private Mode defaultMode = Mode.OPTIONAL;

    private void assertIdentifierAvailable(String label) {
        assert !allFieldsOrdered.containsKey(label):
                String.format("identifier '{}' already exists ({})", label, allFieldsOrdered.get(label));
    }

    @Nonnull public DateField addDate(@Nonnull String identifier, @Nullable String label) {
        assertIdentifierAvailable(identifier);
        DateField newField = new DateField(defaultMode, identifier, label);
        allFieldsOrdered.put(identifier, newField);
        dateFields.put(identifier, newField);
        return newField;
    }

    @Nonnull public EmailField addEmail(@Nonnull String identifier, @Nullable String label) {
        assertIdentifierAvailable(identifier);
        EmailField newField = new EmailField(defaultMode, identifier, label);
        allFieldsOrdered.put(identifier, newField);
        emailFields.put(identifier, newField);
        return newField;
    }

    @Nonnull public String renderAsHtml() {
        StringBuilder formHtml = new StringBuilder(allFieldsOrdered.size());
        for (FormField field : this) {
            formHtml.append(field.renderAsHtml());
        }
        return formHtml.toString();
    }

    @Override
    public Iterator<FormField> iterator() {
        return allFieldsOrdered.values().iterator();
    }

    public void makeReadonly() {
        defaultMode = Mode.READONLY;
        for (FormField field : this) {
            field.makeReadonly();
        }
    }
}
