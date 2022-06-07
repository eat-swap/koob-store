package org.eatswap.koobstore.modules.home.ui.home;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

        Button detailsButton;

        public ViewHolder(View view) {
            super(view);
            this.cardView = (CardView) view;
            this.imageView = cardView.findViewById(R.id.book_cover);
            this.titleView = cardView.findViewById(R.id.book_title);
            this.priceView = cardView.findViewById(R.id.book_price);
            this.detailsButton = cardView.findViewById(R.id.book_details);
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
        Log.d(TAG, "onBindViewHolder: position: " + position);
        var book = books.get(position);
        holder.titleView.setText(book.getTitle());
        holder.priceView.setText(String.format("%.2f", book.getPrice()));

        holder.detailsButton.setOnClickListener(v -> {
            Toast.makeText(context, "Details for " + book.getTitle(), Toast.LENGTH_SHORT).show();

            var intent = new Intent(context, BookDetailsActivity.class);
            intent.putExtra("book_id", book.getId());
            context.startActivity(intent);
        });

        Uri uri = Uri.parse(book.getImageUrl());
        Glide.with(context).load(uri).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}
