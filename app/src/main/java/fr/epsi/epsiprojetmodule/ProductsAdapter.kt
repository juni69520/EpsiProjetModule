package fr.epsi.epsiprojetmodule

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.app.ActivityOptions
import android.widget.ImageView
import com.squareup.picasso.Picasso


class ProductsAdapter(val products: ArrayList<Product>,val currentActivity: Activity): RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contentLayoutProductCellButton = view.findViewById<LinearLayout>(R.id.contentLayoutProductCellButton)
        val textViewProductCellTitle = view.findViewById<TextView>(R.id.textViewProductCellTitle)
        val textViewProductCellDescription = view.findViewById<TextView>(R.id.textViewProductCellDescription)
        val imageViewProductCell = view.findViewById<ImageView>(R.id.imageViewProductCell)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.cell_product, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val product = products.get(position)
        viewHolder.textViewProductCellTitle.text= product.name
        viewHolder.textViewProductCellDescription.text= product.description
        Picasso.get().load(product.picture_url).into(viewHolder.imageViewProductCell)
        viewHolder.contentLayoutProductCellButton.setOnClickListener {
            val intent = Intent(currentActivity, ProductActivity::class.java)
            intent.putExtra("picture_url",product.picture_url)
            intent.putExtra("name",product.name)
            intent.putExtra("description",product.description)
            currentActivity.startActivity(intent)
        }
    }

    override fun getItemCount() = products.size
}
