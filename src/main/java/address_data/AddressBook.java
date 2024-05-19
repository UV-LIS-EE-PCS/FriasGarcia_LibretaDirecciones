package address_data;
import java.util.TreeMap;

public class AddressBook {
    private TreeMap<String, AddressEntry> contactMap = new TreeMap<>();

    public void addEntry(AddressEntry entry) {
        contactMap.put(entry.getApellido(), entry);
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
            System.out.println("Por favor, introduce una entrada válida.");
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
            System.out.println("Por favor, introduce una entrada válida.");
            return false;
        }
    }
    public void printAllContact() {
        for (AddressEntry entry : contactMap.values()) {
            System.out.println(entry);
        }
    }
}
