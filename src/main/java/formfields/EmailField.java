package formfields;

import javax.annotation.Nonnull;

public class EmailField extends FormField<String> {

    EmailField(Mode mode, String identifier, String label) {
        super(mode, identifier, label);
    }

    @Override
    @Nonnull public String renderAsHtml() {
        if (!isVisible()) {
            return "";
        }
        StringBuilder inputHtml = new StringBuilder();
        inputHtml.append("<p class='formField");
        if (hasBeenChecked()) {
            inputHtml.append(isValid() ? " formFieldValid" : " formFieldInvalid");
        }
        inputHtml.append("'>");
        // Label.
        inputHtml.append("<label for='formfield__");
        inputHtml.append(getIdentifier());
        inputHtml.append("'>");
        inputHtml.append(label == null ? "&nbsp;" : label);
        inputHtml.append("</label>");
        if (isEditable()) {
            // Input.
            inputHtml.append("<input id='formfield__");
            inputHtml.append(getIdentifier());
            inputHtml.append("' name='");
            inputHtml.append(getIdentifier());
            inputHtml.append("' class='dateInputField");
            if (isRequired()) {
                inputHtml.append(" requiredInputField");
            }
            inputHtml.append("' type='text' placeholder='email@address'");
            if (getUnvalidatedValue() != null) {
                inputHtml.append(" value='");
                inputHtml.append(getUnvalidatedValue());  // todo: add escaping (for attribute)
                inputHtml.append("'");
            }
            inputHtml.append("/>");
        } else {
            // Display.
            inputHtml.append("<div id='formfield__");
            inputHtml.append(getIdentifier());
            inputHtml.append("' class='dateDisplayField inedibleInputField'>");
            inputHtml.append(getUnvalidatedValue());  // todo: add escaping (for text node)
            inputHtml.append("</div>");
        }
        inputHtml.append("</p>");
        // Errors.
        if (isInvalid()) {
            inputHtml.append("<ul class='formFieldErrors'>");
            inputHtml.append("</ul>");
        }
        inputHtml.append("\n");
        return inputHtml.toString();
    }

    @Override
    @Nonnull public ValidationResult<String, String> validate() {
        cleanValue = rawValue.trim();
        if (rawValue.contains("@")) {
            return ValidationResult.Success(cleanValue);
            // todo: needs much more logic
        } else {
            return ValidationResult.Failure("Email addresses must contain '@' symbol");
        }
    }
}
