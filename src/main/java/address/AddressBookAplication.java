package address;

import java.io.IOException;
/***
 * @author Kevin Sebastián Frias García
 * Class in charge to start the application
 */
public class AddressBookAplication extends Menu{
    public static void main(String[] args) throws IOException {
        Menu StartApp = new AddressBookAplication();
        StartApp.displayMenu();
    }
}