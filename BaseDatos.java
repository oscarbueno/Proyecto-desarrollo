package BaseDatos;
import java.sql.*;
import java.util.Properties;
import javax.swing.*;
import java.awt.*;
import java.io.File;
public class BaseDatos {

	

	    
	    Class c = null; //Clase generica
	    Driver d;
	    Properties clave = new Properties();
	    Connection con;
	    PreparedStatement ins = null;
	    
	    
	    
	    public void conectarBD(){
	        String controlador = "com.mysql.jdbc.Driver";
	        try{
	           c = Class.forName(controlador);
	           d = (Driver)c.newInstance(); //El driver que ponemos al principio, se usa para convertir a Driver el c.jhfj
	           clave.put("User", "root");
	           clave.put("Password", "miguel");
	           con = d.connect("jdbc:mysql://localhost:3306/test", clave); //La mayoria recibe peticiones al puerto 3306
	           //con = d.connect("jdbc:mysql://localhost:5432/test", clave);
	           System.out.println(con);
	        }catch(SQLException e1){ //El que puede generar la sentencia sql
	            System.out.println("SQLException = "+e1.toString());
	        }catch(Exception e2){ // Excepcion normal en la sentencia del codigo
	            System.out.println("Exception = "+e2.toString());
	        }
	    }
	    
	    public void insertarRegistro(String cod, String nom){
	        String sql ="INSERT INTO estudiantes (codigo, nombre, foto) VALUES(?, ?, ?)";
	        try{
	            conectarBD(); //Con los ins le estamos llenando los valores a cada uno de los signos de interrogacion
	            ins = con.prepareCall(sql);
	            ins.clearParameters(); //Borra los parametros que se encuentran en el bufer
	            ins.setString(1, cod);
	            ins.setString(2, nom);
	            String foto = ("f"+cod+".jpg");
	            ins.setString(3, foto);
	            System.out.println(ins.toString());
	            ins.executeUpdate(); 
	            con.close(); //siempre tenemos que cerrar la conexion con el close
	                   
	        }catch(SQLException e1){
	            System.out.println("SQLException = "+e1.toString());
	        }catch(Exception e2){
	            System.out.println("Exception = "+e2.toString());
	        }
	    }
	    
	    public void consultarRegistro(String cod, JLabel datoCod, JLabel datoNom, JLabel datoImag){
	        String sql = "SELECT * FROM estudiantes WHERE codigo = ?";
	        try{
	            conectarBD();
	            ResultSet reg;
	            String img;
	            ins = con.prepareCall(sql);
	            ins.clearParameters();
	            ins.setString(1, cod);
	            reg = ins.executeQuery(); 
	            
	            
	            if(reg.next()==true){
	                
	                datoCod.setText(reg.getString(1));
	                datoNom.setText(reg.getString(2));                
	                ImageIcon imag = new ImageIcon("imgs/"+reg.getString(3));                
	                datoImag.setIcon(imag);
	                
	              
	            }else{
	            datoCod.setText("No Existe...");
	            datoNom.setText("");
	            datoImag.setIcon(null);          
	            }
	            
	            con.close();
	        }catch(SQLException e1)
	        {System.out.println("SQLException="+e1.toString());
	         }catch(Exception e2){
	         System.out.println("SQLException="+e2.toString());
	         }
	    }
	    
	    public void listarRegistro(JTextArea ta){

	        ta.setText("");
	        ResultSet reg;
	        String sql = "SELECT * FROM estudiantes";
	        
	        try{
	            conectarBD();
	           ins = con.prepareCall(sql);
	           reg = ins.executeQuery();
	           while (reg.next()){
	           
	               ta.append(""+reg.getString(1)+" - ");
	               ta.append("  "+reg.getString(2)+" - ");
	               ta.append("  "+reg.getString(3));
	               ta.append("\n");
	               
	           }
	           con.close();
	        }catch(SQLException e1)
	        {System.out.println("SQLException="+e1.toString());
	         }catch(Exception e2){
	         System.out.println("SQLException="+e2.toString());
	         }
	        
	        
	    }
	    
	    public void eliminarRegistro(String cod, JLabel datos, JFrame jf){
	        
	        String sqlC = "SELECT * FROM estudiantes WHERE codigo = ?";
	        
	         try{
	            conectarBD();
	            ResultSet reg;
	            ins = con.prepareCall(sqlC);
	            ins.clearParameters();
	            ins.setString(1, cod);
	            reg = ins.executeQuery();
	            
	            if (reg.next()== true){
	            
	                datos.setText(reg.getString(1)+" - "+reg.getString(2)+" - "+reg.getString(3));
	                
	                int decision= JOptionPane.showConfirmDialog(jf, "Desea Eliminarlo","confirmar",JOptionPane.YES_NO_OPTION);
	                if(decision ==JOptionPane.YES_OPTION){
	                
	                    String sqlE = "DELETE FROM estudiantes WHERE codigo = ?";
	                    ins = con.prepareCall(sqlE);
	                    ins.clearParameters();
	                    ins.setString(1, cod);
	                    ins.executeUpdate();
	                    datos.setText("...Registro Eliminado...");
	                    File img = new File("imgs/f"+reg.getString(3));
	                    img.delete();
	                    
	                }
	                else {}
	            }
	            else{
	                datos.setText("...No Existe...");    
	            }
	            con.close();
	        }catch(SQLException e1)
	        {System.out.println("SQLException="+e1.toString());
	         }catch(Exception e2){
	         System.out.println("SQLException="+e2.toString());
	         }
	        
	        
	    }
	    
	    public void modificarRegistro(String cod,String nom, JLabel datos, JFrame jf){
	        
	        String sqlC = "SELECT * FROM estudiantes WHERE codigo = ?";
	        
	         try{
	            conectarBD();
	            ResultSet reg;
	            ins = con.prepareCall(sqlC);
	            ins.clearParameters();
	            ins.setString(1, cod);
	            reg = ins.executeQuery();
	            
	            if (reg.next()== true){
	            
	                datos.setText(reg.getString(1)+" - "+reg.getString(2)+" - "+reg.getString(3));
	                
	                int decision= JOptionPane.showConfirmDialog(jf, "Desea Modificarlo","confirmar",JOptionPane.YES_NO_OPTION);
	                if(decision ==JOptionPane.YES_OPTION){
	                
	                    String sqlE = "UPDATE estudiantes SET nombre =? WHERE codigo =?";
	                    ins = con.prepareCall(sqlE);
	                    ins.clearParameters();
	                    ins.setString(1, nom);
	                    ins.setString(2, cod);
	                    ins.executeUpdate();
	                    datos.setText("...Registro Modificado...");
	      
	                }
	                else {}
	            }
	            else{
	                datos.setText("...No Existe...");    
	            }
	            con.close();
	        }catch(SQLException e1)
	        {System.out.println("SQLException="+e1.toString());
	         }catch(Exception e2){
	         System.out.println("SQLException="+e2.toString());
	         }
	        
	    }
	    
	}


