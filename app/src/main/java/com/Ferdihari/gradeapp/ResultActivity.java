package com.Ferdihari.gradeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    private TextView txtNIM, txtNama, txtMatkul, txtSemester, txtNilaiAkhir, txtGrade;
    private Button btnKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        txtNIM = findViewById(R.id.txtNIM);
        txtNama = findViewById(R.id.txtNama);
        txtMatkul = findViewById(R.id.txtMatkul);
        txtSemester = findViewById(R.id.txtSemester);
        txtNilaiAkhir = findViewById(R.id.txtNilaiAkhir);
        txtGrade = findViewById(R.id.txtGrade);
        btnKembali = findViewById(R.id.btnKembali);

        // Mendapatkan data dari Intent
        Intent intent = getIntent();
        String nim = intent.getStringExtra("nim");
        String nama = intent.getStringExtra("nama");
        String semester = intent.getStringExtra("semester");
        double nilaiAkhir = intent.getDoubleExtra("nilaiAkhir", 0);
        String grade = intent.getStringExtra("grade");

        // Menampilkan data di TextView
        txtNIM.setText("NIM : " + nim);
        txtNama.setText("Nama : " + nama);
        txtMatkul.setText("Matkul : Mobile Programming");
        txtSemester.setText("Semester : " + semester);
        txtNilaiAkhir.setText("Nilai Akhir : " + nilaiAkhir);
        txtGrade.setText("Grade : " + grade);

        // Tombol kembali ke MainActivity
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}