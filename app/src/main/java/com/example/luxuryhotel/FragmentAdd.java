package com.example.luxuryhotel;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class FragmentAdd extends Fragment {

    TextInputEditText inputNo, inputNama, inputAlamat, inputKamar, inputCheckIn, inputCheckOut;
    Button addBtn, cancelBtn;
    Pemesanan pemesanan;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TextInputEditText tvDateResult;
    AutoCompleteTextView autoCompleteTextView;

    public FragmentAdd() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_add, container, false);
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
        addBtn = view.findViewById(R.id.btn_add);
        cancelBtn = view.findViewById(R.id.btn_cancel);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        tvDateResult = view.findViewById(R.id.input_checkin);

        pemesanan = new Pemesanan();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextInputLayout inputNoLayout = view.findViewById(R.id.input_number_layout);
        final TextInputLayout inputNamaLayout = view.findViewById(R.id.input_nama_layout);
        final TextInputLayout inputAlamatLayout = view.findViewById(R.id.input_alamat_layout);
        final TextInputLayout inputKamarLayout = view.findViewById(R.id.input_kamar_layout);
        final TextInputLayout inputCheckInLayout = view.findViewById(R.id.input_checkin_layout);
        final TextInputLayout inputCheckOutLayout = view.findViewById(R.id.input_checkout_layout);

        tvDateResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String no = String.valueOf(inputNo.getText());
                String nama = String.valueOf(inputNama.getText());
                String alamat = String.valueOf(inputAlamat.getText());
                String kamar = String.valueOf(autoCompleteTextView.getText());
                String checkin = String.valueOf(inputCheckIn.getText());
                String checkout = String.valueOf(inputCheckOut.getText());

                if(no.isEmpty() || nama.isEmpty()||alamat.isEmpty()||kamar.isEmpty()||checkin.isEmpty()||checkout.isEmpty()){
                    inputNoLayout.setError("Harap isi no identitas dengan benar");
                    inputNamaLayout.setError("Harap isi nama dengan benar.");
                    inputAlamatLayout.setError("Harap isi alamat dengan benar.");
                    inputKamarLayout.setError("Harap isi kamar dengan benar.");
                    inputCheckInLayout.setError("Harap isi check in sewa ");
                    inputCheckOutLayout.setError("Harap isi check out dengan benar.");
                }else{
                    pemesanan.setNumber(inputNo.getText().toString());
                    pemesanan.setFullName(inputNama.getText().toString());
                    pemesanan.setAlamat(inputAlamat.getText().toString());
                    pemesanan.setPilihankamar(autoCompleteTextView.getText().toString());
                    pemesanan.setCheckin(inputCheckIn.getText().toString());
                    pemesanan.setCheckout(inputCheckOut.getText().toString());
                    insert(pemesanan);
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(FragmentAdd.this).commit();
            }
        });
    }

    private void insert(final Pemesanan pemesanan){
        class InsertEmp extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                DatabaseClient.getInstance(getActivity().getApplicationContext()).getDatabase()
                        .employeeDao()
                        .insert(pemesanan);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getActivity().getApplicationContext(), "Data Pemesanan Telah Tersimpan", Toast.LENGTH_SHORT).show();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(FragmentAdd.this).commit();
            }
        }

        InsertEmp insert = new InsertEmp();
        insert.execute();
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