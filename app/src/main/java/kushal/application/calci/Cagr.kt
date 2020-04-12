package kushal.application.calci

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cagr.*
import kotlin.math.pow

class Cagr : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cagr)

        val vib = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        cagr_reset.setOnClickListener {
            cagr_time.setText("")
            cagr_absolute.setText("")
            cagr_ans.text = "0 %"

            vib.vibrate(20)
        }

        cagr_calc.setOnClickListener {

            if (cagr_absolute.text.isNullOrBlank() or cagr_time.text.isNullOrBlank()) {
                cagr_absolute.requestFocus()
                return@setOnClickListener
            }

            cagr_time.onEditorAction(EditorInfo.IME_ACTION_DONE)
            vib.vibrate(100)

            val time = cagr_time.text.toString().toDouble()
            val absolute = cagr_absolute.text.toString().toDouble()

            var ans = absolute.pow(1 / time)
            ans = ans.dec()
            ans = ans.times(100)

            cagr_ans.text = String.format("%.2f ", ans)
            cagr_ans.append("%")
        }


    }

}
