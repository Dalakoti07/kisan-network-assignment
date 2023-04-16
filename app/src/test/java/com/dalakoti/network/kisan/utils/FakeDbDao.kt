package com.dalakoti.network.kisan.utils

import com.dalakoti.network.core.data.room.dao.SmsSentDao
import com.dalakoti.network.core.data.room.entities.SmsSentEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDbDao: SmsSentDao {

    companion object{
        var counter = 0
    }

    override fun getAllSentSms(): Flow<List<SmsSentEntity>> {
        return flow{
            emit(emptyList())
        }
    }

    override suspend fun insertIntoDb(sms: SmsSentEntity) {
        println("inserting into DB")
        counter++
    }

}