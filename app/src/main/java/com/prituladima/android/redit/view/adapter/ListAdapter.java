package com.prituladima.android.redit.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.prituladima.android.redit.R;
import com.prituladima.android.redit.model.dto.ArticleDTO;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.prituladima.android.redit.BuildConfig.BASE_URL;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<ArticleDTO> articles;
    private Context context;

    public ListAdapter(Context context) {
        this.context = context;
        articles = new ArrayList<>();
    }

    public void setData(List<ArticleDTO> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }

    public void addData(List<ArticleDTO> articles) {
        this.articles.addAll(articles);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ArticleDTO current = articles.get(position);
        holder.titleTextView.setText(current.title());
        holder.authorTextView.setText(context.getResources().getString(R.string.author_, current.author()));
        holder.subredditTextView.setText(context.getResources().getString(R.string.subreddit_, current.subreddit()));
        holder.thumbImageView.setImageDrawable(null);
        ImageLoader.getInstance().displayImage(current.thumbnail(), holder.thumbImageView);
        holder.listItem.setOnClickListener((view) -> new CustomTabsIntent.Builder().build().launchUrl(context, Uri.parse(BASE_URL + current.url())));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView)
        ImageView thumbImageView;

        @BindView(R.id.text_title)
        TextView titleTextView;

        @BindView(R.id.text_author)
        TextView authorTextView;

        @BindView(R.id.text_subreddit)
        TextView subredditTextView;

        @BindView(R.id.card_view)
        CardView listItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}