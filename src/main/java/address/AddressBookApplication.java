package address;

import java.io.IOException;
/***
 * @author Kevin Sebastián Frias García
 * Class in charge to start the application
 */
public class AddressBookApplication extends Menu{
    public static void main(String[] args) throws IOException {
        Menu StartApp = new AddressBookApplication();
        StartApp.displayMenu();
    }
}