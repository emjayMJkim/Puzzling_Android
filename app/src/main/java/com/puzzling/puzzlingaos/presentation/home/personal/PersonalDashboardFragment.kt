package com.puzzling.puzzlingaos.presentation.home.personal

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.databinding.FragmentPersonalDashboardBinding
import com.puzzling.puzzlingaos.presentation.writeRetrospective.WriteRetrospectiveActivity

class PersonalDashboardFragment :
    BaseFragment<FragmentPersonalDashboardBinding>(R.layout.fragment_personal_dashboard) {
    private val viewModel by viewModels<PersonalDashboardViewModel>()

    private var _actionPlanAdapter: ActionPlanListAdapter? = null
    private val actionPlanAdapter
        get() = requireNotNull(_actionPlanAdapter) { "adapter is null !!" }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionPlanAdapter()
        clickBottomBtn()
        clickMyPuzzleBoardBtn()
    }

    private fun setActionPlanAdapter() {
        _actionPlanAdapter = ActionPlanListAdapter()
        _actionPlanAdapter?.submitList(viewModel.actionPlanList)
        binding.rcvPersonalView.also {
            it.adapter = _actionPlanAdapter
        }
    }

    private fun clickBottomBtn() {
        binding.clPersonalBottomBtn.setOnClickListener {
            activity?.let {
                val intent = Intent(context, WriteRetrospectiveActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun clickMyPuzzleBoardBtn() {
        binding.clPersonalTopBackground.setOnClickListener {
            activity?.let {
                val intent = Intent(context, MyPuzzleBoardActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
