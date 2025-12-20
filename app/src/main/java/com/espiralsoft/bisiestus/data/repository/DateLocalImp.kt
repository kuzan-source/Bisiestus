package com.espiralsoft.bisiestus.data.repository

import com.espiralsoft.bisiestus.data.local.DateLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class DateLocalImp (
    private val dateLocal: DateLocal = DateLocal()
)
{
    suspend fun getCurrentTime(): LocalDateTime {
        return withContext(Dispatchers.IO) {
            dateLocal.getDateTimeNow()
        }
    }
}
