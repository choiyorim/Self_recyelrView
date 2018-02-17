package com.example.choiyorim.self_recyelrview.Fragments;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.choiyorim.self_recyelrview.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class WriteFragment extends Fragment {
    private ImageView img;
    private EditText title;
    private EditText price;
    private EditText bookCon;
    private EditText bookName;
    private Button commit;
    private String email;
    public static HashMap<String, String> bookvalues;
    private ImageView image;
    private FirebaseStorage mStorageRef;
    private Uri filePath;
    public String date;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_write2, container, false);

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_CONTACTS)) {


            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_CONTACTS},
                        1);
            }
        }


        title = v.findViewById(R.id.title);
        price = v.findViewById(R.id.price);
        bookCon = v.findViewById(R.id.condition);
        bookName = v.findViewById(R.id.bookname);
        image = (ImageView) v.findViewById(R.id.img);
        commit = (Button) v.findViewById(R.id.commit);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "이미지를 선택하세요."), 0);
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
        return v;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        filePath = data.getData();
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
            image.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onRequestPermissionsResult ( int requestCode,
                                             String permissions[], int[] grantResults){
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                } else {

                }
                return;
            }
        }
    }


    private static String StringTest(String email){
        String email1 = email;
        int inx = email1.indexOf(".");
        String email2 = email1.substring(0,inx);
        return email2;
    }
    /**
     * FireBase에 Data저장
     */
    private void saveData(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            email = user.getEmail();
        }

        mStorageRef = FirebaseStorage.getInstance();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMHH_mmss");
        Date now = new Date();
        date =formatter.format(now);

        String filename = formatter.format(now) +email+ ".png";
        StorageReference riversRef = mStorageRef.getReferenceFromUrl("gs://selfrecyelrview.appspot.com").child(filename);

        riversRef.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getActivity().getApplicationContext(), "업로드 성공!", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getActivity().getApplicationContext(), "업로드 실패!", Toast.LENGTH_SHORT).show();

                    }
                }
        );


        String title1 =title.getText().toString();
        String price1 = price.getText().toString();
        String bookcon = bookCon.getText().toString();
        String bookname = bookName.getText().toString();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("book");

        String key1 = StringTest(email);
        bookvalues = new HashMap();
        bookvalues.put("image",filename);
        bookvalues.put("title",title1);
        bookvalues.put("price",price1);
        bookvalues.put("bookcondition",bookcon);
        bookvalues.put("bookname",bookname);
        myRef.child(key1).child(formatter.format(now)).setValue(bookvalues);
    }
}
