package com.example.lolbuild;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SingInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SingInFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button signInButton;
    private TextView dontHaveAccountTextView;
    private TextView forgotPasswordTextView;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;

    public SingInFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SingInFragment.
     */
    // TODO: Rename and change types and number of parameters
//    public static SingInFragment newInstance(String param1, String param2) {
//        SingInFragment fragment = new SingInFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sing_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailEditText = view.findViewById(R.id.emailEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        signInButton = view.findViewById(R.id.signInButton);
        forgotPasswordTextView = view.findViewById(R.id.forgotPasswordTextView);
        dontHaveAccountTextView = view.findViewById(R.id.dontHaveAccountTextView);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(getContext(), "You are logged in!", Toast.LENGTH_SHORT).show();
                }
            }
        };

        dontHaveAccountTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_singInFragment_to_signUpFragment);
            }
        });

        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_singInFragment_to_resetPasswordFragment);
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String pwd = passwordEditText.getText().toString();
                auth.signInWithEmailAndPassword(email, pwd);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authStateListener != null) {
            auth.removeAuthStateListener(authStateListener);
            auth.signOut();
        }
    }
}
