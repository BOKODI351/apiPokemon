package com.pokemonapi.api.controllers;

import com.pokemonapi.api.models.Pokemon;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class pokemonController {

    @GetMapping("pokemon")
    public ResponseEntity<List<Pokemon>> getPokemons() {
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(new Pokemon(1, "Arbok", "Poison"));
        pokemons.add(new Pokemon(2, "Arboliva", "Grass"));
        pokemons.add(new Pokemon(3, "Pikachu", "Electric"));

        return ResponseEntity.ok(pokemons);
    }
}
