package kushal.application.calci

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.airbnb.lottie.LottieAnimationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        logo.setOnClickListener {
            for (i in container.children) {
                i.animate().alpha(0f).start()
            }
        }

        animationView.setOnClickListener {
            (it as LottieAnimationView).playAnimation()
        }

        cagr.setOnClickListener {
            startActivity(Intent(this, Cagr::class.java))
        }
        sip.setOnClickListener {
            startActivity(Intent(this, SipAct::class.java))
        }


    }


}
