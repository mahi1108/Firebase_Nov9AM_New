package cubex.mahesh.firebase_nov9am

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_admob.*

class AdmobActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admob)

        var mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = resources.getString(R.string.interestial_id)
        mInterstitialAd.loadAd(AdRequest.Builder().build())


        var  mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this)
        mRewardedVideoAd.loadAd(resources.getString(R.string.video_id),
            AdRequest.Builder().build())

        banner.setOnClickListener {
            val adRequest = AdRequest.Builder().build()
            adView.loadAd(adRequest)
        }
        interestial.setOnClickListener {
            if (mInterstitialAd.isLoaded) {
                mInterstitialAd.show()
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.")
            }
        }
        video.setOnClickListener {

            if (mRewardedVideoAd.isLoaded) {
                mRewardedVideoAd.show()
            }
        }

    }
}
