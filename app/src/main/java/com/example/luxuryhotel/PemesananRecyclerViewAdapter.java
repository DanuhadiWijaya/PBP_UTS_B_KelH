package com.example.luxuryhotel;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PemesananRecyclerViewAdapter extends RecyclerView.Adapter <PemesananRecyclerViewAdapter.EmployeeViewHolder> {

    private Context context;
    public List<Pemesanan> pemesananList;
    public List<Pemesanan> pemesananFull = new ArrayList<>();

    public PemesananRecyclerViewAdapter(Context context, List<Pemesanan> pemesananList)
    {
        this.context=context;
        this.pemesananList = pemesananList;
        pemesananFull.addAll(pemesananList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.item_employee, parent, false);
        return new EmployeeViewHolder(view);
    }

    public void onBindViewHolder (@NonNull EmployeeViewHolder holder, int position )
    {
        Pemesanan pemesanan = pemesananList.get(position);
        holder.txtNumber.setText("No Identitas               : " + pemesanan.getNumber());
        holder.txtName.setText("Nama                          : " + pemesanan.getFullName());
        holder.txtAlamat.setText("Alamat                        : " + pemesanan.getAlamat());
        holder.txtPilihanKamar.setText("Pilihan Kamar            : " + pemesanan.getPilihankamar());
        holder.txtCheckIn.setText("Tanggal Check In      : " + pemesanan.getCheckin());
        holder.txtCheckOut.setText("Tanggal Check Out   : " + pemesanan.getCheckout());
    }

    @Override
    public int getItemCount() { return pemesananList.size(); }

    public Filter getFilter() { return filterEmployee;}

    public class EmployeeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView txtNumber, txtName, txtAlamat, txtPilihanKamar, txtCheckIn, txtCheckOut;
        public EmployeeViewHolder(@NonNull View itemView)
        {
            super(itemView);
            txtNumber = itemView.findViewById(R.id.number_text);
            txtName = itemView.findViewById(R.id.full_name_text);
            txtAlamat = itemView.findViewById(R.id.alamat_text);
            txtPilihanKamar = itemView.findViewById(R.id.pilihankamar_text);
            txtCheckIn = itemView.findViewById(R.id.checkin_text);
            txtCheckOut = itemView.findViewById(R.id.checkout_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            Pemesanan pemesanan = pemesananList.get(getAdapterPosition());
            Bundle data = new Bundle();
            data.putSerializable("employee", pemesanan);
            FragmentUpdate fragmentUpdate = new FragmentUpdate();
            fragmentUpdate.setArguments(data);
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, fragmentUpdate)
                    .commit();
        }
    }

    private Filter filterEmployee = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Pemesanan> filterList = new ArrayList<>();

            if(charSequence == null || charSequence.length() == 0) {
                filterList.addAll(pemesananFull) ;
            }
            else {
                String pattern = charSequence.toString().toLowerCase().trim();

                for(Pemesanan Emp : pemesananFull) {
                    if(Emp.getFullName().toLowerCase().contains(pattern))
                        filterList.add(Emp);
                }
            }

            FilterResults results = new FilterResults();
            results.values = filterList;

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            pemesananList.clear();

            pemesananList.addAll((List<Pemesanan>) filterResults.values);
            notifyDataSetChanged();
        }
    };
}
