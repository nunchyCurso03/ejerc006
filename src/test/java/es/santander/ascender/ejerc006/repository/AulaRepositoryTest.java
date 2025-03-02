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

import es.santander.ascender.ejerc006.model.Aula;
import es.santander.ascender.ejerc006.model.enums.TipoAula;

@SpringBootTest
public class AulaRepositoryTest {

    @Autowired
    private AulaRepository aulaRepository;

    private Aula aula;

    @BeforeEach
    public void setUp() {

        aula = new Aula(null, "Nunchy", TipoAula.BIBLIOTECA, 30, 1, false, 1L);
        aulaRepository.save(aula);
    }

    @AfterEach
    public void tearDown() {
        aulaRepository.deleteAll();
    }

    @Test
    public void testFindById() {
        // aula = aulaRepository.findById(1L).get();
        Optional<Aula> optionalAula = aulaRepository.findById(aula.getId());
        assertTrue(optionalAula.isPresent());
        Aula aula = optionalAula.get();

        // Verifica que el nombre del aula sea 'Nunchy'
        assertEquals("Nunchy", aula.getNombre());        
        // Verifica que el aula no tenga valores nulos
        assertNotNull(aula.getId(), "El ID no debe ser nulo");
        assertNotNull(aula.getNombre(), "El nombre no debe ser nulo");
        // Verifica que los demás atributos se guardaron correctamente
        assertEquals(TipoAula.BIBLIOTECA, aula.getTipoAula(), "El tipo de aula debe ser BIBLIOTECA");
        assertEquals(30, aula.getCapacidadMaximaMesas(), "La capacidad máxima de mesas debe ser 30");
        assertEquals(1, aula.getNumeroMesas(), "El número de mesas debe ser 1");
        assertEquals(false, aula.isProyector(), "El aula no tiene proyector");
        assertEquals(1L, aula.getEdificioId(), "El aula debe estar en el edificio 1");
    }

    @Test
    public void testListarAulas() {
        // Listar
        List<Aula> listaAulas = aulaRepository.findAll();

        // Comprueba que no es nulo y que el documento se guardó en la lista
        assertNotNull(listaAulas);

        // Comprueba que hay un registro
        assertEquals(1, listaAulas.size());

        // Comprueba que la lista es mayor que uno
        assertTrue(listaAulas.size() <= 1);
    }

    @Test
    public void testActualizarAula() {
        // Actualizamos el nombre del aula
        aula.setNombre("Nunchy Actualizado");
        aulaRepository.save(aula);

        // Verificar que aula se actualizó
        Aula actualizado = aulaRepository.findById(aula.getId()).orElse(null);
        assertNotNull(actualizado);
        assertEquals("Nunchy Actualizado", actualizado.getNombre());
    }

    @Test
    public void testEliminarAula() {
        // Eliminaamos un aula
        aulaRepository.deleteById(aula.getId());

        // Verificar que el aula fue eliminado
        Optional<Aula> eliminado = aulaRepository.findById(aula.getId());
        assertFalse(eliminado.isPresent());
    }

}
