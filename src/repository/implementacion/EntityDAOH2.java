package repository.implementacion;

import model.Entidad;
import org.apache.log4j.Logger;
import repository.IDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntityDAOH2 implements IDAO<Entidad> {

    final static Logger log = Logger.getLogger(EntityDAOH2.class);

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/test;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";

    private final static String SQL_INSERT = "INSERT **********";

    private final static String SQL_SELECT_ALL = "SELECT * FROM ******";

    private final static String SQL_SELECT = "SELECT * FROM ***** WHERE ID=?";

    @Override
    public Entidad guardar(Entidad entidad) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER).newInstance();
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement( SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,entidad.getNombre());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                entidad.setId(resultSet.getInt(1));
            }
        }
        catch (Exception e){
            log.error(e.getMessage(), e);
        }
        finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }

        return entidad;
    }

    @Override
    public List<Entidad> listarTodos() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Entidad> entidades = new ArrayList<Entidad>();
        try {
            Class.forName(DB_JDBC_DRIVER).newInstance();
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet resultados = preparedStatement.executeQuery();

            while (resultados.next()){
                Entidad entidad = new Entidad();
                entidad.setId(resultados.getInt(1));
                entidad.setNombre(resultados.getString(2));
                entidades.add(entidad);
            }
        }
        catch (Exception e){
            log.error(e.getMessage(), e);
        }
        finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }
        return entidades;
    }

    @Override
    public Entidad obtener(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Entidad entidad = new Entidad();
        try {
            Class.forName(DB_JDBC_DRIVER).newInstance();
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement(SQL_SELECT);
            ResultSet resultados = preparedStatement.executeQuery();

            if (resultados.next()){
                entidad.setId(resultados.getInt(1));
                entidad.setNombre(resultados.getString(2));
            }
        }
        catch (Exception e){
            log.error(e.getMessage(), e);
        }
        finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }
        return entidad;
    }
}
