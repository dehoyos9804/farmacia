
package Controlador;

/**
 *
 * @author Aldair
 */
public class ComboDetalleVenta {
  String codigo;
  String nombreproducto;
  String cantidadvender;
  String precio;
  String valorneto;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }


    public String getCantidadvender() {
        return cantidadvender;
    }

    public void setCantidadvender(String cantidadvender) {
        this.cantidadvender = cantidadvender;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getValorneto() {
        return valorneto;
    }

    public void setValorneto(String valorneto) {
        this.valorneto = valorneto;
    }

    public ComboDetalleVenta(String codigo, String nombreproducto, String cantidadvender, String precio, String valorneto) {
        this.codigo = codigo;
        this.nombreproducto = nombreproducto;
        this.cantidadvender = cantidadvender;
        this.precio = precio;
        this.valorneto = valorneto;
    }
  
}
