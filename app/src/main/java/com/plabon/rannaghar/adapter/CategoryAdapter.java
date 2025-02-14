package com.plabon.rannaghar.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.plabon.rannaghar.activity.ProductActivity;
import com.plabon.rannaghar.R;
import com.plabon.rannaghar.model.Category;
import com.plabon.rannaghar.util.Utils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    List<Category> categoryList;
    Context context;
    String Tag;

    public CategoryAdapter(List<Category> categoryList, Context context) {
        this.categoryList = categoryList;
        this.context = context;
    }

    public CategoryAdapter(List<Category> categoryList, Context context, String tag) {
        this.categoryList = categoryList;
        this.context = context;
        Tag = tag;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView;
        if (Tag.equalsIgnoreCase("Home")) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_home_category, parent, false);
        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_category, parent, false);
           // itemView = LayoutInflater.from(parent.getContext())
             //       .inflate(R.layout.row_home_category, parent, false);
        }


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        final Category category = categoryList.get(position);
        holder.title.setText(category.getCategry());

        Animation animation = AnimationUtils.loadAnimation(context,
                R.anim.left_enter);

        Log.d("Category Image ==>", Utils.CategoryImage + category.getCateimg());
        if (Tag.equalsIgnoreCase("Category")) {
            Picasso.get()
                    .load(Utils.CategoryImage + category.getCateimg())
                    .into(holder.imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            holder.progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.d("Error : ", e.getMessage());
                        }
                    });
        }
        if (Tag.equalsIgnoreCase("Home")) {
            Picasso.get()
                    .load(Utils.CategoryImage +  category.getCateimg())
                    .into(holder.imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            holder.progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.d("Error : ", e.getMessage());
                        }
                    });
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra("category", category.getCategry());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });
        if (Tag.equalsIgnoreCase("Category")) {
            holder.cardView.startAnimation(animation);
        }
    }

    @Override
    public int getItemCount() {
        if (Tag.equalsIgnoreCase("Home") && categoryList.size() < 6 && categoryList.size() > 3) {
            //return 3;
           return categoryList.size();
        } else if (Tag.equalsIgnoreCase("Home") && categoryList.size() >= 6) {
            //return 6;
           return categoryList.size();
        } else {
            return categoryList.size();
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        ProgressBar progressBar;
        LinearLayout cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.category_image);
            title = itemView.findViewById(R.id.category_title);
            progressBar = itemView.findViewById(R.id.progressbar);
            cardView = itemView.findViewById(R.id.linearCategoryHome);
        }
    }
}
