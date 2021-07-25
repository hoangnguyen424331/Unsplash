package com.example.unsplash.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color.RED
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.constraintlayout.utils.widget.ImageFilterView
import com.example.unsplash.extentions.toBitmap

class CustomImageFilterView : ImageFilterView {

    private val path = Path()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context) : super(context) {
        initPaint()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initPaint()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        this.toBitmap()?.let {
            canvas?.drawBitmap(it, 0F, 0F, null)
        }
        canvas?.drawPath(path, paint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x
        val y = event?.y

        if (isDraw) {
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    x?.let { xData ->
                        y?.let { yData ->
                            path.moveTo(xData, yData)
                        }
                    }
                }
                MotionEvent.ACTION_MOVE -> {
                    x?.let { xData ->
                        y?.let { yData ->
                            path.lineTo(xData, yData)
                        }
                    }
                }
            }
            invalidate()
        }
        return true
    }

    private fun initPaint() {
        paint.apply {
            style = Paint.Style.STROKE
            color = RED
            strokeWidth = STROKE_WIDTH
        }
    }

    companion object {
        var isDraw = false
        private const val STROKE_WIDTH = 10F
    }
}
