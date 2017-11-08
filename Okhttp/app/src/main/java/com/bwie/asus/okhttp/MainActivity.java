package com.bwie.asus.okhttp;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //       tv = (TextView) findViewById(R.id.tv);

        findViewById(R.id.photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                toPhoto();
            }
        });


        findViewById(R.id.camear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                toCamera();
            }
        });

        cache();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                a();
//            }
//        }).start();

        //       b();

    }

    private void a() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("https://publicobject.com/helloworld.txt").build();
            Call call = client.newCall(request);
            Response response = call.execute();
            if (response.isSuccessful()) {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(string);
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void b() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("http://publicobject.com/helloworld.txt").build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String string = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv.setText(string);
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");

    public void postFile(File file) {

        String path = file.getAbsolutePath();

        String[] split = path.split("\\/");

        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("imageFileName", split[split.length - 1])
                .addFormDataPart("image", split[split.length - 1], RequestBody.create(MEDIA_TYPE_PNG, file))
                .build();

        Request request = new Request.Builder().post(requestBody).url("http://qhb.2dyt.com/Bwei/upload").build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


                System.out.println("response = " + response.body().string());

            }
        });

    }

    static final int INTENTFORCAMERA = 1 ;
    static final int INTENTFORPHOTO = 2 ;

    public String LocalPhotoName;
    public String createLocalPhotoName() {
        LocalPhotoName = System.currentTimeMillis() + "face.jpg";
        return  LocalPhotoName ;
    }

    public void toCamera(){
        try {
            Intent intentNow = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri uri = null ;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                uri = FileProvider.getUriForFile(UploadPhotoActivity.this, "com.bw.dliao", SDCardUtils.getMyFaceFile(createLocalPhotoName()));
//            }else {
//                uri = Uri.fromFile(Utils.getMyFaceFile(createLocalPhotoName())) ;
//            }

            intentNow.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intentNow, INTENTFORCAMERA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toPhoto(){
        try {
            createLocalPhotoName();
            Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
            getAlbum.setType("image/*");
            startActivityForResult(getAlbum, INTENTFORPHOTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case INTENTFORPHOTO:
                try {
                    Uri originalUri = data.getData();
                    File f = null;
                    if (originalUri != null) {
                        f = new File(Utils.photoCacheDir, LocalPhotoName);
                        String[] proj = {MediaStore.Images.Media.DATA};
                        Cursor actualimagecursor =  this.getContentResolver().query(originalUri, proj, null, null, null);
                        if (null == actualimagecursor) {
                            if (originalUri.toString().startsWith("file:")) {
                                File file = new File(originalUri.toString().substring(7, originalUri.toString().length()));
                                if(!file.exists()){
                                    originalUri = Uri.parse(URLDecoder.decode(originalUri.toString(),"UTF-8"));
                                    file = new File(originalUri.toString().substring(7, originalUri.toString().length()));
                                }
                                FileInputStream inputStream = new FileInputStream(file);
                                FileOutputStream outputStream = new FileOutputStream(f);
                                copyStream(inputStream, outputStream);
                            }
                        } else {
                            int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                            actualimagecursor.moveToFirst();
                            String img_path = actualimagecursor.getString(actual_image_column_index);
                            if (img_path == null) {
                                InputStream inputStream = this.getContentResolver().openInputStream(originalUri);
                                FileOutputStream outputStream = new FileOutputStream(f);
                                copyStream(inputStream, outputStream);
                            } else {
                                File file = new File(img_path);
                                FileInputStream inputStream = new FileInputStream(file);
                                FileOutputStream outputStream = new FileOutputStream(f);
                                copyStream(inputStream, outputStream);
                            }
                        }
                        Bitmap bitmap = ImageResizeUtils.resizeImage(f.getAbsolutePath(), 1080);
                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();
                        FileOutputStream fos = new FileOutputStream(f.getAbsolutePath());
                        if (bitmap != null) {

                            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fos)) {
                                fos.close();
                                fos.flush();
                            }
                            if (!bitmap.isRecycled()) {
                                bitmap.isRecycled();
                            }

                            System.out.println("f = " + f.length());
                            postFile(f);

                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }
                break;
            case INTENTFORCAMERA:
                try {
                    File file = new File(Utils.photoCacheDir, LocalPhotoName);
                    Bitmap bitmap = ImageResizeUtils.resizeImage(file.getAbsolutePath(), 1080);
                    int width = bitmap.getWidth();
                    int height = bitmap.getHeight();
                    FileOutputStream fos = new FileOutputStream(file.getAbsolutePath());
                    if (bitmap != null) {
                        if (bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fos)) {
                            fos.close();
                            fos.flush();
                        }
                        if (!bitmap.isRecycled()) {
                            bitmap.isRecycled();
                        }
                        System.out.println("f = " + file.length());

                        postFile(file);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public static void copyStream(InputStream inputStream, OutputStream outStream) throws Exception {
        try {
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                inputStream.close();
            }
            if(outStream != null){
                outStream.close();
            }
        }

    }

    public void post(){

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("username","1507D")
                .add("password","1507D")
                .add("postkey","1503")
                .build();


        Request request = new Request.Builder().url("http://qhb.2dyt.com/Bwei/login")
                .post(requestBody).build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                System.out.println("response = " + response.body().string());

            }
        });

    }

    public void cache(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int cacheSize = 10 * 1024 * 1024; // 10 MiB
                    Cache cache = new Cache(getCacheDir(), cacheSize);

                    OkHttpClient client = new OkHttpClient.Builder()
                            .addInterceptor(new Interceptor())
                            .cache(cache)
                            .build();
                    Request request = new Request.Builder()
                            .cacheControl(CacheControl.FORCE_NETWORK)
                            .url("http://publicobject.com/helloworld.txt")
                            .build();

                    String response1Body;
                    Response response1 = client.newCall(request).execute();

                    if (!response1.isSuccessful()) throw new IOException("Unexpected code " + response1);

                    response1Body = response1.body().string();
                    System.out.println("Response 1 response:          " + response1);
                    System.out.println("Response 1 cache response:    " + response1.cacheResponse());
                    System.out.println("Response 1 network response:  " + response1.networkResponse());

                    String response2Body;
//
// {
//                    Response response2 = client.newCall(request).execute();
//
//
////                   Call call1 =   client.newCall(request);
////
////                    call1.cancel();
//
//                    if (!response2.isSuccessful()) throw new IOException("Unexpected code " + response2);
//
//                    response2Body = response2.body().string();
//                    System.out.println("Response 2 response:          " + response2);
//                    System.out.println("Response 2 cache response:    " + response2.cacheResponse());
//                    System.out.println("Response 2 network response:  " + response2.networkResponse());
//
//
//                    System.out.println("Response 2 equals Response 1? " + response1Body.equals(response2Body));
//


                }catch (Exception e) {

                }

            }
        }).start();



    }

    public void percall(){

        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).build();

        OkHttpClient client1 = client.newBuilder().connectTimeout(10, TimeUnit.SECONDS).build();

        OkHttpClient client2 = client.newBuilder().connectTimeout(100, TimeUnit.SECONDS).build();

    }

}
