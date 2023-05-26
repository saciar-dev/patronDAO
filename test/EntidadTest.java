import model.Entidad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repository.implementacion.EntityDAOH2;
import repository.implementacion.EntityDAOMemory;
import services.EntityService;


import java.util.List;

public class EntidadTest {


    private static EntityService entityService = new EntityService(new EntityDAOH2());

    private static EntityService entityServiceMemory = new EntityService(new EntityDAOMemory());

    @Test
    public void testGuardar(){

        Entidad entidad1= new Entidad("sergio");

        Entidad entidadResult = entityService.guardar(entidad1);

        Assertions.assertTrue(entidadResult.getId() != 0);

    }

    @Test
    public void traerTodos(){
        List<Entidad> entidades= entityService.listarTodos();

        Assertions.assertTrue(!entidades.isEmpty());

    }

    @Test
    public void testGuardarMemoria(){
        Entidad entidad1= new Entidad("sergio");

        Entidad entidadResult = entityServiceMemory.guardar(entidad1);

        Assertions.assertTrue(entidadResult.getId() != 0);
    }

}
