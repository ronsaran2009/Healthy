package com.example.lab203_28.healthy;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by LAB203_28 on 20/8/2561.
 */

public class BMIFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ){
        return inflater.inflate(R.layout.fragment_bmi, container, false);
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initCalBtn();
    }
    void initCalBtn(){
        Button _calBtn = (Button) getView().findViewById(R.id.bmi_cal);
        _calBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText _height = (EditText) getView().findViewById(R.id.bmi_height);
                String _heightStr = _height.getText().toString();
                EditText _weight = (EditText) getView().findViewById(R.id.bmi_weight);
                String _weightStr = _weight.getText().toString();
                if (_heightStr.isEmpty() || _weightStr.isEmpty()){
                    Toast.makeText(
                            getActivity(),
                            "กรุณาระบุข้อมูลให้ครบถ้วน",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("USER", "FIELD NAME IS EMPTY");
                }
                else {
                    int _heightInt = Integer.parseInt(_heightStr);
                    int _weightInt = Integer.parseInt(_weightStr);
                    int _ans = _weightInt/(_heightInt*_heightInt);

                    Log.d("USER", "BMI IS VALUE");
                    TextView tlk  = (TextView) getView().findViewById(R.id.bmi_ans);
                    tlk.setText(_ans+"");
                }



            }
        });
    }

}