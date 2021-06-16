package by.it.academy.myprofile


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileModel : ViewModel() {

    private val _selectedPost = MutableLiveData<Int>()
    val selectedPost: LiveData<Int> = _selectedPost

    private val _numOfFollowers = MutableLiveData<Int>()
    val numOfFollowers: LiveData<Int> = _numOfFollowers

    private val _numOfLikes = MutableLiveData<Int>()
    val numOfLikes: LiveData<Int> = _numOfLikes

    private val _notes = MutableLiveData<Int>()
    val notes: LiveData<Int> = _notes

    init {
        _numOfFollowers.value = 1430
        _numOfLikes.value = 12
    }

    fun setSelectedPost(post: Int) {
        _selectedPost.value = post
    }

    fun addFollower() {
        var num: Int = numOfFollowers.value!!
        num++
        _numOfFollowers.value = num
    }

    fun onLikePressed(numLikes: Int) {
        _numOfLikes.value = numLikes
    }

    fun onNotesPressed(pressedNotes: Int) {
        _notes.value = pressedNotes
    }

}