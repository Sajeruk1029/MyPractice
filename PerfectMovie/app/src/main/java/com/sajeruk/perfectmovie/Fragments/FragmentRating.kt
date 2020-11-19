package com.sajeruk.perfectmovie.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sajeruk.perfectmovie.AboutActivity.AboutActivity
import com.sajeruk.perfectmovie.BuildConfig
import com.sajeruk.perfectmovie.MainActivity
import com.sajeruk.perfectmovie.Models.Film
import com.sajeruk.perfectmovie.Movie.Movie
import com.sajeruk.perfectmovie.R
import com.sajeruk.perfectmovie.RecyclerAdapter.Adapter
import kotlinx.android.synthetic.main.fragment_rating.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentRating.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentRating : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var rating: Movie

    private var items: MutableList<Film> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        rating = Movie(BuildConfig.APITop, false)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rating, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentRating.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentRating().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        updateRecycler()

        settingRecycler()
    }

    fun updateRecycler() {
        clearRecycler()
        rating.refreshRating()
        updateItems()
    }

    fun settingRecycler() {
        RecyclerRating.layoutManager = LinearLayoutManager(this.requireContext())

        RecyclerRating.setHasFixedSize(true)

        RecyclerRating.adapter = Adapter(/*this.requireContext(),*/ items, true){

            val intent : Intent = Intent(this.requireContext(), AboutActivity::class.java)
            intent.putExtra("OBJECT", it)
            startActivity(intent)
        }
    }

    fun updateItems() {
        for (count in 1..rating.getCount()) {
            items.add(Film(rating.getTitle(count - 1), "https://image.tmdb.org/t/p/w200/" + rating.getUrlToImage(count - 1), rating.getRating(count - 1), rating.getReleaseDate(count - 1), rating.getDescription(count -1)))
        }
    }

    fun clearRecycler() {
        RecyclerRating.recycledViewPool.clear()

        items.clear()
    }
}