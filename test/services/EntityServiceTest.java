package services;

import model.Entidad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repository.implementacion.EntityDAOH2;
import repository.implementacion.EntityDAOMemory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntityServiceTest {
    private static EntityService entityService = new EntityService(new EntityDAOH2());

    private static EntityService entityServiceMemory = new EntityService(new EntityDAOMemory());


    @Test
    void guardar() {
        Entidad entidad1= new Entidad("sergio");

        Entidad entidadResult = entityService.guardar(entidad1);

        Assertions.assertTrue(entidadResult.getId() != 0);
    }

    @Test
    void listarTodos() {
        List<Entidad> entidades= entityService.listarTodos();

        Assertions.assertTrue(!entidades.isEmpty());
    }
}