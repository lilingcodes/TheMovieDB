package com.lilingxu.themoviedb.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle

@Composable
fun MovieStarRating(vote_average: Double, vote_count: Int) {
    val rating = vote_average / 2

    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = vote_average.toString())

        RatingBar(
            value = rating.toFloat(),
            config = RatingBarConfig()
                .style(RatingBarStyle.Normal)
                .inactiveColor(Color.LightGray)
                .padding(1.dp)
                .size(20.dp),
            onValueChange = { },
            onRatingChanged = { }
        )
        Text(text = "($vote_count)")
    }
}