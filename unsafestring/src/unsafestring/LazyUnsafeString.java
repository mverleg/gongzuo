package unsafestring;

import javax.annotation.Nonnull;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 * An implementation of UnsafeString which computes the safe values each time a method is called.
 */
public class LazyUnsafeString extends UnsafeString {

    /** The raw wrapped value. */
    @Nonnull final protected String rawString;

    /**
     * Construct a text marked as being unsafe, by wrapping a String object.
     *
     * Fails for {@code null}; use {@link makeOrNull} or {@link makeOrEmpty}.
     */
    public LazyUnsafeString(@Nonnull String rawString) {
        if (rawString == null) {
            throw new IllegalArgumentException("UnsafeString cannot be created from 'null'");
        }
        this.rawString = rawString;
    }

    @Override
    @Nonnull public String escapedForXMLTag() {
        return StringEscapeUtils.escapeHtml4(rawString);
    }

    @Override
    @Nonnull public String escapedForInsideDoubleQuotedString() {
        return StringEscapeUtils.escapeEcmaScript(rawString);
    }

    @Override
    @Nonnull protected String unsafeGetRawValue() {
        return rawString;
    }
}
