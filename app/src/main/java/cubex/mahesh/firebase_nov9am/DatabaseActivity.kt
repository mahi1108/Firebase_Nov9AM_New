package cubex.mahesh.firebase_nov9am

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_database.*

class DatabaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)
        submit.setOnClickListener {
            var dBase = FirebaseDatabase.getInstance()
            var dRef = dBase.getReference("users")
            var uid = FirebaseAuth.getInstance().uid
            var uid_ref = dRef.child(uid.toString())
            uid_ref.child("name").
                        setValue(name.text.toString())
            uid_ref.child("gender").
                setValue(gender.text.toString())
            uid_ref.child("mno").
                setValue(mno.text.toString())
            uid_ref.child("address").
                setValue(address.text.toString())
        }
    }
}
