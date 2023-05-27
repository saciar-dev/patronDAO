package services;

import com.sun.source.tree.AssertTree;
import model.Odontologo;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.implementacion.OdontologoDAOH2;
import repository.implementacion.OdontologoDAOMemory;

import java.io.File;
import java.util.List;

class OdontologoServiceTest {

    private static OdontologoService odontologoService;

    private static OdontologoService odontologoServiceMemory;

    @BeforeAll
    public static void inicializar(){
        File log4jfile = new File("./libraries/log4j.properties");
        PropertyConfigurator.configure(log4jfile.getAbsolutePath());

        odontologoService = new OdontologoService(new OdontologoDAOH2());
        odontologoServiceMemory = new OdontologoService(new OdontologoDAOMemory());
    }

    @Test
    void guardar() {
        Odontologo odontologo = new Odontologo(123456,"Juan Manuel","Licini");
        Odontologo odontologoResult = odontologoService.guardar(odontologo);

        Assertions.assertTrue(odontologoResult.getId() != 0);

    }

    @Test
    void guardarEnMemoria(){
        Odontologo odontologo = new Odontologo(789456, "Sergio", "Aciar");
        Odontologo odontologoResult = odontologoServiceMemory.guardar(odontologo);

        Assertions.assertTrue(odontologoResult.getId() != 0);
    }

    @Test
    void listarTodos() {
        List<Odontologo> listado = odontologoService.listarTodos();

        Assertions.assertTrue(!listado.isEmpty());

    }

    @Test
    void listarTodosEnMemoria(){
        List<Odontologo> listado = odontologoServiceMemory.listarTodos();

        Assertions.assertFalse(listado.isEmpty());
    }

}