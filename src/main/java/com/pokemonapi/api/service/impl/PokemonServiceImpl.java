package com.pokemonapi.api.service.impl;

import com.pokemonapi.api.exeptions.PokemonNotFoundExeption;
import com.pokemonapi.api.models.Pokemon;
import com.pokemonapi.api.repository.PokemonRepository;
import com.pokemonapi.api.service.PokemonService;
import dto.PokemonDto;
import dto.PokemonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {
    private PokemonRepository pokemonRepository;

    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public PokemonDto createPokemon(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());

        Pokemon newPokemon = pokemonRepository.save(pokemon);

        PokemonDto pokemonResponse = new PokemonDto();
        pokemonResponse.setId(newPokemon.getId());
        pokemonResponse.setName(newPokemon.getName());
        pokemonResponse.setType(newPokemon.getType());

        return pokemonResponse;
    }

    @Override
    public PokemonResponse getAllPokemon(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Pokemon> pokemon = pokemonRepository.findAll(pageable);
        List<Pokemon> pokemonList = pokemon.getContent();
        //map because it returns a list
        List<PokemonDto> content = pokemonList.stream().map(
                pok -> mapToDto(pok)).collect(Collectors.toList()
        );
        PokemonResponse pokemonResponse = new PokemonResponse();
        pokemonResponse.setContent(content);
        pokemonResponse.setPageNo(pokemon.getNumber());
        pokemonResponse.setPageSize(pokemon.getSize());
        pokemonResponse.setTotalElements(pokemon.getTotalElements());
        pokemonResponse.setTotalPages(pokemon.getTotalPages());
        pokemonResponse.setLast(pokemon.isLast());

        return pokemonResponse;
    }

    @Override
    public List<PokemonDto> getPokemonById(int id) {
        Pokemon pokemon = pokemonRepository.findById((long) id).orElseThrow(() -> new PokemonNotFoundExeption("Pokemon cloud not be found"));
        return Collections.singletonList(mapToDto(pokemon));
    }

    @Override
    public PokemonDto updatePokemon(PokemonDto pokemonDto, int id) {
        Pokemon pokemon = pokemonRepository.findById((long) id).orElseThrow(() -> new PokemonNotFoundExeption("Pokemon cloud not be updated"));

        pokemon.setName(pokemon.getName());
        pokemon.setType(pokemonDto.getType());

        Pokemon updatePokemon = pokemonRepository.save(pokemon);
        return mapToDto(updatePokemon);
    }

    @Override
    public void deletePokemonId(int id) {
        Pokemon pokemon = pokemonRepository.findById((long) id).orElseThrow(
                () -> new PokemonNotFoundExeption("Pokemon could not be deleted")
        );
        pokemonRepository.delete(pokemon);
    }

    private PokemonDto mapToDto(Pokemon pokemon) {
        PokemonDto pokemonDto = new PokemonDto();
        pokemonDto.setId(pokemon.getId());
        pokemonDto.setName(pokemon.getName());
        pokemonDto.setType(pokemon.getType());

        return pokemonDto;
    }

    private Pokemon mapToEntity(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());

        return pokemon;
    }
}
