package com.example.customeasy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull; // Corrected import
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {

    private final Context context;
    private final List<CustomerModel> customerList;

    public CustomerAdapter(Context context, List<CustomerModel> customerList) {
        this.context = context;
        this.customerList = customerList;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.customer_item_layout, parent, false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        CustomerModel customer = customerList.get(position);

        holder.idText.setText(context.getString(R.string.customer_id, customer.getId()));
        holder.nameText.setText(context.getString(R.string.customer_name, customer.getName()));
        holder.addressText.setText(context.getString(R.string.customer_address, customer.getAddress()));
        holder.phoneText.setText(context.getString(R.string.customer_phone, customer.getPhone()));
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public static class CustomerViewHolder extends RecyclerView.ViewHolder {
        TextView idText, nameText, addressText, phoneText;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            idText = itemView.findViewById(R.id.idText);
            nameText = itemView.findViewById(R.id.nameText);
            addressText = itemView.findViewById(R.id.addressText);
            phoneText = itemView.findViewById(R.id.phoneText);
        }
    }
}
