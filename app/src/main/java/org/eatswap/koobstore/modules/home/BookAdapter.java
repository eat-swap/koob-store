package org.eatswap.koobstore.modules.home;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.eatswap.koobstore.R;
import org.eatswap.koobstore.modules.book.data.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private Context context;

    private List<Book> books;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView titleView;
        TextView priceView;

        public ViewHolder(View view) {
            super(view);
            this.cardView = (CardView) view;
            this.imageView = cardView.findViewById(R.id.book_cover);
            this.titleView = cardView.findViewById(R.id.book_title);
            this.priceView = cardView.findViewById(R.id.book_price);
        }
    }

    public BookAdapter(List<Book> books) {
        this.books = books;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = View.inflate(context, R.layout.card_book, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        var book = books.get(position);
        holder.titleView.setText(book.getTitle());
        holder.priceView.setText(String.format("%.2f", book.getPrice()));

        Uri uri = Uri.parse(book.getImageUrl());
        Glide.with(context).load(uri).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}
