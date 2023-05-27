package repository.implementacion;

import model.Odontologo;
import org.apache.log4j.Logger;
import repository.IDAO;

import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOMemory implements IDAO<Odontologo> {

    final static Logger log = Logger.getLogger(OdontologoDAOMemory.class);

    private List<Odontologo> odontologos = new ArrayList<>();

    public List<Odontologo> getOdontologos() {
        return this.odontologos;
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {

        log.info("Guardando un nuevo odontologo en memoria...");
        odontologo.setId(this.odontologos.size()+1);
        this.odontologos.add(odontologo);
        log.info("Se guardo el odontologo con id: "+odontologo.getId()+" correctamente");
        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        log.info("Listando todos los odontologos en memoria...");
        log.info("Se listaron todos los odontologos correctamente");
        return this.odontologos;
    }

}
