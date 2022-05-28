package com.diego.findeciclo.mapper;

import java.util.List;

import com.diego.findeciclo.dto.PeliculaDTO;
import com.diego.findeciclo.dto.PeliculaPedidoDTO;
import com.diego.findeciclo.model.Pelicula;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PeliculaMapper {
    
    PeliculaMapper INSTANCE = Mappers.getMapper( PeliculaMapper.class );

    List<PeliculaDTO> toListPeliculaDTO(List<Pelicula> peliculas);
    List<Pelicula> toListPelicula(List<PeliculaPedidoDTO> peliculasPedidoDTO);

}
