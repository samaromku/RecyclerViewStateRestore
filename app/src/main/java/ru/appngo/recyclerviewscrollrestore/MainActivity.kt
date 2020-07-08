package ru.appngo.recyclerviewscrollrestore

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.appngo.recyclerviewscrollrestoreimport.UsersAdapter
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val adapter = UsersAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler_view.adapter = adapter
        Thread(Runnable {
            while (true) {
                runOnUiThread {
                    setAdapter()
                }
                Thread.sleep(5000)
            }
        }).start()
    }

    private fun setAdapter() {
        val list = mutableListOf<User>()
        for (index in 0..100) {
            val color: Int = Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
            list.add(User(index, color, "$index userName"))
        }
        adapter.data = list
    }
}
