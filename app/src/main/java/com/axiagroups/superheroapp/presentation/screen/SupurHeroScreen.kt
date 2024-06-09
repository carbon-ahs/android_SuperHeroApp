package com.axiagroups.superheroapp.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.axiagroups.superheroapp.data.repository.HeroesRepository
import com.axiagroups.superheroapp.presentation.component.SuperheroCard


/**
 * Created by Ahsan Habib on 6/9/2024.
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SuperHeroScreen(modifier: Modifier = Modifier) {
    val visibleState = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }
    AnimatedVisibility(
        visible = visibleState.targetState,
        enter = fadeIn(animationSpec = spring(dampingRatio = DampingRatioLowBouncy)),
        exit = fadeOut(),
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier
        ) {
            itemsIndexed(HeroesRepository.heroes) { index, hero ->
                SuperheroCard(
                    hero = hero,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        .animateEnterExit(enter = slideInVertically(
                                animationSpec = spring(
                                    stiffness = StiffnessVeryLow,
                                    dampingRatio = DampingRatioLowBouncy
                                ),
                            initialOffsetY = { it * (index + 1) } // staggered entrance
                            )
                        )
                )
            }
        }
    }
}
