package com.example.logonpflocal.pokemonrx.api

import com.example.logonpflocal.pokemonrx.model.Pokemon
import com.example.logonpflocal.pokemonrx.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface PokemonAPI {

    @GET("/api/v2/pokemon")
    fun listaPokemons(): Observable<PokemonResponse>

    @GET("/api/v2/pokemon/{nomePokemon}")
    fun buscaPor(@Path("nomePokemon") nomePokemon: String)
            : Observable<Pokemon>


}