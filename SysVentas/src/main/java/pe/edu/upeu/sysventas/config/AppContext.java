package pe.edu.upeu.sysventas.config;

import pe.edu.upeu.sysventas.controller.*;
import pe.edu.upeu.sysventas.repository.*;
import pe.edu.upeu.sysventas.service.*;
import pe.edu.upeu.sysventas.service.impl.*;

import java.util.HashMap;
import java.util.Map;

public class AppContext {

    // Singleton: una sola instancia en toda la app
    private static AppContext instance;

    public static synchronized AppContext getInstance() {
        if (instance == null) instance = new AppContext();
        return instance;
    }

    // El "directorio": Clase -> Objeto
    private final Map<Class<?>, Object> contenedor = new HashMap<>();

    // Constructor privado: aquí se arma toda la aplicación
    private AppContext() {
        registrarRepositorios();
        registrarServicios();
        registrarControladores();
    }

    // CAPA 1 – REPOSITORIOS
    private void registrarRepositorios() {
        registrar(CategoriaRepository.class, new CategoriaRepository());
        registrar(MarcaRepository.class, new MarcaRepository());
        registrar(UnidadMedidaRepository.class, new UnidadMedidaRepository());
        registrar(ProductoRepository.class, new ProductoRepository());
        registrar(ClienteRepository.class, new ClienteRepository());
    }

    // CAPA 2 – SERVICIOS
    private void registrarServicios() {
        registrar(ICategoriaService.class, new CategoriaServiceImp(getBean(CategoriaRepository.class)));
        registrar(IMarcaService.class, new MarcaServiceImp(getBean(MarcaRepository.class)));
        registrar(IProductoService.class, new ProductoServiceImp(getBean(ProductoRepository.class)));
        registrar(IUnidadMedidaService.class, new UnidadMedidaServiceImp(getBean(UnidadMedidaRepository.class)));
        registrar(IClienteService.class, new ClienteServiceImpl(getBean(ClienteRepository.class)));
    }

    // CAPA 3 – CONTROLADORES JavaFX
    private void registrarControladores() {
        registrar(ProductoController.class,
                new ProductoController(
                        getBean(IMarcaService.class),
                        getBean(ICategoriaService.class),
                        getBean(IProductoService.class),
                        getBean(IUnidadMedidaService.class)));

        registrar(ClienteController.class, new ClienteController(getBean(IClienteService.class)));
    }

    // API del contenedor
    private void registrar(Class<?> tipo, Object bean) {
        contenedor.put(tipo, bean);
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> tipo) {
        Object bean = contenedor.get(tipo);
        if (bean == null) {
            bean = contenedor.values().stream()
                    .filter(b -> tipo.isAssignableFrom(b.getClass()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(
                            "Bean no encontrado: " + tipo.getName() +
                                    "\n-> ¿Lo registraste en AppContext?"));
        }
        return (T) bean;
    }
}