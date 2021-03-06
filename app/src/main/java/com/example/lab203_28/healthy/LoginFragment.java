package com.example.lab203_28.healthy;



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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * Created by LAB203_28 on 20/8/2561.
 */

public class LoginFragment extends Fragment {

    FirebaseAuth _auth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _auth = FirebaseAuth.getInstance();

        if (_auth.getCurrentUser() != null) {
            Log.d("USER", "ALREADY LOGIN");
            Log.d("USER", "GOTO MENU");
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new MenuFragment()).commit();
        } else {
            initRegisterBtn();





            Button _loginBtn = (Button) getView().findViewById(R.id.login_login_btn);
            _loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText _userId = (EditText) getView().findViewById(R.id.login_userid);
                    EditText _password = (EditText) getView().findViewById(R.id.login_password);
                    String _userIdStr = _userId.getText().toString();
                    String _passwordStr = _password.getText().toString();

                    if (_auth.getCurrentUser() != null) {
                        Log.d("USER", "ALREADY LOGIN");
                        Log.d("USER", "GOTO MENU");
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new MenuFragment()).commit();
                    }
                    if (_userIdStr.isEmpty() || _passwordStr.isEmpty()) {
                        Toast.makeText(
                                getActivity(),
                                "กรุณาระบุ user or password",
                                Toast.LENGTH_SHORT
                        ).show();
                        Log.d("USER", "USER OR PASSWORD IS EMPTY");
                    } else {
                        _auth.signInWithEmailAndPassword(_userIdStr, _passwordStr).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                if (_auth.getCurrentUser().isEmailVerified()) {
                                    Log.d("USER", "LOGIN SUCCESS");
                                    Log.d("USER", "GOTO MENU");
                                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new MenuFragment()).commit();
                                } else {
                                    Toast.makeText(
                                            getActivity(),
                                            "Plese Verified Email",
                                            Toast.LENGTH_SHORT
                                    ).show();
                                    _auth.signOut();
                                    Log.d("USER", "INVALID Verified Email");
                                }
                            }

                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(
                                        getActivity(),
                                        e.getMessage(),
                                        Toast.LENGTH_SHORT
                                ).show();
                                _auth.signOut();
                                Log.d("USER", "ERROR = " + e.getMessage());
                            }
                        });

                    }
                }
            });
        }
    }
    void initRegisterBtn(){
        TextView _regBtn = (TextView) getView().findViewById(R.id.login_register);
        _regBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("USER", "GOTO REGISTER");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new RegisterFragment()).commit();
            }
        });
    }
}
