package kr.ac.kumoh.s20200759.w0501carddealer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class CardViewModel : ViewModel(){
    private var _cards = MutableLiveData<IntArray>()
    val cards: LiveData<IntArray>
    get() = _cards
    //val random = Random(100)
    val random = Random(System.currentTimeMillis())
    fun deal(){
        var c = IntArray(5)
        for (i in 0 until c.size)
            c[i] = random.nextInt(52)
        _cards.value = c
    }

}