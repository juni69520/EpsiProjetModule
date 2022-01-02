package fr.epsi.epsiprojetmodule

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        val shelfAdapter = ShelfAdapter(shelfs,this)
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
                Log.d("Error", e.toString())
            }


            override fun onResponse(call: Call, response: Response) {
                val data = response.body!!.string()
                val jsOb= JSONObject(data)
                val jsArray =jsOb.getJSONArray("items")

                for(i in 0 until jsArray.length()){
                    val jsShelf = jsArray.getJSONObject(i)
                    val name =jsShelf.optString("title","")
                    val url =jsShelf.optString("products_url","")
                    val id =jsShelf.optString("category_id", "")
                    val shelf = Shelf(id, name, url)
                        shelfs.add(shelf)
                    }

                    runOnUiThread{
                        shelfAdapter.notifyDataSetChanged()
                    }
            }
        })
    }
}