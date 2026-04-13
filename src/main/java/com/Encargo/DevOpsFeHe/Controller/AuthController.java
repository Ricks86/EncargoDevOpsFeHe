package com.Encargo.DevOpsFeHe.Controller;

import com.Encargo.DevOpsFeHe.Model.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final Usuario usuarioRegistrado = new Usuario("admin", "admin123");

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario credenciales) {
        
        if (credenciales.getUsername().equals(usuarioRegistrado.getUsername()) &&
            credenciales.getPassword().equals(usuarioRegistrado.getPassword())) {
            
            return ResponseEntity.ok("Login exitoso. ¡Bienvenido " + credenciales.getUsername() + "!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo o contraseña incorrectos. Intente nuevamente.");
        }
    }
}