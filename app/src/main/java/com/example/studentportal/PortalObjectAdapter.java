package com.example.studentportal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PortalObjectAdapter extends RecyclerView.Adapter<PortalObjectViewHolder> {

    private List<PortalObject> mPortalObjects;

    public PortalObjectAdapter(List<PortalObject> mPortalObjects){
        this.mPortalObjects = mPortalObjects;
    }

    //Inflates the recyclerView with items. uses grid_cell as layout per item
    @NonNull
    @Override
    public PortalObjectViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_cell, viewGroup, false);
        PortalObjectViewHolder viewHolder = new PortalObjectViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PortalObjectViewHolder portalObjectViewHolder, int i) {
        PortalObject portalObject = mPortalObjects.get(i);
        portalObjectViewHolder.portalName.setText(portalObject.getmPortalName());
    }


    @Override
    public int getItemCount() {
        return mPortalObjects.size();
    }
}
