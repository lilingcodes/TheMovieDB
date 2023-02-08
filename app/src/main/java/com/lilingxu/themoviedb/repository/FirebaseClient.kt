package com.lilingxu.themoviedb.repository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class FirebaseClient @Inject constructor(){
    val auth: FirebaseAuth get() =  Firebase.auth
    val db = Firebase.firestore
}