package repository.implementacion;

import model.Entidad;
import repository.IDAO;

import java.util.ArrayList;
import java.util.List;

public class EntityDAOMemory implements IDAO<Entidad> {

    private List<Entidad> entidades = new ArrayList<Entidad>();

    public List<Entidad> getEntidades() {
        return entidades;
    }

    @Override
    public Entidad guardar(Entidad entidad) {

        entidad.setId(this.entidades.size()+1);
        this.entidades.add(entidad);

        return entidad;
    }

    @Override
    public List<Entidad> listarTodos() {
        return this.entidades;
    }

    @Override
    public Entidad obtener(int id) {
        Entidad entidadEncontrada = null;
        for(Entidad e: this.entidades){
            if(e.getId() == id){
                entidadEncontrada = e;
            }
        }
        return entidadEncontrada;
    }
}
