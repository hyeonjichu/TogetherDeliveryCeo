package com.example.togetherdeliveryceo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ListviewHoler>{

    Context context;
    ArrayList<OrderModel> orderModelArrayList;

    public OrderAdapter(Context context, ArrayList<OrderModel> orderModelArrayList) {
        this.context = context;
        this.orderModelArrayList = orderModelArrayList;

    }

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    @NonNull
    @Override
    public OrderAdapter.ListviewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.order_list, parent,false);

        return new ListviewHoler(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ListviewHoler holder, int position) {

        OrderModel OrderModel = orderModelArrayList.get(position);

        holder.orderId.setText(OrderModel.orderId);
    }

    @Override
    public int getItemCount() {
        return orderModelArrayList.size();
    }

    public class ListviewHoler extends RecyclerView.ViewHolder{

        TextView orderId, orderTime,price,store;

        Button storeBtn;

        public ListviewHoler(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.list_menuName);
            //orderTime = itemView.findViewById(R.id.list_orderTime);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {
                        if (mListener != null) {
                            mListener.onItemClick(v, pos);
                        }
                    }
                }
            });
        }
    }
}

