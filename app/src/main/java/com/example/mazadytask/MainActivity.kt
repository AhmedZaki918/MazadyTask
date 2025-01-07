package com.example.mazadytask

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mainCategoryFl: ConstraintLayout
    private lateinit var mainCategoryFl2: ConstraintLayout
    private lateinit var labelTv: TextView
    private lateinit var labelTv2: TextView
    private lateinit var imageClose: TextView
    private lateinit var dialog: BottomSheetDialog
    private lateinit var dialogView: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
        updateUi()
        mainCategoryFl.setOnClickListener(this)
        mainCategoryFl2.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.includedHeaderOne -> showBottomSheet("Main")
            R.id.includedHeaderTwo -> showBottomSheet("Second")
            R.id.bottomSheetText2 -> dialog.dismiss()
        }
    }

    private fun initViews() {
        mainCategoryFl = findViewById(R.id.includedHeaderOne)
        mainCategoryFl2 = findViewById(R.id.includedHeaderTwo)
        labelTv = mainCategoryFl.findViewById(R.id.textView)
        labelTv2 = mainCategoryFl2.findViewById(R.id.textView)
    }

    private fun showBottomSheet(type: String) {
        dialogView = layoutInflater.inflate(R.layout.bottom_sheet_layout, null)
        dialogView.findViewById<TextView>(R.id.bottomSheetText).text = type
        dialog = BottomSheetDialog(this@MainActivity).apply {
            setContentView(dialogView)
            show()
        }

        imageClose = dialogView.findViewById(R.id.bottomSheetText2)
        imageClose.setOnClickListener(this)
    }

    private fun updateUi() {
        labelTv.text = "Cars"
        labelTv2.text = "Sub category"
    }
}