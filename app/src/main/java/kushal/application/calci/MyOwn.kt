package kushal.application.calci

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_my_own.*
import kotlin.math.pow
import kotlin.math.roundToInt

class MyOwn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_own)

        val vib = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        dcf_reset.setOnClickListener {
            profits.setText("")
            disc_rate.setText("")
            growthRate1to5.setText("")
            terminalRate.setText("")
            growthRate6to10.setText("")
            pe.setText("")

            profits.requestFocus()
            vib.vibrate(20)
        }


        dcf_calci.setOnClickListener {
            if (profits.text.isNullOrEmpty() or disc_rate.text.isNullOrEmpty() or
                growthRate1to5.text.isNullOrEmpty() or growthRate6to10.text.isNullOrEmpty() or
                pe.text.isNullOrEmpty()
            ) {
                profits.requestFocus()
                return@setOnClickListener
            }

            vib.vibrate(100)

            if (terminalRate.text.isNullOrEmpty())
                terminalRate.setText("0")

            var profit = profits.text.toString().toDouble()
            val rate = disc_rate.text.toString().toDouble()
            val g1to5 = growthRate1to5.text.toString().toDouble()
            val g6to10 = growthRate6to10.text.toString().toDouble()
            val pe_ratio = pe.text.toString().toDouble()
            val terminalRate = terminalRate.text.toString().toDouble()

            var dur = 10
            profit *= (1 + g1to5 / 100).pow(5)
            profit *= (1 + g6to10 / 100).pow(5)

            if (terminalRate != 0.0) {
                profit *= (1 + terminalRate / 100).pow(5)
                dur = 15
            }

            profit *= pe_ratio

            val ans = profit / ((1 + rate / 100).pow(dur))

            dcf_ans.text = ans.roundToInt().toString()
        }

    }
}