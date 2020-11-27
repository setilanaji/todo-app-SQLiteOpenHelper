package com.ydh.todoapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.ydh.todoapp.TodoContract
import com.ydh.todoapp.data.TodoRepository
import com.ydh.todoapp.data.local.TodoLocalRepository
import com.ydh.todoapp.data.remote.TodoRemoteRepository
import com.ydh.todoapp.databinding.FragmentTodoListBinding
import com.ydh.todoapp.model.TodoModel
import com.ydh.todoapp.presenter.TodoPresenter
import com.ydh.todoapp.util.SwipeToDelete
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

    private val offlineRepository: TodoRepository by lazy { TodoLocalRepository(requireContext()) }
    private val offlinePresenter: TodoContract.Presenter by lazy {
        TodoPresenter(
            this,
            offlineRepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTodoListBinding.inflate(inflater, container, false)

//        setView()
//        presenter.getAllTodo()
        setView()
        return binding.root
    }

    private fun setView(){
        binding.run {
            rvTodo.adapter = adapter

            val swipeHandler = object : SwipeToDelete(requireContext()) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val adapter = rvTodo.adapter as TodoAdapter
                    val pos = viewHolder.adapterPosition
                    val item = adapter.getData(pos)
                    presenter.deleteTodo(item.id)
                    onDelFavClick(item)

                }
            }

            val itemTouchHelper = ItemTouchHelper(swipeHandler)
            itemTouchHelper.attachToRecyclerView(rvTodo)

            btnAdd.setOnClickListener {
                    presenter.insertTodo(tieTodo.text.toString(), date = getCurrentDate())
            }
        }

    }

    override fun onResume() {
        super.onResume()
        val todo = offlinePresenter.getAllFavTodo()
        println("fav $todo")

        presenter.getAllTodo(todo)
    }

    private fun getCurrentDate(): String{
        val date = LocalDateTime.now()
        return date.format(DateTimeFormatter.ofPattern("dd MM yyyy - HH:mm:ss"))
    }

    override fun onSuccessGetAllTodo(todo: List<TodoModel>) {
        println("get alll todo $todo")
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
//        adapter.addTodo(todoModel)
//        binding.tieTodo.setText("")
        println("On success updaate $todoModel")

        adapter.updateTodo(todoModel)

    }

    override fun onSuccessGetAllFavTodo(todo: List<TodoModel>) {

    }

    override fun onSuccessDeleteFavTodo(id: Long) {
        Toast.makeText(context, "task has been deleted from favorite", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessInsertFavTodo(todoModel: TodoModel) {
        Toast.makeText(context, "new task has been added to fav", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(todoModel: TodoModel) {
        //val action = TodoListFragmentDirections.actionTodoListFragmentToTodoDetailFragment2()
        //findNavController().navigate(action)
    }

    override fun onDelete(id: Long) {
        presenter.deleteTodo(id)
    }

    override fun onChange(todoModel: TodoModel) {
//        todoModel.completeStatus = !todoModel.completeStatus
//        presenter.updateTodo(todoModel)
        println("check onchange $todoModel")

        presenter.updateTodo(todoModel)
        offlinePresenter.updateFavTodo(todoModel)
    }

    override fun onFavClick(todoModel: TodoModel) {
        todoModel.favoriteStatus = true
        offlinePresenter.insertFavTodo(todoModel)
    }

    override fun onDelFavClick(todoModel: TodoModel) {
        offlinePresenter.deleteFavTodo(todoModel)
    }
}