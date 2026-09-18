package com.example.Service;

import com.example.Dto.DtoRegistration.UsuarioRegistrationDTO;
import com.example.Dto.DtoRequest.LoginRequestDTO;
import com.example.Dto.UsuarioResponseDTO;

import java.util.List;
public interface UsuarioService {
    UsuarioResponseDTO registrarUsuario(UsuarioRegistrationDTO dto);
    UsuarioResponseDTO autenticarUsuario(LoginRequestDTO dto);
    UsuarioResponseDTO obtenerPorId(Long id);
    List<UsuarioResponseDTO> obtenerTodos();
}
