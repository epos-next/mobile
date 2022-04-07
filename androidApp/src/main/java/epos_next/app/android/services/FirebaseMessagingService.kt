package epos_next.app.android.services

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class EposFirebaseMessagingService: FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.d("TOKEN", "FCM token is $token")
    }
}