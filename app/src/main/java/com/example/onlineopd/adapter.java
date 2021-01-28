package com.example.onlineopd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineopd.ui.login.Users;
import com.google.android.material.internal.ThemeEnforcement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> implements Filterable {
    //    String[] data1;
//    int[] images;
//    String[] data2;
    Context context;
    List<Users> list;
    List<Users> listfull;
    private recycleViewClickListener listener;

    public adapter(Context c, List<Users> list, recycleViewClickListener listener) {
        context = c;
//        data1 = s1;
//        images = img;
//        data2 = s2;
        this.list = list;
        this.listener = listener;
        listfull = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.myrows, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView.setText(list.get(position).data);
        holder.imageView.setImageResource(list.get(position).image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public  Filter getFilter() {
        return filterUser;
    }

private  Filter filterUser = new Filter() {
    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        String searchtext = charSequence.toString().toLowerCase();
        List<Users> tempList = new ArrayList<>();
        if (searchtext.isEmpty()) {
            tempList.addAll(listfull);
        } else {
            for (Users items : listfull) {
                if (items.data.toLowerCase().contains(searchtext)) {
                    tempList.add(items);
                }
            }
        }
        FilterResults filterResults = new FilterResults();
        filterResults.values = tempList;
        return filterResults;

    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        list.clear();
        list.addAll((Collection<? extends Users>) filterResults.values);
        notifyDataSetChanged();

    }
};

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        CircleImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txt1);
            imageView = itemView.findViewById(R.id.imagg);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }

//
//        @Override
//        public void onClick(View v) {
//            OnNoteListener.On
//        }
//


    }
    public interface recycleViewClickListener {
        void onClick(View v , int position);

    }

}
