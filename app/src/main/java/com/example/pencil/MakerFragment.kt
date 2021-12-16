package com.example.pencil

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pencil.databinding.FragmentMakerBinding
import com.example.pencil.ui.ToolAdapter
import com.example.pencil.ui.ToolClickListeners
import com.example.pencil.ui.common.BaseFragment
import com.example.pencil.ui.common.viewBinding
import com.example.pencil.ui.data.CategoryType
import com.example.pencil.ui.data.Tool
import com.example.pencil.ui.data.getToolList
import com.azeesoft.lib.colorpicker.ColorPickerDialog
import com.example.pencil.ui.data.getBackgroundList
import com.example.pencil.util.getIcon
import java.lang.Exception


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
        binding.recyclerview.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        toolAdapter = ToolAdapter(object : ToolClickListeners {
            override fun getTool(tool: Tool) {

                if (tool.isFormat) {
                    onFormatClicked(tool)
                } else {
                    onToolBarClicked(tool)
                }
            }

        })
        toolAdapter.differ.submitList(list)
        binding.recyclerview.adapter = toolAdapter
    }

    fun onFormatClicked(tool: Tool) {
        when (tool.type) {

            CategoryType.TEXT -> {

            }
            CategoryType.BACKGROUND -> {
                when (tool.id) {
                    1 -> binding.layout.background =
                        resources.getIcon(R.drawable.paper_1)
                    2 -> binding.layout.background = resources.getIcon(R.color.white)
                    3 -> binding.layout.background = resources.getIcon(R.color.black)
                    4 -> binding.layout.background =
                        resources.getIcon(R.color.off_white)
                }
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
                val colorPickerDialog = ColorPickerDialog.createColorPickerDialog(
                    requireContext(),
                    ColorPickerDialog.DARK_THEME
                )
                colorPickerDialog.show()
                colorPickerDialog.setOnColorPickedListener { _, hex ->
                    try {
                        paint.color = Color.parseColor(hex)
                    } catch (e: Exception) {
                        paint.color =
                            ResourcesCompat.getColor(resources, R.color.black, null)
                    }

                }
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
}