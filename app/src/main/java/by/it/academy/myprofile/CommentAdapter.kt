package by.it.academy.myprofile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CommentAdapter(val comment: List<Comment>) :
    RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommentAdapter.CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentAdapter.CommentViewHolder, position: Int) {
        holder.bind(comment[position])
    }

    override fun getItemCount(): Int {
        return comment.size
    }

    class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val commentImage: ImageView = itemView.findViewById(R.id.comment_image)
        private val tvName: TextView = itemView.findViewById(R.id.name)
        private val tvComment: TextView = itemView.findViewById(R.id.comment)

        fun bind(comment: Comment) {
            commentImage.setImageResource(comment.image)
            tvName.text = comment.name
            tvComment.text = comment.comment
        }
    }
}