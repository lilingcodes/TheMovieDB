package com.lilingxu.themoviedb.ui.view.shimmer

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.lilingxu.themoviedb.ui.components.MySpacer
import com.lilingxu.themoviedb.ui.theme.ShimmerColor
import com.lilingxu.themoviedb.utils.IMAGE_HEIGHT_MEDIUM
import com.lilingxu.themoviedb.utils.IMAGE_WIDTH_MEDIUM


@Composable
fun HomeShimmer() {
    val shimmerColor = listOf(
        ShimmerColor.copy(alpha = 0.6f),
        ShimmerColor.copy(alpha = 0.4f),
        ShimmerColor.copy(alpha = 0.2f),
        ShimmerColor.copy(alpha = 0.4f),
        ShimmerColor.copy(alpha = 0.6f),
    )

    val transition = rememberInfiniteTransition()
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2000,
                easing = FastOutSlowInEasing
            )
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColor,
        start = Offset.Zero,
        end = Offset(x = translateAnimation.value, y = translateAnimation.value)
    )

    ShimmerItem(brush = brush)
}

@Composable
fun ShimmerItem(brush: Brush) {
    Column{
        repeat(4) {
            MySpacer()
            HomeSectionShimmerItem(brush = brush)
        }
    }
}


@Composable
fun HomeSectionShimmerItem(brush: Brush) {
    //Title Shimmer
    Spacer(
        modifier = Modifier
            .padding(start = 16.dp)
            .height(27.dp)
            .width(150.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(brush),
    )
    Spacer(Modifier.height(8.dp))
    //Images Shimmer
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        repeat(7) {
            Spacer(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .height(IMAGE_HEIGHT_MEDIUM)
                    .width(IMAGE_WIDTH_MEDIUM)
                    .clip(MaterialTheme.shapes.medium)
                    .background(brush),
            )
        }
    }
}