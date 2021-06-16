package by.it.academy.myprofile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PostAdapter(private val images: MutableList<Int>, val clickListener: PostClickListener) :
    RecyclerView.Adapter<PostAdapter.MyViewHolder>() {

    override fun getItemCount() = images.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(images[position])
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView? = null

        init {
            imageView = itemView.findViewById(R.id.posted_image)
        }

        fun bind(image: Int) {
            imageView?.apply {
                Glide.with(this)
                    .load(image)
                    .into(this)

                setOnClickListener {
                    clickListener.onPostClick(image)
                }
            }
        }
    }
}