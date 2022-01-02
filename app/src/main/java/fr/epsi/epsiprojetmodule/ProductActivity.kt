package fr.epsi.epsiprojetmodule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ProductActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        showBtnBack()
        intent.getStringExtra("name")?.let { setHeaderTitle(it) }

        val imageViewProduct = findViewById<ImageView>(R.id.imageViewProduct)
        val textViewProductDescription = findViewById<TextView>(R.id.textViewProductDescription)

        intent.getStringExtra("description")?.let {
            textViewProductDescription.text = it
        }
        intent.getStringExtra("picture_url")?.let {
            Picasso.get().load(it).into(imageViewProduct)
        }
    }
}