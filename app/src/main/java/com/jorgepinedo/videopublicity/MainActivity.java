package com.jorgepinedo.videopublicity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    VideoView video;
    List<Images> listImages;
    int counter=0;
    ConstraintLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        video =findViewById(R.id.video);
        container =findViewById(R.id.container);

        listImages = new ArrayList<>();

        getImages();

        startVideo(listImages.get(counter).getUrl());


        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                video.stopPlayback();
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });

    }

    public void getImages(){
        listImages.add(new Images("https://spad-media.s3.us-east-2.amazonaws.com/publicity/Lanzamiento_Cheetos_Popcorn_Chocolate_Spot_15_segundos.mp4"));
        listImages.add(new Images("https://spad-media.s3.us-east-2.amazonaws.com/publicity/Nueva_campaa_de_publicidad_de_Royal_Prestige_15_segundos.mp4"));
        listImages.add(new Images("https://spad-media.s3.us-east-2.amazonaws.com/publicity/Publicidad_Pecho_Fro_30_segundos.mp4"));
    }

    public void startVideo(String link){
        Log.d("JORKE11",link);
        Uri vidUri = Uri.parse(link);

        video.setVideoURI(vidUri);

        MediaController vidControl = new MediaController(getApplicationContext());

        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                counter++;
                Log.d("JORKE11","FINISIHS COMERCIAL");

                Log.d("JORKE11"," "+counter);
                Log.d("JORKE11"," size "+listImages.size());

                if(counter == listImages.size()){
                    counter=0;
                }

                startVideo(listImages.get(counter).getUrl());

            }
        });


        video.start();
    }
}
