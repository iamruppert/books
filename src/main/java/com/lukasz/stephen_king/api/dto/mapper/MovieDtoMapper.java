package com.lukasz.stephen_king.api.dto.mapper;

import com.lukasz.stephen_king.api.dto.MovieDto;
import com.lukasz.stephen_king.domain.MovieDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieDtoMapper {

    MovieDto mapToDto(MovieDomain movieDomain);

}
