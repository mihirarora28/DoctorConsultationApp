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

import de.hdodenhof.circleimageview.CircleImageView;

public class adapterCardio extends RecyclerView.Adapter<adapterCardio.ViewHolder> {
    String[] data1;
    int[] images;
    String[] data2;
    String[] data3;
    String[] data4;
    Context context;
    private recycleViewClickListener listener;
    public adapterCardio(Context c, String[] s1, String[] s2,String[] s3, String[] s4, int[] img, recycleViewClickListener listener)
    {
        context = c;
        data1 = s1;
        images = img;
        data2 = s2;
        data3 = s3;
        data4 = s4;
        this.listener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.doctors_profile,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(data1[position]);
        holder.imageView.setImageResource(images[position]);
        holder.textView2.setText(data2[position]);
        holder.textView3.setText(data3[position]);
        holder.textView4.setText(data4[position]);

    }

    @Override
    public int getItemCount() {
        return images.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        CircleImageView imageView;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textCardio1);
            imageView = itemView.findViewById(R.id.imagescardioDoctor);
            textView2 = itemView.findViewById(R.id.textCardio2);
            textView3 = itemView.findViewById(R.id.textCardio3);
            textView4 = itemView.findViewById(R.id.textCardio4);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }


    }
    public interface recycleViewClickListener {
        void onClick(View v , int position);

    }

}
