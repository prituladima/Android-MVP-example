package com.prituladima.android.redit.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.prituladima.android.redit.R;
import com.prituladima.android.redit.databinding.ItemListBinding;
import com.prituladima.android.redit.model.dto.ArticleDTO;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import static com.prituladima.android.redit.BuildConfig.BASE_URL;

public class RedditAdapter extends RecyclerView.Adapter<RedditAdapter.ViewHolder> {


    private List<ArticleDTO> articles;
    private Context context;

    public RedditAdapter(Context context) {
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

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ArticleDTO current = articles.get(position);
        holder.titleTextView.setText(current.title());
        holder.authorTextView.setText(context.getResources().getString(R.string.author_, current.author()));
        holder.subredditTextView.setText(context.getResources().getString(R.string.subreddit_, current.subreddit()));
        long time = Double.valueOf(current.created_utc()).longValue() * 1000;
        holder.text_time.setText(new PrettyTime(new Locale("en")).format(new Date(time)));

        holder.imageView.setImageDrawable(null);
        ImageLoader.getInstance().displayImage(current.thumbnail(), holder.imageView);
        holder.listItem.setOnClickListener((view) -> new CustomTabsIntent.Builder().build().launchUrl(context, Uri.parse(BASE_URL + current.permalink())));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView titleTextView;
        TextView authorTextView;
        TextView subredditTextView;
        TextView text_time;
        CardView listItem;

        public ViewHolder(ItemListBinding binding) {
            super(binding.getRoot());
            imageView = binding.imageView;
            titleTextView = binding.textTitle;
            authorTextView = binding.textAuthor;
            subredditTextView = binding.textSubreddit;
            text_time = binding.textTime;
            listItem = binding.cardView;
        }
    }
}