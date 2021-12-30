package fr.epsi.epsiprojetmodule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ShelfAdapter (private val shelfs: ArrayList<Shelf>): RecyclerView.Adapter<ShelfAdapter.ViewHolder>(){

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val textViewName = view.findViewById<TextView>(R.id.textViewName)
        val textViewUrl = view.findViewById<TextView>(R.id.textViewUrl)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.cell_shelf, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shelf = shelfs.get(position)
        holder.textViewName.text=shelf.name
        holder.textViewUrl.text=shelf.url
    }

    override fun getItemCount(): Int {
        return shelfs.size
    }
}