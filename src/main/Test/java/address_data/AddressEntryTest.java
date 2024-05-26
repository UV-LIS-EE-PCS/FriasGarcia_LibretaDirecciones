package address_data;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

public class AddressEntryTest {
    AddressEntry addressTestOne =  new AddressEntry("Kevin","García","Esmeralda","Coatza"
            ,"Veracruz", 96496,"2172647","zs22017021@estudiantes.uv.mx");
    AddressEntry addressTestTwo = new AddressEntry("Marco","Contreras","Septiembre","Mina"
            ,"Veracruz",99123,"123-5356342","M00MHunnter@gmail.com");

    @Test
    public void getAddressTest() {
        Assert.assertEquals("Kevin",addressTestOne.getNombre());
        Assert.assertEquals("García",addressTestOne.getApellido());
        Assert.assertEquals("Esmeralda",addressTestOne.getCalle());


        Assert.assertEquals("Veracruz",addressTestTwo.getEstado());
        Assert.assertTrue(Pattern.matches("[0-9].*",addressTestTwo.getTelefono()));
        Assert.assertEquals("M00MHunnter@gmail.com",addressTestTwo.getCorreoElectronico());
    }


    @Test
    public void setAddressTest() {
        addressTestOne.setCalle("Escutcheon");
        Assert.assertEquals("Escutcheon",addressTestOne.getCalle());
        addressTestOne.setTelefono("12345");
        Assert.assertEquals("12345",addressTestOne.getTelefono());
        addressTestOne.setCorreoElectronico("kevinsfg24@gmail.com");
        Assert.assertEquals("kevinsfg24@gmail.com",addressTestOne.getCorreoElectronico());

        addressTestTwo.setCodigoPostal(45678);
        Assert.assertEquals(45678,addressTestTwo.getCodigoPostal());
        addressTestTwo.setNombre("Coule");
        Assert.assertEquals("Coule",addressTestTwo.getNombre());
        addressTestTwo.setEstado("Texas");
        Assert.assertEquals("Texas",addressTestTwo.getEstado());
    }
}
