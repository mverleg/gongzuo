package unsafestring;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * An implementation of UnsafeString which computes values the first time and uses the cached result in the future.
 *
 * {@implNote In a multi-threaded context, it is not guaranteed that each computation happens only once.}
 */
public class CachedUnsafeString extends UnsafeString {

    /** The value is wrapped in a LazyUnsafeString which does the escaping. */
    @Nonnull final protected UnsafeString unsafeString;

    /** Cached escaped version of the wrapped value. */
    @Nullable protected String htmlTagSafeString;
    @Nullable protected String htmlAttributeSafeString;
    @Nullable protected String doubleQuoteTextSafeString;

    /**
     * Construct a text marked as being unsafe, by wrapping a String object.
     *
     * Fails for {@code null}; use {@link makeOrNull} or {@link makeOrEmpty}.
     */
    public CachedUnsafeString(@Nonnull String rawString) {
        this(new LazyUnsafeString(rawString));
    }

    /**
     * Construct a cached version by wrapping another UnsafeString which is used for computing.
     */
    public CachedUnsafeString(@Nullable UnsafeString newUnsafeString) {
        if (newUnsafeString == null) {
            newUnsafeString = makeOrEmpty("");
        }
        unsafeString = newUnsafeString;
    }

    @Override
    @Nonnull public String escapedForXMLTag() {
        if (htmlTagSafeString == null) {
            htmlTagSafeString = unsafeString.escapedForXMLTag();
        }
        return htmlTagSafeString;
    }

    @Override
    @Nonnull public String escapedForXMLAttribute() {
        if (htmlAttributeSafeString == null) {
            htmlAttributeSafeString = unsafeString.escapedForXMLTag();
        }
        return htmlAttributeSafeString;
    }

    @Override
    @Nonnull public String escapedForInsideDoubleQuotedString() {
        if (doubleQuoteTextSafeString == null) {
            doubleQuoteTextSafeString = unsafeString.escapedForXMLTag();
        }
        return doubleQuoteTextSafeString;
    }

    @Override
    @Nonnull protected String unsafeGetRawValue() {
        return unsafeString.unsafeGetRawValue();
    }
}
