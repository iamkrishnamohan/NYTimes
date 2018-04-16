package com.example.myapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myapplication.R;
import com.example.myapplication.model.ResponseBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class NewsDetailActivity extends AppCompatActivity {
    @BindView(R.id.imageViewDetail)
    CircleImageView imageDetail;

    @BindView(R.id.tvDetailTitle)
    TextView tvNewsDetailTitle;
    @BindView(R.id.tvDetailDescription)
    TextView tvDetailDescription;

    private String newsDetailImageUrl, newsDetailTitle, newsDetailDescription;
    private List<ResponseBean> responseBeanList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent != null) {
            newsDetailTitle = intent.getStringExtra("title");
            newsDetailImageUrl = intent.getStringExtra("Image");
            newsDetailDescription = intent.getStringExtra("Description");
        }
        responseBeanList = new ArrayList<>();
        tvNewsDetailTitle.setText(newsDetailTitle);
        tvDetailDescription.setText(newsDetailDescription);
        if (newsDetailImageUrl != null) {
            Glide.with(this).load(newsDetailImageUrl)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageDetail);
        }
    }
}
