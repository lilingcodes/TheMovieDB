package com.lilingxu.themoviedb.data.network.firebase

import com.google.firebase.firestore.CollectionReference
import com.lilingxu.themoviedb.data.networkResult.Resource
import com.lilingxu.themoviedb.data.networkResult.logExceptionError
import com.lilingxu.themoviedb.domain.model.Account
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountDAOImpl @Inject constructor(
    private val accountsCollection: CollectionReference
) : AccountDAO {

    override suspend fun createNewAccount(sessionId: String, newAccount: Account): Resource<Boolean> {
       return withContext(Dispatchers.IO) {
            try {
               accountsCollection
                    .document(sessionId)
                    .set(newAccount)
                    .await()

                return@withContext Resource.Success(true)
            } catch (e: IOException) {
                logExceptionError(e)
            }
        }
    }
}