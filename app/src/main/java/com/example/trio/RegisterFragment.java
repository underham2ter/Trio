package com.example.trio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class RegisterFragment extends Fragment {
    private Button buttonRegister;
    private  PreferenceLogger preferenceLogger;
    private EditText userNameText;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_fragment,container,false);
        preferenceLogger = new PreferenceLogger(getContext());
        userNameText = view.findViewById(R.id.editTextUserName);
        buttonRegister = view.findViewById(R.id.buttonLogIn);
        buttonRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(userNameText.getText().equals("")&&userNameText.getText().equals("Username")){
                    Toast.makeText(getContext(),"Input name",Toast.LENGTH_SHORT);
                }else{
                    preferenceLogger.setValue(1);
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        return view;
    }
}
