package epos_next.app.android.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import epos_next.app.android.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilledDropDownInput(
    options: List<String>,
    onSelect: (String) -> Unit,
    placeholder: String,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(TextFieldValue("")) }
    val chevronAnimation by animateFloatAsState(if (expanded) 0f else -90f)

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        FilledInput(
            value = selectedOptionText,
            onValueChange = { },
            readOnly = true,
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.chevron_icon),
                    contentDescription = if (expanded) "open" else "closed",
                    modifier = Modifier.size(18.dp).rotate(chevronAnimation)
                )
            },
            placeholder = placeholder,
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        selectedOptionText = TextFieldValue(selectionOption)
                        expanded = false
                        onSelect(selectionOption)
                    }
                ) {
                    Text(text = selectionOption)
                }
            }
        }
    }
}

@Preview
@Composable
private fun FilledDropDownInputPreview() {
    val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")

    FilledDropDownInput(
        options = options,
        onSelect = {},
        placeholder = "Выберите урок"
    )
}