package org.eatswap.koobstore.modules.home.ui.notifications;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.eatswap.koobstore.R;
import org.eatswap.koobstore.modules.book.services.BookService;
import org.eatswap.koobstore.modules.orders.data.Order;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private Context context;

    private List<Order> orders;

    final private BookService bookService;

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView orderBookCover;

        TextView orderBasicInfo;

        Button buttonOrderDetails;

        public ViewHolder(View view) {
            super(view);
            this.orderBookCover = view.findViewById(R.id.order_book_cover);
            this.orderBasicInfo = view.findViewById(R.id.order_basic_info);
            this.buttonOrderDetails = view.findViewById(R.id.button_order_details);
        }
    }

    public OrderAdapter(List<Order> orders, BookService bookService) {
        this.orders = orders;
        this.bookService = bookService;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = context == null ? parent.getContext() : context;

        View view = View.inflate(context, R.layout.card_order, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        var order = orders.get(position);

        var imgUrl = order.getItems().get(0).getFirst().getImageUrl();
        var uri = Uri.parse(imgUrl);
        Glide.with(context).load(uri).into(holder.orderBookCover);

        final var basicInfoStr = String.format(
                "$%.2f\nTotal %d items",
                order.getTotalAmount(),
                order.getItems().size()
        );
        holder.orderBasicInfo.setText(basicInfoStr);

        holder.buttonOrderDetails.setOnClickListener(v -> {
            Toast.makeText(context, "Order details " + order.getId(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

}
