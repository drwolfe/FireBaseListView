package com.wolfedendevs.firebaselistview.m_FireBase;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.wolfedendevs.firebaselistview.m_Model.Spacecraft;

import java.util.ArrayList;

/**
 * Created by Douglas on 11/13/2017.
 */

public class FirebaseHelper {

    DatabaseReference db;
    Boolean saved;
    ArrayList<Spacecraft> spacecrafts = new ArrayList<>();

    // Pass Database Reference

    public FirebaseHelper(DatabaseReference db) {
        this.db = db;
    }

    // Write if not null

    public Boolean save(Spacecraft spacecraft) {
        if(spacecraft == null) {
            saved = false;
        }else{
            try {
                db.child("Spacecraft").push().setValue(spacecraft);
                saved = true;
            }catch (DatabaseException e){
                e.printStackTrace();
                saved = false;
            }
        }
        return saved;
    }

    // Implement fetch data and fill ArrayList

    private void fetchData(DataSnapshot dataSnapshot) {
        spacecrafts.clear();

        for(DataSnapshot ds : dataSnapshot.getChildren()) {
            Spacecraft spacecraft = ds.getValue(Spacecraft.class);
            spacecrafts.add(spacecraft);
        }
    }

    // Retrieve

    public ArrayList<Spacecraft> retrieve() {
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return spacecrafts;
    }
}
