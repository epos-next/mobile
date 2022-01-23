package epos_next.app.android.helpers

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.toLowerCase
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.time.Duration

object UiHelper {

    fun formatLessonTime(startDate: LocalDateTime, duration: Duration): String {
        val javaStartDate = startDate.toJavaLocalDateTime()
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        val startDateFormatted = javaStartDate.format(formatter)
        val endDate = javaStartDate.plusSeconds(duration.inWholeSeconds)
        val endDateFormatted = endDate.format(formatter)
        return "$startDateFormatted — $endDateFormatted"
    }

    fun getLessonColor(lesson: String): LessonColor {
        val subject = formatSubjectName(lesson)

        if (subject.contains("Физика")) return LessonColor(
            color = Color(0xFFBBEDBF),
            colorAccent = Color(0xFF68D676),
        )
        if (subject.contains("Геометрия")) return LessonColor(
            color = Color(0xFFFBDDC3),
            colorAccent = Color(0xFFF5A664),
        )
        if (subject.contains("География")) return LessonColor(
            color = Color(0xFFFEF0C5),
            colorAccent = Color(0xFFFCCF62),
        )
        if (subject.contains("Английский")) return LessonColor(
            color = Color(0xFFC5CAFE),
            colorAccent = Color(0xFF6D73FD),
        )
        if (subject.contains("Алгебра")) return LessonColor(
            color = Color(0xFFF8CBC4),
            colorAccent = Color(0xFFF18477),
        )
        if (subject.contains("Русский")) return LessonColor(
            color = Color(0xFFE9B6FC),
            colorAccent = Color(0xFFD46DF9),
        )
        if (subject.contains("Химия")) return LessonColor(
            color = Color(0xFFC4EBFD),
            colorAccent = Color(0xFF83D4FC),
        )
        if (subject.contains("Биология")) return LessonColor(
            color = Color(0xFFc4d0fd),
            colorAccent = Color(0xFF8387fc),
        )
        if (subject.contains("История")) return LessonColor(
            color = Color(0xFFc4fdce),
            colorAccent = Color(0xFF6bd082),
        )
        if (subject.contains("Информатика")) return LessonColor(
            color = Color(0xFFc4fdee),
            colorAccent = Color(0xFF74debd),
        )
        if (subject.contains("Технология")) return LessonColor(
            color = Color(0xFFc4fdee),
            colorAccent = Color(0xFF74debd),
        )
        if (subject.contains("Литература")) return LessonColor(
            color = Color(0xFFe8c4fd),
            colorAccent = Color(0xFFe483fc),
        )
        if (subject.contains("Математика")) return LessonColor(
            color = Color(0xFFfddcc4),
            colorAccent = Color(0xFFfcb983),
        )
        if (subject.contains("Обществознание")) return LessonColor(
            color = Color(0xFFfdc4ca),
            colorAccent = Color(0xFFfc8383),
        )
        if (subject.contains("Физкультура")) return LessonColor(
            color = Color(0xFFfdc4d0),
            colorAccent = Color(0xFFfc8393),
        )

        return LessonColor(
            color = Color(0xFFcccccc),
            colorAccent = Color(0xFF8f8f8f)
        )
    }

    fun formatSubjectName(name: String): String {
        val lowerCase = name.lowercase(Locale.getDefault())
        if (lowerCase.contains("основы безопасности жизнедеятельности")) return "ОБЖ"
        if (lowerCase.contains("изобразительное искусство")) return "ИЗО"
        if (lowerCase.contains("английский язык")) return "Английский язык"
        if (lowerCase.contains("история")) return "История"
        if (lowerCase.contains("физическая культура")) return "Физкультура"
        return name
    }
}

data class LessonColor(
    val color: Color,
    val colorAccent: Color
)