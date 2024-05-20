package address_data;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.TreeMap;

public class AddressBook {
    private TreeMap<String, AddressEntry> contactMap = new TreeMap<>();
    // In order to get a dynamic route to the main .txt
    private final String userHome = System.getProperty("user.home");
    private final Path contactDir = Paths.get(userHome, "Contacts");
    private final Path contactFile = contactDir.resolve("AddressLog.txt");

    public void addEntry(AddressEntry entry) {
        contactMap.put(entry.getApellido(), entry);
        saveEntry(entry);
    }
    public void saveEntry(AddressEntry entry){
        try {
            if (!Files.exists(contactDir)) {
                Files.createDirectories(contactDir);
            }
        } catch (IOException e) {
            System.out.println("No se pudo crear el archivo");
        }
        // Explain later
        try (BufferedWriter bwContactList = Files.newBufferedWriter(contactFile,
                java.nio.file.StandardOpenOption.CREATE,
                java.nio.file.StandardOpenOption.APPEND)) {

                bwContactList.write(entry.toString());
                bwContactList.newLine();

        } catch (IOException e) {
            System.out.println("Existe un problema al guardar los parámetros del contacto");
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

}
