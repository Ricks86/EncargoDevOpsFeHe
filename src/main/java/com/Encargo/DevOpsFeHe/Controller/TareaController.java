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
}
