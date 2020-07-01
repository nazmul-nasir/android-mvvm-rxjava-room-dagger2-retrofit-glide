package com.nasir.demo.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.nasir.demo.R;

import java.util.Arrays;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CommonUtils {

    private CommonUtils() {
    }

    public static int getRandomPageNumber() {
        int min = 1;
        int max = 100;
        Random random = new Random();
        return random.nextInt(max) + min;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static boolean isImageUrl(String url) {
        String[] items = {"jpg", "png", "gif", "jpeg", "svg"};
        return Arrays.stream(items).parallel().anyMatch(url::contains);
    }

    public static boolean isYoutubeUrl(String youTubeURl) {
        boolean success;
        String pattern = "^(http(s)?:\\/\\/)?((w){3}.)?youtu(be|.be)?(\\.com)?\\/.+";
        // Not Valid youtube URL
        success = !youTubeURl.isEmpty() && youTubeURl.matches(pattern);
        return success;
    }
    public static String getYoutubeIdFromUrl(String ytUrl) {
        String vId = null;
        Pattern pattern = Pattern.compile(
                "^https?://.*(?:youtu.be/|v/|u/\\w/|embed/|watch?v=)([^#&?]*).*$",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(ytUrl);
        if (matcher.matches()) {
            vId = matcher.group(1);
        }
        return vId;
    }

    public static String getYoutubeVideoThumbnailFromId(String id) {
        return "https://img.youtube.com/vi/" + id + "/hqdefault.jpg";
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }
}
