package com.fahad.reminderme;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fahad.reminderme.model.Todo;
import com.fahad.reminderme.util.TimeUtil;

import java.util.ArrayList;

/**
 * Created by moodi on 26/01/2018.
 */

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {


    private ArrayList<Todo> todoArrayList = new ArrayList<>();
    private Context context;


    public TodoAdapter(ArrayList<Todo> todoArrayList, Context context) {
        this.todoArrayList = todoArrayList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.list_layout, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Todo todo = todoArrayList.get(position);

        holder.time.setText(TimeUtil.parseTimeinLong((todo.getTime())));
        holder.title.setText(todo.getTitle());


    }

    @Override
    public int getItemCount() {
        return todoArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView time;


        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title_textview);
            time = itemView.findViewById(R.id.time_textview);
        }
    }
}
