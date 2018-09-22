package com.arjavax.tapcounter

import android.annotation.SuppressLint
import android.app.Dialog
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.arjavax.tapcounter.utils.Boast
import com.arjavax.tapcounter.utils.ThemeApplication
import com.arjavax.tapcounter.utils.ThemeSwitcher
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val boast: Boast.Companion = Boast
    private var counter: Int = 0
    private var tone: Int = 0
    private var toneName: String? = null
    private var mediaPlayer: MediaPlayer? = null
    private var themeName: String? = null

    companion object {
        var counterEnd: Int? = null
    }

    private var themeSelected: Int = 0

    @SuppressLint("SetTextI18n", "ResourceType", "PrivateResource")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ThemeSwitcher.onActivityCreateSetTheme(this)

        setContentView(R.layout.activity_main)

        lyConstraint.setOnClickListener {

            if (counter == counterEnd?.minus(1)) {
                textViewCounter.text = "Finished\n $counterEnd Tap"
                Boast.showText(this@MainActivity, "Finish")
                mediaPlayer = when (tone) {
                    0 -> MediaPlayer.create(this@MainActivity, R.raw.flabbyburd)
                    1 -> MediaPlayer.create(this@MainActivity, R.raw.firelightercapclosing01)
                    2 -> MediaPlayer.create(this@MainActivity, R.raw.drinkglassbreaking01)
                    3 -> MediaPlayer.create(this@MainActivity, R.raw.churchbellchimingtwice01)
                    4 -> MediaPlayer.create(this@MainActivity, R.raw.boxingbellring01)
                    5 -> MediaPlayer.create(this@MainActivity, R.raw.boxingbellring02)
                    6 -> MediaPlayer.create(this@MainActivity, R.raw.bicycleminibellring01)
                    7 -> MediaPlayer.create(this@MainActivity, R.raw.bicyclebellringclassic02)
                    else -> MediaPlayer.create(this@MainActivity, R.raw.flabbyburd)
                }
                mediaPlayer?.start()
            } else {
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
        when (item?.itemId) {
            R.id.setReset -> {
                resetCounter()
            }
            R.id.setFinish -> {
                showSetCounter()
            }
            R.id.setTone -> {
                showSetTone()
            }
            R.id.setTheme -> {
                showSetTheme()
            }
            R.id.about -> {
                toAboutActivity()
            }
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
        if (counterEnd == null) {
            textView.text = "${resources.getString(R.string.batas_counter_terakhir)} belum ada"
        } else {
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
        val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Pilih Nada Finish")
        builder.setCancelable(false)

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this@MainActivity, android.R.layout.simple_selectable_list_item)
        arrayAdapter.add("Flappy Burd")
        arrayAdapter.add("Fire Lighter Cap Closing 01")
        arrayAdapter.add("Drink Glass Breaking 01")
        arrayAdapter.add("Church Bell Chiming Twice 01")
        arrayAdapter.add("Boxing Bell Ring 01")
        arrayAdapter.add("Boxing Bell Ring 02")
        arrayAdapter.add("Bicycle Mini Bell Ring 01")
        arrayAdapter.add("Bicycle Bell Ring Classic 02")

        builder.setNegativeButton("Cancel") { dialogInterface, _ ->
            dialogInterface.dismiss()
        }

        builder.setAdapter(arrayAdapter) { _, i ->
            toneName = arrayAdapter.getItem(i)
            tone = arrayAdapter.getItemId(i).toInt()
            boast.makeText(this@MainActivity, toneName.toString(), Toast.LENGTH_SHORT).show()
            arrayAdapter.setNotifyOnChange(true)
        }
        builder.show()
    }


    private fun showSetTheme() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Pilih Tema")
        builder.setCancelable(false)

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this@MainActivity, android.R.layout.simple_selectable_list_item)
        arrayAdapter.add("Black Dark Theme")
        arrayAdapter.add("Green Dark Theme")
        arrayAdapter.add("Yellow Dark Theme")

        builder.setNegativeButton("Cancel") { dialogInterface, _ ->
            dialogInterface.dismiss()
        }

        builder.setAdapter(arrayAdapter) { _, i ->
            themeName = arrayAdapter.getItem(i)
            themeSelected = arrayAdapter.getItemId(i).toInt()
            boast.makeText(this@MainActivity, "Tema "+themeName.toString()+" diterapkan.", Toast.LENGTH_SHORT).show()
            arrayAdapter.setNotifyOnChange(true)
            if (ThemeApplication.currentPosition!=themeSelected) {
                ThemeSwitcher.changeToTheme(this@MainActivity, themeSelected)
            }
        }
        builder.show()
    }

    private fun toAboutActivity() {

    }

}
