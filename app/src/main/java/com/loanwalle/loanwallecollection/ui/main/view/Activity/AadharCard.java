package com.loanwalle.loanwallecollection.ui.main.view.Activity;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.BitmapCompat;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.loanwalle.loanwallecollection.R;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


public class AadharCard extends AppCompatActivity {
    LinearLayout addbackimage;
    LinearLayout allimage;
    int bitmapByteCount = 0;
    CropImageView cropImageView;
  //  LinearLayout cropimage_lnere;
    LinearLayout cropper;
    File destination;

    /* renamed from: ff */
    File f1874ff;
    File f187422;
    LinearLayout front_image_id;
    ImageView frontimage;
    ImageView image;
    ImageView imageback;
    LinearLayout imageshowarea;
    private Uri mCropImageUri;
    Button onCropImageClick;
    Button onLoadImageClick;
    String pcitype = "";
    ProgressDialog progressDialog;
    RequestQueue requestQueue;
    ImageView sampleback;
    ImageView sampleimage;
    SharedPreferences sharedPreferences;
    LinearLayout uploadback;
    LinearLayout uploadfornt;

    @Override
    protected void onResume() {
//        getReQuest();

        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_aadhar_card);
        this.sharedPreferences = getSharedPreferences("User_Info", 0);
        this.requestQueue = Volley.newRequestQueue(this);
        this.onLoadImageClick = (Button) findViewById(R.id.onLoadImageClick);
        this.onCropImageClick = (Button) findViewById(R.id.onCropImageClick);
        this.cropImageView = (CropImageView) findViewById(R.id.cropImageView);
     //   this.cropimage_lnere = (LinearLayout) findViewById(R.id.cropimage_lnere);
        this.uploadfornt = (LinearLayout) findViewById(R.id.uploadfornt);
        this.cropper = (LinearLayout) findViewById(R.id.cropper);
        this.imageshowarea = (LinearLayout) findViewById(R.id.imageshowarea);
        this.imageback = (ImageView) findViewById(R.id.imageback);
        this.sampleback = (ImageView) findViewById(R.id.sampleback);
        this.frontimage = (ImageView) findViewById(R.id.frontimage);
        this.sampleimage = (ImageView) findViewById(R.id.sampleimage);
        this.front_image_id = (LinearLayout) findViewById(R.id.front_image_id);
        this.image = (ImageView) findViewById(R.id.image);
        this.allimage = (LinearLayout) findViewById(R.id.allimage);
      //  this.feedtext = (TextView) findViewById(R.id.feedtext);
       // this.feed_select = (LinearLayout) findViewById(R.id.feed_select);
        this.addbackimage = (LinearLayout) findViewById(R.id.addbackimage);
        this.uploadfornt = (LinearLayout) findViewById(R.id.uploadfornt);
        this.uploadback = (LinearLayout) findViewById(R.id.uploadback);
       addbackimage.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

//                AadharCard.this.pcitype = "back";
//                AadharCard.this.PicImage("back");
//
//                Intent i=new Intent(AadharCard.this, UploadAdhar.class);
//                startActivity(i);
            }
        });
        front_image_id.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
//                AadharCard.this.pcitype = "front";
//                AadharCard.this.PicImage("front");
//                Intent i=new Intent(AadharCard.this, UploadAdhar.class);
//                startActivity(i);

            }
        });
        this.uploadfornt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

//                Intent i=new Intent(AadharCard.this, UploadAdhar.class);
//                startActivity(i);
//                if (AadharCard.this.f1874ff != null) {
//                    AadharCard aadharCard = AadharCard.this;
//                    aadharCard.UploadFront(aadharCard.f1874ff);
//                    return;
//                }
//                Toast.makeText(AadharCard.this, "Please Select Image", Toast.LENGTH_LONG).show();
            }
        });
        this.uploadback.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
//                Intent i=new Intent(AadharCard.this, UploadAdhar.class);
//                startActivity(i);
////                if (AadharCard.this.f187422 != null) {
//                    UploadBack(f187422);
//                    return;
//                }
//                Toast.makeText(AadharCard.this, "Please Select Image", Toast.LENGTH_LONG).show();
//
            }
        });
        this.onCropImageClick.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Bitmap croppedImage = AadharCard.this.cropImageView.getCroppedImage(AadharCard.this.cropImageView.getWidth(), AadharCard.this.cropImageView.getHeight());
                if (croppedImage != null) {
                    AadharCard.this.cropImageView.setImageBitmap(croppedImage);
                }
                if (croppedImage != null) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    croppedImage.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                    String format = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    AadharCard aadharCard = AadharCard.this;
                    aadharCard.destination = new File(AadharCard.this.getFilesDir() + File.separator + format + ".jpeg");
                    try {
                        AadharCard.this.destination.createNewFile();
                        FileOutputStream fileOutputStream = new FileOutputStream(AadharCard.this.destination);
                        fileOutputStream.write(byteArrayOutputStream.toByteArray());
                        fileOutputStream.close();
                        AadharCard.this.bitmapByteCount = BitmapCompat.getAllocationByteCount(croppedImage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    AadharCard aadharCard2 = AadharCard.this;
                    aadharCard2.f1874ff = aadharCard2.destination;
                    if (AadharCard.this.pcitype.equalsIgnoreCase("front")) {
                        AadharCard.this.frontimage.setImageBitmap(croppedImage);
                        AadharCard.this.cropper.setVisibility(View.GONE);
                        AadharCard.this.allimage.setVisibility(View.VISIBLE);
                        AadharCard.this.sampleimage.setVisibility(View.GONE);
                        AadharCard.this.imageshowarea.setVisibility(View.VISIBLE);
                        AadharCard.this.frontimage.setVisibility(View.VISIBLE);

                    }else

                    {

                        f187422 = aadharCard2.destination;
                        AadharCard.this.imageback.setImageBitmap(croppedImage);
                        AadharCard.this.cropper.setVisibility(View.GONE);
                        AadharCard.this.allimage.setVisibility(View.VISIBLE);
                        AadharCard.this.sampleback.setVisibility(View.GONE);
                        AadharCard.this.imageshowarea.setVisibility(View.VISIBLE);
                        AadharCard.this.imageback.setVisibility(View.VISIBLE);
                        AadharCard.this.frontimage.setVisibility(View.VISIBLE);
                    }

                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void PicImage(String str) {
        checkPermisionStorage();
    }

    public Intent getPickImageChooserIntent() {
        Uri captureImageOutputUri = getCaptureImageOutputUri();
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = getPackageManager();
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        for (ResolveInfo next : packageManager.queryIntentActivities(intent, 0)) {
            Intent intent2 = new Intent(intent);
            intent2.setComponent(new ComponentName(next.activityInfo.packageName, next.activityInfo.name));
            intent2.setPackage(next.activityInfo.packageName);
            if (captureImageOutputUri != null) {
                intent2.putExtra("output", captureImageOutputUri);
            }
            arrayList.add(intent2);
        }
        Intent intent3 = new Intent("android.intent.action.GET_CONTENT");
        intent3.setType("image/*");
        for (ResolveInfo next2 : packageManager.queryIntentActivities(intent3, 0)) {
            Intent intent4 = new Intent(intent3);
            intent4.setComponent(new ComponentName(next2.activityInfo.packageName, next2.activityInfo.name));
            intent4.setPackage(next2.activityInfo.packageName);
            arrayList.add(intent4);
        }
        Intent intent5 = (Intent) arrayList.get(arrayList.size() - 1);
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Intent intent6 = (Intent) it.next();
            if (intent6.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity")) {
                intent5 = intent6;
                break;
            }
        }
        arrayList.remove(intent5);
        Intent createChooser = Intent.createChooser(intent5, "Select source");
        createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[arrayList.size()]));
        return createChooser;
    }

    private Uri getCaptureImageOutputUri() {
        File externalCacheDir = getExternalCacheDir();
        if (externalCacheDir != null) {
            return Uri.fromFile(new File(externalCacheDir.getPath(), "pickImageResult.jpeg"));
        }
        return null;
    }

    public Uri getPickImageResultUri(Intent intent) {
        String action;
        boolean z = true;
        if (!(intent == null || intent.getData() == null || ((action = intent.getAction()) != null && action.equals("android.media.action.IMAGE_CAPTURE")))) {
            z = false;
        }
        return z ? getCaptureImageOutputUri() : intent.getData();
    }

    public boolean isUriRequiresPermissions(Uri uri) {
        try {
            getContentResolver().openInputStream(uri).close();
        } catch (Exception unused) {
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        boolean z;
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            Uri pickImageResultUri = getPickImageResultUri(intent);
            Log.e("urii", String.valueOf(pickImageResultUri));
            if (Build.VERSION.SDK_INT < 23 || checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") ==  PackageManager.PERMISSION_GRANTED || !isUriRequiresPermissions(pickImageResultUri)) {
                z = false;
            } else {
                z = true;
                this.mCropImageUri = pickImageResultUri;
                requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 0);
            }
            if (!z) {

                this.cropImageView.setImageUriAsync(pickImageResultUri);

            }
            this.cropper.setVisibility(View.VISIBLE);
            this.allimage.setVisibility(View.GONE);
        }
    }

    public void checkPermisionStorage() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] strArr = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
            if (!hasPermissions(this, strArr)) {
                ActivityCompat.requestPermissions(this, strArr, 112);
            } else {
                startActivityForResult(getPickImageChooserIntent(), 200);
            }
        } else {
            startActivityForResult(getPickImageChooserIntent(), 200);
        }
    }

    private static boolean hasPermissions(Context context, String... strArr) {
        if (Build.VERSION.SDK_INT < 23 || context == null || strArr == null) {
            return true;
        }
        for (String checkSelfPermission : strArr) {
            if (ActivityCompat.checkSelfPermission(context, checkSelfPermission) != 0) {
                return false;
            }
        }
        return true;
    }

//    public void UploadFront(File file) {
//        try {
//            showdilege();
//            ((Allapi) ApiClient.getClient().create(Allapi.class)).
//                    UploadAdharfront(RequestBody.create(MediaType.parse("text/plain"),
//                    this.sharedPreferences.getString("user_id", "")),
//                    MultipartBody.Part.createFormData("forntimage",
//                            file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"),
//                                    file))).
//                    enqueue(new Callback<uploadimage>()
//                    {
//
//
//                        @Override
//                        public void onResponse(Call<uploadimage> call, retrofit2.Response<uploadimage> response) {
//                            Toast.makeText(AadharCard.this, "Upload Successful", Toast.LENGTH_LONG).show();
//                            AadharCard.this.hidediloage();
//                        }
//
//                        public void onFailure(Call<uploadimage> call, Throwable th) {
//                    Toast.makeText(AadharCard.this, "Try Again", Toast.LENGTH_LONG).show();
//                    AadharCard.this.hidediloage();
//                }
//            });
//        } catch (Exception unused) {
//            new ProgressbarLoader(this).hidediloage();
//            Toast.makeText(this, "Try Again", Toast.LENGTH_LONG).show();
//        }
//    }
//
//    public void UploadBack(File file) {
//        try {
//            showdilege();
//            ((Allapi) ApiClient.getClient().create(Allapi.class)).UploadAdharback(RequestBody.create(MediaType.parse("text/plain"), this.sharedPreferences.getString("user_id", "")), MultipartBody.Part.createFormData("backimage", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file))).enqueue(new Callback<uploadimage>() {
//
//
//                @Override
//                public void onResponse(Call<uploadimage> call, retrofit2.Response<uploadimage> response) {
//                    Toast.makeText(AadharCard.this, "Upload Successful", Toast.LENGTH_LONG).show();
//                    AadharCard.this.finish();
//                }
//
//                public void onFailure(Call<uploadimage> call, Throwable th) {
//                    Toast.makeText(AadharCard.this, "Try Again", Toast.LENGTH_LONG).show();
//                }
//            });
//        } catch (Exception unused) {
//            new ProgressbarLoader(this).hidediloage();
//            Toast.makeText(this, "Try Again", Toast.LENGTH_LONG).show();
//        }
//    }
//
//    public void getReQuest() {
//        this.progressDialog = ProgressDialog.show(this, "", "Please wait...", false, false);
//        this.requestQueue.add(new StringRequest(1, App_API.view_adhar, new Response.Listener<String>() {
//            public void onResponse(String str) {
//                Log.e("filleee", str);
//                try {
//                    AadharCard.this.progressDialog.dismiss();
//                    JSONObject jSONObject = new JSONObject(str);
//                    jSONObject.getString("success");
//                    JSONObject jSONObject2 = jSONObject.getJSONArray("data").getJSONObject(0);
//                    String string = jSONObject2.getString("forntimage");
//                    String string2 = jSONObject2.getString("backimage");
//                    AadharCard.this.frontimage.setVisibility(View.VISIBLE);
//                    AadharCard.this.sampleimage.setVisibility(View.GONE);
//                    Glide.with( AadharCard.this).load("http://sainiparivarshaadi.com/saini/users/upload/" + string).into((frontimage));
//
//
//
//                    if (string.isEmpty()&& string2.isEmpty())
//                    {
//
//                    }
//
//                    AadharCard.this.imageback.setVisibility(View.VISIBLE);
//                    AadharCard.this.sampleback.setVisibility(View.GONE);
//
//                    Glide.with( AadharCard.this).load("http://sainiparivarshaadi.com/saini/users/upload/" + string2).into((imageback));
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            public void onErrorResponse(VolleyError volleyError) {
//                AadharCard.this.progressDialog.dismiss();
//                Log.e("Error.Response", volleyError.toString());
//            }
//        }) {
//            /* access modifiers changed from: protected */
//            public Map<String, String> getParams() {
//                HashMap hashMap = new HashMap();
//                hashMap.put("user_id", AadharCard.this.sharedPreferences.getString("user_id", ""));
//                return hashMap;
//            }
//        });
//    }

    public void showdilege() {
        ProgressDialog progressDialog2 = new ProgressDialog(this, 2);
        this.progressDialog = progressDialog2;
        progressDialog2.setMessage("Please Wait ...");
        this.progressDialog.setTitle("File Uploading ");
        this.progressDialog.setProgressStyle(0);
        this.progressDialog.show();
        this.progressDialog.setCancelable(false);
    }

    public void hidediloage() {
        this.progressDialog.dismiss();
    }
}
