package com.pokemonapi.api.exeptions;

public class PokemonNotFoundExeption extends RuntimeException {
    private static final long serialVerionUID = 1;

    public PokemonNotFoundExeption(String message) {
        super(message);
    }
}
