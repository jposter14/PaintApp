package com.example.jackieposter.paintapp;

import android.content.DialogInterface;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drawing)
    DrawView drawView;

    private int red = Color.rgb(255,0,0);
    private int orange = Color.rgb(255,127,0);
    private int yellow = Color.rgb(255,255,0);
    private int green = Color.rgb(116,219,33);
    private int blue = Color.rgb(0,0,255);
    private int purple = Color.rgb(127,0,127);
    private int black = Color.rgb(0,0,0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.penSmall)
    public void penSmallClicked() {
        int smallBrush = getResources().getInteger(R.integer.small_size);
        drawView.setErase(false);
        drawView.setPenWidth(smallBrush);
    }

    @OnClick(R.id.penMedium)
    public void penMediumClicked() {
        int mediumBrush = getResources().getInteger(R.integer.medium_size);
        drawView.setErase(false);
        drawView.setPenWidth(mediumBrush);
    }

    @OnClick(R.id.penLarge)
    public void penLargeClicked() {
        int largeBrush = getResources().getInteger(R.integer.large_size);
        drawView.setErase(false);
        drawView.setPenWidth(largeBrush);
    }

    @OnClick(R.id.redFab)
    public void onRedClicked(){
        drawView.setErase(false);
        drawView.setPenColor(red);
    }

    @OnClick(R.id.orangeFab)
    public void onOrangeClicked(){
        drawView.setErase(false);
        drawView.setPenColor(orange);
    }

    @OnClick(R.id.yellowFab)
    public void onYellowClicked(){
        drawView.setErase(false);
        drawView.setPenColor(yellow);
    }

    @OnClick(R.id.greenFab)
    public void onGreenClicked(){
        drawView.setErase(false);
        drawView.setPenColor(green);
    }

    @OnClick(R.id.blueFab)
    public void onBlueClicked() {
        drawView.setErase(false);
        drawView.setPenColor(blue);
    }

    @OnClick(R.id.purpleFab)
    public void onPurpleClicked(){
        drawView.setErase(false);
        drawView.setPenColor(purple);
    }

    @OnClick(R.id.blackFab)
    public void onBlackClicked(){
        drawView.setErase(false);
        drawView.setPenColor(black);
    }

    @OnClick(R.id.erase)
    public void onEraseClicked(){
        drawView.setErase(true);
    }

    @OnClick(R.id.newDrawing)
    public void onNewDrawingClicked(){
        AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
        newDialog.setTitle("New drawing");
        newDialog.setMessage("Start new drawing (you will lose the current drawing)?");
        newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                drawView.startNew();
                dialog.dismiss();
            }
        });
        newDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        newDialog.show();
    }

    @OnClick(R.id.save)
    public void onSaveClicked() {
        AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
        saveDialog.setTitle("Save drawing");
        saveDialog.setMessage("Save drawing to device Gallery?");
        saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                //save drawing
                drawView.setDrawingCacheEnabled(true);

                String imgSaved = MediaStore.Images.Media.insertImage(
                        getContentResolver(), drawView.getDrawingCache(),
                        UUID.randomUUID().toString()+".png", "drawing");

                if(imgSaved!=null){
                    Toast savedToast = Toast.makeText(getApplicationContext(),
                            "Drawing saved to Gallery!", Toast.LENGTH_SHORT);
                    savedToast.show();
                }
                else{
                    Toast unsavedToast = Toast.makeText(getApplicationContext(),
                            "Oops! Image could not be saved.", Toast.LENGTH_SHORT);
                    unsavedToast.show();
                }

                drawView.destroyDrawingCache();
            }

        });
        saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        saveDialog.show();
    }
}
