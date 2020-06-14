package com.example.productreview.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.productreview.R;
import com.example.productreview.activity.ProductActivity;
import com.example.productreview.models.Product;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> mProducts;
    private Context context;

    public ProductAdapter(List<Product> products) {
        this.mProducts = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_row, viewGroup, false);
        context = viewGroup.getContext();
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Product product = mProducts.get(i);
        viewHolder.mName.setText(product.getName());
        viewHolder.mRating.setText(product.getRating());
        Glide.with(context).load(product.getImageUrl()).into(viewHolder.mImageView);
        viewHolder.mDescription.setText(product.getDescription());
        viewHolder.mAveragePrice.setText(product.getAveragePrice());
        viewHolder.mCreateBy.setText(product.getCreatedBy());
        viewHolder.mLastModifiedBy.setText(product.getLastModifiedBy());
        viewHolder.mCreateTime.setText(product.getCreateTime());
        viewHolder.mLastModifyTime.setText(product.getLastModifyTime());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra("productId", product.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public void addItems(List<Product> products) {
        this.mProducts.addAll(products);
        notifyDataSetChanged();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.product_name)
        TextView mName;
        @BindView(R.id.product_description)
        TextView mDescription;
        @BindView(R.id.product_average_price)
        TextView mAveragePrice;
        @BindView(R.id.product_image_view)
        ImageView mImageView;
        @BindView(R.id.product_rating)
        TextView mRating;
        @BindView(R.id.product_create_by)
        TextView mCreateBy;
        @BindView(R.id.product_last_modified_by)
        TextView mLastModifiedBy;
        @BindView(R.id.product_create_time)
        TextView mCreateTime;
        @BindView(R.id.product_last_modify_time)
        TextView mLastModifyTime;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
