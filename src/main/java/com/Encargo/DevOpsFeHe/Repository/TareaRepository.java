package com.Encargo.DevOpsFeHe.Repository;

import com.Encargo.DevOpsFeHe.Model.Tarea;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TareaRepository {
    List<Tarea> tareas = new ArrayList<>();

    public List<Tarea> getTareas (){
        return tareas;
    }

    public Tarea findTareaPorId(int id){
        for (Tarea tarea: tareas){
            if (tarea.getId() == id){
                return tarea;
            }
        }
        return null;
    }

    public void addTarea(Tarea ta){
        tareas.add(ta);
    }

    public void deleteTarea(int id){
        if(findTareaPorId(id) != null){
            tareas.remove(findTareaPorId(id));
        }
    }

    public void updateTarea(Tarea tareaActualizada){
        int idTarea =0;
        int idPosicion=0;
        for(int i=0;i<tareas.size();i++){
            if(tareas.get(i).getId() == tareaActualizada.getId()){
                idTarea = tareas.get(i).getId();
                idPosicion = i;
                break;
            }
        }
        tareas.set(idPosicion, tareaActualizada);

    }



}
