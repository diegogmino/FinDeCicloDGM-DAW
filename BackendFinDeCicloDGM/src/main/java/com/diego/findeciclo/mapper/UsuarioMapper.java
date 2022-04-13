package com.diego.findeciclo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.diego.findeciclo.dto.UsuarioDTO;
import com.diego.findeciclo.model.Usuario;

@Mapper
public interface UsuarioMapper {

    UsuarioDTO toUsuarioDTO(Usuario usuario);
    List<UsuarioDTO> toListUsuarioDTO(List<Usuario> usuarios);
	
}
