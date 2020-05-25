package com.artemissoftware.pokedex.di;


import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

@Module
public class FirebaseModule {


    @Provides
    @Singleton
    FirebaseFirestore provideFirebaseFirestore() {

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        Timber.d("Providing FirebaseFirestore: " + firestore);
        return firestore;
    }


    @Provides
    @Singleton
    CollectionReference provideCollectionReference(FirebaseFirestore firestore) {

        CollectionReference reference = firestore.collection("favourites");

        Timber.d("Providing CollectionReference: " + reference);
        return reference;
    }

}
