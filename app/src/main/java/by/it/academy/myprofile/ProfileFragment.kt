package by.it.academy.myprofile

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.URLSpan
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView


class ProfileFragment(private val model: ProfileModel) : Fragment(), PostClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val bottomNavView = view.findViewById<BottomNavigationView>(R.id.bottomNavView)
        bottomNavView.background = null
        bottomNavView.menu.getItem(2).isEnabled = false
        val rView = view.findViewById<RecyclerView>(R.id.rv_images)
        rView.layoutManager = GridLayoutManager(context, 2)
        rView.adapter = PostAdapter(imageList, this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val numOfFollowers = view.findViewById<TextView>(R.id.followers_number)

        model.numOfFollowers.observe(viewLifecycleOwner, Observer {
            numOfFollowers.text = it.toString()
        })
        view.findViewById<ImageView>(R.id.avatar).setOnClickListener {
            onPostClick(R.drawable.avatar)
        }
        view.findViewById<Button>(R.id.button_follow).setOnClickListener {
            model.addFollower()
        }

        val profileLink = view.findViewById<TextView>(R.id.profile_link)
        profileLink.text = "linkedin.com/in/annazenevich"
        Linkify.addLinks(profileLink, Linkify.ALL)
        stripUnderlines(profileLink)
    }

    private fun stripUnderlines(textView: TextView) {
        val s: Spannable = SpannableString(textView.text)
        val spans = s.getSpans(0, s.length, URLSpan::class.java)
        var span: URLSpan
        for (sp in spans) {
            val start = s.getSpanStart(sp)
            val end = s.getSpanEnd(sp)
            s.removeSpan(sp)
            span = URLSpanNoUnderline(sp.url)
            s.setSpan(span, start, end, 0)
        }
        textView.text = s
    }

    private class URLSpanNoUnderline(url: String?) : URLSpan(url) {
        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.isUnderlineText = false
        }
    }

    companion object {
        val imageList: MutableList<Int> = mutableListOf(
            R.drawable.avatar, R.drawable.model, R.drawable.image_one,
            R.drawable.image_two, R.drawable.image_three, R.drawable.image_four
        )
    }

    override fun onPostClick(image: Int) {
        model.setSelectedPost(image)
        (requireActivity() as MainInterface).openPostFragment()
    }
}