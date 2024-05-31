package address_data;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.SortedMap;
import java.util.TreeMap;

/***
 * @author Kevin Sebastián Frias García
 * @implNote Class in charge to store the contact info
 * @see SortedMap
 */
public class AddressBook {
    private SortedMap<String, AddressEntry> contactMap = new TreeMap<>();
    // To get a dynamic route to the archives
    private final String userHome = System.getProperty("user.home");
    private final Path contactDir = Paths.get(userHome, "Contacts");
    private final Path contactFile = contactDir.resolve("Contact.txt");
    private final Path contactFileObject = contactDir.resolve("AddressLog.dat");
    /***
        @implSpec  The constructor loads the default file to avoid duplicates.
     */
    public AddressBook() {
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
            } else {
                contactMap.put(entry.getApellido(), entry);
                saveContact(entry);
            }
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error en la creación del contacto: " + e);
        }
    }

    public void saveContact(AddressEntry entry) throws IOException {
        try (FileOutputStream foContactList = new FileOutputStream(contactFileObject.toFile());
             ObjectOutputStream ooContactList = new ObjectOutputStream(foContactList)) {
            // Check if the object is already in the map
            ooContactList.writeObject(contactMap);
            // Now we write on the Contact.txt once we ensure there is not duplicate contact
            try (BufferedWriter bwContactList = Files.newBufferedWriter(contactFile, StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND)) {
                    bwContactList.write(entry.toString());
                    bwContactList.newLine();
            }
        } catch (IOException e) {
            System.out.println("Existe un problema al guardar los parámetros del contacto: "
                    + e.getMessage());
        }
    }

    public boolean deleteContact(String lastName) {
        try {
            if (contactMap.containsKey(lastName)) {
                contactMap.remove(lastName);
                System.out.println("Contacto eliminado: "+lastName);
                Files.delete(contactFileObject);
                Files.delete(contactFile);
                loadFileAddressLog();
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
    /* WET (Write Everything Twice) Principle
    *  The reason is to avoid the duplicate data is generated when the program starts.
    */

    @SuppressWarnings("unchecked")
    public void loadFileAddressLog() {
        try {
            if (!Files.exists(contactDir)) {
                Files.createDirectories(contactDir);
            }
            if (!Files.exists(contactFileObject)) {
                Files.createFile(contactFileObject);
            }
            if (Files.exists(contactFileObject)) {
                try (FileInputStream fis = new FileInputStream(contactFileObject.toFile()); ObjectInputStream ois = new ObjectInputStream(fis)) {
                    SortedMap<String, AddressEntry> tempContactMap = new TreeMap<>();
                    tempContactMap = (SortedMap<String, AddressEntry>) ois.readObject();
                    contactMap = tempContactMap;
                } catch (IOException e) {
                    System.out.println("No hay contactos en el archivo.");
                } catch (ClassNotFoundException e) {
                    System.out.println("No existe dicho archivo");
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo crear el archivo: "+e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    public void loadContactsFromOtherFile(String fileToBeLoad) {
        final Path file = contactDir.resolve(fileToBeLoad);
        if (Files.exists(file)) {
            try (FileInputStream fis = new FileInputStream(file.toFile()); ObjectInputStream ois = new ObjectInputStream(fis)) {
                SortedMap<String, AddressEntry> tempContactMap = new TreeMap<>();
                tempContactMap = (SortedMap<String, AddressEntry>) ois.readObject();
                for (AddressEntry entry : tempContactMap.values()) {
                    addContact(entry);
                }
            } catch (IOException e) {
                System.out.println("No hay contactos en el archivo.");
            } catch (ClassNotFoundException e) {
                System.out.println("No existe dicho archivo");
            }
        }
    }
}
