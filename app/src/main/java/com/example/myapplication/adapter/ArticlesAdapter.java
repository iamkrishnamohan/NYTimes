package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.activities.NewsDetailActivity;
import com.example.myapplication.model.ResponseBean;

import java.util.ArrayList;
import java.util.List;


public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder> {

    private Context mContext;
    private List<ResponseBean> responseList;

    public ArticlesAdapter(Context context, List<ResponseBean> responseList) {
        this.mContext = context;
        this.responseList = new ArrayList<>(responseList);
    }

    @NonNull
    @Override
    public ArticlesAdapter.ArticlesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.articles_list_item, null);

        return new ArticlesAdapter.ArticlesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticlesAdapter.ArticlesViewHolder holder, int position) {

        final ResponseBean responseBean = responseList.get(position);
        holder.tvArticleTitle.setText(responseBean.getTitle());
        holder.tvArticleDesc.setText(responseBean.getDescription());
        holder.tvArticleDate.setText(responseBean.getDate());

        Glide.with(mContext)
                .load(responseBean.getImage())
                .centerCrop()
                .into(holder.imageArticle);

        holder.newsItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                bundle.putString("title", responseBean.getTitle());
                bundle.putString("Description",responseBean.getDescription());
                bundle.putString("Image",responseBean.getImage());
                Intent intent = new Intent(mContext, NewsDetailActivity.class);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return responseList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public class ArticlesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageArticle;
        private TextView tvArticleTitle, tvArticleDesc, tvArticleDate;
private RelativeLayout newsItem;

        public ArticlesViewHolder(View itemView) {
            super(itemView);
            imageArticle = (ImageView) itemView.findViewById(R.id.thumbnail);
            tvArticleTitle = (TextView) itemView.findViewById(R.id.title);
            tvArticleDesc = (TextView) itemView.findViewById(R.id.genre);
            tvArticleDate = (TextView) itemView.findViewById(R.id.releaseYear);
            newsItem=(RelativeLayout)itemView.findViewById(R.id.news_list_item);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
