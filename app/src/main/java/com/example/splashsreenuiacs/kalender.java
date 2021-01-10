package com.example.splashsreenuiacs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class kalender extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference agendaRef = db.collection("KELAS");

    private agendaAdapter adapter;

    CalendarView calender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalender);

        calender = (CalendarView) findViewById(R.id.calender);

        setUpRecyclerView();
    }

    private void setUpRecyclerView(){
        Query query = agendaRef;

        FirestoreRecyclerOptions<kelasclass> options = new FirestoreRecyclerOptions.Builder<kelasclass>()
                .setQuery(query, kelasclass.class)
                .build();

        adapter = new agendaAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recyclerviewAgenda);
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