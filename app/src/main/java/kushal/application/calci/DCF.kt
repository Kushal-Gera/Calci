package kushal.application.calci

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_d_c_f.*
import kotlin.math.pow
import kotlin.math.roundToInt

class DCF : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d_c_f)

        val vib = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        dcf_reset.setOnClickListener {
            initialFCF.setText("")
            disc_rate.setText("")
            growthRate1to5.setText("")
            marketCap.setText("")
            currentPrice.setText("")
            debt.setText("")
            terminalRate.setText("")
            growthRate6to10.setText("")

            vib.vibrate(20)
        }

        dcf_calci.setOnClickListener {
            if (initialFCF.text.isNullOrEmpty() or disc_rate.text.isNullOrEmpty() or
                growthRate1to5.text.isNullOrEmpty() or growthRate6to10.text.isNullOrEmpty() or
                marketCap.text.isNullOrEmpty() or currentPrice.text.isNullOrEmpty()
                or terminalRate.text.isNullOrEmpty()
            ) {
                initialFCF.requestFocus()
                return@setOnClickListener
            }

            vib.vibrate(100)

            val cashFlow = initialFCF.text.toString().toDouble()
            val marketCap = marketCap.text.toString().toDouble()
            val price = currentPrice.text.toString().toDouble()
            val rate = disc_rate.text.toString().toDouble()
            val g1to5 = growthRate1to5.text.toString().toDouble()
            val g6to10 = growthRate6to10.text.toString().toDouble()
            val terminalRate = terminalRate.text.toString().toDouble()
            val debt = debt.text.toString()
            val cashEqui = cash.text.toString()
            var safety = pe.text.toString()

            val shares: Double = marketCap / price
            var total = 0.0
            val list = arrayListOf<Double>()

            list.add(cashFlow * (1 + (g1to5 / 100)))
            for (i in 1..5) list.add(list[i - 1] * (1 + (g6to10 / 100)))
            for (i in 6..9) list.add(list[i - 1] * (1 + (g6to10 / 100)))
            list.add(list.last() * ((1 + (terminalRate / 100)) / (rate / 100 - (terminalRate / 100))))

            for (x in 0..9)
                list[x] = list[x] / ((1 + (rate / 100)).pow(x + 1))
            list[10] = list[10] / ((1 + (rate / 100)).pow(10))

            for (x in 0..10)
                Log.i("Tag", (x + 1).toString() + ". " + list[x].toString())

            for (x in list) total += x

            if (debt.isNotEmpty())
                total -= debt.toDouble()
            if (cashEqui.isNotEmpty())
                total += cashEqui.toDouble()

            var ans = total / shares

            if (safety.isEmpty()) safety = "0"
            if ((safety.toInt() <= 100) and (safety.toInt() > 0))
                ans -= (ans * safety.toInt()) / 100
            else
                pe.setText("0")

            dcf_ans.text = ans.roundToInt().toString()
            scroller.scrollTo(0, scroller.bottom)
        }

    }
}
