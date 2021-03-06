package com.example.contacts.presentation.steps

import android.content.Context
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.contacts.R
import com.example.contacts.databinding.FragmentContactBinding
import com.example.contacts.databinding.FragmentStepStatusLineBinding
import com.example.contacts.presentation.base_adapter.BaseItem
import com.example.contacts.presentation.base_adapter.BaseViewHolder

class StepsViewHolder(
    private val context: Context,
    private val quantitySteps: Int,
    private val lastStepIsDrawable: Boolean = false
) :
    BaseViewHolder {

    override val viewType: Int
        get() = R.layout.fragment_step_status_line

    override fun getViewHolder(parent: ViewGroup): BaseViewHolder.ViewHolder {
        val binding =
            FragmentStepStatusLineBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return object : BaseViewHolder.ViewHolder(binding.root){
        override fun bind(item: BaseItem) {
            val stepStatusLine = itemView.findViewById<LinearLayout>(R.id.step_status_line)
            val stepItem = item as Step

            if (stepStatusLine.childCount > 0) {
                recycleStepItemViews(stepStatusLine, stepItem.stepId)
            } else {
                addStepItemViews(stepStatusLine, stepItem.stepId)
            }
        }
        }
    }

    private fun recycleStepItemViews(stepStatusLine: LinearLayout, stepId: Int) {
        for (i in 0 until stepStatusLine.childCount) {
            val stepStatusLineChild = stepStatusLine.getChildAt(i)
            if (stepStatusLineChild.id <= stepId) {
                stepStatusLineChild.isEnabled = true
            } else if (stepStatusLineChild.id in (stepId + 1)..999) {
                stepStatusLineChild.isEnabled = false
            } else if (stepStatusLineChild.id > 1000) {
                stepStatusLineChild.isEnabled = (stepStatusLineChild.id - 1000) <= stepId
            }
        }
    }

    private fun addStepItemViews(stepStatusLine: LinearLayout, stepId: Int) {
        for (i in 0 until quantitySteps) {
            val stepIsEnabled = stepId >= i
            if (lastStepIsDrawable && i == quantitySteps - 1) {
                stepStatusLine.addView(getDrawableLineView((i + 1000), stepIsEnabled))
                stepStatusLine.addView(getDrawableStepItemView(i, stepIsEnabled))
            } else {
                if (i != 0) {
                    stepStatusLine.addView(getDrawableLineView((i + 1000), stepIsEnabled))
                }
                stepStatusLine.addView(getTextStepItemView(i, stepIsEnabled))
            }
        }
    }

    private fun getTextStepItemView(
        id: Int,
        isEnabled: Boolean
    ): RelativeLayout {
        val relativeLayoutSideSize = context.resources.getDimension(R.dimen.default_side_size)
        val relativeLayoutParams =
            RelativeLayout.LayoutParams(
                relativeLayoutSideSize.toInt(),
                relativeLayoutSideSize.toInt()
            )
        val textViewLayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        val newRelativeLayout = RelativeLayout(context)
        val newTextView = TextView(context) //, null, R.style.step_id)
        val stepNumber = id + 1

        textViewLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)

        newRelativeLayout.layoutParams = relativeLayoutParams
        newRelativeLayout.id = id
        newRelativeLayout.setBackgroundResource(R.drawable.selector_step_item)
        newRelativeLayout.isEnabled = isEnabled

        newTextView.layoutParams = textViewLayoutParams
        newTextView.text = stepNumber.toString()
        newTextView.setLineSpacing(
            context.resources.getDimension(R.dimen.current_line_spacing),
            context.resources.getDimension(R.dimen.default_line_spacing)
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            newTextView.setTextAppearance(R.style.step_id)
        } else {
            newTextView.setTextAppearance(context, R.style.step_id)
        }

        newRelativeLayout.addView(newTextView)

        return newRelativeLayout
    }

    private fun getDrawableStepItemView(
        id: Int,
        isEnabled: Boolean
    ): RelativeLayout {
        val relativeLayoutSideSize = context.resources.getDimension(R.dimen.default_side_size)
        val relativeLayoutParams =
            RelativeLayout.LayoutParams(
                relativeLayoutSideSize.toInt(),
                relativeLayoutSideSize.toInt()
            )
        val newRelativeLayout = RelativeLayout(context)
        val newImageView = ImageView(context)

        newRelativeLayout.layoutParams = relativeLayoutParams
        newRelativeLayout.id = id
        newRelativeLayout.setBackgroundResource(R.drawable.selector_step_item)
        newRelativeLayout.isEnabled = isEnabled

        newImageView.layoutParams = relativeLayoutParams
        newImageView.setImageResource(R.drawable.ic_checkmark)

        newRelativeLayout.addView(newImageView)

        return newRelativeLayout
    }

    private fun getDrawableLineView(id: Int, isEnabled: Boolean): RelativeLayout {
        val relativeLayoutWidthSize = context.resources.getDimension(R.dimen.drawable_line_width)
        val relativeLayoutHeightSize = context.resources.getDimension(R.dimen.default_side_size)
        val relativeLayoutPadding = context.resources.getDimension(R.dimen.default_padding_size)
        val relativeLayoutParams =
            RelativeLayout.LayoutParams(
                relativeLayoutWidthSize.toInt(),
                relativeLayoutHeightSize.toInt()
            )
        val imageViewLayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        val newRelativeLayout = RelativeLayout(context)
        val newImageView = ImageView(context)

        imageViewLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)

        newRelativeLayout.layoutParams = relativeLayoutParams
        newRelativeLayout.gravity = Gravity.CENTER
        newRelativeLayout.id = id
        newRelativeLayout.isEnabled = isEnabled
        newRelativeLayout.setPadding(
            relativeLayoutPadding.toInt(),
            0,
            relativeLayoutPadding.toInt(),
            0
        )

        newImageView.layoutParams = imageViewLayoutParams
        newImageView.setImageResource(R.drawable.selector_step_line)

        newRelativeLayout.addView(newImageView)

        return newRelativeLayout
    }

//    private fun dpToPx(dp: Float) = TypedValue.applyDimension(
//        TypedValue.COMPLEX_UNIT_DIP,
//        dp,
//        context.resources.displayMetrics
//    ).toInt()

}
