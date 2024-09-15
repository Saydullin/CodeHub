package com.saydullin.codehub.presentation.component.editor.input.attachment

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun InputAttachmentEditor(
    modifier: Modifier = Modifier,
    isBigContent: Boolean = false,
    textStyle: TextStyle = MaterialTheme.typography.displaySmall,
    onInputEdit: (String) -> Unit,
    icons: List<ImageVector> = listOf(),
    placeholder: String,
    label: String? = null,
    contentLimit: Int = 0,
) {

    val input = remember { mutableStateOf("") }

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (label != null) {
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp),
                    style = MaterialTheme.typography.titleMedium,
                    text = label
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            AnimatedVisibility(
                visible = input.value.length > (contentLimit - 31),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Text(
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    text = "${input.value.length}/$contentLimit"
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.outlineVariant,
                    shape = RoundedCornerShape(16.dp)
                ),
        ) {
            TextField(
                modifier = Modifier
                    .weight(1f)
                    .heightIn(min = if (isBigContent) 200.dp else 0.dp),
                value = input.value,
                onValueChange = {
                    if (contentLimit == 0 || it.length <= contentLimit) {
                        onInputEdit(it)
                        input.value = it
                    }
                },
                placeholder = {
                    Text(
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.outline,
                        text = placeholder,
                    )
                },
                shape = RoundedCornerShape(16.dp),
                textStyle = textStyle,
                label = null,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                ),
            )
            if (icons.isNotEmpty()) {
                Column {
                    icons.forEach {
                        IconButton(
                            onClick = { /*TODO*/ }
                        ) {
                            Icon(
                                imageVector = it,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.outline,
                            )
                        }
                    }
                }
            }
        }
    }
}


