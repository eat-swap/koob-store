package org.eatswap.koobstore.modules.home.ui.dashboard;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.eatswap.koobstore.R;
import org.eatswap.koobstore.modules.book.services.BookService;
import org.eatswap.koobstore.modules.cart.Cart;
import org.eatswap.koobstore.modules.cart.CartService;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private Context context;

    private List<Cart> carts;

    final private BookService bookService;

    final private CartService cartService;

    static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;

        ImageView cartBookCover;

        TextView cartBookTitle;

        TextView cartBookCount;

        Button cartBookRemove;

        Button cartBookAdd;

        public ViewHolder(View view) {
            super(view);
            this.cardView = (CardView) view;
            this.cartBookCover = cardView.findViewById(R.id.cart_book_cover);
            this.cartBookTitle = cardView.findViewById(R.id.cart_book_title);
            this.cartBookCount = cardView.findViewById(R.id.cart_book_count);
            this.cartBookRemove = cardView.findViewById(R.id.cart_book_remove);
            this.cartBookAdd = cardView.findViewById(R.id.cart_book_add);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = View.inflate(context, R.layout.card_cart, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: position: " + position);
        var cart = carts.get(position);
        var book = bookService.findById(cart.getBookId());
        if (book == null) {
            // throw new RuntimeException("Book not found");
        }

        Uri uri = Uri.parse(book.getImageUrl());
        Glide.with(context).load(uri).into(holder.cartBookCover);

        holder.cartBookTitle.setText(book.getTitle());
        holder.cartBookCount.setText(String.format("$%.2f x%d", book.getPrice(), cart.getQuantity()));
        holder.cartBookRemove.setOnClickListener(v -> {
            var removed = cartService.removeItem(cart.getBookId());
            if (removed) {
                carts.remove(cart);
                notifyItemRemoved(position);
            } else {
                cart.setQuantity(cart.getQuantity() - 1);
                notifyItemChanged(position);
            }
        });

        holder.cartBookAdd.setOnClickListener(v -> {
            cartService.addItem(cart.getBookId());
            cart.setQuantity(cart.getQuantity() + 1);
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public CartAdapter(List<Cart> carts, BookService bookService, CartService cartService) {
        this.carts = carts;
        this.bookService = bookService;
        this.cartService = cartService;
    }
}
