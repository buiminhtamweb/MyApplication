package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Main2Activity extends AppCompatActivity {

    String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


    }


    public <T> T createService(Class<T> serviceClass, String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(new OkHttpClient.Builder().build())
                .build();
        return retrofit.create(serviceClass);
    }

//    private void downloadZipFile() {
//
//        RetrofitInterface downloadService = createService(RetrofitInterface.class, "https://github.com/");
//        Call<ResponseBody> call = downloadService.downloadFileByUrl("anupamchugh/AnimateTextAndImageView/archive/master.zip");
//
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
//                if (response.isSuccessful()) {
//                    Log.d(TAG, "Got the body for the file");
//
//                    Toast.makeText(getApplicationContext(), "Downloading...", Toast.LENGTH_SHORT).show();
//
//                    downloadZipFileTask = new DownloadZipFileTask();
//                    downloadZipFileTask.execute(response.body());
//
//                } else {
//                    Log.d(TAG, "Connection failed " + response.errorBody());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                t.printStackTrace();
//                Log.e(TAG, t.getMessage());
//            }
//        });
//    }


//    private class DownloadZipFileTask extends AsyncTask<ResponseBody, Pair<Integer, Long>, String> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//        }
//
//        @Override
//        protected String doInBackground(ResponseBody... urls) {
//            //Copy you logic to calculate progress and call
//            saveToDisk(urls[0], "journaldev-project.zip");
//            return null;
//        }
//
//        protected void onProgressUpdate(Pair<Integer, Long>... progress) {
//
//            Log.d("API123", progress[0].second + " ");
//
//            if (progress[0].first == 100)
//                Toast.makeText(getApplicationContext(), "File downloaded successfully", Toast.LENGTH_SHORT).show();
//
//
//            if (progress[0].second > 0) {
//                int currentProgress = (int) ((double) progress[0].first / (double) progress[0].second * 100);
//                progressBar.setProgress(currentProgress);
//                txtProgressPercent.setText("Progress " + currentProgress + "%");
//
//            }
//
//            if (progress[0].first == -1) {
//                Toast.makeText(getApplicationContext(), "Download failed", Toast.LENGTH_SHORT).show();
//            }
//
//        }
//
//        public void doProgress(Pair<Integer, Long> progressDetails) {
//            publishProgress(progressDetails);
//
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//
//        }
//    }

//    private void saveToDisk(ResponseBody body, String filename) {
//        try {
//
//            File destinationFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), filename);
//
//            InputStream inputStream = null;
//            OutputStream outputStream = null;
//
//            try {
//
//                inputStream = body.byteStream();
//                try {
//                    outputStream = new FileOutputStream(destinationFile);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//                byte data[] = new byte[4096];
//                int count;
//                int progress = 0;
//                long fileSize = body.contentLength();
//                Log.d(TAG, "File Size=" + fileSize);
//                while ((count = inputStream.read(data)) != -1) {
//                    outputStream.write(data, 0, count);
//                    progress += count;
//                    Pair<Integer, Long> pairs = new Pair<>(progress, fileSize);
//                    downloadZipFileTask.doProgress(pairs);
//                    Log.d(TAG, "Progress: " + progress + "/" + fileSize + " >>>> " + (float) progress / fileSize);
//                }
//
//                outputStream.flush();
//
//                Log.d(TAG, destinationFile.getParent());
//                Pair<Integer, Long> pairs = new Pair<>(100, 100L);
//                downloadZipFileTask.doProgress(pairs);
//                return;
//            } catch (IOException e) {
//                e.printStackTrace();
//                Pair<Integer, Long> pairs = new Pair<>(-1, Long.valueOf(-1));
//                downloadZipFileTask.doProgress(pairs);
//                Log.d(TAG, "Failed to save the file!");
//                return;
//            } finally {
//                if (inputStream != null) inputStream.close();
//                if (outputStream != null) outputStream.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            Log.d(TAG, "Failed to save the file!");
//            return;
//        }
//    }

    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);

            } else {
                ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
            }
        } else if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
            Toast.makeText(getApplicationContext(), "Permission was denied", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED) {

            if (requestCode == 101)
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }
}
