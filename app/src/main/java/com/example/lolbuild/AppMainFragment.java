package com.example.lolbuild;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lolbuild.jobs.FetchChampions;
import com.example.lolbuild.jobs.FetchLolVersion;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppMainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppMainFragment extends Fragment {

    private static String championsJson;

    public AppMainFragment() {
        // Required empty public constructor
    }

    public static String getChampionsJson() {
        return championsJson;
    }

    public static void setChampionsJson(String championsJson) {
        AppMainFragment.championsJson = championsJson;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                getActivity().finishAndRemoveTask();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_app_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String email = auth.getCurrentUser().getEmail();
        Log.i("User", auth.getCurrentUser().toString());
        FetchLolVersion fetchLolVersion = new FetchLolVersion();
        fetchLolVersion.execute();
    }
}
