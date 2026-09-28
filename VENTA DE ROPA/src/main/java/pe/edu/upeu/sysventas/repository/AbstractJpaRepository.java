package pe.edu.upeu.sysventas.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractJpaRepository<T, ID> implements ICrudGenericoRepository<T, ID> {

    protected final List<T> entities = new ArrayList<>();

    protected abstract ID getId(T entity);
    protected abstract void setId(T entity, ID id);
    protected abstract ID generateId();

    public T save(T entity) {
        if (getId(entity) == null) {
            setId(entity, generateId());
            entities.add(entity);
        } else {
            deleteById(getId(entity));
            entities.add(entity);
        }
        return entity;
    }

    public List<T> findAll() {
        return new ArrayList<>(entities);
    }

    public Optional<T> findById(ID id) {
        return entities.stream()
                .filter(e -> getId(e) != null && getId(e).equals(id))
                .findFirst();
    }

    public void deleteById(ID id) {
        entities.removeIf(e -> getId(e) != null && getId(e).equals(id));
    }
}