package com.lukasz.stephen_king.buisness.mapper;

import com.lukasz.stephen_king.domain.CastMemberDomain;
import com.lukasz.stephen_king.infrastructure.movie.CastMember;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CastMemberMapper {

    CastMemberDomain mapToDomain(CastMember castMember);
}