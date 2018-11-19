package com.example.lab203_28.healthy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lab203_28.healthy.Sleep.SleepFragment;
import com.example.lab203_28.healthy.Weight.WeightFragment;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class MenuFragment extends Fragment{
    FirebaseAuth _auth;
    ArrayList<String> _menu = new ArrayList<>();
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _menu.add("BMI");
        _menu.add("Weight");
        _menu.add("Sleep");
        _menu.add("Post");
        _menu.add("Setup");
        _menu.add("Sing out");

        _auth = FirebaseAuth.getInstance();

        final ArrayAdapter<String> _menuAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                _menu
        );
        ListView _menuList = (ListView) getView().findViewById(R.id.menu_list);
        _menuList.setAdapter(_menuAdapter);
        _menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Log.d("text" , "Click on  " + _menu.get(i));

                _menuAdapter.notifyDataSetChanged();
                if (_menu.get(i).equals("BMI")){
                    Log.d("USER", "GOTO BMI");
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new BMIFragment()).commit();
                }
                else if (_menu.get(i).equals("Weight")){
                    Log.d("USER", "GOTO Weight");
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new WeightFragment()).commit();
                }
                else if (_menu.get(i).equals("Sleep")){
                    Log.d("USER", "GOTO SLEEP");
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new SleepFragment()).commit();
                }
                else if (_menu.get(i).equals("Post")){
                    Log.d("USER", "GOTO Post");
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new PostFragment()).commit();
                }
                else if (_menu.get(i).equals("Sing out")){
                    _auth.signOut();
                    Log.d("USER", "SING OUT GOTO LOGIN");
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new LoginFragment()).commit();
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }
}
