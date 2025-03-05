package com.example.week6_recyc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {
    private List<Person> kisiListesi;

    public PersonAdapter(List<Person> kisiListesi) {
        this.kisiListesi = kisiListesi;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person kisi = kisiListesi.get(position);
        holder.textView.setText(kisi.getName());
    }

    @Override
    public int getItemCount() {
        return kisiListesi.size();
    }

    static class PersonViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}
