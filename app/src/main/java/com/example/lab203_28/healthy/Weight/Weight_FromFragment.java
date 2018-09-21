package com.example.lab203_28.healthy.Weight;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab203_28.healthy.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Weight_FromFragment extends Fragment {

    FirebaseFirestore _firestore;
    FirebaseAuth _auth;



    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight_from, container, false);
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        _firestore = FirebaseFirestore.getInstance();
        _auth = FirebaseAuth.getInstance();


        initbackBtn();
        initrecBtn();


    }


    void initbackBtn(){
        TextView _backBtn = (TextView) getView().findViewById(R.id.weight_from_backBnt);
        _backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("USER", "ฺBACK GOTO WEIGHT");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new WeightFragment()).commit();
            }
        });
    }
    void initrecBtn(){
        Button _recBtn = (Button) getView().findViewById(R.id.weight_from_recBnt);
        _recBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                EditText _date = (EditText) getView().findViewById(R.id.weight_from_date);
                EditText _weight = (EditText) getView().findViewById(R.id.weight_from_weight);
                String _dateStr = _date.getText().toString();
                String _weightStr = _weight.getText().toString();

                if (_weightStr.isEmpty() || _dateStr.isEmpty()){
                    Log.d("USER", "EMPTY_WEIGHT_DATE");

                    Toast.makeText(
                            getActivity(),
                            "กรุณาระบุ WEIGHT or DATE",
                            Toast.LENGTH_SHORT
                    ).show();
                }
                else {
                    int _weightInt = Integer.parseInt(_weightStr);

                    String _uid = _auth.getCurrentUser().getUid();
                    Weight _data = new Weight(_dateStr,_weightInt,"UP");

                    _firestore.collection("myfitness")
                            .document(_uid)
                            .collection("weight")
                            .document(_dateStr)
                            .set(_data)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("USER", "RECORD_WEIGHT");

                                    Toast.makeText(
                                            getActivity(),
                                            "SAVE",
                                            Toast.LENGTH_SHORT
                                    ).show();
                                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new WeightFragment()).commit();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(
                                    getActivity(),
                                    "ERROR = " + e.getMessage(),
                                    Toast.LENGTH_SHORT
                            ).show();
                            Log.d("USER", "ERROR = " + e.getMessage());
                        }
                    });
                }

            }
        });
    }
}
