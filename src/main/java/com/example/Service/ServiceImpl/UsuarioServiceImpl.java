package com.example.Service.ServiceImpl;

import com.example.Dto.DtoRegistration.UsuarioRegistrationDTO;
import com.example.Dto.DtoRequest.LoginRequestDTO;
import com.example.Dto.UsuarioResponseDTO;
import com.example.Entity.Usuario;
import com.example.Exception.ResourceNotFoundException;
import com.example.Repository.UsuarioRepository;
import com.example.Service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UsuarioResponseDTO registrarUsuario(UsuarioRegistrationDTO dto) {
        if (usuarioRepository.existsByCorreo(dto.getCorreo())) {
            throw new RuntimeException("El correo electrónico ya se encuentra registrado");
        }

        Usuario usuario = Usuario.builder()
                .nombre(dto.getNombre())
                .correo(dto.getCorreo())
                .password(passwordEncoder.encode(dto.getPassword()))
                .telefono(dto.getTelefono())
                .rol(dto.getRol())
                .fotoPerfil(dto.getFotoPerfil())
                .estado(dto.getEstado() != null ? dto.getEstado() : "ACTIVO")
                .build();

        Usuario guardado = usuarioRepository.save(usuario);
        return mapToResponseDTO(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponseDTO autenticarUsuario(LoginRequestDTO dto) {
        Usuario usuario = usuarioRepository.findByCorreo(dto.getCorreo())
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        if (!passwordEncoder.matches(dto.getPassword(), usuario.getPassword())){
            throw new RuntimeException("Credenciales inválidas");
        }

        return mapToResponseDTO(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponseDTO obtenerPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
        return mapToResponseDTO(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> obtenerTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private UsuarioResponseDTO mapToResponseDTO(Usuario u) {
        return UsuarioResponseDTO.builder()
                .idUsuario(u.getIdUsuario())
                .nombre(u.getNombre())
                .correo(u.getCorreo())
                .telefono(u.getTelefono())
                .rol(u.getRol())
                .fotoPerfil(u.getFotoPerfil())
                .estado(u.getEstado())
                .fechaRegistro(u.getFechaRegistro())
                .build();
    }
}