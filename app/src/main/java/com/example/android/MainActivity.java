package com.example.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareTextEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openWebsite(View view) {
        //Get a reference to the EditText instance and assign it to mWebsiteEditText
        mWebsiteEditText = findViewById(R.id.website_edittext);

        //Get the string value of the EditText
        String url = mWebsiteEditText.getText().toString();

        //Encode and parse that string into a Uri object
        Uri webpage = Uri.parse(url);

        //Create new Intent with Intent.ACTION_VIEW as the action and the URI as the data
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        //Make sure there is at least one Activity that can handle your requests.
        if (intent.resolveActivity(getPackageManager()) != null) {
            //Send the intent
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this!");
        }
    }

    public void openLocation(View view) {
        //Get a reference to the EditText instance and assign it to mLocationEditText
        mLocationEditText = findViewById(R.id.location_edittext);

        //Get the string value of the mLocationEditText EditText
        String loc = mLocationEditText.getText().toString();

        //Parse the string into a Uri object with a geo search query
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);

        //Create a new Intent with Intent.ACTION_VIEW as the action and loc as the data
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        //Make sure that the Intent resolved successfully
        //If so, startActivity(), otherwise log an error message
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    public void shareText(View view) {
        //Get a reference to the EditText instance and assign it to mShareTextEditText
        mShareTextEditText = findViewById(R.id.share_edittext);

        //Get the string value of the mShareTextEditText EditText
        String txt = mShareTextEditText.getText().toString();

        //Define the mime type of the text to share
        String mimeType = "text/plain";

        //Call ShareCompat.IntentBuilder with the following methods:
        //from(): the Activity that launches the share Intent
        //setType(): The Mime type of the item to be shared
        //setChooserTitle(): The title that appears on the system app chooser
        //setText(): The actual text to be shared
        //startChooser(): Show the system app chooser and send the Intent
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with: ")
                .setText(txt)
                .startChooser();
    }

    public void takePhoto(View view) {
        //Create a new Intent with Intent.ACTION_IMAGE_CAPTURE as the action
        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //Make sure that the Intent resolved successfully
        //If so, startActivity(), otherwise log an error message
        if (pictureIntent.resolveActivity(getPackageManager()) != null) {
            try {
                startActivity(pictureIntent);
            } catch (ActivityNotFoundException error) {
                // display error state to the user
            }
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }
}