package com.lilingxu.themoviedb.ui.view.shimmer

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.lilingxu.themoviedb.ui.components.MySpacer
import com.lilingxu.themoviedb.ui.theme.Shapes
import com.lilingxu.themoviedb.ui.theme.ShimmerColor
import com.lilingxu.themoviedb.ui.view.MyDivider
import com.lilingxu.themoviedb.utils.IMAGE_HEIGHT_SMALL
import com.lilingxu.themoviedb.utils.IMAGE_WIDTH_SMALL


@Composable
fun MovieDetailsShimmer() {
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

    MovieDetailsShimmerItem(brush = brush)
}


@Composable
fun MovieDetailsShimmerItem(brush: Brush) {
    Column() {
        MovieDetailsHeaderShimmerItem(brush)
        MySpacer()

        //Overview
        MovieOverviewShimmer(brush)

        //Cast
        MovieCastShimmer(brush)
    }
}

@Composable
fun MovieCastShimmer(brush: Brush) {
    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .height(20.dp)
                .width(150.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(brush),
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            repeat(4) {
                Spacer(
                    modifier = Modifier
                        .height(IMAGE_HEIGHT_SMALL)
                        .width(IMAGE_WIDTH_SMALL)
                        .clip(
                            MaterialTheme.shapes.medium.copy(
                                bottomStart = CornerSize(0.dp),
                                bottomEnd = CornerSize(0.dp)
                            )
                        )
                        .background(brush),
                )
            }
        }
    }
}

@Composable
fun MovieOverviewShimmer(brush: Brush) {
    Column(Modifier.padding(horizontal = 16.dp)) {

        Spacer(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .height(20.dp)
                .width(150.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(brush),
        )
        Spacer(
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .background(brush),
        )
        MySpacer()
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            repeat(4) {
                Spacer(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(Shapes.large)
                        .background(brush),
                )
            }
        }
    }
    MyDivider()
}

@Composable
fun MovieDetailsHeaderShimmerItem(brush: Brush, modifier: Modifier = Modifier) {
    Row(modifier.padding(16.dp), Arrangement.spacedBy(8.dp)) {
        //Image header
        Spacer(
            modifier = Modifier
                .height(IMAGE_HEIGHT_SMALL)
                .width(IMAGE_WIDTH_SMALL)
                .clip(MaterialTheme.shapes.medium)
                .background(brush),
        )
        //Movie general data
        Column(modifier = Modifier.padding(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Spacer(
                modifier = Modifier
                    .height(27.dp)
                    .width(300.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(brush),
            )
            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .width(145.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(brush),
            )
            Spacer(
                modifier = Modifier
                    .height(15.dp)
                    .width(140.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(brush),
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                repeat(3) {
                    Spacer(
                        modifier = Modifier
                            .height(40.dp)
                            .width(100.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .background(brush),
                    )
                }
            }
        }
    }
}
