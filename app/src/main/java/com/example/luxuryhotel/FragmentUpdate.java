package com.example.luxuryhotel;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FragmentUpdate extends Fragment {

    TextInputEditText inputNo, inputNama, inputAlamat, inputCheckIn, inputCheckOut;
    Button saveBtn, deleteBtn, cancelBtn;
    Pemesanan pemesanan;
    AutoCompleteTextView autoCompleteTextView;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TextInputEditText tvDateResult;

    public FragmentUpdate() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_update, container, false);
        pemesanan = (Pemesanan) getArguments().getSerializable("employee");
        inputNo = view.findViewById(R.id.input_number);
        inputNama = view.findViewById(R.id.input_nama);
        inputAlamat = view.findViewById(R.id.input_alamat);
        inputCheckIn = view.findViewById(R.id.input_checkin);
        inputCheckOut = view.findViewById(R.id.input_checkout);
        String[] choose = {"Kings Rooms" , "Diamond Rooms" , "Premium Rooms" , "Gold Rooms" , "Silver Rooms",
                "Bronze Rooms", "Double Bed Rooms", "Meeting Rooms"};
        autoCompleteTextView = view.findViewById(R.id.input_kamar);

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.dropdownitem, choose);
        autoCompleteTextView.setAdapter(arrayAdapter);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        tvDateResult = view.findViewById(R.id.input_checkin);
        saveBtn = view.findViewById(R.id.btn_update);
        deleteBtn = view.findViewById(R.id.btn_delete);
        cancelBtn = view.findViewById(R.id.btn_cancel);

        try {
            if (pemesanan.getNumber() != null &&pemesanan.getFullName() != null && pemesanan.getAlamat() != null &&
                    pemesanan.getPilihankamar() != null && pemesanan.getCheckin() != null  && pemesanan.getCheckout() != null) {
                inputNo.setText(pemesanan.getNumber());
                inputNama.setText(pemesanan.getFullName());
                inputAlamat.setText(pemesanan.getAlamat());
                inputCheckIn.setText(pemesanan.getCheckin());
                inputCheckOut.setText(pemesanan.getCheckout());
            } else {
                inputNo.setText("-");
                inputNama.setText("-");
                inputAlamat.setText("-");
                inputCheckIn.setText("-");
                inputCheckOut.setText("-");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvDateResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pemesanan.setNumber(inputNo.getText().toString());
                pemesanan.setFullName(inputNama.getText().toString());
                pemesanan.setAlamat(inputAlamat.getText().toString());
                pemesanan.setPilihankamar(autoCompleteTextView.getText().toString());
                pemesanan.setCheckin(inputCheckIn.getText().toString());
                pemesanan.setCheckout(inputCheckOut.getText().toString());
                update(pemesanan);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(FragmentUpdate.this).commit();
            }
        });
    }

    private void update(final Pemesanan pemesanan){
        class UpdatePenyewa extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                DatabaseClient.getInstance(getActivity().getApplicationContext()).getDatabase()
                        .employeeDao()
                        .update(pemesanan);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getActivity().getApplicationContext(), "Data Pemesanan berhasil diperbarui", Toast.LENGTH_SHORT).show();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(FragmentUpdate.this).commit();
            }
        }

        UpdatePenyewa update = new UpdatePenyewa();
        update.execute();
    }

    private void delete(final Pemesanan pemesanan){
        class DeletePenyewa extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                DatabaseClient.getInstance(getActivity().getApplicationContext()).getDatabase()
                        .employeeDao()
                        .delete(pemesanan);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getActivity().getApplicationContext(), "Data Pemesanan Telah Terhapus", Toast.LENGTH_SHORT).show();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(FragmentUpdate.this).commit();
            }
        }

        DeletePenyewa delete = new DeletePenyewa();
        delete.execute();
    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setMessage("Apakah Yakin Untuk Menghapus Data ini");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                delete(pemesanan);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void showDateDialog(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                tvDateResult.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
}