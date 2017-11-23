package unsafestring;

import java.util.regex.Pattern;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * This class represents a string which may not be safe to use in some contexts because it's source isn't trusted.
 *
 * {@implNote The encapsulated class cannot be {@code null}; assign {@code null} instead of {@link LazyUnsafeString }to represent a missing value.}
 */
public abstract class UnsafeString implements Comparable<UnsafeString> {

    private static final Pattern ALPHANUM_ONLY_REGEX = Pattern.compile("[^a-zA-Z0-9\\-+_\\.,]");

    /**
     * Escape the String for use inside an HTML tag, e.g. {@code <p>}{@link}{@code </p>}.
     */
    @Nonnull public abstract String escapedForXMLTag();

    /**
     * Escape the String for use inside an HTML attribute, e.g. {@code <p class="}{@link}{@code "></p>}.
     */
    @Nonnull public String escapedForXMLAttribute() {
        return new LazyUnsafeString(escapedForXMLTag()).escapedForInsideDoubleQuotedString();
    }

    /**
     * Escape the String for use inside a double-quoted string, e.g. a javascript one: {@code var text = "hello }{@link}{@code ";}.
     */
    @Nonnull public abstract String escapedForInsideDoubleQuotedString();

    // todo: others include e.g. URLs, SQL

    /**
     * Escape the String for use inside a double-quoted string, e.g. a javascript one: {@code var text = "hello }{@link}{@code ";}.
     */
    @Nonnull protected abstract String unsafeGetRawValue();

    /**
     * Construct an UnsafeString, or return {@code null} if the input is {@code null}.
     */
    @Nullable public static UnsafeString makeOrNull(@Nullable String rawString) {
        if (rawString == null) {
            return null;
        }
        return new CachedUnsafeString(rawString);
    }

    /**
     * Construct an UnsafeString, using an empty string if the input is {@code null}.
     */
    @Nonnull public static UnsafeString makeOrEmpty(@Nullable String rawString) {
        if (rawString == null) {
            rawString = "";
        }
        return new CachedUnsafeString(rawString);
    }

    /**
     * Join a number of unsafe strings together and returns the result.
     *
     * @param separator: An (assumed safe) separation character to insert between the strings, e.g. a comma.
     * @param unsafeStrings: Any number of (unsafe) strings which will be joined together.
     */
    public static UnsafeString join(@Nullable String separator, UnsafeString... unsafeStrings) {
        if (separator == null) {
            return join(unsafeStrings);
        }
        StringBuilder rawJoinedString = new StringBuilder(2 * unsafeStrings.length - 1);
        boolean isFirst = true;
        for (UnsafeString str : unsafeStrings) {
            if (isFirst) {
                isFirst = false;
            } else if (separator != null) {
                rawJoinedString.append(separator);
            }
            rawJoinedString.append(str.unsafeGetRawValue());
        }
        return makeOrEmpty(rawJoinedString.toString());
    }

    /**
     * See {@link #join(String, UnsafeString...)}.
     */
    public static UnsafeString join(UnsafeString... unsafeStrings) {
        StringBuilder rawJoinedString = new StringBuilder(unsafeStrings.length);
        for (UnsafeString str : unsafeStrings) {
            rawJoinedString.append(str.unsafeGetRawValue());
        }
        return makeOrEmpty(rawJoinedString.toString());
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof UnsafeString)) {
            return false;
        }
        return unsafeGetRawValue().equals(((UnsafeString) other).unsafeGetRawValue());
    }

    @Override
    public int hashCode() {
        return 2 * unsafeGetRawValue().hashCode() + 37;
    }

    @Override
    public int compareTo(UnsafeString other) {
        if (other == null) {
            return 0;
        }
        return unsafeGetRawValue().compareTo(other.unsafeGetRawValue());
    }

    @Override
    public String toString() {
        return "[UNSAFE STRING ~ " + ALPHANUM_ONLY_REGEX.matcher(unsafeGetRawValue()).replaceAll("?") + "]";
    }
}
