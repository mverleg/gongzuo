package unsafestring;

import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        UnsafeString txt1 = UnsafeString.makeOrEmpty("Hello World");
        UnsafeString txt2 = LazyUnsafeString.makeOrEmpty("Lazy");
        txt2.escapedForXMLTag();
        txt2.escapedForXMLTag();
        UnsafeString txt3 = CachedUnsafeString.makeOrEmpty("Cached");
        txt3.escapedForXMLTag();
        txt3.escapedForXMLTag();
        UnsafeString res = UnsafeString.join(", ", txt1, txt3, txt2);
        System.out.println(res);

        String rgtxt = "[^a-zA-Z0-9]";
        Pattern RX = Pattern.compile(rgtxt);
        String val = RX.matcher("Henk</p>").replaceAll("");
        System.out.println("VALUE = " + val);
        System.out.println("VALUE = " + val.replaceAll(rgtxt, ""));

        example();
    }

    public static void example() {

        Object resultSet = null;
        MockSqlHelper sqlHelper = new MockSqlHelper();
        ////


        /* In the entity. */
        String extId = sqlHelper.getSafeString(resultSet, "externalidentifier");
        UnsafeString firstName = sqlHelper.getString(resultSet, "firstname");

        /* In the old frontend, without UnsafeString */
        // input:  var welcomeMsg = "Welcome, <%= firstName %>!";
        // output: var welcomeMsg = "Welcome, Henk"
        // ...     </p>!";
        /* In the old frontend, with UnsafeString without escaping */
        // input:  var welcomeMsg = "Welcome, <%= firstName %>!";
        // output: var welcomeMsg = "Welcome, [UNSAFE STRING ~ Henk????p?]!";
        /* In the old frontend, with escaping */
        // input:  var welcomeMsg = "Welcome, <%= firstName.escapedForInsideDoubleQuotedString() %>!";
        // output: var welcomeMsg = "Welcome, Henk\"\n<\/p>!";

        System.out.println("var welcomeMsg = \"Welcome, " + firstName.unsafeGetRawValue() + "!\";");
        System.out.println("var welcomeMsg = \"Welcome, " + firstName + "!\";");
        System.out.println("var welcomeMsg = \"Welcome, " + firstName.escapedForInsideDoubleQuotedString() + "!\";");


    }

    private static class MockSqlHelper {
        public String getSafeString(Object obj, String str) {
            return "Henk\"\n</p>";
        }
        public UnsafeString getString(Object obj, String str) {
            return new LazyUnsafeString(getSafeString(obj, str));
        }
    }
}
