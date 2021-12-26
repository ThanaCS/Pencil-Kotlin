package com.example.pencil.ui

import android.graphics.Paint
import android.os.Bundle
import android.view.View
import com.example.pencil.CanvasView
import com.example.pencil.R
import com.example.pencil.databinding.FragmentMakerBinding
import com.example.pencil.ui.common.BaseFragment
import com.example.pencil.ui.common.viewBinding
import com.example.pencil.ui.data.CategoryType
import com.example.pencil.ui.data.Tool
import com.example.pencil.ui.data.getBackgroundList
import com.example.pencil.ui.data.getToolList
import com.example.pencil.util.getIcon
import com.skydoves.colorpickerview.ColorPickerDialog
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener

class MakerFragment : BaseFragment(R.layout.fragment_maker) {

    private val binding by viewBinding(FragmentMakerBinding::bind)
    private val list: MutableList<Tool> = mutableListOf()

    private lateinit var toolAdapter: ToolAdapter
    lateinit var canvas: CanvasView
    private lateinit var paint: Paint

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        canvas = CanvasView(requireContext())
        paint = canvas.paint
        binding.layout.addView(canvas)

        initAdapter()
    }

    private fun collapse(view: View, collapseView: View) {
        view.visibility = View.INVISIBLE
        collapseView.visibility = View.INVISIBLE
    }

    private fun initAdapter() {
        list.addAll(getToolList(requireContext()))

        toolAdapter = ToolAdapter(object : ToolClickListeners {
            override fun getTool(tool: Tool) {
                if (tool.isFormat) onFormatClicked(tool) else onToolBarClicked(tool)
            }
        })

        toolAdapter.differ.submitList(list)
        binding.recyclerview.adapter = toolAdapter
    }

    fun onFormatClicked(tool: Tool) {
        when (tool.type) {

            CategoryType.TEXT -> {
            }
            CategoryType.BACKGROUND -> when (tool.id) {
                1 -> binding.layout.background = resources.getIcon(R.drawable.paper_1)
                2 -> binding.layout.background = resources.getIcon(R.color.white)
                3 -> binding.layout.background = resources.getIcon(R.color.black)
                4 -> binding.layout.background = resources.getIcon(R.color.off_white)
            }
            CategoryType.BACK -> {
                updateList(getToolList(requireContext()))
            }
            else -> {
            }
        }

    }

    fun onToolBarClicked(tool: Tool) {
        when (tool.type) {
            CategoryType.BRUSH -> {
                canvas.removeEraser()
                sliderWidth()
            }
            CategoryType.COLOR -> {
                canvas.removeEraser()
                getColorPicker()
            }
            CategoryType.TEXT -> {

            }
            CategoryType.BACKGROUND -> {
                updateList(getBackgroundList(requireContext()))
            }
            CategoryType.CLEAR -> {
                canvas.clearCanvasDrawing()
            }
            CategoryType.ERASE -> {
                canvas.eraser()
                sliderWidth()
            }
            else -> {
            }
        }
    }

    private fun sliderWidth() {
        binding.slider.visibility = View.VISIBLE
        binding.cancel.visibility = View.VISIBLE
        binding.slider.value = paint.strokeWidth
        binding.slider.addOnChangeListener { _, value, _ ->
            paint.strokeWidth = value

        }
        binding.cancel.setOnClickListener {
            collapse(binding.slider, binding.cancel)
        }

    }

    private fun updateList(newList: MutableList<Tool>) {
        list.clear()
        list.addAll(newList)
        toolAdapter.notifyDataSetChanged()
    }

    private fun getColorPicker() {
        ColorPickerDialog.Builder(requireContext())
            .setTitle("Color Picker")
            .setPreferenceName("MyColorPickerDialog")
            .setPositiveButton("Ok",
                ColorEnvelopeListener { envelope, _ ->
                    paint.color = envelope.color

                })
            .setNegativeButton(
                "Cancel"
            ) { dialogInterface, _ -> dialogInterface.dismiss() }
            .attachAlphaSlideBar(true)
            .attachBrightnessSlideBar(true)
            .setBottomSpace(12)
            .show()
    }
}