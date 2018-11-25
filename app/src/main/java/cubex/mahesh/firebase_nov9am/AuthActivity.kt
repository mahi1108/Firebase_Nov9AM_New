package cubex.mahesh.firebase_nov9am

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        login.setOnClickListener {
            var auth = FirebaseAuth.getInstance()
            auth.signInWithEmailAndPassword(
                editText.text.toString(),
                editText2.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(
                        this@AuthActivity,
                        "Auth Success", Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        this@AuthActivity,
                        "Auth Fail", Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        register.setOnClickListener {
                var auth = FirebaseAuth.getInstance()
                auth.createUserWithEmailAndPassword(
                    editText.text.toString(),
                    editText2.text.toString()).
                    addOnCompleteListener {
                        if(it.isSuccessful){
                            Toast.makeText(this@AuthActivity,
                                "Auth Success",Toast.LENGTH_LONG).
                                    show()
                        }else{
                            Toast.makeText(this@AuthActivity,
                                "Auth Fail",Toast.LENGTH_LONG).
                                show()
                        }
                    }
        }

    } //onCreate( )
}
