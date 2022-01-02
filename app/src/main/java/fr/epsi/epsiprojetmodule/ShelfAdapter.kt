package fr.epsi.epsiprojetmodule

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import android.app.ActivityOptions

class ShelfAdapter (val shelfs: ArrayList<Shelf>,val currentActivity: Activity): RecyclerView.Adapter<ShelfAdapter.ViewHolder>(){

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val shelfTitleButton = view.findViewById<TextView>(R.id.shelfTitleButton)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.cell_shelf, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shelf = shelfs.get(position)
        holder.shelfTitleButton.text= shelf.title
        holder.shelfTitleButton.setOnClickListener {
            val intent = Intent(currentActivity, ProductsActivity::class.java)
            intent.putExtra("products_url",shelf.products_url)
            intent.putExtra("title",shelf.title)
            currentActivity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return shelfs.size
    }
}