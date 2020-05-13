package com.artemissoftware.pokedex.util;

import com.artemissoftware.pokedex.requests.models.PokemonResponse;
import com.artemissoftware.pokedex.ui.pokemon.models.Pokemon;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

@Mapper
public interface ModelMapping {

    static final ModelMapping INSTANCE = Mappers.getMapper( ModelMapping.class );

    @Mapping(source = "number", target = "id")
    //@Mapping(source = "height", target = "especie")
    Pokemon map(PokemonResponse car);


    @Mapping(source = "id", target = "number")
    PokemonResponse map(Pokemon car);
}
