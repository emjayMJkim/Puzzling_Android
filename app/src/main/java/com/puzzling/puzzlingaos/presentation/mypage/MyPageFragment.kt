package com.puzzling.puzzlingaos.presentation.mypage

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.data.model.response.ResponseMyPageProjectDto
import com.puzzling.puzzlingaos.databinding.FragmentMyPageBinding
import com.puzzling.puzzlingaos.domain.entity.Project
import com.puzzling.puzzlingaos.presentation.home.personal.PersonalDashboardViewModel
import com.puzzling.puzzlingaos.presentation.mypage.adapter.MyProjectBottomAdapter
import com.puzzling.puzzlingaos.presentation.mypage.adapter.MyProjectContentAdapter
import com.puzzling.puzzlingaos.presentation.mypage.adapter.MyProjectTitleAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private lateinit var viewModel: MyRetrospectViewModel
    private val personalViewModel by activityViewModels<PersonalDashboardViewModel>()

    private val dummyItemList = mutableListOf<ResponseMyPageProjectDto>(
        ResponseMyPageProjectDto("Piickle", "2023-07-03", 2),
        ResponseMyPageProjectDto("HARA", "2023-07-28", 3),
        ResponseMyPageProjectDto("낫투두", "2023-07-12", 4),
        ResponseMyPageProjectDto("PEEKABOOK", "2023-07-20", 5),
        ResponseMyPageProjectDto("킵고잇", "2023-06-25", 8),
        ResponseMyPageProjectDto("킵고잇", "2023-06-25", 8),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MyRetrospectViewModel::class.java]
        viewModel.getMyProjectList()
        viewModel.getProjectWeekCycle()
        initAdapter()
        showPopupMessage()

        viewModel.retroWeek.observe(this) {
            binding.tvMypagePopupContent.text = "매주 $it \n 회고를 작성해주세요"
        }
    }

    private fun initAdapter() {
        val myProjectContentAdapter = MyProjectContentAdapter()
        val myProjectTitleAdapter = MyProjectTitleAdapter()
        myProjectTitleAdapter.submitList(listOf(personalViewModel.myNickname.value ?: "김민주"))
        val concatAdapter =
            ConcatAdapter(
                myProjectTitleAdapter,
                myProjectContentAdapter,
                MyProjectBottomAdapter(),
            )

        with(binding) {
            rcvMyPageMain.adapter = concatAdapter
            rcvMyPageMain.layoutManager = LinearLayoutManager(activity)
        }

        viewModel.responseProjectList.observe(this) {
            myProjectContentAdapter.submitList(it)
        }

        myProjectContentAdapter.setOnItemClickListener(object :
            MyProjectContentAdapter.OnItemClickListener {
            override fun onItemClick(v: View, data: Project, pos: Int) {
                viewModel.setCurrentProject(data.projectName)
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fcv_main_container, MyRetrospectFragment()).addToBackStack(null)
                    .commit()
            }
        })
    }

    private fun showPopupMessage() {
        binding.btnHomeNotification.setOnClickListener {
            val isCardVisible = binding.cvMypagePopup.isVisible
            binding.cvMypagePopup.isVisible = !isCardVisible
        }
    }
}
