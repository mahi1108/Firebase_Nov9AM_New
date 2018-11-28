package cubex.mahesh.firebase_nov9am

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fb_auth.setOnClickListener {
            startActivity(Intent(this@MainActivity,
                    AuthActivity::class.java))
        }
        fb_db.setOnClickListener {
            startActivity(Intent(this@MainActivity,
                DatabaseActivity::class.java))
        }
        fb_storage.setOnClickListener {
            startActivity(Intent(this@MainActivity,
                StorageActivity::class.java))
        }
        fb_admob.setOnClickListener {
            startActivity(Intent(this@MainActivity,
                AdmobActivity::class.java))
        }
        fb_mlkit.setOnClickListener {
            startActivity(Intent(this@MainActivity,
                MLKitActivity::class.java))
        }
        userInfo.setOnClickListener {
            startActivity(Intent(this@MainActivity,
                UserInfoActivity::class.java))
        }

    }
}
