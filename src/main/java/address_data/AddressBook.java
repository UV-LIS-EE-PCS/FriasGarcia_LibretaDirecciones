package address_data;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.TreeMap;
/***
 * @author Kevin Sebastián Frias García
 * Class in charge to store the contact info
 */
public class AddressBook {
    private TreeMap<String, AddressEntry> contactMap = new TreeMap<>();
    // To get a dynamic route to the contact.txt
    private final String userHome = System.getProperty("user.home");
    private final Path contactDir = Paths.get(userHome, "Contacts");
    private final Path contactFile = contactDir.resolve("Contact.txt");
    private final Path contactFileObject = contactDir.resolve("AddressLog.dat");
    /***
        @apiNote The constructor loads the default file to avoid duplicates
     */
    public AddressBook() {
        try {
            if (!Files.exists(contactDir)) {
                Files.createDirectories(contactDir);
            }
            if (!Files.exists(contactFileObject)) {
                Files.createFile(contactFileObject);
            }
        } catch (IOException e) {
            System.out.println("No se pudo crear el archivo: "+e.getMessage());
        }

        loadFileAddressLog();
    }

    public Path getContactFile() {
        return contactFile;
    }

    public Path getContactFileObject() {
        return contactFileObject;
    }

    public void addContact(AddressEntry entry) {
        try {
            if (contactMap.containsKey(entry.getApellido())) {
                System.out.println("Ya existe una contacto con ese apellido");
                searchContact(entry.getApellido());
                Thread.sleep(600);
            } else {
                contactMap.put(entry.getApellido(), entry);
                saveContact(entry);
            }
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error en la creación del contacto: " + e);
        }
    }
    public void saveContact(AddressEntry entry){
        try {
            if (!Files.exists(contactDir)) {
                Files.createDirectories(contactDir);
            }
            if (!Files.exists(contactFileObject)) {
                Files.createFile(contactFileObject);
            }
        } catch (IOException e) {
            System.out.println("No se pudo crear el archivo: "+e.getMessage());
        }
        try (BufferedWriter bwContactList = Files.newBufferedWriter(contactFile,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {

                bwContactList.write(entry.toString());
                bwContactList.newLine();

        } catch (IOException e) {
            System.out.println("Existe un problema al guardar los parámetros del contacto: "
            + e.getMessage());
        }

        // The main file that will be overwritten
        try (FileOutputStream foContactList = new FileOutputStream(contactFileObject.toFile());
             ObjectOutputStream ooContactList = new ObjectOutputStream(foContactList)) {
            ooContactList.writeObject(contactMap);
        } catch (IOException e) {
            System.out.println("No se pudo escribir en el archivo: "+e.getMessage());
        }

    }
    public boolean deleteContact(String lastName) {
        try {
            if (contactMap.containsKey(lastName)) {
                contactMap.remove(lastName);
                System.out.println("Contacto eliminado: "+lastName);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Por favor, introduce una entrada valida.");
            return false;
        }
    }
    public boolean searchContact(String lastName) {
        try {
            if (contactMap.containsKey(lastName)) {
                System.out.print(contactMap.get(lastName));
                return true;
            } else {
                System.out.println("El contacto no existe...");
                return false;
            }
        }catch(Exception e) {
            System.out.println("Por favor, introduce una entrada valida.");
            return false;
        }
    }
    public void printAllContact() {
        if (contactMap.isEmpty()) {
            System.out.println("No hay contactos...\n");
            return;
        }
        for (AddressEntry entry : contactMap.values()) {
            System.out.println(entry);
        }
    }

    public void loadFileAddressLog() {
        loadContacts(contactFileObject);
    }
    public void loadContactsFromOtherFile(String fileToBeLoad) {
        final Path file = contactDir.resolve(fileToBeLoad);
        loadContacts(file);
    }
    // This method is in charge of search the file to be loaded
    @SuppressWarnings("unchecked")
    private void loadContacts(Path file) {
        if (Files.exists(file)) {
            try (FileInputStream fis = new FileInputStream(file.toFile());
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                TreeMap<String, AddressEntry> tempContactMap = new TreeMap<>();
                tempContactMap = (TreeMap<String, AddressEntry>) ois.readObject();
                for (AddressEntry entry : tempContactMap.values()) {
                    addContact(entry);
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("No se pudo leer el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("El archivo no existe...");
        }
    }
}
