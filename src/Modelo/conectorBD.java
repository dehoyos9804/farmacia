
package Modelo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class conectorBD {
    private Connection conexion;
    private Statement sentencia;
    
    private final String servidor="localhost";
    private final String puerto="3306";
    private final String bd="bdfarmacia";
    private final String usuario="root";
    private final String clave="";
    private final String URL="jdbc:mysql://" +
            servidor + ":" + puerto + "/" + bd;

    public conectorBD(){
        this.conexion=null;
        this.sentencia=null;
    }    
    
    public boolean conectar(){
        boolean estado=false;
        try
         {
            Class.forName("com.mysql.jdbc.Driver");
            
             try{ 
                 conexion= DriverManager.getConnection(URL,usuario,clave);
                 estado= true;
             }catch (SQLException sqle){
                 System.err.println("ERROR: conexion con BD");
                 
                }
             
         }catch (ClassNotFoundException EX){
            System.err.println("ERROR: no existe el Driver"); 
             
         }
        return estado;
    }
    
    
    public ResultSet seleccionar (String sql){
       ResultSet resultado = null;  
       
       try {
           sentencia =conexion.createStatement();
           resultado= sentencia.executeQuery(sql);
           
       }catch (SQLException sqle){
           System.err.println("ERROR: conectorBD.seleccionar (sql)");  
            System.err.println( sqle.getMessage()); 
       }
       
       return resultado;
     }
     public boolean ejecutar (String sql){
         boolean estado=false;
          try{
              sentencia= conexion.createStatement();
              sentencia.execute (sql);
              sentencia.close();
              estado=true;
          }catch (SQLException sqle){
            System.err.println("ERROR: conectorBD.seleccionar (sql)");  
            System.err.println( sqle.getMessage());    
          }
          return estado;
     }
     
     
     public void desconectar (){
         try {
             if (conexion != null){
                 conexion.close();
             }
         }catch (SQLException sqle){
            System.err.println("ERROR: conectorBD.seleccionar (sql)");  
            System.err.println( sqle.getMessage()); 
         }
     }
     
    public  Connection getconecction (){
        return conexion; 
    }
}
