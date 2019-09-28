/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

public class ComboMedicamento {
private String idmedicamento;
private String nombre;
private String cantidadmedicamento;
private String preciounidad; 
private String preciocompra;

    public String getIdmedicamento() {
        return idmedicamento;
    }

    public void setIdmedicamento(String idmedicamento) {
        this.idmedicamento = idmedicamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidadmedicamento() {
        return cantidadmedicamento;
    }

    public void setCantidadmedicamento(String cantidadmedicamento) {
        this.cantidadmedicamento = cantidadmedicamento;
    }

    public String getPreciounidad() {
        return preciounidad;
    }

    public void setPreciounidad(String preciounidad) {
        this.preciounidad = preciounidad;
    }
    
    public String getPrecioCompra(){
        return preciocompra;
    }
    
    public void setPrecioCompra(String preciocompra){
        this.preciocompra=preciocompra;
    }
    
    public ComboMedicamento(String idmedicamento, String nombre, String cantidadmedicamento, String preciounidad,String preciocompra) {
        this.idmedicamento = idmedicamento;
        this.nombre = nombre;
        this.cantidadmedicamento = cantidadmedicamento;
        this.preciounidad = preciounidad;
        this.preciocompra = preciocompra;
    }

    public String toString(){
        return nombre;
    }
    
   public boolean equals(Object objeto){
        if(objeto==null){
            return false;
        }
        if(this==objeto){
            return true;
        }
        if(getClass()!=objeto.getClass()){
            return false;
        }
        ComboMedicamento other=(ComboMedicamento) objeto;
        if(this.nombre !=other.nombre){
            return false;
        }
        
        return true;
    }

}
