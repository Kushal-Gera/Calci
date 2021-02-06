package kushal.application.calci

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_dialog_view.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        
        cagr.setOnClickListener {
            startActivity(Intent(this, Cagr::class.java))
        }
        sip.setOnClickListener {
            startActivity(Intent(this, SipAct::class.java))
        }
        lumsum.setOnClickListener {
            startActivity(Intent(this, LumsumAct::class.java))
        }
        value.setOnClickListener {
            show_bottom_dialog()
        }

    }

    fun show_bottom_dialog() {
        val dialog = BottomSheetDialog(this, R.style.BottomSheetDialog)
        dialog.setContentView(R.layout.bottom_dialog_view)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        dialog.dia_benja.setOnClickListener {
            startActivity(Intent(this, Benjamin_Intrinsic::class.java))
            dialog.dismiss()
        }

        dialog.dia_dcf.setOnClickListener {
            startActivity(Intent(this, DCF::class.java))
            dialog.dismiss()
        }

        dialog.my_own.setOnClickListener {
            startActivity(Intent(this, MyOwn::class.java))
            dialog.dismiss()
        }
    }

}
