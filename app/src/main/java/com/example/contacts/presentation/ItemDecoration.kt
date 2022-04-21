package com.example.contacts.presentation

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class ItemDecoration(private val dividerDrawable: Drawable) : RecyclerView.ItemDecoration() {
    private val dividerWidth = dividerDrawable.intrinsicWidth
    private val dividerHeight = dividerDrawable.intrinsicHeight

    override fun getItemOffsets(rect: Rect, v: View, parent: RecyclerView, s: RecyclerView.State) {
        parent.adapter?.let { adapter ->
            val childAdapterPosition = parent.getChildAdapterPosition(v)
                .let { if (it == RecyclerView.NO_POSITION) return else it }
            rect.bottom =
                if (childAdapterPosition == adapter.itemCount - 1) 0
                else dividerWidth + 5
        }
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left: Int = parent.paddingLeft + 80
        val right: Int = parent.width - parent.paddingRight - 80
        val childCount: Int = parent.childCount

        for (i in 0 until childCount) {
            val child: View = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom: Int = top + dividerHeight
            dividerDrawable.setBounds(left, top, right, bottom)
            dividerDrawable.draw(canvas)
        }
    }
}
