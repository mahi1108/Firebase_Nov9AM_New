package cubex.mahesh.firebase_nov9am

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_user_info.*

class UserInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        var uid = FirebaseAuth.getInstance().uid
        var dBase = FirebaseDatabase.getInstance()
        var dRef = dBase.getReference("users/$uid")
        dRef.addValueEventListener(
            object:ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                }
                override fun onDataChange(p0: DataSnapshot) {
                var iterable:Iterable<DataSnapshot> = p0.children
                var temp_list = mutableListOf<String>()
                    iterable.forEach {
                        if(it.key.equals("pic_url")){

                            Glide.with(this@UserInfoActivity).
                                    load(it.value).into(cview)

                        }else {
                            temp_list.add(it.key + " : " + it.value)
                        }
                        var adapter = ArrayAdapter<String>(
                            this@UserInfoActivity,
                         android.R.layout.simple_list_item_single_choice,
                            temp_list)
                        lview.adapter = adapter
                    }

                }
            })
    }
}
