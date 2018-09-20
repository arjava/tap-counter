package com.arjavax.tapcounter

import android.annotation.SuppressLint
import android.app.Dialog
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import com.arjavax.tapcounter.utils.Boast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val boast: Boast.Companion = Boast
    private var counter: Int = 0

    companion object {
        var counterEnd: Int? = null
    }

    @SuppressLint("SetTextI18n", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lyConstraint.setOnClickListener {

            if (counter == counterEnd?.minus(1)) {
                textViewCounter.text = "Finished\n $counterEnd Tap"
                Boast.showText(this@MainActivity, "Finish")
                val mediaPlayer = MediaPlayer.create(this@MainActivity, R.raw.flabbyburd)
                mediaPlayer.start()
            }else {
                counter += 1
                textViewCounter.text = "$counter"
            }
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.setReset -> { resetCounter() }
            R.id.setFinish -> { showSetCounter() }
            R.id.setTone -> { showSetTone() }
            R.id.setTheme -> { showSetTheme() }
            R.id.about -> { toAboutActivity() }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun resetCounter() {
        counter = 0
        textViewCounter.text = "$counter"
        boast.makeText(this@MainActivity, "Counter Resets", Toast.LENGTH_SHORT).show(true)
    }

    @SuppressLint("SetTextI18n")
    private fun showSetCounter() {

        val d = Dialog(this@MainActivity)
        d.setTitle("Select Counter Finish")
        d.setContentView(R.layout.activity_set_finish)
        val textView = d.findViewById<TextView>(R.id.tvBatasCounter)
        val np = d.findViewById(R.id.numbPicker) as NumberPicker
        val buttonSet: Button = d.findViewById(R.id.buttonSet)
        if (counterEnd==null){
            textView.text = "${resources.getString(R.string.batas_counter_terakhir)} belum ada"
        }else {
            textView.text = "${resources.getString(R.string.batas_counter_terakhir)} $counterEnd"
        }
        np.maxValue = 1000000
        np.minValue = 0
        np.wrapSelectorWheel = false
        buttonSet.setOnClickListener {
            np.clearFocus()
            counterEnd = np.value
            d.dismiss()
        }
        d.show()

    }

    private fun showSetTone() {

    }

    private fun showSetTheme() {

    }

    private fun toAboutActivity() {

    }

}
