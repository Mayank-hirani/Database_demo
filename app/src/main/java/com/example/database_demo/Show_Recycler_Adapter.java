package com.example.database_demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Show_Recycler_Adapter extends RecyclerView.Adapter<Show_Recycler_Adapter.ShowrecycleHolder> {
    Show_Activity show_activity;
    ArrayList<Integer> idList;
    ArrayList<String> nameList;
    ArrayList<String> emailList;
    public Show_Recycler_Adapter(Show_Activity show_activity, ArrayList<Integer> idList, ArrayList<String> nameList, ArrayList<String> emailList) {
        this.show_activity = show_activity;
        this.idList = idList;
        this.nameList = nameList;
        this.emailList = emailList;

    }


    @NonNull
    @Override
    public Show_Recycler_Adapter.ShowrecycleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(show_activity).inflate(R.layout.activity_show_item,parent,false);
        ShowrecycleHolder holder = new ShowrecycleHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Show_Recycler_Adapter.ShowrecycleHolder holder, int position) {
        holder.textView1.setText(""+idList.get(position));
        holder.textView2.setText(""+nameList.get(position));
        holder.textView3.setText(""+emailList.get(position));

        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MydataBase mydataBase=new MydataBase(show_activity);
                mydataBase.updateData(idList.get(holder.getAdapterPosition()),nameList.get(holder.getAdapterPosition()),emailList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return idList.size();//idList.toArray().length;
    }

    public class ShowrecycleHolder extends RecyclerView.ViewHolder {
        TextView textView1,textView2,textView3;
        ImageButton more;
        public ShowrecycleHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.show_id_item);
            textView2 = itemView.findViewById(R.id.show_name_item);
            textView3 = itemView.findViewById(R.id.show_email_item);
            more = itemView.findViewById(R.id.more);


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    MydataBase mydataBase=new MydataBase(show_activity);
                    mydataBase.deleteData(idList.get(getAdapterPosition()));
                    notifyDataSetChanged();

                    return true;
                }
            });
        }
    }
}