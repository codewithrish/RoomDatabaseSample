package com.codewithrish.roomdatabasesample.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.codewithrish.roomdatabasesample.data.db.entity.Task
import com.codewithrish.roomdatabasesample.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val navController: NavController? by lazy { view?.findNavController() }

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tasksObserver()
        addTask()
    }

    private fun addTask() {
        binding.fab.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddUpdateFragment(null);
            navController?.navigate(action)
        }
    }

    private fun tasksObserver() {
        homeViewModel.taskList.observe(viewLifecycleOwner) {
            val adapter = TaskAdapter()
            binding.rvTasks.adapter = adapter
            adapter.submitList(it)

            adapter.deleteTask = { task ->
                homeViewModel.deleteTask(task)
                homeViewModel.getAllTasks()
            }

            adapter.updateTask = { task ->
                val action = HomeFragmentDirections.actionHomeFragmentToAddUpdateFragment(task)
                navController?.navigate(action)
            }
        }
    }

    override fun onResume() {
        homeViewModel.getAllTasks()
        super.onResume()
    }
}