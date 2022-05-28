package com.moviles.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.moviles.retrofit.remote.PokemonEntry
import com.moviles.retrofit.remote.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retrofit = RetrofitBuilder.create().getPokemonById("27")
      //  val response = retrofit.getPokemonById("27")

        retrofit.enqueue(object: Callback<PokemonEntry>{
            override fun onResponse(call: Call<PokemonEntry>, response: Response<PokemonEntry>) {
                val resBody = response.body()
                if (resBody != null){
                    Log.d("retrofitresponse", "res: ${resBody}")
                    Log.d("retrofitresponse", "name : ${resBody.name}")
                    Log.d("retrofitresponse","front_default : ${resBody.sprite.front_default}" )


                    for (stat in resBody.stats){
                        Log.d("retrofitresponse", "${stat.stat.stat_value}: ${stat.basestats}")

                    }
                    Log.d("retrofitresponse", "type : ${resBody.types[0].type.name}")

                    }

            }

            override fun onFailure(call: Call<PokemonEntry>, t: Throwable) {
              Log.e("retrofitresponse", "error: ${t.message}")
            }
        })
    }
}