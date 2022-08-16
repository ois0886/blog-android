package com.pocs.presentation.view.component

import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PocsDivider(startIndent: Dp = 0.dp, thickness: Dp = 1.dp) {
    Divider(startIndent = startIndent, modifier = Modifier.alpha(0.2f), thickness = thickness)
}

@Composable
fun ThickDivider() {
    Divider(modifier = Modifier.alpha(0.1f), thickness = 16.dp)
}