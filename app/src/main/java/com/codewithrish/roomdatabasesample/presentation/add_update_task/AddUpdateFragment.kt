package com.codewithrish.roomdatabasesample.presentation.add_update_task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.codewithrish.roomdatabasesample.R
import com.codewithrish.roomdatabasesample.data.db.entity.Task
import com.codewithrish.roomdatabasesample.databinding.FragmentAddUpdateBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddUpdateFragment : Fragment() {
    private var _binding: FragmentAddUpdateBinding? = null
    private val binding get() = _binding!!

    private val navController: NavController? by lazy { view?.findNavController() }

    private val addUpdateViewModel by viewModels<AddUpdateViewModel>()
    private val args: AddUpdateFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val task = args.task
        if (task != null) {
            binding.btnAddUpdateTask.text = getString(R.string.update_task)
            loadTask(task)
        }
        binding.btnAddUpdateTask.setOnClickListener {
            if (task == null) {
                addTask()
            } else {
                updateTask(task)
            }
        }
    }

    private fun loadTask(task: Task) {
        binding.etTask.setText(task.task)
        when(task.importance) {
            getString(R.string.important) -> binding.rgImportance.check(R.id.rb_important)
            getString(R.string.normal) -> binding.rgImportance.check(R.id.rb_normal)
            getString(R.string.not_important) -> binding.rgImportance.check(R.id.rb_not_important)
        }
    }

    private fun addTask() {
        if (!isTaskEmpty() && !isImportanceChecked()) {
            val rb: RadioButton? = view?.findViewById<RadioButton>(binding.rgImportance.checkedRadioButtonId)
            addUpdateViewModel.addTask(
                Task(
                    task = binding.etTask.text.toString(),
                    importance = rb?.text.toString()
                )
            )
            Toast.makeText(requireContext(), getString(R.string.task_added), Toast.LENGTH_SHORT).show()
            navController?.popBackStack()
        }
    }

    private fun updateTask(task: Task) {
        if (!isTaskEmpty() && !isImportanceChecked()) {
            val rb: RadioButton? = view?.findViewById<RadioButton>(binding.rgImportance.checkedRadioButtonId)
            task.task = binding.etTask.text.toString()
            task.importance = rb?.text.toString()
            addUpdateViewModel.updateTask(task)
            Toast.makeText(requireContext(), getString(R.string.task_updated), Toast.LENGTH_SHORT).show()
            navController?.popBackStack()
        }
    }

    private fun isTaskEmpty(): Boolean {
        if (binding.etTask.text.isNullOrEmpty()) {
            binding.etTask.error = getString(R.string.enter_task)
            binding.etTask.requestFocus()
            return true
        }
        return false
    }

    private fun isImportanceChecked(): Boolean {
        val rb: RadioButton? = view?.findViewById<RadioButton>(binding.rgImportance.checkedRadioButtonId)
        if (rb == null) {
            Toast.makeText(requireContext(), getText(R.string.select_importance), Toast.LENGTH_SHORT).show()
            return true
        }
        return false
    }
}