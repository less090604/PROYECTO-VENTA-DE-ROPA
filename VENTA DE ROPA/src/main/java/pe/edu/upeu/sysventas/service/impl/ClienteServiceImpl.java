package pe.edu.upeu.sysventas.service.impl;

import lombok.RequiredArgsConstructor;
import pe.edu.upeu.sysventas.model.Cliente;
import pe.edu.upeu.sysventas.repository.ClienteRepository;
import pe.edu.upeu.sysventas.repository.ICrudGenericoRepository;
import pe.edu.upeu.sysventas.service.IClienteService;
import pe.edu.upeu.sysventas.service.impl.CrudGenericoServiceImpl;

import java.util.List;

@RequiredArgsConstructor
public class ClienteServiceImpl extends CrudGenericoServiceImpl<Cliente, Long> implements IClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    protected ICrudGenericoRepository<Cliente, Long> getRepo() {
        return clienteRepository;
    }

    @Override
    public List<Cliente> findAll() {
        if (clienteRepository.findAll().isEmpty()) {
            clienteRepository.seedData();
        }
        return clienteRepository.findAll();
    }
    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente update(Long id, Cliente cliente) {
        cliente.setIdCliente(id);
        return clienteRepository.save(cliente);
    }
}
