package com.example.splashsreenuiacs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class detailkelas extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference kelasRef = db.collection("TUGAS");

    private detailkelasAdapter adapter;

    TextView matkul, matkullengkap, dosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailkelas);

        matkul = findViewById(R.id.namamatkul);
        matkullengkap = findViewById(R.id.detailmatkullengkap);
        dosen = findViewById(R.id.namadosen);

        matkul.setText(getIntent().getStringExtra("nama"));
        matkullengkap.setText(getIntent().getStringExtra("lengkap"));
        dosen.setText(getIntent().getStringExtra("dosen"));

        setUpRecyclerView();
    }

    private void setUpRecyclerView(){
        Query query = FirebaseFirestore.getInstance().collection("TUGAS").whereEqualTo("documentId", getIntent().getStringExtra("documentId"));

        FirestoreRecyclerOptions<detailkelasclass> options = new FirestoreRecyclerOptions.Builder<detailkelasclass>()
                .setQuery(query, detailkelasclass.class)
                .build();

        adapter = new detailkelasAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.tugasRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}