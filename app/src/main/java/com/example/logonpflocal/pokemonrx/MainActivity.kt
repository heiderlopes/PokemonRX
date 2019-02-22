package com.example.logonpflocal.pokemonrx

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.logonpflocal.pokemonrx.api.PokemonAPI
import com.example.logonpflocal.pokemonrx.api.PokemonService
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = PokemonService()

        api.loadPokemons()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {Log.i("POKEMON", "${it.name} - ${it.sprites.frontDefault}")},
                        {e -> e.printStackTrace()},
                        {Log.i("POKEMON", "Todos pokemons emitidos")}
                )
    }
}
