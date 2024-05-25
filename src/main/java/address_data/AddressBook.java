package address_data;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.TreeMap;

public class AddressBook {
    private TreeMap<String, AddressEntry> contactMap = new TreeMap<>();
    // To get a dynamic route to the contact.txt
    private final String userHome = System.getProperty("user.home");
    private final Path contactDir = Paths.get(userHome, "Contacts");
    private final Path contactFile = contactDir.resolve("Contact.txt");
    private final Path contactFileObject = contactDir.resolve("AddressLog.dat");


    public void addEntry(AddressEntry entry) {
        contactMap.put(entry.getApellido(), entry);
        saveEntry(entry);
    }
    public void saveEntry(AddressEntry entry){
        try {
            if (!Files.exists(contactDir)) {
                Files.createDirectories(contactDir);
            }
            if (!Files.exists(contactFileObject)) {
                Files.createFile(contactFileObject);
            }
        } catch (IOException e) {
            System.out.println("No se pudo crear el archivo");
        }
        // Explain later
        try (BufferedWriter bwContactList = Files.newBufferedWriter(contactFile,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {

                bwContactList.write(entry.toString());
                bwContactList.newLine();

        } catch (IOException e) {
            System.out.println("Existe un problema al guardar los parámetros del contacto");
        }

        // The main file that will be overwritten
        try (FileOutputStream foContactList = new FileOutputStream(contactFileObject.toFile());
             ObjectOutputStream ooContactList = new ObjectOutputStream(foContactList)) {
            ooContactList.writeObject(contactMap);
        } catch (IOException e) {
            System.out.println("No se pudo escribir en el archivo");
        }

    }

    public boolean deleteEntry(String lastName) {
        try {
            if (contactMap.containsKey(lastName)) {
                contactMap.remove(lastName);
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
                return false;
            }
        }catch(Exception e) {
            System.out.println("Por favor, introduce una entrada valida.");
            return false;
        }
    }

    public void printAllContact() {
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
    private void loadContacts(Path file) {
        if (Files.exists(file)) {
            try (FileInputStream fis = new FileInputStream(file.toFile());
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                contactMap = (TreeMap<String, AddressEntry>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("No se pudo leer el archivo: " + e.getMessage());
            }
        }
    }
}
