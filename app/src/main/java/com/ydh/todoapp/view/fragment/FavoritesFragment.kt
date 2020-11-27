package com.ydh.todoapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.ydh.todoapp.R
import com.ydh.todoapp.TodoContract
import com.ydh.todoapp.data.TodoRepository
import com.ydh.todoapp.data.local.TodoLocalRepository
import com.ydh.todoapp.data.remote.TodoClient
import com.ydh.todoapp.data.remote.TodoRemoteRepository
import com.ydh.todoapp.data.remote.TodoService
import com.ydh.todoapp.databinding.FragmentFavoritesBinding
import com.ydh.todoapp.model.TodoModel
import com.ydh.todoapp.presenter.TodoPresenter
import com.ydh.todoapp.util.SwipeToDelete
import com.ydh.todoapp.view.TodoAdapter
import com.ydh.todoapp.view.TodoFavAdapter

class FavoritesFragment : Fragment(), TodoContract.View,  TodoFavAdapter.TodoListener {

    private val binding by lazy { FragmentFavoritesBinding.inflate(layoutInflater) }
    private val adapter by lazy { TodoFavAdapter(requireContext(), this) }
    private val repository: TodoRepository by lazy { TodoRemoteRepository(requireContext()) }
    private val offlineRepository: TodoRepository by lazy { TodoLocalRepository(requireContext()) }
    private val presenter: TodoContract.Presenter by lazy { TodoPresenter(this, repository) }
    private val offlinePresenter: TodoContract.Presenter by lazy { TodoPresenter(
        this,
        offlineRepository
    ) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.run {
            rvFavorite.adapter = adapter
            val swipeHandler = object : SwipeToDelete(requireContext()) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val adapter = rvFavorite.adapter as TodoAdapter
                    val pos = viewHolder.adapterPosition
                    val item = adapter.getData(pos)
                    presenter.deleteTodo(item.id)

                }
            }
            val itemTouchHelper = ItemTouchHelper(swipeHandler)
            itemTouchHelper.attachToRecyclerView(rvFavorite)
        }


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        offlinePresenter.getAllFavTodo()
    }

    override fun onSuccessGetAllTodo(todo: List<TodoModel>) {
        TODO("Not yet implemented")
    }

    override fun onSuccessInsertTodo(todoModel: TodoModel) {
        TODO("Not yet implemented")
    }

    override fun onSuccessDeleteTodo(id: Long) {
        adapter.deleteTodo(id)
    }

    override fun onSuccessUpdateTodo(todoModel: TodoModel) {
        todoModel.favoriteStatus = true
        adapter.updateTodo(todoModel)
    }

    override fun onSuccessGetAllFavTodo(todo: List<TodoModel>) {
        adapter.setData(todo.toMutableList())
    }

    override fun onSuccessDeleteFavTodo(id: Long) {
        Toast.makeText(context, "task has been deleted from favorite", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessInsertFavTodo(todoModel: TodoModel) {
        TODO("Not yet implemented")
    }

    override fun onClick(todoModel: TodoModel) {
        TODO("Not yet implemented")
    }

    override fun onDelete(id: Long) {
        TODO("Not yet implemented")
    }

    override fun onChange(todoModel: TodoModel) {
        presenter.updateTodo(todoModel)
        offlinePresenter.updateFavTodo(todoModel)
    }

    override fun onFavClick(todoModel: TodoModel) {
        todoModel.favoriteStatus = true
        offlinePresenter.insertFavTodo(todoModel)
    }

    override fun onDelFavClick(todoModel: TodoModel) {
        offlinePresenter.deleteFavTodo(todoModel)
        onSuccessDeleteTodo(todoModel.id)
    }
}