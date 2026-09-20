package com.example.Controller;

import com.example.Dto.DtoRegistration.UsuarioRegistrationDTO;
import com.example.Dto.DtoRequest.AuthResponseDTO;
import com.example.Dto.DtoRequest.LoginRequestDTO;
import com.example.Dto.UsuarioResponseDTO;
import com.example.Security.JwtUtils;
import com.example.Service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final JwtUtils jwtUtils;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> registrarUsuario(@Valid @RequestBody UsuarioRegistrationDTO dto) {
        UsuarioResponseDTO creado = usuarioService.registrarUsuario(dto);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerPorId(id));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> autenticarUsuario(@Valid @RequestBody LoginRequestDTO dto) {
        //atentica al usuario en databse
        UsuarioResponseDTO usuario = usuarioService.autenticarUsuario(dto);

        //genera el token
        String token = jwtUtils.generarToken(usuario.getCorreo(), usuario.getRol());

        //devuelve el token y los datos del usuario
        return ResponseEntity.ok(new AuthResponseDTO(token, usuario));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }
}
