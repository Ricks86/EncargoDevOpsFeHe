package com.Encargo.DevOpsFeHe.Service;

import com.Encargo.DevOpsFeHe.Model.Tarea;
import com.Encargo.DevOpsFeHe.Repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaService {
    @Autowired
    TareaRepository tareaRepository = new TareaRepository();

    public List<Tarea> obtenerTareas(){
        return tareaRepository.getTareas();
    }

    public Tarea buscarTareaPorId(int id){
        return tareaRepository.findTareaPorId(id);
    }

    public void anadirTarea(Tarea tarea){
        tareaRepository.addTarea(tarea);
    }

    public void eliminarTarea(int id){
        tareaRepository.deleteTarea(id);
    }

    public void actualizarTarea(Tarea tarea){
        tareaRepository.updateTarea(tarea);
    }

    public List<Tarea> filtrarTareasMensuales(int mes) {

        return tareaRepository.buscarPorMes(mes);
    }

    public boolean marcarComoTerminada(String titulo) {
        Optional<Tarea> tareaOpt = tareaRepository.buscarPorTitulo(titulo);

        if (tareaOpt.isPresent()) {
            Tarea tarea = tareaOpt.get();
            tarea.setCompletada(true);
            return true;
        }
        return false;
    }
}


