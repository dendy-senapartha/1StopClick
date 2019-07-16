package com.a1stopclick.transaction;

import android.view.*;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.a1stopclick.R;
import com.domain.order.OrderResult;
import java.util.List;

/**
 * Created by dendy.prtha
 */

public class TransactionRecyclerViewAdapter extends RecyclerView.Adapter<TransactionItemViewHolder> {

    List<OrderResult> items;
    Fragment parentFragment;

    public TransactionRecyclerViewAdapter(List<OrderResult> items, Fragment fragment) {
        this.items = items;
        this.parentFragment = fragment;
    }

    @Override
    public TransactionItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_transaction_item,
                parent, false);
        return new TransactionItemViewHolder(view, parentFragment);
    }

    @Override
    public void onBindViewHolder(TransactionItemViewHolder holder, int position) {
        holder.bind(items.get(position));
        if (parentFragment != null) {

        }
    }

    public void setItems( List<OrderResult> items)
    {
        this.items.clear();
        this.items = items;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
