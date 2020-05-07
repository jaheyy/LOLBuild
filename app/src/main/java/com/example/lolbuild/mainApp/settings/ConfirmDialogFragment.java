package com.example.lolbuild.mainApp.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;

import com.example.lolbuild.authentication.AuthenticationActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class ConfirmDialogFragment extends DialogFragment {
    private Activity activity;
    private Context context;
    private NavController navController;

    public ConfirmDialogFragment(Activity activity, Context context, NavController navController) {
        this.activity = activity;
        this.context = context;
        this.navController = navController;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Do you really want to delete your account? This operation is irreversible.");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (user != null) {
                    user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(context, "Successfully deleted your account. Waiting 5 second to sign you out.", Toast.LENGTH_SHORT).show();
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        auth.signOut();
                                        Intent myIntent = new Intent(context, AuthenticationActivity.class);
                                        activity.finish();
                                        activity.startActivity(myIntent);
                                    }
                                }, 5000);
                            }
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthInvalidUserException e) {
                                Toast.makeText(context, "Your account have been disabled, deleted or your credentials are no longer valid.", Toast.LENGTH_SHORT).show();
                            } catch (FirebaseAuthRecentLoginRequiredException e) {
                                SettingsFragmentDirections
                                        .ActionSettingsFragmentToReauthenticateFragment navAction
                                        = SettingsFragmentDirections
                                        .actionSettingsFragmentToReauthenticateFragment
                                                (null, null)
                                        .setDeleteAccount(true);
                                navController.navigate(navAction);
                            } catch (Exception e) {
                                Log.e("Info", "Account deleted");
                            }
                        }
                    });
                } else {
                    Toast.makeText(getContext(), "Something went wrong. Try to resign in.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }
}
