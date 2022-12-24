package com.pokemonapi.api.service;

import dto.PokemonDto;

import javax.sql.PooledConnection;
import java.util.List;

public interface PokemonService {
    PokemonDto createPokemon(PokemonDto pokemonDto);
    List<PokemonDto> getAllPokemon();
}
