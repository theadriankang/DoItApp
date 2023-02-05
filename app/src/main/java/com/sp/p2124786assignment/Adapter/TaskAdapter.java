package com.sp.p2124786assignment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sp.p2124786assignment.Model.TaskModel;
import com.sp.p2124786assignment.Tasks.OpenTask;
import com.sp.p2124786assignment.R;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {
    private Context context;
    private ArrayList title, points, desc;

    private ArrayList<TaskModel> modelArrayList;

    public TaskAdapter(Context context, ArrayList title, ArrayList points, ArrayList desc) {
        this.context = context;
        this.title = title;
        this.points = points;
        this.desc = desc;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.task_row, parent, false);
        return new MyViewHolder(v);
    }

    private OnItemClickListener mItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return context;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.taskTitle.setText(String.valueOf(title.get(position)));
        holder.taskPoints.setText(String.valueOf(points.get(position)) + " points");
//      holder.taskDesc.setText(String.valueOf(desc.get(position)));

        holder.taskRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OpenTask.class);
                int pos = holder.getAdapterPosition();
                intent.putExtra("Task Name", title.get(pos).toString());
                intent.putExtra("Task Points", points.get(pos).toString());
                intent.putExtra("Task Description", desc.get(pos).toString());

                // In Run
                System.out.println(title.get(pos).toString());
                System.out.println(points.get(pos).toString());
                System.out.println(desc.get(pos).toString());

                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return title.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView taskTitle, taskPoints, taskDesc;
        private CardView taskRow;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            taskTitle = itemView.findViewById(R.id.taskTitle);
            taskPoints = itemView.findViewById(R.id.taskPoints);
            taskDesc = itemView.findViewById(R.id.taskDesc);
            taskRow = itemView.findViewById(R.id.taskrow_CardView);
        }
    }
}
