package kushal.application.calci

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_benjamin__intrinsic.*
import kotlinx.android.synthetic.main.activity_d_c_f.*
import kotlin.math.roundToInt

class Benjamin_Intrinsic : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_benjamin__intrinsic)


        val vib = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        benja_reset.setOnClickListener {
            benja_safety.setText("")
            benja_10year_yield.setText("")
            benja_eps.setText("")
            benja_growth.setText("")

            vib.vibrate(20)
        }

        benja_calc.setOnClickListener {
            if (benja_10year_yield.text.isNullOrEmpty() or benja_10year_yield.text.isNullOrEmpty() or
                benja_growth.text.isNullOrEmpty() or benja_growth.text.isNullOrEmpty() or
                benja_eps.text.isNullOrEmpty() or benja_eps.text.isNullOrEmpty()
            ) {
                benja_eps.requestFocus()
                return@setOnClickListener
            }

            vib.vibrate(100)

            val eps = benja_eps.text.toString().toDouble()
            val growth = benja_growth.text.toString().toDouble()
            var safety = benja_safety.text.toString()
            val bond_yield = benja_10year_yield.text.toString().toDouble()

            var ans = eps * 8.5 * (7 + growth) / bond_yield

            if (safety.isEmpty()) safety = "0"
            if ((safety.toInt() <= 100) and (safety.toInt() > 0))
                ans -= (ans * safety.toInt()) / 100
            else
                benja_safety.setText("0")

            benja_ans.text = ans.roundToInt().toString()
        }
    }

}