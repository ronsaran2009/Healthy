package com.example.lab203_28.healthy.Weight;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lab203_28.healthy.BMIFragment;
import com.example.lab203_28.healthy.R;
import com.example.lab203_28.healthy.Weight.Weight_FromFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class WeightFragment extends Fragment{

    ArrayList<Weight> weights = new ArrayList<>();
    FirebaseFirestore _firestore;
    FirebaseAuth _auth;
    String _uid;
    WeightAdaptor _weightAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight, container, false);
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        _firestore = FirebaseFirestore.getInstance();
        _auth = FirebaseAuth.getInstance();

        _uid  = _auth.getUid();
        getWeightData();
        initAdd_WeightBtn();
        ListView _weightList = (ListView) getView().findViewById(R.id.weight_listview);
        _weightAdapter = new WeightAdaptor(
                getActivity(),
                R.layout.weight_,
                weights
        );
        _weightList.setAdapter(_weightAdapter);






//        weights.add(new Weight("1 june 18", 29, "up"));
//        weights.add(new Weight("2 june 18", 21, "up"));
//        weights.add(new Weight("3 june 18", 26, "up"));







    }
    void getWeightData(){_firestore.collection("myfitness")
            .document(_uid)
            .collection("weight")
            .get()
            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot documentSnapshots) {
                    for (QueryDocumentSnapshot doc : documentSnapshots ) {
                        weights.add(doc.toObject(Weight.class));
                        _weightAdapter.notifyDataSetChanged();
                    }
                }

            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("USER", "ERROR : "+ e.getMessage());
                }
            });
    }



    void initAdd_WeightBtn(){
        TextView _add_weightBtn = (TextView) getView().findViewById(R.id.weight_add_weightbnt);
        _add_weightBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("USER", "GOTO ADD_WEIGHT");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new Weight_FromFragment()).commit();
            }
        });
    }
}
