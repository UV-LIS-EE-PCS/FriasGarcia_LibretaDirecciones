package address;
import address_data.AddressBook;
import address_data.AddressEntry;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/***
 * @author Kevin Sebastián Frias García
 * Class in charge to implements the methods
 */
public class Menu {
    private AddressBook conctactList = new AddressBook();
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    protected void displayMenu () throws IOException {
        System.out.print("\n" +
                "      ___                   ___          ___        ___          ___          ___                   ___         ___     \n" +
                "     /\\  \\        ___      /\\  \\        /\\__\\      /\\__\\        /\\  \\        /\\__\\        ___      /\\  \\       /\\  \\    \n" +
                "    /::\\  \\      /\\  \\    /::\\  \\      /::|  |    /:/  /       /::\\  \\      /::|  |      /\\  \\    /::\\  \\     /::\\  \\   \n" +
                "   /:/\\:\\  \\     \\:\\  \\  /:/\\:\\  \\    /:|:|  |   /:/  /       /:/\\:\\  \\    /:|:|  |      \\:\\  \\  /:/\\:\\  \\   /:/\\:\\  \\  \n" +
                "  /::\\~\\:\\__\\    /::\\__\\/::\\~\\:\\  \\  /:/|:|  |__/:/__/  ___  /::\\~\\:\\  \\  /:/|:|  |__    /::\\__\\/:/  \\:\\__\\ /:/  \\:\\  \\ \n" +
                " /:/\\:\\ \\:|__|__/:/\\/__/:/\\:\\ \\:\\__\\/:/ |:| /\\__\\:|  | /\\__\\/:/\\:\\ \\:\\__\\/:/ |:| /\\__\\__/:/\\/__/:/__/ \\:|__/:/__/ \\:\\__\\\n" +
                " \\:\\~\\:\\/:/  /\\/:/  /  \\:\\~\\:\\ \\/__/\\/__|:|/:/  /:|  |/:/  /\\:\\~\\:\\ \\/__/\\/__|:|/:/  /\\/:/  /  \\:\\  \\ /:/  |:\\  \\ /:/  /\n" +
                "  \\:\\ \\::/  /\\::/__/    \\:\\ \\:\\__\\      |:/:/  /|:|__/:/  /  \\:\\ \\:\\__\\      |:/:/  /\\::/__/    \\:\\  /:/  / \\:\\  /:/  / \n" +
                "   \\:\\/:/  /  \\:\\__\\     \\:\\ \\/__/      |::/  /  \\::::/__/    \\:\\ \\/__/      |::/  /  \\:\\__\\     \\:\\/:/  /   \\:\\/:/  /  \n" +
                "    \\::/__/    \\/__/      \\:\\__\\        /:/  /    ~~~~         \\:\\__\\        /:/  /    \\/__/      \\::/__/     \\::/  /   \n" +
                "     ~~                    \\/__/        \\/__/                   \\/__/        \\/__/                 ~~          \\/__/    \n");
        System.out.println("Recuerda leer los mensajes del sitema");
        while (true) {
            System.out.println("Selecciona una opción:\n" +
                    "-----------------------------------\n" +
                    "a) Cargar un archivo\n" +
                    "b) Agregar contacto\n" +
                    "c) Eliminar contacto\n" +
                    "d) Buscar contacto\n" +
                    "e) Mostrar todos los contactos\n" +
                    "f) Salir del programa\n" +
                    "-----------------------------------");
            try {
                String optionSelected = reader.readLine();
                switch (optionSelected) {
                    case "a":
                        System.out.println("Ingrese el nombre del archivo\nRecuerde:\n1- El archivo de estar en el directorio Contacts" +
                                "\n2- El archivo debe haber sido creado por el programa (.dat)");
                        try {
                            final String file = reader.readLine();
                            conctactList.loadContactsFromOtherFile(file);
                        } catch (IOException e) {
                            System.out.println("Ha habido un error inesperado");
                        }


                        break;
                    case "b":
                        try {
                            String[] contactInfo = new String[7];
                            System.out.println("Nombre:");
                            contactInfo[0] = reader.readLine();
                            System.out.println("Apellido:");
                            contactInfo[1] = reader.readLine();
                            System.out.println("Calle:");
                            contactInfo[2] = reader.readLine();
                            System.out.println("Ciudad:");
                            contactInfo[3] = reader.readLine();
                            System.out.println("Estado:");
                            contactInfo[4] = reader.readLine();
                            System.out.println("Código postal:");
                            int postal = Integer.parseInt(reader.readLine());
                            System.out.println("Teléfono:");
                            contactInfo[5] = reader.readLine();
                            System.out.println("Correo electrónico:");
                            contactInfo[6] = reader.readLine();

                            conctactList.addContact(new AddressEntry(contactInfo[0], contactInfo[1],
                                    contactInfo[2], contactInfo[3], contactInfo[4], postal, contactInfo[5], contactInfo[6]));

                        } catch (IOException | NumberFormatException e) {
                            System.out.println("Revisa los valores ingresados...\n"+e.getMessage());
                        }
                        break;
                    case "c":
                        System.out.println("Ingrese el apellido del contacto a eliminar");
                        String contactLastname;
                        try {
                            contactLastname = reader.readLine();
                            if (conctactList.searchContact(contactLastname)) {
                                System.out.println("Estas seguro: Y/N");
                                String confirmation = reader.readLine();
                                if (confirmation.equals("Y")) {
                                    conctactList.deleteContact(contactLastname);
                                }
                            }
                        } catch (IOException e) {
                            System.out.println("Revisa los valores ingresados...\n"+e.getMessage());
                        }
                        break;
                    case "d":
                        System.out.println("Ingrese el apellido del contacto a buscar:");
                        String contactLastName;
                        try {
                            contactLastName = reader.readLine();
                            conctactList.searchContact(contactLastName);
                        } catch (IOException e) {
                            System.out.println("Revisa los valores ingresados...\n"+e.getMessage());
                        }
                        break;
                    case "e":
                        System.out.println("Mostrando la lista de contactos:");
                        conctactList.printAllContact();
                        break;
                    case "f":
                        System.out.println("Saliendo del programa...");
                        reader.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Intenta escribir un valor valido -> "+optionSelected);
                        break;
                }
            } catch (IOException e) {
                System.out.println("El valor introducido no es váildo: "+ e.getMessage());
            }
        }
    }

    public AddressBook getConctactList() {
        return conctactList;
    }
}
