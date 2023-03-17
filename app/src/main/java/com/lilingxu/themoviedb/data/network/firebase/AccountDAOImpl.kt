package com.lilingxu.themoviedb.data.network.firebase

import android.util.Log
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
    private val accountsCollection: CollectionReference,
) : AccountDAO {

    override suspend fun saveAccount(newAccount: Account, onExist: suspend (String) -> Unit): Resource<Boolean> {
        return withContext(Dispatchers.IO) {
            try {
                val querySnapshot = accountsCollection
                    .document(newAccount.username)
                    .get()
                    .await()

                if (querySnapshot.exists()) {
                    val oldSessionId = querySnapshot.getString("sessionId")
                    Log.e("PRUEBAS1", "ya existe en DB borro antiguo session: $oldSessionId")
                    val dataToUpdate = HashMap<String, Any>()
                    if (!newAccount.sessionId.isNullOrEmpty())
                        dataToUpdate["sessionId"] = newAccount.sessionId!!
                    if (!newAccount.avatar_path.isNullOrEmpty())
                        dataToUpdate["avatar_path"] = newAccount.avatar_path!!
                    querySnapshot.reference.update(dataToUpdate)
                    onExist(oldSessionId.toString())
                } else {
                    accountsCollection
                        .document(newAccount.username)
                        .set(newAccount)
                        .await()
                }
                return@withContext Resource.Success(true)
            } catch (e: IOException) {
                logExceptionError(e)
            }
        }
    }

    override suspend fun getSessionIdByUsername(username: String): Resource<String> {
        return withContext(Dispatchers.IO) {
            try {
                val querySnapshot = accountsCollection
                    .document(username)
                    .get()
                    .await()

                val account = querySnapshot.toObject(Account::class.java)

                if (account != null) {
                    return@withContext Resource.Success(account.sessionId)
                }
                return@withContext Resource.Error("")
            } catch (e: IOException) {
                logExceptionError(e)
            }
        }
    }
}