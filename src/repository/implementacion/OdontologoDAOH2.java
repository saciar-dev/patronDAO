package repository.implementacion;

import model.Odontologo;
import org.apache.log4j.Logger;
import repository.IDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements IDAO<Odontologo> {

    final static Logger log = Logger.getLogger(OdontologoDAOH2.class);

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/test;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";

    private final static String SQL_INSERT = "INSERT INTO odontologo (matricula, nombre, apellido) VALUES (?,?,?)";

    private final static String SQL_SELECT_ALL = "SELECT * FROM odontologo";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        log.info("Guardando un nuevo odontologo...");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER).newInstance();
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement( SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,odontologo.getMatricula());
            preparedStatement.setString(2,odontologo.getNombre());
            preparedStatement.setString(3,odontologo.getApellido());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                odontologo.setId(resultSet.getInt(1));
            }
            log.info("Se guardo el odontologo con id: "+odontologo.getId()+" correctamente");
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
            }
        }

        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        log.info("listando todos los odontologos...");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Odontologo> listaOdontologos = new ArrayList<>();
        try {
            Class.forName(DB_JDBC_DRIVER).newInstance();
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet resultados = preparedStatement.executeQuery();

            while (resultados.next()){
                Odontologo odontologo = new Odontologo();
                odontologo.setId(resultados.getInt(1));
                odontologo.setMatricula(resultados.getInt(2));
                odontologo.setNombre(resultados.getString(3));
                odontologo.setApellido(resultados.getString(4));
                listaOdontologos.add(odontologo);
            }
            log.info("Se listaron todos los odontologos correctamente");
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
            }
        }
        return listaOdontologos;
    }

}
