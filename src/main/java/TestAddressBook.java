import address.Menu;

import java.util.Scanner;

public class TestAddressBook extends Menu {
    public static void main(String[] args) {
        Menu mainMenu = new Menu();
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        mainMenu.displayMenu(answer);
    }
}
