package com.example.popularlibraries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import com.example.popularlibraries.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding
    val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnCounter1.setOnClickListener { presenter.counterClick(ButtonNumber.ONE) }
            btnCounter2.setOnClickListener { presenter.counterClick(ButtonNumber.TWO) }
            btnCounter3.setOnClickListener { presenter.counterClick(ButtonNumber.THREE) }
        }
    }

    override fun setButton1Text(text: String) {
        binding.btnCounter1.text = text
    }

    override fun setButton2Text(text: String) {
        binding.btnCounter2.text = text
    }

    override fun setButton3Text(text: String) {
        binding.btnCounter3.text = text
    }
}