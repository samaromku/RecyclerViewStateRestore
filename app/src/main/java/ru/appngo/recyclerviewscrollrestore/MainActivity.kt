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
            Thread.sleep(1000)
            runOnUiThread {
                setAdapter()
            }
        }).start()
    }

    private fun setAdapter() {
        val list = mutableListOf<User>()
        for (index in 0..100) {
            val color: Int = Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
            val userIndex = Random.nextInt(256)
            list.add(User(userIndex, color, "$userIndex userName"))
        }
        adapter.data = list
    }
}
