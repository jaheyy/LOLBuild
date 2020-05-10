# LOLBuild
> Android App for creating and sharing League of Legends builds.

## Table of contents
* [General info](#general-info)
* [Screenshots](#screenshots)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Status](#status)
* [Contact](#contact)

## General info
LOLBuild is a student project for Android. It allows you to create and share with other users League of Legends builds for any champion. 
It always has actual data, because it uses official League of Legends developer resources, so it automatically updates items and champions, when there is a new LOL patch.
This app uses Google's Firebase for managing users, cloud database and its access. For navigation features it has Android Jetpack navigation graphs implemented.

## Screenshots
* ![Sign in form](/screenshots/1.PNG)
* ![Sign up form](/screenshots/2.PNG)
* ![Reset password form](/screenshots/3.PNG)
* ![My Builds fragment](/screenshots/4.PNG)
* ![Choose champion fragment](/screenshots/5.PNG)
* ![Add new build fragment](/screenshots/6.PNG)
* ![Explore builds fragment](/screenshots/7.PNG)
* ![Account settings fragment](/screenshots/8.PNG)
* ![Confirmation modal](/screenshots/9.PNG)
* ![Authentication activity navigation graph](/screenshots/11.PNG)
* ![Main app activity navigation graph](/screenshots/10.PNG)

## Technologies
* Android studio 3.6.3
* Android SDK 23
* Firebase Firestore 21.4.3
* Firebase Auth 19.3.1
* Google Services 4.3.3
* [Android Saripaar 2.0.3] (https://github.com/ragunathjawahar/android-saripaar)

## Setup
To setup a project, you need to clone the repo or download its content and open up the main folder in Android Studio, version 3.6.3 or newer.

## Features
List of ready features
* Signing in
* Signing up
* Sending reset password email
* Change email
* Change account name
* Change password
* Delete account
* Add new build
* Add other user build to your account
* Delete build from your account
* Save League of Legends data to Shared preferences 
* Fetch data from League of Legends Developer's resources (If current LOL Patch is newer than saved in Shared Preferences)

## Code Examples
* Example of Asynchronious Task fetching LOL Resources. It uses delegate of interface AsyncResponse to notify fragment when task has been done.
If current LOL Version is newer than saved in shared preferences it fetch the data, otherwise it just takes if from shared preferences.
```java
public class FetchChampions extends AsyncTask<Void, Void, Void> {
    private String championJson;
    private AsyncResponse delegate;
    private SharedPreferences sharedPreferences;

    public FetchChampions(SharedPreferences sharedPreferences, AsyncResponse delegate) {
        this.delegate = delegate;
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        championJson = Utilities.fetchData("http://ddragon.leagueoflegends.com/cdn/" + MainAppActivity.getLolVersion() + "/data/en_US/champion.json");
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        try {
            JSONObject champions = new JSONObject(championJson).getJSONObject("data");
            ArrayList<String> championList = new ArrayList<>();
            champions.keys().forEachRemaining(championList::add);
            MainAppActivity.setChampions(championList);
            Gson gson = new Gson();
            String championsJson = gson.toJson(championList);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("champions", championsJson);
            editor.commit();
            delegate.processFinish("Success");
        } catch (JSONException e) {
            e.printStackTrace();
            delegate.processFinish("Something went wrong.");
        }
    }
}
```
* View holders in adapters uses buildViewModel to save state of currently added new build
```java
holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (itemSet) {
                    case (0) :
                        buildViewModel.addStartingItem(currentItem);
                        break;
                    case (1) :
                        buildViewModel.addCoreItem(currentItem);
                        break;
                    case (2):
                        buildViewModel.addSituationalItem(currentItem);
                        break;
                }
                navController.navigate(R.id.action_itemsFragment_to_addBuildFragment);
            }
        });
```
* Example of Edit Texts field validations with saripaar and checking Firebase auth exceptions.
```java
validator = new Validator(this);
        ValidationListener validationListener = new ValidationListener() {
            @Override
            public void onValidationSucceeded() {
                String email = emailEditText.getText().toString();
                String pwd = passwordEditText.getText().toString();
                auth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthInvalidUserException e) {
                                emailEditText.setError("Such user doesn't exist.");
                                passwordEditText.setError("Such user doesn't exist.");
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                passwordEditText.setError("Incorrect password.");
                            } catch (Exception e) {
                                Log.e("Auth Exception", e.getMessage());
                            }
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
        };
```
## Status
Project has its main functionalities, but it's still in progress, especially front-end needs to be better.

## Contact
Created by [@jahey](https://github.com/jaheyy)
