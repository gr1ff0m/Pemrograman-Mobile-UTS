package com.edumate.ui.pomodoro

import android.os.CountDownTimer
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.edumate.R

@Composable
fun PomodoroScreen(navController: NavController) {
    var isTimerRunning by remember { mutableStateOf(false) }
    var timeRemaining by remember { mutableStateOf(1500) } // 25 menit dalam detik
    var timer: CountDownTimer? by remember { mutableStateOf(null) }

    val minutes = timeRemaining / 60
    val seconds = timeRemaining % 60

    fun startStopTimer() {
        if (isTimerRunning) {
            timer?.cancel()
        } else {
            timer = object : CountDownTimer(timeRemaining.toLong() * 1000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    timeRemaining = (millisUntilFinished / 1000).toInt()
                }

                override fun onFinish() {
                    timeRemaining = 0
                    isTimerRunning = false
                }
            }
            timer?.start()
        }
        isTimerRunning = !isTimerRunning
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.pomodoro_timer_title),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "${minutes}m ${seconds}s",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { startStopTimer() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = if (isTimerRunning)
                    stringResource(id = R.string.stop_timer)
                else
                    stringResource(id = R.string.start_timer)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.back_to_home))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PomodoroScreenPreview() {
    PomodoroScreen(navController = rememberNavController())
}
