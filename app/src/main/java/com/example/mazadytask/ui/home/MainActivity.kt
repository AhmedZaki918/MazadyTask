package com.example.mazadytask.ui.home

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mazadytask.R
import com.example.mazadytask.data.model.OptionType
import com.example.mazadytask.ui.adapter.GenericAdapter
import com.example.mazadytask.util.DropDownId
import com.example.mazadytask.util.MainCategory
import com.example.mazadytask.util.carsDummy
import com.example.mazadytask.util.computerDummy
import com.example.mazadytask.util.mainTypeDummy
import com.example.mazadytask.util.mobileDummy
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity(), View.OnClickListener, OnItemClicked {

    private lateinit var mainCategoryFl: ConstraintLayout
    private lateinit var mainCategoryFl2: ConstraintLayout
    private lateinit var labelTv: TextView
    private lateinit var labelTv2: TextView
    private lateinit var imageClose: ImageView
    private lateinit var dialog: BottomSheetDialog
    private lateinit var dialogView: View
    private var activeDropDownId: Byte = 0
    private var categoryType: String = ""


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
            R.id.includedHeaderOne -> {
                showBottomSheet("Main")
                setupRecyclerView(mainTypeDummy())
                activeDropDownId = DropDownId.FIRST.value
            }

            R.id.includedHeaderTwo -> {
                showBottomSheet("Second")
                updateChildList()
                activeDropDownId = DropDownId.SECOND.value
            }

            R.id.close_iv -> dialog.dismiss()
        }
    }

    override fun <T> onItemClick(model: T) {
        model as OptionType
        dialog.dismiss()

        if (activeDropDownId == DropDownId.FIRST.value) {
            categoryType = model.optionName
            labelTv.text = model.optionName
            labelTv2.text = "Sub category"
        } else if (activeDropDownId == DropDownId.SECOND.value) {
            labelTv2.text = model.optionName
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
        dialogView.findViewById<TextView>(R.id.process_type_tv).text = type
        dialog = BottomSheetDialog(this@MainActivity).apply {
            setContentView(dialogView)
            show()
        }
        imageClose = dialogView.findViewById(R.id.close_iv)
        imageClose.setOnClickListener(this)
    }

    private fun setupRecyclerView(data: List<OptionType>) {
        val recyclerView = dialogView.findViewById<RecyclerView>(R.id.options_rv)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = GenericAdapter(
            itemList = data,
            bindView = { view, item, isLastItem ->
                val optionTv = view.findViewById<TextView>(R.id.option_tv)
                val lineView = view.findViewById<View>(R.id.line_view)
                optionTv.text = item.optionName
                if (isLastItem) {
                    lineView.visibility = View.GONE
                }
            },
            onItemClicked = this
        )
    }

    private fun updateUi() {
        labelTv.text = "Cars"
        labelTv2.text = "Sub category"
    }

    private fun updateChildList() {
        when (categoryType) {
            MainCategory.CARS.value -> setupRecyclerView(carsDummy())
            MainCategory.COMPUTER.value -> setupRecyclerView(computerDummy())
            MainCategory.MOBILE.value -> setupRecyclerView(mobileDummy())
        }
    }
}