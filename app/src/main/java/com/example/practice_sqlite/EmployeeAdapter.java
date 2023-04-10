package com.example.practice_sqlite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.MyViewHolder> {

    Context context;
    ArrayList<EmployeeEntity> employeelist;


    public EmployeeAdapter() {
    }

    public EmployeeAdapter(Context context, ArrayList<EmployeeEntity> employeelist) {
        this.context = context;
        this.employeelist = employeelist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.employee_raw, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        EmployeeEntity emp = this.employeelist.get(position);

        holder.eid.setText(String.valueOf(emp.getEid()));
        holder.ename.setText(emp.getEname());
        holder.salary.setText(String.valueOf(emp.getSalary()));

        SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",
                Locale.ENGLISH);
        holder.datetime.setText("Entry Date >>" + sdf.format(emp.getCreated_At()));

        holder.delRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int id = holder.getAdapterPosition();
                DBhandler database = new DBhandler(context);
                database.deleteEntry(id);
                employeelist.remove(id);
                notifyItemRemoved(id);
                notifyDataSetChanged();
                database.close();
            }
        });
    }


    @Override
    public int getItemCount() {
        return employeelist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView eid, ename, salary, datetime;
        ImageView delRecord,updateRecord;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.eid = itemView.findViewById(R.id.eid);
            this.ename = itemView.findViewById(R.id.ename);
            this.salary = itemView.findViewById(R.id.salary);
            this.datetime = itemView.findViewById(R.id.datetime);
            this.delRecord = itemView.findViewById(R.id.delRecord);

        }
    }


}
