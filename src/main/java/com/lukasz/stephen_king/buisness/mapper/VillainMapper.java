package com.lukasz.stephen_king.buisness.mapper;

import com.lukasz.stephen_king.domain.VillainDomain;
import com.lukasz.stephen_king.infrastructure.book.Villain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VillainMapper {
    VillainDomain mapToDomain(Villain villain);
}
