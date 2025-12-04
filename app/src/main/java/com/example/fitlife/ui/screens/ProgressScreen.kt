package com.example.fitlife.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun ProgressScreen(navController: NavController) {
    // Datos de ejemplo para la UI
    val weeklyProgress = 0.75f // 75% de la meta semanal
    val workoutStreak = remember {
        setOf(
            LocalDate.now().minusDays(1),
            LocalDate.now().minusDays(2),
            LocalDate.now().minusDays(4),
            LocalDate.now().minusDays(8),
            LocalDate.now().minusDays(9),
            LocalDate.now().minusDays(10),
        )
    }
    val currentMonth = YearMonth.now()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Tu Progreso",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        WeeklyProgressIndicator(progress = weeklyProgress)

        Spacer(modifier = Modifier.height(32.dp))

        WorkoutStreakCalendar(
            currentMonth = currentMonth,
            workoutDays = workoutStreak
        )
    }
}

@Composable
fun WeeklyProgressIndicator(progress: Float) {
    val animatedProgress by animateFloatAsState(targetValue = progress, label = "progressAnimation")

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            "Meta Semanal",
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(contentAlignment = Alignment.Center, modifier = Modifier.size(150.dp)) {
            CircularProgressIndicator(
                progress = 1f,
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.1f),
                strokeWidth = 12.dp,
            )
            CircularProgressIndicator(
                progress = animatedProgress,
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.primary,
                strokeWidth = 12.dp,
                strokeCap = StrokeCap.Round
            )
            Text(
                text = "${(animatedProgress * 100).toInt()}%",
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun WorkoutStreakCalendar(
    currentMonth: YearMonth,
    workoutDays: Set<LocalDate>
) {
    val daysInMonth = currentMonth.lengthOfMonth()
    val firstDayOfMonth = currentMonth.atDay(1)
    val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value // Lunes=1, Domingo=7
    val days = (1..daysInMonth).map { currentMonth.atDay(it) }
    val today = LocalDate.now()
    // Usamos "es" para español. "L" para Lunes, "M" para Martes, etc.
    val daysOfWeek = listOf("L", "M", "X", "J", "V", "S", "D")

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = currentMonth.month.getDisplayName(TextStyle.FULL, Locale("es", "ES")).replaceFirstChar { it.uppercase() } + " ${currentMonth.year}",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            // Encabezados de los días de la semana
            Row(modifier = Modifier.fillMaxWidth()) {
                 daysOfWeek.forEach {
                    Text(text=it, textAlign = TextAlign.Center, modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
                 }
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
                userScrollEnabled = false
            ) {
                // Espacios en blanco para alinear el primer día del mes
                items(count = firstDayOfWeek - 1) { Box {} }

                items(days) { day ->
                    val isWorkoutDay = day in workoutDays
                    val isToday = day == today
                    DayCell(
                        day = day.dayOfMonth.toString(),
                        isWorkoutDay = isWorkoutDay,
                        isToday = isToday
                    )
                }
            }
        }
    }
}

@Composable
fun DayCell(day: String, isWorkoutDay: Boolean, isToday: Boolean) {
    val backgroundColor = when {
        isWorkoutDay -> MaterialTheme.colors.primary
        isToday -> MaterialTheme.colors.primary.copy(alpha = 0.3f)
        else -> Color.Transparent
    }
    val textColor = when {
        isWorkoutDay -> MaterialTheme.colors.onPrimary
        else -> MaterialTheme.colors.onSurface
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(4.dp)
            .aspectRatio(1f)
            .clip(CircleShape)
            .background(backgroundColor)
    ) {
        Text(text = day, color = textColor, fontSize = 14.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressScreenPreview() {
    ProgressScreen(navController = NavController(LocalContext.current))
}
