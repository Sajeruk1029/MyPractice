package com.sajeruk.perfectmovie.AboutActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sajeruk.perfectmovie.Models.Film
import com.sajeruk.perfectmovie.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val item : Film? = intent.getParcelableExtra<Film>("OBJECT")

        Picasso.get().load(item?.urlToImage).fit().placeholder(R.color.black).error(R.color.black).into(ImageFilm)

        TitleFilm.text = "Title: " + item?.title

        DescriptionFilm.text = "Description: " + item?.overview

        ReleaseDateFilm.text = "Release Date: " + item?.releaseDate

        RatingFilm.text = "Rating: " + item?.voteVerage
    }
}