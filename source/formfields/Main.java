package formfields;

public class Main {
    public static void main(String[] args) {
        Form form = new Form();
        DateField date = form.addDate("birthday", "When is your birthday?");
        EmailField email = form.addEmail("myAddress", "Your email addresss");
        email.makeRequired();
        EmailField guestAddress = form.addEmail("guestAddress", "Guest email address");
        System.out.println(form.renderAsHtml());
    }
}
