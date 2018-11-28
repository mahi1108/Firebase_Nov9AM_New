package cubex.mahesh.firebase_nov9am

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import kotlinx.android.synthetic.main.activity_mlkit.*
import kotlinx.android.synthetic.main.activity_storage.*

class MLKitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mlkit)
        camera.setOnClickListener {
            var i = Intent("android.media.action.IMAGE_CAPTURE")
            startActivityForResult(i,123)
        }
    } //onCreate( )

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==123 && resultCode== Activity.RESULT_OK)
        {
            var  bmp: Bitmap = data!!.extras.get("data") as Bitmap
            val image = FirebaseVisionImage.fromBitmap(bmp)
            val detector = FirebaseVision.getInstance()
                .onDeviceTextRecognizer
            detector.processImage(image).
                addOnSuccessListener {

              var text_blocks =   it.textBlocks
              var msg = ""
               for(block in text_blocks){
                   msg = msg + block.text+" "
               }
                tview.text = msg
            }

        }
    }


}
