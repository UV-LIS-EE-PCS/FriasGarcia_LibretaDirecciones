package address;
import address_data.AddressBook;
import address_data.AddressEntry;
import java.util.Scanner;

public class Menu extends AddressBook{
    AddressBook conctactList = new AddressBook();
    public void displayMenu (String optionSelected) {
        System.out.println("Ingrese la opción a ejecutar");
        Scanner sc = new Scanner(System.in);
        switch (optionSelected) {
            case "a": //Load file
                System.out.println("Ingrese el nombre del archivo");
                // working in progress...
                break;
            case "b": // Add
                String [] contactInfo = new String[7];
                System.out.println("Nombre:");
                contactInfo[0] = sc.nextLine();
                System.out.println("Apellido:");
                contactInfo[1] = sc.nextLine();
                System.out.println("Calle:");
                contactInfo[2] = sc.nextLine();
                System.out.println("Ciudad:");
                contactInfo[3] = sc.nextLine();
                System.out.println("Estado:");
                contactInfo[4] = sc.nextLine();
                System.out.println("Código postal:");
                int postal = sc.nextInt();
                System.out.println("Teléfono:");
                contactInfo[5] = sc.nextLine();
                System.out.println("Correo electrónico:");
                contactInfo[6] = sc.nextLine();
                conctactList.addEntry(new AddressEntry(contactInfo[0],contactInfo[1],
                        contactInfo[2],contactInfo[3],contactInfo[4],postal,contactInfo[5],contactInfo[6]));
                break;
            case "c": // Delete
                System.out.println("Ingrese el apellido del contacto a eliminar");
                String contactLastname = sc.nextLine();
                if (conctactList.searchContact(contactLastname)) {
                    System.out.println("Estas seguro: Y/N");
                    sc.nextLine();
                    if (sc.nextLine().equals("Y")) {
                        conctactList.deleteEntry(contactLastname);
                    }
                }
                break;
            case "d":
                System.out.println("Ingrese el apellido del contacto a buscar:");
                String contactLastName = sc.nextLine();
                conctactList.searchContact(contactLastName);
            case "e":
                System.out.println("Mostrando la lista de contactos:");
                conctactList.printAllContact();
            case "f":
                System.exit(0);
                break;
            default:
                System.out.println("Intenta escribir un valor valido");
                break;
        }
    }
}
