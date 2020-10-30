package com.ironlabs;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ironlabs.norris.R;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> categoryList;
    private Context context;

    private OnCategorySelected onCategorySelected;

    public CategoriesAdapter(List<String> categoryList) {
        this.categoryList = categoryList;
    }

    public void setOnCategorySelected(OnCategorySelected onCategorySelected) {
        this.onCategorySelected = onCategorySelected;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View root = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_category,viewGroup,false);
        context = viewGroup.getContext();

        return new CategoryViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        CategoryViewHolder vh = (CategoryViewHolder)viewHolder;
        vh.bind(categoryList.get(i));
    }

    @Override
    public int getItemCount() {
        return categoryList.size() !=0 ? categoryList.size() : 0;
    }


    class CategoryViewHolder extends RecyclerView.ViewHolder{

        private View item;
        private Button txtCategory;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView;
            txtCategory = item.findViewById(R.id.category);

            txtCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onCategorySelected!=null){
                        onCategorySelected.onCategorySelected(categoryList.get(getAdapterPosition()));
                    }
                }
            });

        }

        public void bind(String category){
            txtCategory.setText(category);
        }

    }

    public interface OnCategorySelected{
        void onCategorySelected(String category);
    }

}
