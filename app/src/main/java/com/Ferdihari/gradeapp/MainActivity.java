package com.Ferdihari.gradeapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText inputNIM, inputNama, inputPresensi, inputTugas, inputUTS, inputUAS;
    private RadioGroup semesterGroup;
    private Button hitungNilaiButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNIM = findViewById(R.id.inputNIM);
        inputNama = findViewById(R.id.inputNama);
        inputPresensi = findViewById(R.id.inputPresensi);
        inputTugas = findViewById(R.id.inputTugas);
        inputUTS = findViewById(R.id.inputUTS);
        inputUAS = findViewById(R.id.inputUAS);
        semesterGroup = findViewById(R.id.semesterGroup);
        hitungNilaiButton = findViewById(R.id.hitungNilaiButton);

        hitungNilaiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hitungNilaiAkhir();
            }
        });
    }

    private void hitungNilaiAkhir() {
        String nim = inputNIM.getText().toString().trim();
        String nama = inputNama.getText().toString().trim();
        String presensiStr = inputPresensi.getText().toString().trim();
        String tugasStr = inputTugas.getText().toString().trim();
        String utsStr = inputUTS.getText().toString().trim();
        String uasStr = inputUAS.getText().toString().trim();

        // Validasi input kosong
        if (TextUtils.isEmpty(nim) || TextUtils.isEmpty(nama) || TextUtils.isEmpty(presensiStr)
                || TextUtils.isEmpty(tugasStr) || TextUtils.isEmpty(utsStr) || TextUtils.isEmpty(uasStr)
                || semesterGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Seluruh data wajib diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int presensi = Integer.parseInt(presensiStr);
            int tugas = Integer.parseInt(tugasStr);
            int uts = Integer.parseInt(utsStr);
            int uas = Integer.parseInt(uasStr);

            // Validasi nilai dalam rentang 10-100
            if (!isValidScore(presensi) || !isValidScore(tugas) || !isValidScore(uts) || !isValidScore(uas)) {
                Toast.makeText(this, "Nilai tidak boleh lebih kecil dari 10 dan tidak boleh lebih besar dari 100", Toast.LENGTH_SHORT).show();
                return;
            }

            // Hitung nilai akhir dan tentukan grade
            double nilaiAkhir = (presensi * 0.1) + (tugas * 0.2) + (uts * 0.3) + (uas * 0.4);
            String grade = calculateGrade(nilaiAkhir);

            // Mendapatkan semester yang dipilih
            int selectedSemesterId = semesterGroup.getCheckedRadioButtonId();
            RadioButton selectedSemester = findViewById(selectedSemesterId);
            String semester = selectedSemester.getText().toString();

            // Mulai ResultActivity dan kirim data
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("nim", nim);
            intent.putExtra("nama", nama);
            intent.putExtra("semester", semester);
            intent.putExtra("nilaiAkhir", nilaiAkhir);
            intent.putExtra("grade", grade);
            startActivity(intent);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Input nilai harus berupa angka", Toast.LENGTH_SHORT).show();
        }
    }

    // Fungsi validasi nilai dalam rentang 10-100
    private boolean isValidScore(int score) {
        return score >= 10 && score <= 100;
    }

    // Fungsi untuk menghitung grade
    private String calculateGrade(double nilaiAkhir) {
        if (nilaiAkhir >= 85) return "A";
        else if (nilaiAkhir >= 70) return "B";
        else if (nilaiAkhir >= 50) return "C";
        else return "D";
    }
}