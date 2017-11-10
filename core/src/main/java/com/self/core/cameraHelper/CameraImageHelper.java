package com.self.core.cameraHelper;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import com.self.core.R;
import com.self.core.utility.IntentKeys;
import com.self.core.utility.Utility;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;


/**
 * Created by paragsarkar on 19/10/17.
 */


public class CameraImageHelper extends AppCompatActivity {

    private Intent intent;
    private int whatHelpdoYouNeed;

    private Toolbar toolbar;
    private Utility utility;

    private Uri imageUri;


    private File mCameraCapturedImageFile;
    public final static int IMAGE_URI = 1;

    public static final int GET_MAIN_BIZ_IMAGE_USE_CAMERA = 1005;
    public static final int GET_CIRCLE_PROFILE_IMAGE_USE_CAMERA = 1001;
    public static final int GET_CIRCLE_PROFILE_IMAGE_USE_PHOTOS = 1002;
    public static final int GET_IMAGE_USE_CAMERA = 1003;
    public static final int GET_IMAGE_USE_PHOTOS = 1004;
    public static final int GET_IMAGE_USE_PHOTOS_WITH_2_1_ASPECT_RATIO = 1007;
    public static final int GET_IMAGE_USE_CAMERA_WITH_2_1_ASPECT_RATIO = 1008;

    public static final int GET_MAIN_BIZ_IMAGE_USE_PHOTOS = 1006;
    public static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 103;
    public static final int PHOTOS_PICK_IMAGE_REQUEST_CODE = 104;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_helper);

        utility = Utility.getInstance();


    }

    @Override
    protected void onResume() {
        super.onResume();
        whatHelpdoYouNeed = getIntent().getIntExtra(IntentKeys.imageHelper,0);
        setSupportActionBar(toolbar);
        switch (whatHelpdoYouNeed) {
            case GET_CIRCLE_PROFILE_IMAGE_USE_CAMERA:
            case GET_IMAGE_USE_CAMERA:
            case GET_IMAGE_USE_CAMERA_WITH_2_1_ASPECT_RATIO:
                launchCameraForImage();
                break;
            case GET_CIRCLE_PROFILE_IMAGE_USE_PHOTOS:
            case GET_IMAGE_USE_PHOTOS:
            case GET_IMAGE_USE_PHOTOS_WITH_2_1_ASPECT_RATIO:
                launchPhotosForImage();
                break;
        }
        getSupportActionBar().hide();
    }

    public void galleryAddPic(File imageFile) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(imageFile);
        mediaScanIntent.setData(contentUri);
        getApplicationContext().sendBroadcast(mediaScanIntent);

    }


    void launchPhotosForImage() {
        PackageManager packageManager = this.getPackageManager();
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        if (galleryIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(galleryIntent, PHOTOS_PICK_IMAGE_REQUEST_CODE);
        } else {
        }
    }

    void launchCameraForImage() {
        mCameraCapturedImageFile = utility.getFileForCapturingImage();
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            takePictureIntent
                    .putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCameraCapturedImageFile));
            startActivityForResult(takePictureIntent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
        } else {
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CAMERA_CAPTURE_IMAGE_REQUEST_CODE:
                onCameraCaptureImageRequestResult(resultCode,data);
                break;
            case PHOTOS_PICK_IMAGE_REQUEST_CODE:
                onPhotosPickImageRequestResult(resultCode,data);
                break;


        }

    }

    void onPhotosPickImageRequestResult(int resultCode, Intent data) {
        switch (resultCode) {
            case Activity.RESULT_OK:
                imageUri = data.getData();
                switch (whatHelpdoYouNeed) {
                    case GET_CIRCLE_PROFILE_IMAGE_USE_PHOTOS:
                        CropImage.activity(imageUri)
                                .setCropShape(CropImageView.CropShape.OVAL)
                                .setFixAspectRatio(true)
                                .setBorderLineThickness(5)
                                .setMinCropWindowSize(300, 300)
                                .setMaxCropResultSize(3200, 3200)
                                .setGuidelines(CropImageView.Guidelines.ON)
                                .setAutoZoomEnabled(true)
                                .setRequestedSize(300, 300)
                                .start(this);
                        break;
                    case GET_MAIN_BIZ_IMAGE_USE_PHOTOS:
                        CropImage.activity(imageUri)
                                .setCropShape(CropImageView.CropShape.RECTANGLE)
                                .setFixAspectRatio(true)
                                .setBorderLineThickness(5)
                                .setGuidelines(CropImageView.Guidelines.ON)
                                .setAutoZoomEnabled(true)
                                .setRequestedSize(2400, 1350)
                                .setAspectRatio(16, 9)
                                .start(this);
                        break;
                    case GET_IMAGE_USE_PHOTOS_WITH_2_1_ASPECT_RATIO:
                        CropImage.activity(imageUri)
                                .setCropShape(CropImageView.CropShape.RECTANGLE)
                                .setFixAspectRatio(true)
                                .setBorderLineThickness(5)
                                .setGuidelines(CropImageView.Guidelines.ON)
                                .setAutoZoomEnabled(true)
                                .setAspectRatio(2, 1)
                                .start(this);
                        break;
                    case GET_IMAGE_USE_PHOTOS:
                        finishWithResult(IMAGE_URI, imageUri.toString());
                        break;
                }
                break;
        }
    }

    void onCameraCaptureImageRequestResult(int resultCode,Intent data){
        switch (resultCode) {
            case Activity.RESULT_OK:
                galleryAddPic(mCameraCapturedImageFile);
                switch (whatHelpdoYouNeed) {
                    case GET_CIRCLE_PROFILE_IMAGE_USE_CAMERA:
                        CropImage.activity(Uri.parse("file:" + mCameraCapturedImageFile.getAbsolutePath()))
                                .setCropShape(CropImageView.CropShape.OVAL)
                                .setFixAspectRatio(true)
                                .setBorderLineThickness(5)
                                .setMinCropWindowSize(300, 300)
                                .setMaxCropResultSize(3200, 3200)
                                .setGuidelines(CropImageView.Guidelines.ON)
                                .setAutoZoomEnabled(true)
                                .setRequestedSize(300, 300)
                                .start(this);
                        break;
                    case GET_MAIN_BIZ_IMAGE_USE_CAMERA:
                        Uri myUri = Uri.fromFile(new File(getExternalCacheDir(), "image.jpeg"));
                        CropImage.activity(Uri.fromFile(mCameraCapturedImageFile))
                                .setOutputUri(myUri)
                                .setCropShape(CropImageView.CropShape.RECTANGLE)
                                .setFixAspectRatio(true)
                                .setBorderLineThickness(5)
                                .setGuidelines(CropImageView.Guidelines.ON)
                                .setAutoZoomEnabled(true)
                                .setAspectRatio(16, 9)
                                .setRequestedSize(2400, 1350)
                                .start(this);
                        break;
                    case GET_IMAGE_USE_CAMERA_WITH_2_1_ASPECT_RATIO:
                        CropImage.activity(Uri.fromFile(mCameraCapturedImageFile))
                                .setCropShape(CropImageView.CropShape.RECTANGLE)
                                .setFixAspectRatio(true)
                                .setBorderLineThickness(5)
                                .setGuidelines(CropImageView.Guidelines.ON)
                                .setAutoZoomEnabled(true)
                                .setAspectRatio(2, 1)
                                .start(this);
                        break;
                    case GET_IMAGE_USE_CAMERA:
                        finishWithResult(IMAGE_URI, Uri.parse("file:" + mCameraCapturedImageFile.getAbsolutePath()).toString());
                        break;
                }
                break;
        }
    }

    void finishWithResult(int resultCode, String resultData) {
        Intent data = new Intent();

        switch (resultCode) {
            case IMAGE_URI:
                data.putExtra("imageUri", resultData);
                break;
        }
        if (getParent() == null) {
            setResult(resultCode, data);
        } else {
            getParent().setResult(resultCode, data);
        }
        finish();
    }

}
