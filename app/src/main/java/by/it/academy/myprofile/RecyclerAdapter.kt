package by.it.academy.myprofile

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerAdapter(private val images: MutableList<Int>) :
        RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    override fun getItemCount() = images.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.imageView?.apply {
            Glide.with(context)
                .load(images[position])
                .into(this)
            setOnClickListener {
                val intent = Intent(context, PostFragment::class.java)
                intent.putExtra("image", images[position])
                context?.startActivity(intent)
            }
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView? = null

        init {
            imageView = itemView.findViewById(R.id.posted_image)
        }
    }
}