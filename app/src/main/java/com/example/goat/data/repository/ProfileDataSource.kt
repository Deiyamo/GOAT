package com.example.goat.data.repository

import android.util.Log
import com.example.goat.domain.model.User
import com.example.goat.domain.repository.AuthenticationRepository
import com.example.goat.domain.repository.ProfileRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

class ProfileDataSource @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val authenticationRepository: AuthenticationRepository
) :
    ProfileRepository {

    override suspend fun modifyUser(user: User): User? {
        TODO("Not yet implemented")
    }

    override suspend fun getUserFirestore(): User {
        val docRef =
            firestore.collection("users").document(authenticationRepository.getCurrentUser()!!.id)
        val document = docRef.get().await()

        if (document != null && document.exists()) {
            val email = document.getString("email")
            val pseudo = document.getString("pseudo")
            val photo = document.getString("photo")
            val firstname = document.getString("firstname")
            val lastname = document.getString("lastname")

            return User(
                authenticationRepository.getCurrentUser()!!.id,
                email ?: "",
                pseudo ?: "",
                photo ?: "",
                firstname ?: "",
                lastname ?: ""
            )
        } else {
            Timber.tag("Empty").d("No such document")
        }
        return User(authenticationRepository.getCurrentUser()!!.id, "", "", "", "", "")
    }

}