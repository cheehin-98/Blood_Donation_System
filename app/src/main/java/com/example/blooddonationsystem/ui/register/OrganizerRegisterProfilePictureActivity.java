package com.example.blooddonationsystem.ui.register;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.blooddonationsystem.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class OrganizerRegisterProfilePictureActivity extends AppCompatActivity {

    private Button buttonNext;
    private ImageView buttonPreviousPage;
    private CircleImageView organizer_upload_profile_picture;
    Uri imageuri;

    private String userType;
    private String organizerName;
    private String organizerContact;
    private String organizerAddressLine1;
    private String organizerAddressLine2;
    private String organizerCity;
    private String organizerState;
    private String organizerCountry;
    private String organizerZipCode;
    private String organizerUsername;
    private String organizerImage;
    StorageReference storage;
    private StorageTask uploadTask;


    public static final int IMAGE_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_organizer_registration_profile_picture);

        buttonNext = (Button) findViewById(R.id.button_next_organizer_profile_picture);
        organizer_upload_profile_picture = findViewById(R.id.organizer_upload_profile_picture);

        userType = getIntent().getExtras().get("userType").toString();
        organizerName = getIntent().getExtras().get("organizerName").toString();
        organizerContact = getIntent().getExtras().get("organizerContact").toString();
        organizerAddressLine1 = getIntent().getExtras().get("organizerAddressLine1").toString();
        organizerAddressLine2 = getIntent().getExtras().get("organizerAddressLine2").toString();
        organizerCity = getIntent().getExtras().get("organizerCity").toString();
        organizerState = getIntent().getExtras().get("organizerState").toString();
        organizerCountry = getIntent().getExtras().get("organizerCountry").toString();
        organizerZipCode = getIntent().getExtras().get("organizerZipCode").toString();
        organizerUsername = getIntent().getExtras().get("organizerUsername").toString();

        storage = FirebaseStorage.getInstance().getReference(" Organizer Images");


        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                organizer_upload_profile_picture.setImageURI(imageuri);
                organizerImage = imageuri.toString();
                if (uploadTask != null && uploadTask.isInProgress()) {
                    Toast.makeText(OrganizerRegisterProfilePictureActivity.this, "Upload in progress", Toast.LENGTH_LONG).show();
                } else {
                    Fileuploader();
                }


                NextOrganizerRegisterScreen();
                Log.e("INFO", "Organizer " + userType);
                Log.e("INFO", "Organizer Name " + organizerName);
                Log.e("INFO", "Organizer Contact " + organizerContact);
                Log.e("INFO", "Organizer Address Line 1 " + organizerAddressLine1);
                Log.e("INFO", "Organizer Address Line 2 " + organizerAddressLine2);
                Log.e("INFO", "Organizer City " + organizerCity);
                Log.e("INFO", "Organizer State " + organizerState);
                Log.e("INFO", "Organizer Country " + organizerCountry);
                Log.e("INFO", "Organizer Zip Code " + organizerZipCode);
                Log.e("INFO", "Organizer Username " + organizerUsername);
            }
        });


        buttonPreviousPage = (ImageView) findViewById((R.id.image_back_button_organizer_profile_picture));
        buttonPreviousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackToOrganizerUsernamePage();
            }
        });

    }

    private String getExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    public void uploadPhoto(View view) {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageuri = data.getData();
            organizer_upload_profile_picture.setImageURI(imageuri);
        }
    }

    private void NextOrganizerRegisterScreen() {
        Intent intent = new Intent(this, OrganizerRegisterEmailPasswordActivity.class);
        intent.putExtra("userType", userType);
        intent.putExtra("organizerName", organizerName);
        intent.putExtra("organizerContact", organizerContact);
        intent.putExtra("organizerAddressLine1", organizerAddressLine1);
        intent.putExtra("organizerAddressLine2", organizerAddressLine2);
        intent.putExtra("organizerCity", organizerCity);
        intent.putExtra("organizerState", organizerState);
        intent.putExtra("organizerCountry", organizerCountry);
        intent.putExtra("organizerZipCode", organizerZipCode);
        intent.putExtra("organizerUsername", organizerUsername);
        intent.putExtra("organizerImage", organizerImage);
        startActivity(intent);
    }

    private void BackToOrganizerUsernamePage() {
        Intent intent = new Intent(this, OrganizerRegisterUsernameActivity.class);
        this.onBackPressed();
    }

    private void Fileuploader() {
        StorageReference Ref = storage.child(System.currentTimeMillis() + "." + getExtension(imageuri));
        uploadTask = Ref.putFile(imageuri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        Toast.makeText(OrganizerRegisterProfilePictureActivity.this, "Image Uploaded Successfully", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
    }
}
