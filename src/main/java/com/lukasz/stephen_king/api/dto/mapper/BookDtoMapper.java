package com.lukasz.stephen_king.api.dto.mapper;

import com.lukasz.stephen_king.api.dto.BookDto;
import com.lukasz.stephen_king.domain.BookDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookDtoMapper {

    BookDto mapToDto(BookDomain bookDomain);
}
