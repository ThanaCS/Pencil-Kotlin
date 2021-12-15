package com.example.pencil

import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import androidx.core.content.res.ResourcesCompat

class CanvasView(context: Context) : View(context) {

    private lateinit var extraCanvas: Canvas
    lateinit var extraBitmap: Bitmap
    private var path = Path()

    private var motionTouchEventX = 0f
    private var motionTouchEventY = 0f

    private var currentX = 0f
    private var currentY = 0f


    private var paint = Paint()

    init {
        paint = Paint().apply {

            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.MITER
            strokeCap = Paint.Cap.ROUND
            color = ResourcesCompat.getColor(resources, R.color.black, null)
            strokeWidth = 20f
            isAntiAlias = true
        }

    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)
        if (::extraBitmap.isInitialized) extraBitmap.recycle()

        extraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            drawBitmap(extraBitmap, 0f, 0f, null)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        motionTouchEventX = event.x
        motionTouchEventY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchStart()
            MotionEvent.ACTION_MOVE -> touchMove()
        }
        return true

    }

    private fun touchStart() {
        path.reset()
        path.moveTo(motionTouchEventX, motionTouchEventY)
        currentX = motionTouchEventX
        currentY = motionTouchEventY
    }

    private fun touchMove() {
        path.lineTo((motionTouchEventX + currentX) / 2, (motionTouchEventY + currentY) / 2)
        currentX = motionTouchEventX
        currentY = motionTouchEventY
        extraCanvas.drawPath(path, paint)

        invalidate()
    }

    fun clearCanvasDrawing() {
        extraCanvas.drawColor(0, PorterDuff.Mode.CLEAR)
        path.reset()
        invalidate()
    }
}