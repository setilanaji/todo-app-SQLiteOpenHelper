package com.ydh.todoapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ydh.todoapp.R
import com.ydh.todoapp.databinding.ItemTodoBinding
import com.ydh.todoapp.model.TodoModel

class TodoFavAdapter(
        private val context: Context, private val listener: TodoListener
) : RecyclerView.Adapter<TodoFavAdapter.MyViewHolder>() {
    private var favTodoList = mutableListOf<TodoModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: ItemTodoBinding = DataBindingUtil.inflate(inflater,
                R.layout.item_todo,parent,false)
        return MyViewHolder(binding, listener)
    }

    fun setData(item: MutableList<TodoModel>){
        this.favTodoList = item
        notifyDataSetChanged()
    }

    fun updateTodo(todoModel: TodoModel) {
        val index = favTodoList.indexOfFirst { it.id == todoModel.id }
        if (index != -1) {
            favTodoList[index] = todoModel
            notifyItemChanged(index)
        }
    }
    fun deleteTodo(id: Long) {
        val index = favTodoList.indexOfFirst { it.id == id }
        if (index != -1) {
            favTodoList.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    fun getData(position: Int): TodoModel{
        return favTodoList[position]
    }

    fun addTodo(todoModel: TodoModel) {
        favTodoList.add(0, todoModel)
        notifyItemInserted(0)
    }

    interface TodoListener {
        fun onClick(todoModel: TodoModel)
        fun onDelete(id: Long)
        fun onChange(todoModel: TodoModel)
        fun onFavClick(todoModel: TodoModel)
        fun onDelFavClick(todoModel: TodoModel)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemBinding.todo = favTodoList[position]
    }

    override fun getItemCount(): Int {
        return favTodoList.size
    }

    class MyViewHolder(val itemBinding: ItemTodoBinding,
                       private val listener: TodoListener
    ) : RecyclerView.ViewHolder(itemBinding.root){

        private var binding : ItemTodoBinding? = null

        init {
            this.binding = itemBinding
            itemBinding.ivFavTodo.setOnClickListener{
                if (itemBinding.ivFavTodo.isChecked){
                    listener.onFavClick(itemBinding.todo!!)
                }else{
                    listener.onDelFavClick(itemBinding.todo!!)
                }
            }

            itemBinding.ivStatus.setOnClickListener{
                val item = itemBinding.todo!!
                if (itemBinding.ivStatus.isChecked){
                    item.completeStatus = true
                    listener.onChange(item)
                }else{
                    item.completeStatus = false
                    listener.onChange(item)
                }
            }
        }

    }

}