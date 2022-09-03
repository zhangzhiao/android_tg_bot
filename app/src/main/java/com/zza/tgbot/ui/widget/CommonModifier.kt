package com.zza.tgbot.ui.widget

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp
import androidx.constraintlayout.compose.ConstrainScope
import androidx.constraintlayout.compose.ConstraintLayoutScope
import kotlin.random.Random

fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = this.then(
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        val firstBaseline = placeable[FirstBaseline]
        val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
        val height = placeable.height + placeableY
        layout(placeable.width, height) {
            placeable.placeRelative(0, placeableY)
        }
    }
)

/**
 * 通过宽高和坐标算中心点坐标
 */
fun Offset.centerCoordinates(width: Float, height: Float): Offset {
    return Offset((x + width / 2), (y + height / 2))
}

/**
 * 随机一个透明度
 */
fun randomAlpha(minimumValue: Float = 0.3f, maximumValue: Float = 0.9f): Float {
    return Random.nextFloat().coerceIn(minimumValue, maximumValue)
}

/**
 * 随机一个颜色
 */
fun randomColor(): Float {
    return Random.nextFloat().coerceIn(0f, 255f)
}

fun ConstrainScope.linkBase() {
    this.top.linkTo(parent.top)
    this.bottom.linkTo(parent.bottom)
    this.start.linkTo(parent.start)
    this.end.linkTo(parent.end)
}

fun ConstrainScope.linkBottom() {
    this.bottom.linkTo(parent.bottom)
    this.start.linkTo(parent.start)
    this.end.linkTo(parent.end)
}

fun ConstrainScope.linkTop() {
    this.top.linkTo(parent.top)
    this.start.linkTo(parent.start)
    this.end.linkTo(parent.end)
}