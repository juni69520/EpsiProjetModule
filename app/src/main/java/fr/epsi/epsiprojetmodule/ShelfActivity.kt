package fr.epsi.epsiprojetmodule

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ShelfActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shelfs)
        showBtnBack()
        setHeaderTitle(getString(R.string.txt_home_liste_rayon))

        val shelfs = arrayListOf<Shelf>()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewShelfs)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val shelfAdapter = ShelfAdapter(shelfs)
        recyclerView.adapter = shelfAdapter

        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val mRequestURL ="https://djemam.com/epsi/categories.json"
        val request = Request.Builder()
            .url(mRequestURL)
            .get()
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()
        okHttpClient.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                val data = response.body?.string()

                if(data !=null){
                    val jsOb= JSONObject(data)
                    val jsArray =jsOb.getJSONArray("items")

                    for(i in 0 until jsArray.length()){

                        val jsShelf = jsArray.getJSONObject(i)
                        val name =jsShelf.optString("name","")
                        val url =jsShelf.optString("url","")
                        val id =jsShelf.optInt("id")
                        val shelf = Shelf(id, name, url)
                        shelfs.add(shelf)
                        Log.d("Shelf",shelf.name)
                    }

                    runOnUiThread(Runnable {
                        shelfAdapter.notifyDataSetChanged()
                    })

//                    Handler(Looper.getMainLooper()).post(Runnable {
//                        studentAdapter.notifyDataSetChanged()
//                    })

                    Log.d("WS",data)
                    Log.d("Student","${shelfs.size}")
                }

            }
        })
    }
}