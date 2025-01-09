package com.example.mazadytask.ui.home

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mazadytask.R
import com.example.mazadytask.data.model.categories.Category
import com.example.mazadytask.data.model.categories.Children
import com.example.mazadytask.data.model.properties.Option
import com.example.mazadytask.data.model.properties.PropertiesData
import com.example.mazadytask.ui.adapter.GenericAdapter
import com.example.mazadytask.util.RequestState
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener, OnItemClicked {

    private lateinit var mainCategoryFl: ConstraintLayout
    private lateinit var mainCategoryFl2: ConstraintLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var labelTv: TextView
    private lateinit var labelTv2: TextView
    private lateinit var imageClose: ImageView
    private lateinit var dialog: BottomSheetDialog
    private lateinit var dialogView: View

    private lateinit var viewModel: HomeViewModel
    lateinit var categories: List<Category>
    private lateinit var subCategories: List<Children>


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
        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                updateAllCategories(uiState)

                when (uiState.propertiesState) {
                    RequestState.LOADING -> {
                        progressBar.visibility = VISIBLE
                    }

                    RequestState.SUCCESS -> {
                        displayProperties(uiState.properties.data)
                    }

                    RequestState.ERROR -> {
                        progressBar.visibility = GONE
                        Toast.makeText(
                            this@MainActivity, uiState.errorMessage, Toast.LENGTH_SHORT
                        ).show()
                    }

                    RequestState.IDLE -> Unit
                }
            }
        }

        mainCategoryFl.setOnClickListener(this)
        mainCategoryFl2.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.includedHeaderOne -> {
                showBottomSheet(getString(R.string.main_category))
                displayCategories(categories)
            }

            R.id.includedHeaderTwo -> {
                showBottomSheet(getString(R.string.sub_category))
                displaySubCategories(subCategories)
            }

            R.id.close_iv -> dialog.dismiss()
        }
    }

    override fun <T> onItemClick(model: T) {
        when (model) {
            is Category -> {
                dialog.dismiss()
                subCategories = model.children
                labelTv2.text = model.children[0].slug
                labelTv.text = model.slug
                viewModel.fetchProperties(subCategories[0].id)
            }

            is Children -> {
                labelTv2.text = model.slug
                dialog.dismiss()
                viewModel.fetchProperties(model.id)
            }

             is PropertiesData -> {
                 showBottomSheet(model.slug)
                 displaySubProperties(model.options)
             }

            is Option -> {
                dialog.dismiss()
            }
        }
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        progressBar = findViewById(R.id.circularProgressBar)
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

    private fun updateDefaultValueForAllCategories(defaultCategoryName: Boolean) {
        if (defaultCategoryName && categories.isNotEmpty()) {
            labelTv.text = categories[0].slug
            if (categories[0].children.isNotEmpty()) {
                labelTv2.text = categories[0].children[0].slug
                subCategories = categories[0].children
            }
        }
    }

    private fun updateAllCategories(response: HomeUi) {
        when (response.homeState) {
            RequestState.SUCCESS -> {
                categories = response.categories.data.categories

                updateDefaultValueForAllCategories(response.defaultCategoryName)
                progressBar.visibility = GONE
                mainCategoryFl.visibility = VISIBLE
                mainCategoryFl2.visibility = VISIBLE
            }

            RequestState.ERROR -> {
                progressBar.visibility = GONE
                Toast.makeText(this, response.errorMessage, Toast.LENGTH_SHORT).show()
            }

            RequestState.IDLE -> Unit
            RequestState.LOADING -> Unit
        }
    }

    private fun displayCategories(categories: List<Category>) {
        val recyclerView = dialogView.findViewById<RecyclerView>(R.id.options_rv)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = GenericAdapter(
            itemList = categories,
            bindView = { view, item, isLastItem ->
                val optionTv = view.findViewById<TextView>(R.id.option_tv)
                val lineView = view.findViewById<View>(R.id.line_view)
                optionTv.text = item.slug
                if (isLastItem) {
                    lineView.visibility = GONE
                }
            },
            onItemClicked = this
        )
    }

    private fun displayProperties(properties: List<PropertiesData>) {
        val recyclerView = findViewById<RecyclerView>(R.id.properties_rv)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = GenericAdapter(
            itemList = properties,
            bindView = { view, item, isLastItem ->
                val optionTv = view.findViewById<TextView>(R.id.textView)
                optionTv.text = item.slug
            },
            onItemClicked = this,
            isSecondLayout = true
        )
    }

    private fun displaySubProperties(options: List<Option>) {
        val recyclerView = dialogView.findViewById<RecyclerView>(R.id.options_rv)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = GenericAdapter(
            itemList = options,
            bindView = { view, item, isLastItem ->
                val optionTv = view.findViewById<TextView>(R.id.option_tv)
                optionTv.text = item.slug
            },
            onItemClicked = this
        )
    }

    private fun displaySubCategories(children: List<Children>) {
        val recyclerView = dialogView.findViewById<RecyclerView>(R.id.options_rv)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = GenericAdapter(
            itemList = children,
            bindView = { view, item, isLastItem ->
                val optionTv = view.findViewById<TextView>(R.id.option_tv)
                val lineView = view.findViewById<View>(R.id.line_view)
                optionTv.text = item.slug
                if (isLastItem) {
                    lineView.visibility = GONE
                }
            },
            onItemClicked = this
        )
    }
}