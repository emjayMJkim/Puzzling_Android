import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import com.puzzling.puzzlingaos.R
import com.puzzling.puzzlingaos.base.BaseFragment
import com.puzzling.puzzlingaos.databinding.FragmentWrite5fBinding
import com.puzzling.puzzlingaos.presentation.writeRetrospective.WriteRetrospectiveViewModel

class Write5fFragment :
    BaseFragment<FragmentWrite5fBinding>(R.layout.fragment_write5f) {
    private val viewModel by activityViewModels<WriteRetrospectiveViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        textInputListener(viewModel.question5f1)
        textInputListener(viewModel.question5f2)
        textInputListener(viewModel.question5f3)
        textInputListener(viewModel.question5f4)
        textInputListener(viewModel.question5f5)

        validBtnEnabled()
    }

    private fun textInputListener(textBox: MutableLiveData<String>) {
        textBox.observe(this) { textBoxString ->
            viewModel.let { viewModel ->
                if (!viewModel.validTextBox(textBoxString)) {
                    when (textBox) {
                        viewModel.question5f1 -> {
                            binding.edtWriteFirstMain.error = ERROR_MESSAGE
                            viewModel.isValid5fquestion1.value = false
                        }
                        viewModel.question5f2 -> {
                            binding.edtWriteSecondMain.error = ERROR_MESSAGE
                            viewModel.isValid5fquestion2.value = false
                        }
                        viewModel.question5f3 -> {
                            binding.edtWriteThirdMain.error = ERROR_MESSAGE
                            viewModel.isValid5fquestion3.value = false
                        }
                        viewModel.question5f4 -> {
                            binding.edtWriteFourthMain.error = ERROR_MESSAGE
                            viewModel.isValid5fquestion4.value = false
                        }
                        viewModel.question5f5 -> {
                            binding.edtWriteFifthMain.error = ERROR_MESSAGE
                            viewModel.isValid5fquestion5.value = false
                        }
                    }
                } else {
                    when (textBox) {
                        viewModel.question5f1 -> {
                            binding.edtWriteFirstMain.error = null
                            viewModel.isValid5fquestion1.value = true
                        }
                        viewModel.question5f2 -> {
                            binding.edtWriteSecondMain.error = null
                            viewModel.isValid5fquestion2.value = true
                        }
                        viewModel.question5f3 -> {
                            binding.edtWriteThirdMain.error = null
                            viewModel.isValid5fquestion3.value = true
                        }
                        viewModel.question5f4 -> {
                            binding.edtWriteFourthMain.error = null
                            viewModel.isValid5fquestion4.value = true
                        }
                        viewModel.question5f5 -> {
                            binding.edtWriteFifthMain.error = null
                            viewModel.isValid5fquestion5.value = true
                        }
                    }
                }
            }
        }
    }

    private fun validBtnEnabled() {
        viewModel.isEnabled5fRegister.observe(this) {
            viewModel.checkBtnEnabled()
        }
    }

    companion object {
        const val ERROR_MESSAGE = "이모지를 사용할 수 없어요"
    }
}