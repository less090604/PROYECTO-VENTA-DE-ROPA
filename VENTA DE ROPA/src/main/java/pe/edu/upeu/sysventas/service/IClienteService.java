package pe.edu.upeu.sysventas.service;

import pe.edu.upeu.sysventas.model.Cliente;
import java.util.List;
import java.util.Optional;

public interface IClienteService {
    Cliente save(Cliente cliente);
    Cliente update(Long id, Cliente cliente);
    List<Cliente> findAll();
    Optional<Cliente> findById(Long id);
    void deleteById(Long id);
}