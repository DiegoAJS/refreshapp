package org.developerjs.refreshapp.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;


import org.developerjs.refreshapp.R;

import java.io.File;

/**
 * Created by hp on 9/6/2017.
 */

public class IntentUtiles {

    public static void intentTelefonos(Context context, String telefono){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+ telefono));
        context.startActivity(intent);
    }

    public static void intentSMS(Context context, String numero,String texto){
        Uri uri = Uri.parse("smsto:"+ numero);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", texto);
        context.startActivity(intent);
    }

    public static void intentWeb(Context context, String url){

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);

    }

    public static void intentEmail(Context context, String email,String text){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",email, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name));
        emailIntent.putExtra(Intent.EXTRA_TEXT, text);
        context.startActivity(Intent.createChooser(emailIntent, context.getString(R.string.sendemail)));
    }

    public static void intentShareText(Context context, String texto){

        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_TEXT,texto);
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name));
        context.startActivity(Intent.createChooser(sharingIntent, context.getString(R.string.share)));

    }

    public static void intentShareImagenText(Context context, String imagen, String texto){

        Intent share = new Intent(Intent.ACTION_SEND);

        // If you want to share a png image only, you can do:
        // setType("image/png"); OR for jpeg: setType("image/jpeg");
        share.setType("image/*");

        // Make sure you put example png image named myImage.png in your
        // directory
        //String imagePath = Environment.getExternalStorageDirectory()+ "/myImage.png";

        //File imageFileToShare = new File(imagen);

        Uri uri = Uri.parse(imagen);
        share.putExtra(Intent.EXTRA_STREAM, uri);

        context.startActivity(Intent.createChooser(share, "Share Image!"));

    }

}
