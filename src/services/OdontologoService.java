package services;

import model.Odontologo;
import repository.IDAO;

import java.util.List;

public class OdontologoService {

    private IDAO<Odontologo> odontologoDAO;

    public OdontologoService(IDAO<Odontologo> odontologoDAO) {
        this.odontologoDAO = odontologoDAO;
    }

    public IDAO<Odontologo> getOdontologoDAO() {
        return odontologoDAO;
    }

    public void setOdontologoDAO(IDAO<Odontologo> odontologoDAO) {
        this.odontologoDAO = odontologoDAO;
    }

    public Odontologo guardar(Odontologo odontologo){
        return this.odontologoDAO.guardar(odontologo);
    }

    public List<Odontologo> listarTodos(){
        return this.odontologoDAO.listarTodos();
    }

}
