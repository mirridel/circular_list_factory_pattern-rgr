import data.types.UserFactory;
import data.types.UserType;
import ListActionListener;
import ListActionListenerImpl;
import UI;

public class Main {
    public static void main(String[] args) {
        UserType t = UserFactory.getBuilderByName("Point");
        ListActionListener listActionListener = new ListActionListenerImpl();
        new UI(listActionListener);
    }
}