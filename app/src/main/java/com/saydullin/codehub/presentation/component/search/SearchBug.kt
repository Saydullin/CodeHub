package com.saydullin.codehub.presentation.component.search

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.saydullin.codehub.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleSearch(
    onActiveCallback: (isActiveValue: Boolean) -> Unit
) {

    val query = remember { mutableStateOf("") }
    val isActive = remember { mutableStateOf(false) }
    val sideMargins = animateDpAsState(
        targetValue =
            if (isActive.value) {
                0.dp
            } else {
                16.dp
            }
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = sideMargins.value),
        contentAlignment = Alignment.Center
    ) {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth(),
            query = query.value,
            onQueryChange = {
                query.value = it
            },
            onSearch = {},
            active = isActive.value,
            onActiveChange = {
                isActive.value = it
                onActiveCallback(it)
                if (!it) {
                    query.value = ""
                }
            },
            placeholder = {
               Text(
                   modifier = Modifier
                       .alpha(.5f),
                   text = "How to fix my bug ...",
                   style = MaterialTheme.typography.bodyLarge,
               )
            },
            leadingIcon = {
                if (isActive.value) {
                    Icon(
                        modifier = Modifier
                            .clickable {
                                query.value = ""
                                isActive.value = false
                                onActiveCallback(false)
                            },
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                } else {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = stringResource(R.string.search_cd),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            },
            trailingIcon = {
                if (query.value.trim().isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Outlined.Clear,
                        contentDescription = stringResource(R.string.clear_cd),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier
                            .clickable {
                                query.value = ""
                            }
                    )
                }
            },
        ) {
        }
    }

}


