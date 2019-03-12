package com.example.studentportal;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class PortalObjectViewHolder extends RecyclerView.ViewHolder {

    public TextView portalName;
    public View view;

    public PortalObjectViewHolder(View view) {
        super(view);
        portalName = itemView.findViewById(R.id.portalText);
        this.view = view;
    }
}
