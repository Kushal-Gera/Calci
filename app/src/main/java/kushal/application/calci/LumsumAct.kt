package kushal.application.calci

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_lumsum.*
import java.text.DecimalFormat
import kotlin.math.pow

class LumsumAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lumsum)


        val vib = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        lum_reset.setOnClickListener {
            lum_time.setText("")
            lum_amount.setText("")
            lum_rate.setText("")
            lum_ans.text = ""

            vib.vibrate(20)
        }

        lum_calc.setOnClickListener {

            if (lum_amount.text.isNullOrBlank() or lum_rate.text.isNullOrBlank() or lum_time.text.isNullOrBlank()) {
                lum_amount.requestFocus()
                return@setOnClickListener
            }

            lum_time.onEditorAction(EditorInfo.IME_ACTION_DONE)
            vib.vibrate(100)

            val amount = lum_amount.text.toString().toInt()
            var rate = lum_rate.text.toString().toFloat()
            val time = lum_time.text.toString().toInt()
            rate /= 100

            val ans = (rate + 1).pow(time) * amount

            val formatter = DecimalFormat("###,##,##,##0")
            val str = formatter.format(ans.toDouble())
            lum_ans.text = str
        }

    }
}
