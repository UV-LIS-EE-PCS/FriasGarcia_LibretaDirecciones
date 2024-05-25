package address;

import com.github.lalyos.jfiglet.FigletFont;

import java.awt.desktop.AppEvent;
import java.io.IOException;

public class AddressBookAplication extends Menu{
    public static void main(String[] args) throws IOException {
        Menu StartApp = new AddressBookAplication();
        StartApp.displayMenu();
    }
}