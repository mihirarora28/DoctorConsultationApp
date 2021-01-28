package com.example.onlineopd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.internal.ThemeEnforcement;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class historyclass extends RecyclerView.Adapter<historyclass.ViewHolder> {

    ArrayList<String>ans;
    Context context;
    public historyclass(Context c, ArrayList<String> cd)
    {
        context = c;
        ans = cd;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.historylayout,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String cd = ans.get(position);
        String ans = "";
        int ctr= 0 ;
        for(int i=0;i<cd.length();i++)
        {
            if(cd.charAt(i)!=',')
            ans= ans + cd.charAt(i);
            else
            {
             ctr++;
             if(ctr==4)
             {

                 holder.action.setText(holder.action.getText() + ans);

                 ans="";
                 continue;
             }
             if(ctr==2)
             {
                 holder.date.setText(holder.date.getText() + ans);

                 ans="";
                 continue;
             }
             if(ctr==1)
             {
                 holder.day.setText(holder.day.getText() + ans);
                 ans="";
                 continue;
             }
             if(ctr==3)
             {
                 holder.time.setText(holder.time.getText() + ans);
                 ans="";
                 continue;
             }
            }
        }
    }



    @Override
    public int getItemCount() {
        return ans.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView time;

        TextView date;
        TextView day;
        TextView action;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
            day = itemView.findViewById(R.id.day);
            date = itemView.findViewById(R.id.date);
            action = itemView.findViewById(R.id.action);

        }

    }

}
