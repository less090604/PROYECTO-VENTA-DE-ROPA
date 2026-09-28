package pe.edu.upeu.sysventas.repository;

import pe.edu.upeu.sysventas.enums.TipoDocumento;
import pe.edu.upeu.sysventas.model.Cliente;

public class ClienteRepository extends AbstractJpaRepository<Cliente, Long> {

    private long sequence = 1;

    @Override
    protected Long getId(Cliente entity) {
        return entity.getIdCliente();
    }

    @Override
    protected void setId(Cliente entity, Long id) {
        entity.setIdCliente(id);
    }

    @Override
    protected Long generateId() {
        return sequence++;
    }

    public void seedData() {
        if (findAll().isEmpty()) {
            save(Cliente.builder()
                    .idCliente(generateId())
                    .dniruc("20123456789")
                    .nombres("Empresa Ropa S.A.C.")
                    .repLegal("Juan Pérez")
                    .tipoDocumento(TipoDocumento.RUC)
                    .build());
        }
    }
}