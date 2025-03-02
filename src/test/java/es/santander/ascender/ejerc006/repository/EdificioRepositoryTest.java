package es.santander.ascender.ejerc006.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.santander.ascender.ejerc006.model.Edificio;
import es.santander.ascender.ejerc006.model.enums.TipoEdificio;

@SpringBootTest
public class EdificioRepositoryTest {

    @Autowired
    private EdificioRepository edificioRepository;

    private Edificio edificio;

    @BeforeEach
    public void setUp() {
        edificio = new Edificio(null, "Principal", "Plaza Principal, s/n", 3, 300, TipoEdificio.INTERFACULTATIVO);
        edificioRepository.save(edificio);
    }

     @AfterEach
    public void tearDown() {
        edificioRepository.deleteAll();
    }

    @Test
    public void testFindById() {
        //edificio = edificioRepository.findById(1L).get();
         Optional<Edificio> optionalEdificio = edificioRepository.findById(edificio.getId());
        assertTrue(optionalEdificio.isPresent());
        Edificio edificio = optionalEdificio.get();


        // Verifica que el nombre sea 'Principal'
        assertEquals("Principal", edificio.getNombre());       
        // Verifica que el edificio no tenga valores nulos  
        assertNotNull(edificio.getId(), "El ID no debe ser nulo");
        assertNotNull(edificio.getNombre(), "El nombre no debe ser nulo");

        // Verificamos que el edificio tenga valores válidos en otros campos   
        assertNotNull(edificio.getDireccion(), "La dirección no debe ser nula");

        assertTrue(edificio.getNumPlantas() > 0, "El número de plantas debe ser mayor que 0");
    }

     
    @Test
    public void testListarEdificios() {
        // Listar
        List<Edificio> listaEdificios = edificioRepository.findAll();

        // Comprueba que no es nulo y que el documento se guardó en la lista
        assertNotNull(listaEdificios);

        // Comprueba que hay un registro
        assertEquals(1, listaEdificios.size());

        // Comprueba que la lista es mayor que uno
        assertTrue(listaEdificios.size() <= 1);
    }
    @Test
    public void testActualizarEdificio() {
        // Actualizar el edificio
        edificio.setNombre("Principal Actualizado");
        edificioRepository.save(edificio);

        // Verificar que el edificio se actualizó correctamente
        Edificio actualizado = edificioRepository.findById(edificio.getId()).orElse(null);
        assertNotNull(actualizado);
        assertEquals("Principal Actualizado", actualizado.getNombre());
    }
    
       @Test
    public void testEliminarEdificio() {
        // Eliminar el edificio
        edificioRepository.deleteById(edificio.getId());

        // Verificar que el edificio fue eliminado
        Optional<Edificio> eliminado = edificioRepository.findById(edificio.getId());
        assertFalse(eliminado.isPresent());
    }
}
