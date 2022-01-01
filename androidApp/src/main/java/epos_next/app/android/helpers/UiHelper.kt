package epos_next.app.android.helpers

import androidx.compose.ui.graphics.Color
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.time.Duration

object UiHelper {

    fun formatLessonTime(startDate: LocalDateTime, duration: Duration): String {
        val javaStartDate = startDate.toJavaLocalDateTime()
        val formatter = DateTimeFormatter.ofPattern("hh:mm")
        val startDateFormatted = javaStartDate.format(formatter)
        val endDate = javaStartDate.plusSeconds(duration.inWholeSeconds)
        val endDateFormatted = endDate.format(formatter)
        return "$startDateFormatted — $endDateFormatted"
    }

    fun getLessonColor(lesson: String): LessonColor {
        if (lesson.contains("Физика")) return LessonColor(
            color = Color(0xFFBBEDBF),
            colorAccent = Color(0xFF68D676),
        )
        if (lesson.contains("Геометрия")) return LessonColor(
            color = Color(0xFFFBDDC3),
            colorAccent = Color(0xFFF5A664),
        )
        if (lesson.contains("География")) return LessonColor(
            color = Color(0xFFFEF0C5),
            colorAccent = Color(0xFFFCCF62),
        )
        if (lesson.contains("Английский")) return LessonColor(
            color = Color(0xFFC5CAFE),
            colorAccent = Color(0xFF6D73FD),
        )
        if (lesson.contains("Алгебра")) return LessonColor(
            color = Color(0xFFF8CBC4),
            colorAccent = Color(0xFFF18477),
        )
        if (lesson.contains("Русский")) return LessonColor(
            color = Color(0xFFE9B6FC),
            colorAccent = Color(0xFFD46DF9),
        )
        if (lesson.contains("Химия")) return LessonColor(
            color = Color(0xFFC4EBFD),
            colorAccent = Color(0xFF83D4FC),
        )
        if (lesson.contains("Биология")) return LessonColor(
            color = Color(0xFFc4d0fd),
            colorAccent = Color(0xFF8387fc),
        )
        if (lesson.contains("История")) return LessonColor(
            color = Color(0xFFc4fdce),
            colorAccent = Color(0xFF6bd082),
        )
        if (lesson.contains("Информатика")) return LessonColor(
            color = Color(0xFFc4fdee),
            colorAccent = Color(0xFF74debd),
        )
        if (lesson.contains("Технология")) return LessonColor(
            color = Color(0xFFc4fdee),
            colorAccent = Color(0xFF74debd),
        )
        if (lesson.contains("Литература")) return LessonColor(
            color = Color(0xFFe8c4fd),
            colorAccent = Color(0xFFe483fc),
        )
        if (lesson.contains("Математика")) return LessonColor(
            color = Color(0xFFfddcc4),
            colorAccent = Color(0xFFfcb983),
        )
        if (lesson.contains("Обществознание")) return LessonColor(
            color = Color(0xFFfdc4ca),
            colorAccent = Color(0xFFfc8383),
        )
        if (lesson.contains("Физкультура")) return LessonColor(
            color = Color(0xFFfdc4d0),
            colorAccent = Color(0xFFfc8393),
        )

        return LessonColor(
            color = Color(0xFFcccccc),
            colorAccent = Color(0xFF8f8f8f)
        )
    }
}

data class LessonColor(
    val color: Color,
    val colorAccent: Color
)