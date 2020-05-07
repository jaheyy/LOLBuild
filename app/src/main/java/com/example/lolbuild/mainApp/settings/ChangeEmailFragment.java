package com.example.lolbuild.mainApp.settings;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lolbuild.R;
import com.example.lolbuild.authentication.AuthenticationActivity;
import com.example.lolbuild.mainApp.MainAppActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import org.w3c.dom.Text;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangeEmailFragment extends Fragment {
    private FirebaseUser user;
    @Email
    @NotEmpty
    private EditText emailEditText;

    public ChangeEmailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_email, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailEditText = view.findViewById(R.id.emailEditText);
        Button confirmButton = view.findViewById(R.id.confirmButton);
        NavController navController = Navigation.findNavController(view);

        Validator validator = new Validator(this);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });

        validator.setValidationListener(new Validator.ValidationListener() {
            @Override
            public void onValidationSucceeded() {
                if (user == null) {
                    Intent myIntent = new Intent(getContext(), AuthenticationActivity.class);
                    getActivity().finish();
                    startActivity(myIntent);
                }
                user.updateEmail(emailEditText.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            navController.popBackStack();
                            Toast.makeText(getContext(), "Successfully changed email address.", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                navController.popBackStack();
                                Toast.makeText(getContext(), "Invalid email address.", Toast.LENGTH_SHORT).show();
                            } catch (FirebaseAuthUserCollisionException e) {
                                navController.popBackStack();
                                Toast.makeText(getContext(), "The email address has already been taken.", Toast.LENGTH_SHORT).show();
                            } catch (FirebaseAuthInvalidUserException e) {
                                Intent myIntent = new Intent(getContext(), AuthenticationActivity.class);
                                getActivity().finish();
                                startActivity(myIntent);
                            } catch(FirebaseAuthRecentLoginRequiredException e) {
                                ChangeEmailFragmentDirections.ActionChangeEmailFragmentToReauthenticateFragment navAction
                                        = ChangeEmailFragmentDirections.actionChangeEmailFragmentToReauthenticateFragment(null, emailEditText.getText().toString());
                                navController.navigate(navAction);
                            } catch (Exception e) {
                                e.printStackTrace();
                                navController.popBackStack();
                                Toast.makeText(getContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(getContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void onValidationFailed(List<ValidationError> errors) {
                for (ValidationError error : errors) {
                    View view = error.getView();
                    String message = error.getCollatedErrorMessage(getContext());
                    if (view instanceof EditText) {
                        ((EditText) view).setError(message);
                    }
                }
            }
        });
    }
}
