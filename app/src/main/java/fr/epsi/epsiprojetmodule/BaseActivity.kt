package fr.epsi.epsiprojetmodule

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    fun showBtnBack(){
        val imageViewBack=findViewById<ImageView>(R.id.imageViewBack)
        imageViewBack.visibility=View.VISIBLE
        imageViewBack.setOnClickListener(View.OnClickListener {
            finish()
        })
    }

    fun setHeaderTitle(txt : String){
        val textViewTitle = findViewById<TextView>(R.id.textViewTitle)
        textViewTitle.text = txt
    }

    fun setName(txt : String){
        val textViewTitle = findViewById<TextView>(R.id.textViewName)
        textViewTitle.text = txt
    }

    fun setEmail(txt : String){
        val textViewTitle = findViewById<TextView>(R.id.textViewEmail)
        textViewTitle.text = txt
    }

    fun setGroupe(txt : String){
        val textViewTitle = findViewById<TextView>(R.id.textViewGroup)
        textViewTitle.text = txt
    }

    fun setLink(txt : String){
        val textViewTitle = findViewById<TextView>(R.id.textViewLink)
        textViewTitle.text = txt
    }
}