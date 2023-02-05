package com.sp.p2124786assignment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sp.p2124786assignment.Database.GoalDBHelper;
import com.sp.p2124786assignment.R;
import com.sp.p2124786assignment.Tasks.OpenTask;

import java.util.ArrayList;

public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.MyViewHolder>{

    Context context;
    ArrayList goalName, goalPoint;

//    ArrayList<Bitmap> goalPhoto;


    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public GoalAdapter(Context context, ArrayList goalName, ArrayList goalPoint) {
        this.context = context;
        this.goalName = goalName;
        this.goalPoint = goalPoint;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public GoalAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_goal, parent, false);
        MyViewHolder evh = new MyViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        holder.goalTitle.setText(String.valueOf(goalName.get(position)));
        holder.goalPoints.setText(String.valueOf(goalPoint.get(position)));

        holder.goalrow_CardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, OpenTask.class);
//                int pos = holder.getAdapterPosition();
//                intent.putExtra("Task Name", title.get(pos).toString());
//                intent.putExtra("Task Points", points.get(pos).toString());
//                intent.putExtra("Task Description", desc.get(pos).toString());
//
//                // In Run
//                System.out.println(title.get(pos).toString());
//                System.out.println(points.get(pos).toString());
//                System.out.println(desc.get(pos).toString());
//
//                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return goalName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView goalTitle, goalPoints;
        ImageView goalImage;
        CardView goalrow_CardView;


        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            goalTitle = itemView.findViewById(R.id.goalTitle);
            goalPoints = itemView.findViewById(R.id.goalPoints);
            goalImage = itemView.findViewById(R.id.goalImage);
            goalrow_CardView = itemView.findViewById(R.id.goalrow_CardView);
        }
    }
}
