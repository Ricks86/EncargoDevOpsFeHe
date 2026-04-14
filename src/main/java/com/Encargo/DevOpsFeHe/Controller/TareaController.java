package com.Encargo.DevOpsFeHe.Controller;

import com.Encargo.DevOpsFeHe.Model.Tarea;
import com.Encargo.DevOpsFeHe.Service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Tareas")
public class TareaController {
    @Autowired
    TareaService tareaService = new TareaService();

    @GetMapping
    public List<Tarea> listarTareas(){
        return tareaService.obtenerTareas();
    }
    @GetMapping("/{id}")
    public Tarea obtenerTareaPorId(@PathVariable int id){
        return tareaService.buscarTareaPorId(id);
    }

    @PostMapping
    public void crearTarea(@RequestBody Tarea tarea){
        tareaService.anadirTarea(tarea);
    }
    @DeleteMapping("/{id}")
    public void borrarTarea(@PathVariable int id){
        tareaService.eliminarTarea(id);
    }

    @PutMapping("/modificar")
    public void modificarTarea(@RequestBody Tarea tarea){
        tareaService.actualizarTarea(tarea);
    }

    @GetMapping("/filtrar/{mes}")
    public void listarPorMes(@PathVariable int mes) {
        List<Tarea> filtradas = tareaService.filtrarTareasMensuales(mes);
        if (filtradas.isEmpty()) {
            System.out.println("No hay tareas en el mes " + mes);
        } else {
            filtradas.forEach(t -> System.out.println(t.getTitulo() + " - " + t.getFechaCreacion()));
        }
    }
    @PutMapping("/completar/{titulo}")
    public void completarTarea(@PathVariable String titulo) {
        if (tareaService.marcarComoTerminada(titulo)) {
            System.out.println("¡Tarea '" + titulo + "' finalizada con éxito!");
        } else {
            System.out.println("Error: No se encontró la tarea.");
        }
    }
}
