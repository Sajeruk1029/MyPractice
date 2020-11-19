package com.sajeruk.perfectmovie.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(val title : String, val urlToImage : String, val voteVerage : String, val releaseDate : String, val overview : String) : Parcelable {
}