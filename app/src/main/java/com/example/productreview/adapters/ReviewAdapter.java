package com.example.productreview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.productreview.R;
import com.example.productreview.models.Review;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder>{

    private List<Review> mReviews;
    private Context context;

    public ReviewAdapter(List<Review> mReviews) {
        this.mReviews = mReviews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.review_row, viewGroup, false);
        context = viewGroup.getContext();
        return new ReviewAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Review review = mReviews.get(i);
        viewHolder.mTitle.setText(review.getTitle());
        viewHolder.mRating.setText(review.getRating());
        viewHolder.mSystem.setText(review.getReviewSystem());
        viewHolder.mReviewerName.setText(review.getReviewerName());
        viewHolder.mLink.setText(review.getReadLink());
        viewHolder.mPostTime.setText(review.getPostTime());
        viewHolder.mBody.setText(review.getBody());
        viewHolder.mPluses.setText(StringUtils.join(review.getPluses(), ','));
        viewHolder.mMinuses.setText(StringUtils.join(review.getMinuses(), ','));

    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }


    public class  ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.review_title)
        TextView mTitle;
        @BindView(R.id.reviewer_name)
        TextView mReviewerName;
        @BindView(R.id.review_rating)
        TextView mRating;
        @BindView(R.id.review_system)
        TextView mSystem;
        @BindView(R.id.review_post_time)
        TextView mPostTime;
        @BindView(R.id.review_body)
        TextView mBody;
        @BindView(R.id.review_pluses)
        TextView mPluses;
        @BindView(R.id.review_minuses)
        TextView mMinuses;
        @BindView(R.id.review_link)
        TextView mLink;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
