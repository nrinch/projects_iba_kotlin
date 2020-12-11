package na.severinchik.iba_kotlin_lesson_12

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import na.severinchik.iba_kotlin_lesson_12.databinding.ActivityHomeBinding


const val TOPIC = "/topic/newTopic"

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firabaseStorage: FirebaseStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        firabaseStorage = Firebase.storage("gs://ibaktln.appspot.com")
        firabaseStorage.reference.child("/bobr.png").downloadUrl.addOnSuccessListener {
            Log.d("TAG", "URI ${it}")
            Picasso.get()
                .load(it)
                .into(binding.imageContainer)
        }

        binding.userid.text = firebaseAuth.currentUser?.uid
        binding.logout.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        AppFirebaseMessagingService.sharedPreferences =
            getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            AppFirebaseMessagingService.token = it.token
            binding.receiverNotification.setText(it.token)
        }
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)


        val db = Firebase.firestore

        db.collection("users").whereEqualTo("uuid", firebaseAuth.currentUser?.uid).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d("TAG", "${document.id} => ${document.data}")

                }
                val users = documents.toObjects(User::class.java)
                if (!users.isEmpty()) {
                    binding.userid.text = users[0].userName
                    binding.userid.text = firebaseAuth.currentUser?.uid

                } else {
                    val user = User(firebaseAuth.currentUser?.uid!!, "New Usever")
                    db.collection("users").add(user.toHashMap())
                }
            }
        binding.editUsername.setOnClickListener {
            val newUserName = binding.editFieldUsername.text

            var users =
                db.collection("users").whereEqualTo("uuid", firebaseAuth.currentUser?.uid).get()
            for (user in users.result?.documents!!) {

            }


            db.collection("users").whereEqualTo("uuid", firebaseAuth.currentUser?.uid).get()
                .addOnSuccessListener { document ->
                    val id = document.documents[0].id
                    var user = document.documents[0].toObject(User::class.java)
                    user!!.userName = newUserName.toString()
                    db.collection("users").document(id).set(user.toHashMap()).addOnSuccessListener {
                        binding.userid.text = newUserName
                    }

                }


        }

        binding.sendNotification.setOnClickListener {
            val title = binding.titleNotification.text.toString()
            val message = binding.messageNotification.text.toString()
            val receiver = binding.receiverNotification.text.toString()
            if (title.isNotEmpty() && message.isNotEmpty() && receiver.isNotEmpty()) {
                PushNotification(
                    NotificationData(title, message),
                    TOPIC
                ).also {
                    sendNotification(it)
                }
            }
        }


    }

    private fun sendNotification(notification: PushNotification) =
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.api.postNotification(notification)
                if (response.isSuccessful) {
                    Log.d("TAG", "sendNotification: ${Gson().toJson(response)}")
                } else {
                    Log.e("TAG", response.errorBody().toString())
                }
            } catch (e: Exception) {

            }
        }
}


class User() {
    var uuid: String = ""
    var userName: String = ""

    constructor(uuid: String, userName: String) : this() {
        this.uuid = uuid
        this.userName = userName
    }

    fun toHashMap(): HashMap<String, Any> {
        return hashMapOf(
            "uuid" to uuid,
            "userName" to userName
        )
    }
}