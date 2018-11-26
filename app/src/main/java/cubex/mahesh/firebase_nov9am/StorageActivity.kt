package cubex.mahesh.firebase_nov9am

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_storage.*
import java.io.ByteArrayOutputStream
import java.io.File

class StorageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage)

        iviw.setOnClickListener {

            var aDialog = AlertDialog.Builder(this@StorageActivity)
            aDialog.setTitle("Message")
            aDialog.setMessage("select option to add an attachment")
            aDialog.setPositiveButton("Camera",
                object: DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        var i = Intent("android.media.action.IMAGE_CAPTURE")
                        startActivityForResult(i,123)
                    }
                })
            aDialog.setNegativeButton("File Explorer",
                object:DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        var i = Intent( )
                        i.setAction(Intent.ACTION_GET_CONTENT)
                        i.setType("image/*")
                        startActivityForResult(i,124)
                    }
                })
            aDialog.setNeutralButton("Cancel",
                object:DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        p0!!.dismiss()
                    }
                })
            aDialog.show()
        } // attach onclick listener

    }  //onCreate( )

    var uri:Uri? = null
    override fun onActivityResult(requestCode: Int,
                                  resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==123 && resultCode== Activity.RESULT_OK)
        {
            var  bmp: Bitmap = data!!.extras.get("data") as Bitmap
            uri = getImageUri(this@StorageActivity,bmp)
            iviw.setImageURI(uri)
            uploadFile()
        }
        if(requestCode==124 && resultCode== Activity.RESULT_OK)
        {
            uri = data!!.data
            iviw.setImageURI(uri)
            uploadFile()
        }
    } // onActivityResult

    fun getImageUri(inContext: Context, inImage: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null)
        return Uri.parse(path)
    }

    fun  uploadFile( ){

        var sRef = FirebaseStorage.getInstance().
            getReference("files")
        var file = File(uri!!.path)
        sRef.child(file.name).putFile(uri!!).
            addOnSuccessListener {

                var url = it.downloadUrl.toString()
                var dBase = FirebaseDatabase.getInstance()
                var dRef = dBase.getReference("users")
                var uid = FirebaseAuth.getInstance().uid
                var uid_ref = dRef.child(uid.toString())
                uid_ref.child("pic_url").setValue(url)

        }


    }

} //StorageActivity
