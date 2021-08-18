package model.dao;

import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.JDBCUtilities;
import model.vo.MaterialConstruccion;

public class MaterialConstruccionDao {

    //Consultar todos (READ)
    public ArrayList<MaterialConstruccion> consultarTodos() throws SQLException {

        ArrayList<MaterialConstruccion> respuesta = new ArrayList<MaterialConstruccion>();
        Connection conexion = null;        

        try{
            conexion = JDBCUtilities.getConnection();

            String consulta = "SELECT * FROM MaterialConstruccion;";

            PreparedStatement statement = conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                MaterialConstruccion material = new MaterialConstruccion();
                material.setIdMaterialConstruccion(resultSet.getInt(1));
                material.setNombreMaterial(resultSet.getString(2));
                material.setImportado(resultSet.getString(3));
                material.setPrecioUnidad(resultSet.getInt(4));                              
                respuesta.add(material);
            }

            //Abiertas esas interacciones con BD
            resultSet.close();
            statement.close();


        }catch(SQLException e){
            System.err.println("Error consultando todos los materiales! "+e);
        }finally{
            //Cierre del controlador
            if(conexion != null){
                conexion.close();
            }
        }

        //Retornar colección de vo's que satisfacen el requerimiento
        return respuesta;        
    }

    //Insertar material (Create)
    public MaterialConstruccion adicionarMaterial(MaterialConstruccion nuevoMaterial) throws SQLException {

        MaterialConstruccion materialAdicionado = null;
        Connection conexion = null;        

        try{
            conexion = JDBCUtilities.getConnection();

            //Estrictamente la estructura de las tuplas de la entidad/tabla
            //"INSERT INTO MaterialConstruccion VALUES (?, ?, ?, ?)"

            //Especificar la estructura de la tupla, ventajas:
            //No falla cuando hay campos nuevos
            //Deja en manos de la BD la generación de la llave primaria
            String consulta = "INSERT INTO MaterialConstruccion (Nombre_Material, Importado, Precio_Unidad) VALUES (?, ?, ?)";           

            PreparedStatement statement = conexion.prepareStatement(consulta);

            statement.setString(1, nuevoMaterial.getNombreMaterial());
            statement.setString(2, nuevoMaterial.getImportado());
            statement.setInt(3, nuevoMaterial.getPrecioUnidad());

            //Realizar la actualización: Crear material
            statement.executeUpdate();

            //Cerrar interacciones con BD            
            statement.close();

            //Si el proceso fue exitoso cambiar el estado
            materialAdicionado = nuevoMaterial;

        }catch(SQLException e){
            System.err.println("Error registrando material! "+e);
        }finally{
            //Cierre del controlador
            if(conexion != null){
                conexion.close();
            }
        }

        //Retornar la instancia del material o el nulo para validaciones posteriores
        return materialAdicionado;        
    }    

    //Actualizar material (Update)
    public MaterialConstruccion actualizarMaterial(MaterialConstruccion materialActualizar) throws SQLException {

        MaterialConstruccion materialActualizado = null;
        Connection conexion = null;        

        try{
            conexion = JDBCUtilities.getConnection();            

            //Construir el DML de actualización
            String consulta = "UPDATE MaterialConstruccion SET Nombre_Material = ?, Importado = ?, Precio_Unidad = ? WHERE ID_MaterialConstruccion = ?;";           

            PreparedStatement statement = conexion.prepareStatement(consulta);

            statement.setString(1, materialActualizar.getNombreMaterial());
            statement.setString(2, materialActualizar.getImportado());
            statement.setInt(3, materialActualizar.getPrecioUnidad());
            statement.setInt(4, materialActualizar.getIdMaterialConstruccion());

            //Realizar la actualización: Crear material
            statement.executeUpdate();

            //Cerrar interacciones con BD            
            statement.close();

            //Si el proceso fue exitoso cambiar el estado
            materialActualizado = materialActualizar;

        }catch(SQLException e){
            System.err.println("Error actualizando materia!! "+e);
        }finally{
            //Cierre del controlador
            if(conexion != null){
                conexion.close();
            }
        }

        //Retornar la instancia del material o el nulo para validaciones posteriores
        return materialActualizado;        
    }


    //Eliminar material (Delete)    
    public MaterialConstruccion eliminarMaterial(MaterialConstruccion materialEliminar) throws SQLException {

        MaterialConstruccion materialEliminado = null;
        Connection conexion = null;        

        try{
            conexion = JDBCUtilities.getConnection();            

            //Construir el DML de actualización
            String consulta = "DELETE FROM MaterialConstruccion WHERE ID_MaterialConstruccion = ?;";           

            PreparedStatement statement = conexion.prepareStatement(consulta);
            
            statement.setInt(1, materialEliminar.getIdMaterialConstruccion());

            //Realizar la actualización: Crear material
            statement.executeUpdate();

            //Cerrar interacciones con BD            
            statement.close();

            //Si el proceso fue exitoso cambiar el estado
            materialEliminado = materialEliminar;

        }catch(SQLException e){
            System.err.println("Error eliminando material!! "+e);
        }finally{
            //Cierre del controlador
            if(conexion != null){
                conexion.close();
            }
        }

        //Retornar la instancia del material o el nulo para validaciones posteriores
        return materialEliminado;        
    }

    
    
    
}
