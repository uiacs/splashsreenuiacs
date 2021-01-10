package com.example.splashsreenuiacs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class tugas extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference tugasRef = db.collection("TUGAS");

    private tugasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tugas);

        setUpRecyclerView();
    }

    private void setUpRecyclerView(){
        Query query = tugasRef.whereEqualTo("tugasmateri", "TUGAS");

        FirestoreRecyclerOptions<detailkelasclass> options = new FirestoreRecyclerOptions.Builder<detailkelasclass>()
                .setQuery(query, detailkelasclass.class)
                .build();

        adapter = new tugasAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.tugasRecyclerView);
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