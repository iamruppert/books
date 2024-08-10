package com.lukasz.stephen_king.buisness.mapper;

import com.lukasz.stephen_king.domain.VillainDomain;
import com.lukasz.stephen_king.infrastructure.stephen_king.Villain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VillainMapper {
    VillainDomain mapToDomain(Villain villain);
}
