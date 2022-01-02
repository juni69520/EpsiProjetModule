package fr.epsi.epsiprojetmodule

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ProductsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        showBtnBack()
        setHeaderTitle(intent.getStringExtra("title") ?: "Produits")

        val products = arrayListOf<Product>()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewProducts)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val productsAdapter = ProductsAdapter(products,this)
        recyclerView.adapter = productsAdapter

        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        intent.getStringExtra("products_url")?.let{ mRequestURL ->
            val request = Request.Builder()
                .url(mRequestURL).get()
                .cacheControl(CacheControl.FORCE_NETWORK).build()
            okHttpClient.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.d("Error", e.toString())
                }

                override fun onResponse(call: Call, response: Response) {
                    val data = response.body!!.string()
                    val jsOb= JSONObject(data)
                    val jsArray =jsOb.getJSONArray("items")

                    for(i in 0 until jsArray.length()){
                        val jsProducts = jsArray.getJSONObject(i)
                        val name =jsProducts.optString("name","")
                        val description =jsProducts.optString("description","")
                        val picture_url =jsProducts.optString("picture_url", "")
                        val product = Product(name, description, picture_url)
                        products.add(product)
                    }
                        runOnUiThread{
                            productsAdapter.notifyDataSetChanged()
                        }
                    }
            })
        }
    }
}