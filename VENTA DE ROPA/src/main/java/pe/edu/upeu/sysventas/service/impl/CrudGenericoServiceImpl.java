package pe.edu.upeu.sysventas.service.impl;

import pe.edu.upeu.sysventas.repository.ICrudGenericoRepository;
import java.util.List;
import java.util.Optional;

public abstract class CrudGenericoServiceImpl<T, ID> {

    protected abstract ICrudGenericoRepository<T, ID> getRepo();

    public T save(T entity) {
        return getRepo().save(entity);
    }

    public List<T> findAll() {
        return getRepo().findAll();
    }

    public Optional<T> findById(ID id) {
        return getRepo().findById(id);
    }

    public void deleteById(ID id) {

        getRepo().deleteById(id);
    }
    public T update(ID id, T entity) {
        return getRepo().save(entity);
    }
}