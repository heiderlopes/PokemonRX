package com.example.logonpflocal.pokemonrx

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.logonpflocal.pokemonrx.api.PokemonAPI
import com.example.logonpflocal.pokemonrx.api.PokemonService
import com.example.logonpflocal.pokemonrx.model.Pokemon
import kotlinx.android.synthetic.main.activity_main.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    lateinit var adapter: PokemonAdapter

    val pokemons = ArrayList<Pokemon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = PokemonService()

        adapter = PokemonAdapter(this, pokemons, {})
        rvPokemons.adapter = adapter
        //rvPokemons.layoutManager = LinearLayoutManager(this)
        rvPokemons.layoutManager = GridLayoutManager(this, 3)

        api.loadPokemons()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {pokemons.add(it)},
                        {e -> e.printStackTrace()},
                        {adapter.notifyDataSetChanged()
                        }
                )
    }
}
