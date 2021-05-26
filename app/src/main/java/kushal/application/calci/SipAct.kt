package kushal.application.calci

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sip.*
import java.text.DecimalFormat
import java.util.*
import kotlin.math.pow

class SipAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sip)


        val vib = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        sip_reset.setOnClickListener {
            sip_time.setText("")
            sip_amount.setText("")
            sip_rate.setText("")
            sip_stepup_rate.setText("")
            sip_ans.text = ""

            vib.vibrate(20)
        }


        sip_calc.setOnClickListener {

            if (sip_amount.text.isNullOrBlank() or sip_rate.text.isNullOrBlank() or sip_time.text.isNullOrBlank()) {
                sip_amount.requestFocus()
                return@setOnClickListener
            }

            sip_time.onEditorAction(EditorInfo.IME_ACTION_DONE)
            vib.vibrate(100)

            var amount = sip_amount.text.toString().toInt()
            var rate = sip_rate.text.toString().toFloat()
            val time = sip_time.text.toString().toFloat()

            var ans = 0.0
            if (!sip_stepup_rate.text.isNullOrBlank() and (sip_stepup_rate.text.toString() != "0")) {
                val stepup_rate = sip_stepup_rate.text.toString().toFloat().toDouble()
                var s = stepup_rate
                s /= 100 * 12

                for (i in 1..time.toInt()) {
                    val amt_to_be_added =
                        (amount * (s + 1) * (((1 + s).pow(12) - 1) / s))

                    amount = (amount * (1 + (stepup_rate / 100))).toInt()
                    ans *= (1 + rate / 100)
                    ans += amt_to_be_added

                    Log.i("Tag", "time: $i , amount: $amount, ans: $ans")
                }

            } else {
                rate /= 100 * 12
                val installments = time * 12
                ans = (amount * (rate + 1) * (((1 + rate).pow(installments) - 1) / rate)).toDouble()
            }

            val formatter = DecimalFormat("###,##,##,##0")
            val str = formatter.format(ans)

            sip_ans.text = str
        }
    }
}
