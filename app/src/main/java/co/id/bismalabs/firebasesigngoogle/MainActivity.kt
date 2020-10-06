package co.id.bismalabs.firebasesigngoogle

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.transform.RoundedCornersTransformation
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        user?.let {
            name.text = it.displayName
            email.text = it.email

            imgProfile.load(it.photoUrl) {
                crossfade(true)
                crossfade(1000)
                transformations(RoundedCornersTransformation(10f))

            }
        }


        btnLogout.setOnClickListener {
            mAuth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}