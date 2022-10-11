package kr.ac.kumoh.s20200759.w0501carddealer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import kr.ac.kumoh.s20200759.w0501carddealer.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val model : CardViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)
        model.cards.observe(this, Observer<IntArray> {
            val res = IntArray(5)
            val c = IntArray(5)

            for (i in it.indices) {
                c[i] = Random.nextInt(52)

                Log.i("Test", "${c[i]} : " +
                        "${getCardName(c[i])}")

                res[i] = resources.getIdentifier(
                    getCardName(c[i]),
                    "drawable",
                    packageName
                )
            }
            binding.card1.setImageResource(res[0])
            binding.card2.setImageResource(res[1])
            binding.card3.setImageResource(res[2])
            binding.card4.setImageResource(res[3])
            binding.card5.setImageResource(res[4])

        })

        binding.btnDeal.setOnClickListener {
            model.deal()
        }
    }
    private fun getCardName(c: Int): String{
        Log.i("getCardName() : ", (c).toString())
        val shape = when (c/13){
            0 -> "spades"
            1 -> "diamonds"
            2 -> "hearts"
            3 -> "clubs"
            else -> "error"
        }
        Log.i("getCardName() : ", shape.toString())
        val number = when (c%13){
            0 -> "ace"
            in 1..9 -> (c % 13 + 1).toString()
            10 -> "jack"
            11 -> "queen"
            12 -> "king"
            else -> "error"
        }
        Log.i("getCardName() : ", number.toString())
        return "c_${number}_of_${shape}"
    }
}