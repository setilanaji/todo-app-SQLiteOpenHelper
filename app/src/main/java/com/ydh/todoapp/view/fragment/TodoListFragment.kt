package com.ydh.todoapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.ydh.todoapp.TodoContract
import com.ydh.todoapp.data.TodoRepository
import com.ydh.todoapp.data.remote.TodoRemoteRepository
import com.ydh.todoapp.databinding.FragmentTodoListBinding
import com.ydh.todoapp.model.TodoModel
import com.ydh.todoapp.presenter.TodoPresenter
import com.ydh.todoapp.view.TodoAdapter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TodoListFragment : Fragment(), TodoContract.View, TodoAdapter.TodoListener {

    private val adapter by lazy { TodoAdapter(requireContext(), this) }
    lateinit var binding: FragmentTodoListBinding

    private val repository: TodoRepository by lazy {
        TodoRemoteRepository(requireContext())
    }
    private val presenter: TodoContract.Presenter by lazy { TodoPresenter(this, repository) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTodoListBinding.inflate(inflater, container, false)

        setView()
        presenter.getAllTodo()
        setView()
        return binding.root
    }

    private fun setView(){
        binding.run {
            rvTodo.adapter = adapter
            btnAdd.setOnClickListener {
                    presenter.insertTodo(tieTodo.text.toString(), date = getCurrentDate())
            }
        }

    }

    private fun getCurrentDate(): String{
        val date = LocalDateTime.now()
        return date.format(DateTimeFormatter.ofPattern("dd MM yyyy - HH:mm:ss"))
    }

    override fun onSuccessGetAllTodo(todo: List<TodoModel>) {
        val data = todo as MutableList<TodoModel>
        adapter.setData(data)
    }

    override fun onSuccessInsertTodo(todoModel: TodoModel) {
        adapter.addTodo(todoModel)
        binding.tieTodo.setText("")
    }

    override fun onSuccessDeleteTodo(id: Long) {
        adapter.deleteTodo(id)
    }

    override fun onSuccessUpdateTodo(todoModel: TodoModel) {
        adapter.addTodo(todoModel)
        binding.tieTodo.setText("")
    }

    override fun onClick(todoModel: TodoModel) {
        //val action = TodoListFragmentDirections.actionTodoListFragmentToTodoDetailFragment2()
        //findNavController().navigate(action)
    }

    override fun onDelete(id: Long) {
        presenter.deleteTodo(id)
    }

    override fun onChange(todoModel: TodoModel) {
        todoModel.completeStatus = !todoModel.completeStatus
        presenter.updateTodo(todoModel)
    }
}