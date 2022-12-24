package com.pokemonapi.api.repository;

import com.pokemonapi.api.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Long>{
}
