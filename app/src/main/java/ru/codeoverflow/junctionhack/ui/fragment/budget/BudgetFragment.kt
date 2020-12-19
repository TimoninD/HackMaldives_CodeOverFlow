package ru.codeoverflow.junctionhack.ui.fragment.budget

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_budget.*
import ru.codeoverflow.junctionhack.ui.common.BaseFragment
import ru.codeoverflow.junctionhack.R
import ru.codeoverflow.junctionhack.ui.RubberRangePicker

class BudgetFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_budget

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNext.setOnClickListener {

        }

        seekBarPrice.setOnRubberRangePickerChangeListener(object :
            RubberRangePicker.OnRubberRangePickerChangeListener {
            override fun onProgressChanged(
                rangePicker: RubberRangePicker,
                startValue: Int,
                endValue: Int,
                fromUser: Boolean
            ) {
                tvPriceRange.text = getString(R.string.price_range, startValue, endValue)
            }

            override fun onStartTrackingTouch(
                rangePicker: RubberRangePicker,
                isStartThumb: Boolean
            ) {
                //Nothing to do
            }

            override fun onStopTrackingTouch(
                rangePicker: RubberRangePicker,
                isStartThumb: Boolean
            ) {
                //Nothing to do
            }

        })
    }
}