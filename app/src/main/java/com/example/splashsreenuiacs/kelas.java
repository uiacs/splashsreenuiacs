package com.example.splashsreenuiacs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.w3c.dom.Text;

public class kelas extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference kelasRef = db.collection("KELAS");

    private kelasAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelas);

        setUpRecyclerView();
    }

    private void setUpRecyclerView(){
        Query query = kelasRef;

        FirestoreRecyclerOptions<kelasclass> options = new FirestoreRecyclerOptions.Builder<kelasclass>()
                .setQuery(query, kelasclass.class)
                .build();

        adapter = new kelasAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.listKelas);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemCLickListener(new kelasAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String documentId = documentSnapshot.getId();
                kelasclass kelas = documentSnapshot.toObject(com.example.splashsreenuiacs.kelasclass.class);

                Intent intent = new Intent(kelas.this, detailkelas.class);
                intent.putExtra("nama", kelas.getMatkul());
                intent.putExtra("lengkap", kelas.getMatkullengkap());
                intent.putExtra("dosen", kelas.getDosen());
                intent.putExtra("jadwalkelas", kelas.getJadwalkelas());
                intent.putExtra("documentId", documentId);
                startActivity(intent);
            }
        });
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