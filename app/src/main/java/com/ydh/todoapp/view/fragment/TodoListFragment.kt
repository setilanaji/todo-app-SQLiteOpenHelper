package com.ydh.todoapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ydh.todoapp.R
import com.ydh.todoapp.TodoContract
import com.ydh.todoapp.data.TodoRepository
import com.ydh.todoapp.data.remote.TodoRemoteRepository
import com.ydh.todoapp.databinding.FragmentTodoListBinding
import com.ydh.todoapp.model.TodoModel
import com.ydh.todoapp.presenter.TodoPresenter
import com.ydh.todoapp.view.TodoAdapter

class TodoListFragment : Fragment(), TodoContract.View {

    private val adapter by lazy { TodoAdapter(requireContext()) }
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
        presenter.getAllTodo()

        binding.rvTodo.adapter = adapter
        return binding.root
    }

    override fun onSuccessGetAllTodo(todo: List<TodoModel>) {
        val data = todo as MutableList<TodoModel>
adapter.setData(data)

    }

    override fun onSuccessInsertTodo(todoModel: TodoModel) {
        TODO("Not yet implemented")
    }

    override fun onSuccessDeleteTodo(id: Long) {
        TODO("Not yet implemented")
    }

    override fun onSuccessUpdateTodo(todoModel: TodoModel) {
        TODO("Not yet implemented")
    }
}