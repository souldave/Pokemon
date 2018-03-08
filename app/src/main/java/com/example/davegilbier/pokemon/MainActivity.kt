package com.example.davegilbier.pokemon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.net.URL
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    private val url = "https://pokeapi.co/api/v2/pokemon/"

    private val listPokemon = ArrayList<Pokemon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      //  genderBtn.setOnClickListener {
        //    progressBar.visibility= View.VISIBLE
        //    genderLbl.visibility= View.GONE
        //    val name = nameEditText.text.toString()
        //doAsync {
        //  val resultJson = URL(url+name).readText()
        //      uiThread {
        //         val jsonObj = JSONObject(resultJson) // converted to class instance
        //         val gender = jsonObj.getString("gender")
        //         genderLbl.text =gender
// another way esp many data are needed
        //         val genderize: Genderize= Gson().fromJson(resultJson, Genderize::class.java)
        //         val gender = genderize.gender
        //         genderLbl.text =gender

        val pokemonName = "name"
        val pokemonSprite= "sprites"
        val pokemonMSprite = "front_default"

        recyclerVieww.layoutManager = LinearLayoutManager(this)
        for (i in 1..20) {
            doAsync {
                val result = URL(url + i).readText()
                uiThread {
                    val jsonObject = JSONObject(result)
                    val pokemonName = jsonObject.getString(pokemonName)
                    val pokemonSprite = jsonObject.getString(pokemonSprite)
                    val pokemonMSprite = JSONObject(pokemonSprite).getString(pokemonMSprite)

                    listPokemon.add(Pokemon(pokemonName, Sprite(pokemonMSprite)))
                    recyclerVieww.adapter = PokemonAdapter(listPokemon)
                    txtViewUpdate.text = "You now have " +listPokemon.size.toString() + " Pokemons!"
                    if (listPokemon.size == 20) {
                        progressBar.visibility = View.GONE
                    }

                }
            }

        }
    }

}
