package com.Encargo.DevOpsFeHe.Repository;

import com.Encargo.DevOpsFeHe.Model.Tarea;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TareaRepositoryTest {

    private TareaRepository tareaRepository;

    @BeforeEach
    void setUp() {
        tareaRepository = new TareaRepository();

        Tarea t1 = new Tarea("Tarea 1", "Descripción 1");
        t1.setId(1);
        tareaRepository.addTarea(t1);

        Tarea t2 = new Tarea("Tarea 2", "Descripción 2");
        t2.setId(2);
        tareaRepository.addTarea(t2);
    }

    @Test
    void testAddAndFindTarea() {
        Tarea nueva = new Tarea("Tarea 3", "Descripción 3");
        nueva.setId(3);
        tareaRepository.addTarea(nueva);

        Tarea encontrada = tareaRepository.findTareaPorId(3);
        assertNotNull(encontrada);
        assertEquals("Tarea 3", encontrada.getTitulo());
    }

    @Test
    void testUpdateTareaExitosa() {
        Tarea actualizada = new Tarea("Tarea 1 Modificada", "Nueva Descripción");
        actualizada.setId(1);

        tareaRepository.updateTarea(actualizada);

        Tarea resultado = tareaRepository.findTareaPorId(1);
        assertEquals("Tarea 1 Modificada", resultado.getTitulo());
    }

    @Test
    void testHotfixUpdateTareaIdNoExiste() {
        Tarea tareaInvalida = new Tarea("Tarea Fantasma", "No existe");
        tareaInvalida.setId(99);

        tareaRepository.updateTarea(tareaInvalida);

        Tarea posicionCero = tareaRepository.findTareaPorId(1);
        assertNotNull(posicionCero);
        assertEquals("Tarea 1", posicionCero.getTitulo());
    }
}