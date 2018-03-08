package com.example.davegilbier.pokemon

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pokemon_list_layout.view.*


/**
 * Created by Dave Gilbier on 09/03/2018.
 */
class PokemonAdapter(val pokemon: ArrayList<Pokemon>) : RecyclerView.Adapter<CustomViewHolder>(){

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
        holder?.view?.txtViewName?.text = pokemon[position].name
        val image = holder?.view?.imgView
        Picasso.with(holder?.view?.context).load(pokemon[position].sprite.pokeString).into(image)
        holder?.view?.listPokemons?.setOnClickListener { println(pokemon[position].name) }
    }

    override fun getItemCount(): Int {
        return pokemon.size
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder? {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.pokemon_list_layout, parent, false)
        return CustomViewHolder(v)
    }

}
class CustomViewHolder (var view: View): RecyclerView.ViewHolder(view)

