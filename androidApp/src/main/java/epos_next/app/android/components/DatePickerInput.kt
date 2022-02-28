package epos_next.app.android.components

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.material.datepicker.MaterialDatePicker
import epos_next.app.android.R
import epos_next.app.android.lib.noRippleClickable
import epos_next.app.utils.FormatHelper
import kotlinx.datetime.*

@Composable
fun FilledDatePickerInput(
    placeholder: String,
    onChange: (LocalDateTime) -> Unit,
) {
    var date by remember { mutableStateOf<LocalDateTime?>(null) }
    val activity = LocalContext.current as AppCompatActivity

    Box {
        FilledInput(
            value = TextFieldValue(formatDate(date)),
            onValueChange = {},
            readOnly = true,
            placeholder = placeholder,
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.calendar),
                    contentDescription = "open",
                    modifier = Modifier
                        .width(19.dp)
                        .height(19.dp),
                )
            }
        )

        Box(
            Modifier
                .height(60.dp)
                .fillMaxWidth()
                .noRippleClickable {
                    val picker = MaterialDatePicker.Builder
                        .datePicker()
                        .build()
                    picker.show(activity.supportFragmentManager, picker.toString())
                    picker.addOnPositiveButtonClickListener {
                        val instant = Instant.fromEpochMilliseconds(it)

                        if (instant > Clock.System.now()) {
                            val tz = TimeZone.currentSystemDefault()
                            val newDate = instant.toLocalDateTime(tz)
                            date = newDate
                            onChange(newDate)
                        }
                    }
                }
        )
    }
}

@Composable
fun FilledDatePickerInput(
    placeholder: String,
    name: String,
    onChange: (LocalDateTime) -> Unit,
    value: LocalDateTime? = null,
    onlyFuture: Boolean = false,
    onlyPast: Boolean = false,
) {
    val activity = LocalContext.current as AppCompatActivity

    Column {
        Text(
            text = name,
            fontSize = 14.sp,
            color = MaterialTheme.colors.secondary,
        )

        Spacer(modifier = Modifier.height(7.dp))
        Box {
            FilledInput(
                value = TextFieldValue(if (onlyFuture) formatFutureDate(value) else formatDate(value)),
                onValueChange = {},
                readOnly = true,
                placeholder = placeholder,
                trailingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.calendar),
                        contentDescription = "open",
                        modifier = Modifier
                            .width(19.dp)
                            .height(19.dp),
                    )
                },
            )

            Box(
                Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .noRippleClickable {
                        val picker = MaterialDatePicker.Builder
                            .datePicker()
                            .build()
                        picker.show(activity.supportFragmentManager, picker.toString())
                        picker.addOnPositiveButtonClickListener {
                            val instant = Instant.fromEpochMilliseconds(it)

                            if ((!onlyFuture && !onlyPast) || (onlyFuture && instant > Clock.System.now()) || (onlyPast && instant < Clock.System.now())) {
                                val tz = TimeZone.currentSystemDefault()
                                val newDate = instant.toLocalDateTime(tz)
                                onChange(newDate)
                            }
                        }
                    }
            )
        }
    }
}

private fun formatDate(date: LocalDateTime?): String {
    if (date == null) return ""
    return FormatHelper.formatMarkDate(date)
}

private fun formatFutureDate(date: LocalDateTime?): String {
    if (date == null) return ""
    return FormatHelper.futureDate(date)
}