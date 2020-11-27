package com.ydh.todoapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ydh.todoapp.R
import com.ydh.todoapp.databinding.ItemTodoBinding
import com.ydh.todoapp.model.TodoModel

class TodoAdapter(
    private val context: Context, private val listener: TodoListener
) : RecyclerView.Adapter<TodoAdapter.MyViewHolder>() {
    private var todoList = mutableListOf<TodoModel>()

//    var list = mutableListOf<TodoModel>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: ItemTodoBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.item_todo, parent, false
        )
        return MyViewHolder(binding, listener)
    }

    fun setData(item: MutableList<TodoModel>) {

        this.todoList = item
        notifyDataSetChanged()
    }


    fun addTodo(todoModel: TodoModel) {
        todoList.add(0, todoModel)
        notifyItemInserted(0)
    }

    fun getData(position: Int): TodoModel{
        return todoList[position]
    }

    fun deleteTodo(id: Long) {

        val index = todoList.indexOfFirst { it.id == id }
        if (index != -1) {
            todoList.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    fun updateTodo(todoModel: TodoModel) {
        val index = todoList.indexOfFirst { it.id == todoModel.id }
        if (index != -1) {
            todoList[index] = todoModel
            notifyItemChanged(index)
        }
    }

    interface TodoListener {
        fun onClick(todoModel: TodoModel)
        fun onDelete(id: Long)
        fun onChange(todoModel: TodoModel)
        fun onFavClick(todoModel: TodoModel)
        fun onDelFavClick(todoModel: TodoModel)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemBinding.todo = todoList[position]
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    class MyViewHolder(
        val itemBinding: ItemTodoBinding,
        private val listener: TodoListener
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        private var binding: ItemTodoBinding? = null


        init {
            this.binding = itemBinding
            itemBinding.run {

//                fun bindData(todoModel: TodoModel) {

//                    tvTodo.text = todoModel.task

//                    root.setOnClickListener { listener.onClick(todoModel) }
//                    ivStatus.setImageResource(if (todoModel.completeStatus) R.drawable.ic_done else R.drawable.ic_pause_circle)
//                    ivStatus.setOnClickListener { listener.onChange(todoModel) }

                    itemBinding.ivFavTodo.setOnClickListener{
                        if (itemBinding.ivFavTodo.isChecked){
                            listener.onFavClick(itemBinding.todo!!)
                        }else{
                            listener.onDelFavClick(itemBinding.todo!!)
                        }
                    }

                    itemBinding.ivStatus.setOnClickListener{
                        val item = itemBinding.todo
                        if (itemBinding.ivStatus.isChecked){
                            item!!.completeStatus = true
                            listener.onChange(item)
                        }else{
                            item!!.completeStatus = false
                            listener.onChange(item)
                        }
                    }

                }
//            }
        }

    }

}