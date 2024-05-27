package address_data;

import java.io.Serializable;

/***
 * @author Kevin Sebastián Frias García
 * @implNote Class in charge to store the contact info
 * @see java.io.Serializable
 */
public class AddressEntry implements Serializable {
    private static final long serialVersionUID = 84320199408259009L;

   private String nombre;
    private String apellido;
    private String calle;
    private String ciudad;
    private String estado;
    private int codigoPostal;
    private String telefono;
    private String correoElectronico;

    public AddressEntry(String nombre, String apellido, String calle, String ciudad
            , String estado, int codigoPostal, String telefono, String correoElectronico) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.calle = calle;
        this.ciudad = ciudad;
        this.estado = estado;
        this.codigoPostal = codigoPostal;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    @Override
    public String toString() {
        return (getNombre()+" "+getApellido()+"\n"+getCalle()+" \n"
                +getCiudad()+", "+getEstado()+". "+getCodigoPostal()+"\n"+getCorreoElectronico()+"\n"
                +getTelefono()+"\n\n");
    }
}
