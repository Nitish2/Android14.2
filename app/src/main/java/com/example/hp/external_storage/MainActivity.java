package com.example.hp.external_storage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;


public class MainActivity extends AppCompatActivity {
    // Declaring variables
    String extStorageDirectory;
    Bitmap bm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Permission for External stotage
        ActivityCompat.requestPermissions(MainActivity.this,new String[]
                {android.Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        // Creating and initializing objects by ID
        Button button = (Button)findViewById(R.id.button);
        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        /*
         * BitmapFactory creates Bitmap objects from various sources.
         * Creating bitmap object
         */

        bm = BitmapFactory.decodeResource( getResources(), R.drawable.android);
        imageView.setImageBitmap(bm);

        /*
         * Environment provides access to environment variables.
         * getExternalStorageDirectory() will return the primary external storage directory.
         */
        extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        button.setOnClickListener(buttonSaveOnClickListener);  // Calling OnClickListener Method
    }


    Button.OnClickListener buttonSaveOnClickListener // Creating onClickListener method
            = new Button.OnClickListener(){

        @Override
        public void onClick(View arg0) {
            //  An OutputStream accepts output bytes and sends them to some sink.
            OutputStream outStream = null;
            File file = new File(extStorageDirectory, "My image.PNG");// Creating new file
            try {
                /*
                 * Bitmap.CompressFormat is the format of the compressed image
                 * 100 is Hint to the compressor.
                 * outputStream The outputstream to write the compressed data.
                 */
                outStream = new FileOutputStream(file);
                bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);

                /*
                 * flush()Flushes this output stream and forces any buffered output bytes
                   to be written out.
                 * close()closes this output stream and releases any system resources
                   associated with this stream.
                  */
                outStream.flush();
                outStream.close();

                Toast.makeText(MainActivity.this, "Saved",
                        Toast.LENGTH_LONG).show();//Toast Message

            } catch (Exception e) { // Catch statement
                e.printStackTrace();
                Toast.makeText(MainActivity.this, e.toString(),
                        Toast.LENGTH_LONG).show();//Toast Message
            }

        }

    };

}
