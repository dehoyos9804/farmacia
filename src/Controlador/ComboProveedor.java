
package Controlador;

/**
 *
 * @author Aldair
 */
public class ComboProveedor {
   private String idproveedor;
   private String nombre;
   private String direccion;
   private String telefonos;
   private String email;

    public String getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(String idproveedor) {
        this.idproveedor = idproveedor;
    }

   
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ComboProveedor(String idproveedor, String nombre, String direccion, String telefonos, String email) {
        this.idproveedor = idproveedor;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefonos = telefonos;
        this.email = email;
    }


   
    public String toString(){
        return nombre;
    }
    public boolean equals(Object objeto){
        if(objeto==null){
            return false;
        }
        if(getClass()!=objeto.getClass()){
            return false;
        }
        final ComboProveedor other=(ComboProveedor) objeto;
        if(this.nombre!=other.nombre){
            return false;
        }
        return true;
    }
}
