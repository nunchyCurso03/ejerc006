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

import es.santander.ascender.ejerc006.model.Mesa;
 

@SpringBootTest
public class MesaRepositoryTest {


    @Autowired
    private MesaRepository mesaRepository;

    private Mesa mesa;

    @BeforeEach
    public void setUp() {

        mesa = new Mesa(null,"mesa 1-1", 4, 2, 1L);
        mesaRepository.save(mesa);
    }

    @AfterEach
    public void tearDown() {
        mesaRepository.deleteAll();
    }


  @Test
    public void testFindById(){

        //mesa = mesaRepository.findById(1L).get();
        Optional<Mesa> optionalMesa = mesaRepository.findById(mesa.getId());
        assertTrue(optionalMesa.isPresent());
        Mesa mesa = optionalMesa.get();

        assertEquals("mesa 1-1", mesa.getNombre());        
        assertNotNull(mesa.getId(), "El ID no debe ser nulo");
        assertNotNull(mesa.getNombre(), "El nombre no debe ser nulo");
        assertEquals(4, mesa.getCapacidadMaximaSillas(), "La capacidad máxima de sillas debe ser 4");
        assertEquals(2, mesa.getNumeroSillas(), "El número de sillas debe ser 2");
        assertEquals(1L, mesa.getAulaId(), "La mesa debe estar en el aula 1");
    }

      @Test
    public void testListarMesas() {
        Mesa mesa2 = new Mesa(null, "Mesa 2-2", 10,5,2L);
        mesaRepository.save(mesa2);
        // Listar
        List<Mesa> listaMesas = mesaRepository.findAll();

        // Comprueba que no es nulo  
        assertNotNull(listaMesas);

        // Comprueba que hay dos registros
        assertEquals(2, listaMesas.size());

        // Comprueba que la lista es dos
        assertTrue(listaMesas.size() <= 2);
    }

       @Test
    public void testActualizarMesa() {
        // Actualizamos la mesa
        mesa.setNombre("Mesa 1-1 Actualizada");
        mesaRepository.save(mesa);
        

        // Verificar que la mesa se actualizó correctamente
        Mesa actualizado = mesaRepository.findById(mesa.getId()).orElse(null);
        assertNotNull(actualizado);
        assertEquals("Mesa 1-1 Actualizada", actualizado.getNombre());
    }

      @Test
    public void testEliminarMesa() {
        // Eliminamos una mesa
        mesaRepository.deleteById(mesa.getId());

        // Verificar que se ha eliminado la mesa
        Optional<Mesa> eliminado = mesaRepository.findById(mesa.getId());
        assertFalse(eliminado.isPresent());
    }




    @Test
    void testCountByAulaId() {
        // Guardamos una segunda mesa en el aula 1
    Mesa otraMesa = new Mesa(null, "Mesa 1-2", 4, 2, 1L);
    mesaRepository.save(otraMesa);

    // Contamos cuántas mesas hay en el aula 1
    long count = mesaRepository.countByAulaId(1L);

    // Debe haber dos mesas en el aula 1
    assertEquals(2, count, "El número de mesas en el aula 1 debería ser 2");

    }

    @Test
    void testFindByAulaId() {
        // Guardamos una segunda mesa en el aula 1
    Mesa otraMesa = new Mesa(null, "Mesa 1-2", 4, 2, 1L);
    mesaRepository.save(otraMesa);

    // Buscamos todas las mesas del aula 1
    List<Mesa> mesasAula1 = mesaRepository.findByAulaId(1L);

    // Verificamos que la lista no sea nula y que contenga 2 mesas
    assertNotNull(mesasAula1);
    assertEquals(2, mesasAula1.size(), "Debería haber 2 mesas en el aula 1");

    // Verificamos que los nombres coinciden
    assertTrue(mesa.getNombre().equals("mesa 1-1"));
    assertTrue(otraMesa.getNombre().equals("Mesa 1-2"));
    }
}
