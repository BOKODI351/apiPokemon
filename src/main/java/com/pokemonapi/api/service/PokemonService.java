package com.pokemonapi.api.service;

import com.pokemonapi.api.models.Pokemon;
import dto.PokemonDto;
import dto.PokemonResponse;

import javax.sql.PooledConnection;
import java.util.List;

public interface PokemonService {
    PokemonDto createPokemon(PokemonDto pokemonDto);
    PokemonResponse getAllPokemon(int pageNo, int pageSize);
    List<PokemonDto> getPokemonById(int id);
    PokemonDto updatePokemon(PokemonDto pokemonDto, int id);
    void deletePokemonId(int id);
}
