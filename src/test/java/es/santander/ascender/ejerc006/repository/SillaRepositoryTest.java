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

import es.santander.ascender.ejerc006.model.Silla;

@SpringBootTest
public class SillaRepositoryTest {

    @Autowired
    private SillaRepository sillaRepository;

    private Silla silla;

    @BeforeEach
    public void setUp() {

        silla = new Silla(null, "silla 1-1", true, 1L);
        sillaRepository.save(silla);
    }

    @AfterEach
    public void tearDown() {
        sillaRepository.deleteAll();
    }

    @Test
    public void testFindById() {

       // Silla silla = sillaRepository.findById(1L).get();
        Optional<Silla> optionalSilla = sillaRepository.findById(silla.getId());
        assertTrue(optionalSilla.isPresent());
        Silla silla = optionalSilla.get();

        assertEquals("silla 1-1", silla.getNombre());        
        assertNotNull(silla.getId(), "El ID no debe ser nulo");
        assertNotNull(silla.getNombre(), "El nombre no debe ser nulo");
        assertEquals(true, silla.isDisponible(), "La silla debe estar ocupada");
        assertEquals(1L, silla.getMesaId(), "La silla debe estar en la mesa 1");
    }

    @Test
    public void testListarSillas() {
        Silla silla2 = new Silla(null, "Mesa 2-2", false, 2L);
        sillaRepository.save(silla2);
        // Listar
        List<Silla> listaSillas = sillaRepository.findAll();

        // Comprueba que no es nulo
        assertNotNull(listaSillas);

        // Comprueba que hay dos registros
        assertEquals(2, listaSillas.size());

        // Comprueba que la lista es dos
        assertTrue(listaSillas.size() <= 2);
    }

    @Test
    public void testActualizarSilla() {
        // Actualizamos la silla
        silla.setNombre("silla 1-1 Actualizada");
        sillaRepository.save(silla);

        // Verificar que la silla se actualizó correctamente
        Silla actualizado = sillaRepository.findById(silla.getId()).orElse(null);
        assertNotNull(actualizado);
        assertEquals("silla 1-1 Actualizada", actualizado.getNombre());
    }

    @Test
    public void testEliminarSilla() {
        // Eliminamos una silla
        sillaRepository.deleteById(silla.getId());

        // Verificar que se ha eliminado la silla
        Optional<Silla> eliminado = sillaRepository.findById(silla.getId());
        assertFalse(eliminado.isPresent());
    }

    @Test
    void testFindByMesaId() {
        // Guardamos una segunda silla en la mesa 1
        Silla otraSilla = new Silla(null, "silla 1-2", false, 1L);
        sillaRepository.save(otraSilla);

        // Buscamos todas las sillas de la mesa 1
        List<Silla> sillasMesa1 = sillaRepository.findByMesaId(1L);

        // Verificamos que la lista no sea nula y que contenga 2 sillas
        assertNotNull(sillasMesa1);
        assertEquals(2, sillasMesa1.size(), "Debería haber 2 mesas en el aula 1");

        // Verificamos que los nombres coinciden
        assertTrue(silla.getNombre().equals("silla 1-1"));
        assertTrue(otraSilla.getNombre().equals("silla 1-2"));
    }

}
