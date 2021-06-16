package by.it.academy.myprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PostFragment(private val viewModel: ProfileModel) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var numPressed = 0
        val rView = view.findViewById<RecyclerView>(R.id.comment_rv)
        rView.layoutManager = LinearLayoutManager(requireContext())
        rView.adapter = CommentAdapter(comments)
        val selectedImage = view.findViewById<ImageView>(R.id.selected_image)
        val like = view.findViewById<ImageView>(R.id.iv_likes_icon)
        val tvNumOfLikes = view.findViewById<TextView>(R.id.tv_num_of_likes)
        val tvNotes = view.findViewById<ImageView>(R.id.iv_flag)
        var numOfLikes: Int = 0
        viewModel.numOfLikes.observe(viewLifecycleOwner, Observer {
            numOfLikes = it
            tvNumOfLikes.text = numOfLikes.toString()
        })
        like.setOnClickListener {
            numPressed++
            if (numPressed % 2 != 0) {
                like.setImageResource(R.drawable.ic_filled_favorite)
                viewModel.onLikePressed(++numOfLikes)
            } else {
                like.setImageResource(R.drawable.ic_favorite)
                viewModel.onLikePressed(--numOfLikes)
            }
        }
        var notes = 1
        viewModel.notes.observe(viewLifecycleOwner, Observer {
            notes = it
        })
        tvNotes.setOnClickListener {
            if (notes % 2 != 0) {
                tvNotes.setImageResource(R.drawable.ic_turned_in_filled)
                viewModel.onNotesPressed(++notes)
            } else {
                tvNotes.setImageResource(R.drawable.ic_turned_in)
                viewModel.onNotesPressed(++notes)
            }
        }
        viewModel.selectedPost.observe(viewLifecycleOwner, Observer {
            Glide.with(view)
                .load(it)
                .centerCrop()
                .into(selectedImage)
        })
    }

    companion object {
        val comments: List<Comment> = listOf(
            Comment("Adriana Lima", "You are awesome!!!", R.drawable.adriana_lima),
            Comment("Peter Parker", "Beautiful", R.drawable.peter_parker),
            Comment("Aiden Catrice", "So cool!", R.drawable.aiden_catrice)
        )
    }
}