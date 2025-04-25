package com.aiyu.hostel.core.firebase;


import com.aiyu.hostel.core.data.FoodItem;
import com.aiyu.hostel.core.data.Hostel;
import com.aiyu.hostel.core.data.Ticket;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javax.inject.Inject;


public class FirebaseInteraction {

    private static final String TAG = "FirebaseInteraction";

    private static final String USER_DATABASE_PATH = "User";

    private static final String HOSTEL_DATABASE_PATH = "Hostel";

    private static final String TICKET_DATABASE_PATH = "Ticket";

    private static final String FOOD_DATABASE_PATH = "Food";


    private final FirebaseFirestore firebaseFirestore;
    private final FirebaseAuth firebaseAuth;

    @Inject
    public FirebaseInteraction(FirebaseFirestore firebaseFirestore, FirebaseAuth firebaseAuth) {
        this.firebaseFirestore = firebaseFirestore;
        this.firebaseAuth = firebaseAuth;
    }


    public void insertUser(User user, Consumer<Exception> onUserAdded) {
        var ref = firebaseFirestore.collection(USER_DATABASE_PATH).document(user.getUid());
        ref.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                onUserAdded.accept(null);
            } else {
                addUserToDatabase(user, onUserAdded, ref);
            }
        }).addOnFailureListener(e -> addUserToDatabase(user, onUserAdded, ref));
    }

    public void updateUser(User user, Consumer<Exception> onUserAdded) {
        var ref = firebaseFirestore.collection(USER_DATABASE_PATH).document(user.getUid());
        ref.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                addUserToDatabase(user, onUserAdded, ref);
            } else {
                onUserAdded.accept(new Exception("User not found"));
            }
        }).addOnFailureListener(onUserAdded::accept);
    }

    private static void addUserToDatabase(User user, Consumer<Exception> onUserAdded, DocumentReference ref) {
        ref.set(user).addOnSuccessListener(aVoid -> {
            onUserAdded.accept(null);
        }).addOnFailureListener(onUserAdded::accept);
    }

    public void getUser(BiConsumer<User, Exception> onUserRetrieved) {
        if (firebaseAuth.getUid() == null) {
            onUserRetrieved.accept(null, new Exception("User not logged in"));
            return;
        }
        firebaseFirestore.collection(USER_DATABASE_PATH).document(firebaseAuth.getUid()).get().addOnSuccessListener(documentSnapshot -> {
            User user = documentSnapshot.toObject(User.class);
            onUserRetrieved.accept(user, null);
        }).addOnFailureListener(e -> {
            onUserRetrieved.accept(null, e);
        });
    }

    public void getHostel(BiConsumer<Hostel, Exception> onHostelRetrieved) {
//        if (firebaseAuth.getUid() == null) {
//            onHostelRetrieved.accept(null, new Exception("User not logged in"));
//            return;
//        }
        firebaseFirestore.collection(HOSTEL_DATABASE_PATH).document(firebaseAuth.getUid()).get().addOnSuccessListener(documentSnapshot -> {
            Hostel hostel = documentSnapshot.toObject(Hostel.class);
            onHostelRetrieved.accept(hostel, null);
        }).addOnFailureListener(e -> {
            onHostelRetrieved.accept(null, e);
        });
    }

    public void insertHostel(Hostel hostel, Consumer<Exception> onHostelAdded) {
        var ref = firebaseFirestore.collection(HOSTEL_DATABASE_PATH);
        String id = ref.document().getId();
        hostel.setId(id);
        ref.document(id).set(hostel).addOnSuccessListener(aVoid -> {
            onHostelAdded.accept(null);
        }).addOnFailureListener(onHostelAdded::accept);
    }

    public void updateHostel(Hostel hostel, Consumer<Exception> onHostelAdded) {
        var ref = firebaseFirestore.collection(HOSTEL_DATABASE_PATH).document(hostel.getId());
        ref.set(hostel).addOnSuccessListener(aVoid -> {
            onHostelAdded.accept(null);
        }).addOnFailureListener(onHostelAdded::accept);
    }

    public void deleteHostel(String hostelId, Consumer<Exception> onHostelDeleted) {
        var ref = firebaseFirestore.collection(HOSTEL_DATABASE_PATH).document(hostelId);
        ref.delete().addOnSuccessListener(aVoid -> {
            onHostelDeleted.accept(null);
        }).addOnFailureListener(onHostelDeleted::accept);
    }


    public void insertTicket(Ticket ticket, Consumer<Exception> onTicketAdded) {
        var ref = firebaseFirestore.collection(TICKET_DATABASE_PATH);
        String id = ref.document().getId();
        ticket.setId(id);
        ref.document(id).set(ticket).addOnSuccessListener(aVoid -> {
            onTicketAdded.accept(null);
        }).addOnFailureListener(onTicketAdded::accept);
    }

    public void updateTicket(Ticket ticket, Consumer<Exception> onTicketAdded) {
        var ref = firebaseFirestore.collection(TICKET_DATABASE_PATH).document(ticket.getId());
        ref.set(ticket).addOnSuccessListener(aVoid -> {
            onTicketAdded.accept(null);
        }).addOnFailureListener(onTicketAdded::accept);
    }

    public void deleteTicket(String ticketId, Consumer<Exception> onTicketDeleted) {
        var ref = firebaseFirestore.collection(TICKET_DATABASE_PATH).document(ticketId);
        ref.delete().addOnSuccessListener(aVoid -> {
            onTicketDeleted.accept(null);
        }).addOnFailureListener(onTicketDeleted::accept);
    }

    public void getTicket(BiConsumer<Ticket, Exception> onTicketRetrieved) {
//        if (firebaseAuth.getUid() == null) {
//            onTicketRetrieved.accept(null, new Exception("User not logged in"));
//            return;
//        }
        firebaseFirestore.collection(TICKET_DATABASE_PATH).document(firebaseAuth.getUid()).get().addOnSuccessListener(documentSnapshot -> {
            Ticket ticket = documentSnapshot.toObject(Ticket.class);
            onTicketRetrieved.accept(ticket, null);
        }).addOnFailureListener(e -> {
            onTicketRetrieved.accept(null, e);
        });
    }


    public void getAllFood(BiConsumer<List<FoodItem>, Exception> onFoodRetrieved) {
//        if (firebaseAuth.getUid() == null) {
//            onFoodRetrieved.accept(null, new Exception("User not logged in"));
//            return;
//        }
        firebaseFirestore.collection(FOOD_DATABASE_PATH).get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<FoodItem> foodList = queryDocumentSnapshots.toObjects(FoodItem.class);
            onFoodRetrieved.accept(foodList, null);
        }).addOnFailureListener(e -> {
            onFoodRetrieved.accept(null, e);
        });
    }

    public void insertFood(FoodItem foodItem, Consumer<Exception> onFoodAdded) {
        var ref = firebaseFirestore.collection(FOOD_DATABASE_PATH);
        String id = ref.document().getId();
        foodItem.setId(id);
        ref.document(id).set(foodItem).addOnSuccessListener(aVoid -> {
            onFoodAdded.accept(null);
        }).addOnFailureListener(onFoodAdded::accept);
    }

    public void updateFood(FoodItem foodItem, Consumer<Exception> onFoodAdded) {
        var ref = firebaseFirestore.collection(FOOD_DATABASE_PATH).document(foodItem.getId());
        ref.set(foodItem).addOnSuccessListener(aVoid -> {
            onFoodAdded.accept(null);
        }).addOnFailureListener(onFoodAdded::accept);
    }

    public void deleteFood(String foodId, Consumer<Exception> onFoodDeleted) {
        var ref = firebaseFirestore.collection(FOOD_DATABASE_PATH).document(foodId);
        ref.delete().addOnSuccessListener(aVoid -> {
            onFoodDeleted.accept(null);
        }).addOnFailureListener(onFoodDeleted::accept);
    }



    public void logOut(Consumer<Void> onLoggedOut) {
        firebaseAuth.signOut();
        onLoggedOut.accept(null);
    }

}