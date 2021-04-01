package by.it.academy.myprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProfileFragment : Fragment() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_profile, container, false)
        val rView = view.findViewById<RecyclerView>(R.id.rv_images)
        rView.layoutManager = LinearLayoutManager(context)
        rView.adapter = RecyclerAdapter(imageList)
        return view
    }

    companion object {
        val imageList: MutableList<Int> = mutableListOf(R.drawable.model, R.drawable.image_one,
            R.drawable.image_two, R.drawable.image_three, R.drawable.image_four)
    }
}