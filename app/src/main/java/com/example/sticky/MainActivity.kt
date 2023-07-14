package com.example.sticky

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sticky.databinding.ActivityMainBinding
import com.example.sticky.view.TestAdapter
import com.example.sticky.view.model.Content
import com.example.sticky.view.model.Footer
import com.example.sticky.view.model.Header
import com.example.sticky.view.model.Item
import kotlin.math.max

class MainActivity : AppCompatActivity() {

    companion object {
        const val ALPHA_MAX = 255
        const val ALPHA_MIN = 0
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var dummyItems = mutableListOf<Item>()
        dummyItems.add(Header(id = "Header"))
        for (i in 1..10) {
            dummyItems.add(Content(id = "Content $i"))
        }
        dummyItems.add(Footer(id = "Footer"))

        val adapter = TestAdapter(dummyItems)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var scrollY = 0f
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                scrollY += dy.toFloat()
                scrollY = max(scrollY, 0f)

                val alpha: Int
                if (scrollY > getHeaderMeasuredHeight()) {
                    alpha = ALPHA_MAX
                    binding.stickyHeader.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.black))
                } else if (scrollY == 0f) {
                    alpha = ALPHA_MIN
                    binding.stickyHeader.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.white))
                } else {
                    alpha = scrollY.div(getHeaderMeasuredHeight()).times(255).toInt()
                }
                binding.stickyHeader.background.alpha = alpha
            }

            fun getHeaderMeasuredHeight(): Int {
                return binding.stickyHeader.measuredHeight
            }
        })
    }
}