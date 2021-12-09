package fr.epsi.epsiprojetmodule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.json.JSONObject

class StudentsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students)
        showBtnBack()
        intent.getStringExtra("Title")?.let { setHeaderTitle(it) }
        intent.getStringExtra("Name")?.let { setName(it) }
        intent.getStringExtra("Email")?.let { setEmail(it) }
        intent.getStringExtra("Groupe")?.let { setGroupe(it) }
        intent.getStringExtra("Link")?.let { setLink(it) }
        val imageViewDetail = findViewById<ImageView>(R.id.imageViewDetail)
        val urlImage= intent.getStringExtra("urlImage")
        Picasso.get().load(urlImage).into(imageViewDetail)
    }
}