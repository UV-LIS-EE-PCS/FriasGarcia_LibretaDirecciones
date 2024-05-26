package address_data;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AddressBookTest extends AddressBook {

    AddressBook TestOne = new AddressBook();
    AddressEntry newAddress =  new AddressEntry("Kevin","García","Esmeralda","Coatza"
            ,"Veracruz", 96496,"2172647","zs22017021@estudiantes.uv.mx");
    AddressBook TestTwo = new AddressBook();
    AddressEntry addressTestTwo = new AddressEntry("Jose","Vergara","Buganvilias","Coatza"
    ,"Ver",98767,"98765431","jvergara@uv.mx");

    @Test
    public void addContactTest() {

        TestOne.addContact(newAddress);
        TestTwo.addContact(addressTestTwo);

        Assert.assertTrue(TestOne.searchContact(newAddress.getApellido()));
        Assert.assertTrue(TestTwo.searchContact(addressTestTwo.getApellido()));
    }

    @Test
    public void wasFileCreate () {
        final String userHome = System.getProperty("user.home");
        final Path contactDir = Paths.get(userHome, "Contacts");
        final Path contactFile = contactDir.resolve("Contact.txt");
        final Path contactFileObject = contactDir.resolve("AddressLog.dat");

        TestTwo.loadFileAddressLog();

        Assert.assertEquals(contactFile, TestTwo.getContactFile());
        Assert.assertEquals(contactFileObject, TestTwo.getContactFileObject());
    }

    @Test
    public void deleteContactTest() {
        TestTwo.deleteContact("Vergara");
        Assert.assertFalse(TestTwo.searchContact("Vergara"));

    }

    @Test
    public void searchContactTest() {
        TestOne.addContact(addressTestTwo);
        Assert.assertTrue(TestOne.searchContact(addressTestTwo.getApellido()));
    }

    @Test
    public void printAllContact() {
        TestOne.addContact(newAddress);
        TestOne.addContact(addressTestTwo);
        TestOne.printAllContact();
        TestTwo.printAllContact();
        // This method should return when the map contains some contacts and when it doesn't
        // Example for none contacts: "No hay contactos..."
    }
}
