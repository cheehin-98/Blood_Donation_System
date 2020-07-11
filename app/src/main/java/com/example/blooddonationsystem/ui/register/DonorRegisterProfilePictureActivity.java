package com.example.blooddonationsystem.ui.register;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
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
import com.google.firebase.firestore.auth.User;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;


public class DonorRegisterProfilePictureActivity extends AppCompatActivity {
    private Button buttonNext;
    private ImageView btnBackToPrevious ;
    private CircleImageView donor_upload_profile_picture;
    Uri imageuri;
    private String userType;
    private String Birthday;
    private String Gender;
    private String Contact;
    private String FullName;
    private String Username;
    private String Image;
    StorageReference storage;
    private StorageTask uploadTask;

    public static final int IMAGE_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_donor_registration_profile_picture);
        buttonNext = findViewById(R.id.button_next_donor_profile_picture);
        donor_upload_profile_picture = findViewById(R.id.donor_upload_profile_picture);
        userType = getIntent().getExtras().get("userType").toString();
        Birthday = getIntent().getExtras().get("Birthday").toString();
        Gender = getIntent().getExtras().get("Gender").toString();
        Contact = getIntent().getExtras().get("Contact").toString();
        FullName = getIntent().getExtras().get("FullName").toString();
        Username = getIntent().getExtras().get("Username").toString();

        storage = FirebaseStorage.getInstance().getReference("Donor Images");


        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donor_upload_profile_picture.setImageURI(imageuri);
                Image = imageuri.toString();
                if (uploadTask != null && uploadTask.isInProgress()) {
                    Toast.makeText(DonorRegisterProfilePictureActivity.this, "Upload in progress", Toast.LENGTH_LONG).show();
                } else {
                    Fileuploader();
                }

                NextDonorRegisterScreen();
                Log.e("INFO", "Donor " + userType);
                Log.e("INFO", "Birthday " + Birthday);
                Log.e("INFO", "Gender " + Gender);
                Log.e("INFO", "Contact " + Contact);
                Log.e("INFO", "FullName " + FullName);
                Log.e("INFO", "Username " + Username);

            }
        });
        btnBackToPrevious =  findViewById((R.id.img_back_button_profile_picture));
        btnBackToPrevious.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                BackToUsernamePage();
            }
        });
    }
    private String getExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    public void selectPhoto(View view){

        Intent intent   = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==IMAGE_CODE && resultCode==RESULT_OK && data !=null && data.getData() !=null){
            imageuri = data.getData();
            donor_upload_profile_picture.setImageURI(imageuri);
        }
    }

    private  void NextDonorRegisterScreen(){
        Intent intent   = new Intent(this, DonorRegisterAddressActivity.class);
        intent.putExtra("userType", userType);
        intent.putExtra("Birthday", Birthday);
        intent.putExtra("Gender", Gender);
        intent.putExtra("Contact", Contact);
        intent.putExtra("FullName", FullName);
        intent.putExtra("Username", Username);
        intent.putExtra("Image", Image);

        startActivity(intent);
    }
    private  void BackToUsernamePage(){
        Intent intent   = new Intent(this, DonorRegisterUsernameActivtity.class);
        this.onBackPressed();
    }
    private void Fileuploader() {
        StorageReference Ref = storage.child(System.currentTimeMillis() + "." + getExtension(imageuri));
        uploadTask = Ref.putFile(imageuri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        Toast.makeText(DonorRegisterProfilePictureActivity.this, "Image Uploaded Successfully", Toast.LENGTH_LONG).show();
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
