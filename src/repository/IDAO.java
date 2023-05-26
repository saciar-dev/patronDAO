package repository;

import java.util.List;

public interface IDAO<T> {

    public T guardar(T t);

    public List<T> listarTodos();

    public T obtener(int id);
}
