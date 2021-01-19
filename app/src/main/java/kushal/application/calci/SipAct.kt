package kushal.application.calci

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
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

            val amount = sip_amount.text.toString().toInt()
            var rate = sip_rate.text.toString().toFloat()
            val time = sip_time.text.toString().toFloat()
            rate /= 100 * 12

            val installments = time * 12
            val ans = amount * (rate + 1) * (((1 + rate).pow(installments) - 1) / rate)

            val formatter = DecimalFormat("###,##,##,##0")
            val str = formatter.format(ans.toDouble())

            sip_ans.text = str
        }

    }
}
