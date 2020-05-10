package com.example.lolbuild.mainApp.myBuilds;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lolbuild.R;
import com.example.lolbuild.models.Item;
import com.example.lolbuild.utilities.Utilities;
import com.example.lolbuild.viewModels.BuildViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddBuildFragment extends Fragment {

    private BuildViewModel buildViewModel;
    private String userID;
    private FirebaseFirestore db;

    public AddBuildFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userID = firebaseUser.getUid();
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_build, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buildViewModel = new ViewModelProvider(requireActivity()).get(BuildViewModel.class);
        ArrayList<Item> chosenStartingItems = buildViewModel.getStartingItems();
        ArrayList<Item> chosenCoreItems = buildViewModel.getCoreItems();
        ArrayList<Item> chosenSituationalItems = buildViewModel.getSituationalItems();
        NavController navController = Navigation.findNavController(view);

        TextView championTextView = view.findViewById(R.id.championTextView);
        ImageView championImageView = view.findViewById(R.id.championImageView);
        ImageView startingItem1 = view.findViewById(R.id.startingItem1);
        ImageView startingItem2 = view.findViewById(R.id.startingItem2);
        ImageView startingItem3 = view.findViewById(R.id.startingItem3);
        ImageView startingItem4 = view.findViewById(R.id.startingItem4);
        ImageView startingItem5 = view.findViewById(R.id.startingItem5);
        ImageView startingItem6 = view.findViewById(R.id.startingItem6);
        ImageView[] startingItems = new ImageView[] {startingItem1, startingItem2, startingItem3, startingItem4, startingItem5, startingItem6};
        ImageButton addStartingItemButton = view.findViewById(R.id.addStartingItemButton);
        ImageView coreItem1 = view.findViewById(R.id.coreItem1);
        ImageView coreItem2 = view.findViewById(R.id.coreItem2);
        ImageView coreItem3 = view.findViewById(R.id.coreItem3);
        ImageView coreItem4 = view.findViewById(R.id.coreItem4);
        ImageView coreItem5 = view.findViewById(R.id.coreItem5);
        ImageView coreItem6 = view.findViewById(R.id.coreItem6);
        ImageView[] coreItems = new ImageView[] {coreItem1, coreItem2, coreItem3, coreItem4, coreItem5, coreItem6};
        ImageButton addCoreItemButton = view.findViewById(R.id.addCoreItemButton);
        ImageView situationalItem1 = view.findViewById(R.id.situationalItem1);
        ImageView situationalItem2 = view.findViewById(R.id.situationalItem2);
        ImageView situationalItem3 = view.findViewById(R.id.situationalItem3);
        ImageView situationalItem4 = view.findViewById(R.id.situationalItem4);
        ImageView situationalItem5 = view.findViewById(R.id.situationalItem5);
        ImageView situationalItem6 = view.findViewById(R.id.situationalItem6);
        ImageView[] situationalItems = new ImageView[] {situationalItem1, situationalItem2, situationalItem3, situationalItem4, situationalItem5, situationalItem6};
        ImageButton addSituationalItemButton = view.findViewById(R.id.addSituationalItemButton);
        Button saveBuildButton = view.findViewById(R.id.saveBuildButton);

        championTextView.setText(buildViewModel.getChampion());
        Bitmap championImage = Utilities.getImageFromAssets(getContext(), "champions", buildViewModel.getChampion() + ".png");
        championImageView.setImageBitmap(championImage);


        for (int i = 0; i < startingItems.length; i++) {
            if (chosenStartingItems.size() > i) {
                Bitmap bitmap = Utilities.getImageFromAssets(getContext(), "items", chosenStartingItems.get(i).getImagePath());
                startingItems[i].setImageBitmap(bitmap);
                int finalI = i;
                startingItems[i].setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        chosenStartingItems.remove(finalI);
                        navController.navigate(R.id.action_addBuildFragment_self);
                        return false;
                    }
                });
            } else {
                break;
            }
        }

        if (chosenStartingItems.size() < 6) {
            addStartingItemButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AddBuildFragmentDirections.ActionAddBuildFragmentToItemsFragment navAction =
                            AddBuildFragmentDirections.actionAddBuildFragmentToItemsFragment(0);
                    navController.navigate(navAction);
                }
            });
        } else {
            addStartingItemButton.setVisibility(View.INVISIBLE);
        }


        for (int i = 0; i < coreItems.length; i++) {
            if (chosenCoreItems.size() > i) {
                Bitmap bitmap = Utilities.getImageFromAssets(getContext(), "items", chosenCoreItems.get(i).getImagePath());
                coreItems[i].setImageBitmap(bitmap);
                int finalI = i;
                coreItems[i].setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        chosenCoreItems.remove(finalI);
                        navController.navigate(R.id.action_addBuildFragment_self);
                        return false;
                    }
                });
            } else {
                break;
            }
        }

        if (chosenCoreItems.size() < 6) {
            addCoreItemButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AddBuildFragmentDirections.ActionAddBuildFragmentToItemsFragment navAction =
                            AddBuildFragmentDirections.actionAddBuildFragmentToItemsFragment(1);
                    navController.navigate(navAction);
                }
            });
        } else {
            addCoreItemButton.setVisibility(View.INVISIBLE);
        }

        for (int i = 0; i < situationalItems.length; i++) {
            if (chosenSituationalItems.size() > i) {
                Bitmap bitmap = Utilities.getImageFromAssets(getContext(), "items", chosenSituationalItems.get(i).getImagePath());
                situationalItems[i].setImageBitmap(bitmap);
                int finalI = i;
                situationalItems[i].setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        chosenSituationalItems.remove(finalI);
                        navController.navigate(R.id.action_addBuildFragment_self);
                        return false;
                    }
                });
            } else {
                break;
            }
        }

        if (chosenSituationalItems.size() < 6) {
            addSituationalItemButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AddBuildFragmentDirections.ActionAddBuildFragmentToItemsFragment navAction =
                            AddBuildFragmentDirections.actionAddBuildFragmentToItemsFragment(2);
                    navController.navigate(navAction);
                }
            });
        } else {
            addCoreItemButton.setVisibility(View.INVISIBLE);
        }

        saveBuildButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String validationResult = validate();
                if (validationResult.equals("Success")) {
                    Map<String, Object> newBuild = new HashMap<>();
                    newBuild.put("authorID", userID);
                    newBuild.put("champion", buildViewModel.getChampion());
                    newBuild.put("startingItems", buildViewModel.getStartingItemsIDs());
                    newBuild.put("coreItems", buildViewModel.getCoreItemsIDs());
                    newBuild.put("situationalItems", buildViewModel.getSituationalItemsIDs());
                    Task<DocumentReference> addedDocument = db.collection("builds").add(newBuild);
                    addedDocument.addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isSuccessful()) {
                                String buildID = task.getResult().getId();
                                DocumentReference myBuildsRef = db.collection("accounts").document(userID);
                                Task<Void> arrayUnion = myBuildsRef.update("savedBuilds",
                                        FieldValue.arrayUnion(buildID));
                                arrayUnion.addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(getContext(), "Succesfully added a new build.", Toast.LENGTH_SHORT).show();
                                            navController.navigate(R.id.action_addBuildFragment_to_myBuildsFragment);
                                        } else {
                                            Toast.makeText(getContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(getContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(getContext(), validationResult, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String validate() {
        if (userID == null)
            return "You must be signed in.";
        if (buildViewModel.getChampion() == null)
            return  "You must pick a champion.";
        if (buildViewModel.getCoreItems().size() != 6)
            return "You must pick 6 items for example build.";
        if (buildViewModel.getStartingItems().size() < 1)
            return "You must pick at least one starting item.";
        return "Success";
    }
}
