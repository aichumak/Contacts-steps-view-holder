package com.example.contacts.presentation.steps

import android.content.Context
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.contacts.R
import com.example.contacts.databinding.FragmentStepStatusLineBinding
import com.example.contacts.presentation.base_adapter.BaseItem
import com.example.contacts.presentation.base_adapter.BaseViewHolder

class StepsViewHolder(
    private val context: Context,
    private val quantitySteps: Int,
    private val lastStepIsDrawable: Boolean = false
) :
    BaseViewHolder {
    private var _binding: FragmentStepStatusLineBinding? = null
    private val binding get() = checkNotNull(_binding)

    override val viewType: Int
        get() = R.layout.fragment_step_status_line

    override fun getViewHolder(itemView: View) = object : BaseViewHolder.ViewHolder(itemView) {

        @RequiresApi(Build.VERSION_CODES.M)
        override fun bind(item: BaseItem) {
            _binding = FragmentStepStatusLineBinding.bind(itemView)
            binding.root
            val stepStatusLine = binding.stepStatusLine
            val step = item as Step

            for (i in 0 until quantitySteps) {
                val stepIsEnabled = step.stepId >= i
                if (lastStepIsDrawable && i == quantitySteps - 1) {
                    stepStatusLine.addView(getDrawableLineView(stepIsEnabled))
                    stepStatusLine.addView(getDrawableStepItemView(i, stepIsEnabled))
                } else {
                    if (i != 0) {
                        stepStatusLine.addView(getDrawableLineView(stepIsEnabled))
                    }
                    stepStatusLine.addView(getTextStepItemView(i, stepIsEnabled))
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun getTextStepItemView(id: Int, isEnabled: Boolean): RelativeLayout {
        val relativeLayoutSideSize = dpToPx(16f)
        val relativeLayoutParams =
            RelativeLayout.LayoutParams(relativeLayoutSideSize, relativeLayoutSideSize)
        val textViewLayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )

        textViewLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)

        val newRelativeLayout = RelativeLayout(context)
        newRelativeLayout.layoutParams = relativeLayoutParams
        newRelativeLayout.id = id
        newRelativeLayout.setBackgroundResource(R.drawable.selector_step_item)
        newRelativeLayout.isEnabled = isEnabled

        val newTextView = TextView(context)
        newTextView.layoutParams = textViewLayoutParams
        newTextView.text = "${(id + 1)}"
        newTextView.setLineSpacing(-2.0f, 1f)
        newTextView.setTextAppearance(R.style.step_id)

        newRelativeLayout.addView(newTextView)

        return newRelativeLayout
    }

    fun getDrawableStepItemView(id: Int, isEnabled: Boolean): RelativeLayout {
        val relativeLayoutSideSize = dpToPx(16f)
        val relativeLayoutParams =
            RelativeLayout.LayoutParams(relativeLayoutSideSize, relativeLayoutSideSize)

        val newRelativeLayout = RelativeLayout(context)
        newRelativeLayout.layoutParams = relativeLayoutParams
        newRelativeLayout.id = id
        newRelativeLayout.setBackgroundResource(R.drawable.selector_step_item)
        newRelativeLayout.isEnabled = isEnabled

        val newImageView = ImageView(context)
        newImageView.layoutParams = relativeLayoutParams
        newImageView.setImageResource(R.drawable.ic_checkmark)

        newRelativeLayout.addView(newImageView)

        return newRelativeLayout
    }

    fun getDrawableLineView(isEnabled: Boolean): RelativeLayout {
        val relativeLayoutWidthSize = dpToPx(26f)
        val relativeLayoutHeightSize = dpToPx(1f)
        val relativeLayoutPadding = dpToPx(2f)
        val relativeLayoutParams =
            RelativeLayout.LayoutParams(relativeLayoutWidthSize, relativeLayoutHeightSize)
        val imageViewLayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        imageViewLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)

        val newRelativeLayout = RelativeLayout(context)
        newRelativeLayout.layoutParams = relativeLayoutParams
        newRelativeLayout.setPadding(relativeLayoutPadding, 0, relativeLayoutPadding, 0)
        newRelativeLayout.isEnabled = isEnabled

        val newImageView = ImageView(context)
        newImageView.layoutParams = imageViewLayoutParams
        newImageView.setImageResource(R.drawable.selector_step_line)

        newRelativeLayout.addView(newImageView)

        return newRelativeLayout
    }

    private fun dpToPx(dp: Float) = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        context.resources.displayMetrics
    ).toInt()

}


