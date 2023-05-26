package services;

import model.Entidad;
import repository.IDAO;

import java.util.List;

public class EntityService {

    private IDAO<Entidad> entidadesDAO;

    public EntityService(IDAO<Entidad> entidadesDAO) {
        this.entidadesDAO = entidadesDAO;
    }

    public IDAO<Entidad> getEntidadesDAO() {
        return entidadesDAO;
    }

    public void setEntidadesDAO(IDAO<Entidad> entidadesDAO) {
        this.entidadesDAO = entidadesDAO;
    }

    public Entidad guardar(Entidad entidad){
        return this.entidadesDAO.guardar(entidad);
    }

    public List<Entidad> listarTodos(){
        return this.entidadesDAO.listarTodos();
    }

    public Entidad obtener(int id){
        return this.entidadesDAO.obtener(id);
    }
}
