package kushal.application.calci

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        animationView.setOnClickListener {
            (it as LottieAnimationView).playAnimation()
        }

        cagr.setOnClickListener {
            startActivity(Intent(this, Cagr::class.java))
        }
        sip.setOnClickListener {
            startActivity(Intent(this, SipAct::class.java))
        }
        lumsum.setOnClickListener {
            startActivity(Intent(this, LumsumAct::class.java))
        }
        dcf.setOnClickListener {
            startActivity(Intent(this, DCF::class.java));
        }

    }


}
