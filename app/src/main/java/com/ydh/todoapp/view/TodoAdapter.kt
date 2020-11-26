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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: ItemTodoBinding = DataBindingUtil.inflate(inflater,
                R.layout.item_todo,parent,false)
        return MyViewHolder(binding, listener)
    }

    fun setData(item: MutableList<TodoModel>){
        println(item)
        println("set Data")
        this.todoList = item
        notifyDataSetChanged()
    }


    fun addTodo(todoModel: TodoModel) {
        todoList.add(0, todoModel)
        notifyItemInserted(0)
    }

    interface TodoListener {
        fun onClick(todoModel: TodoModel)
        fun onDelete(id: Long)
        fun onChange(todoModel: TodoModel)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemBinding.todo = todoList[position]
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    class MyViewHolder(val itemBinding: ItemTodoBinding,
                       private val listener: TodoListener) : RecyclerView.ViewHolder(itemBinding.root){

        private var binding : ItemTodoBinding? = null

        init {
            this.binding = itemBinding
        }

    }

}