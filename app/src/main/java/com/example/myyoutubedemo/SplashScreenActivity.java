package com.example.myyoutubedemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myyoutubedemo.db.myDbAdapter;

public class SplashScreenActivity extends AppCompatActivity {
    myDbAdapter helper;
    private static int SPLASH_TIME_OUT = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_layout);


        helper = new myDbAdapter(this);
        helper.deleteAllAtricles();

//        helper.deleteDb(this);
//
//        if (helper.getSearchResult()!=null)
//        {
//
//            Log.e("enter","enter1");
//
//            Log.e("SearchResult",helper.getSearchResult());
//        }
//        else
//        {
//            Log.e("enter2","enter12");
            helper.insertData("Extraction | Official Trailer | Netflix","rtsp://r1---sn-npoe7ned.googlevideo.com/Cj0LENy73wIaNAlWnpWOnPejLxMYESARFC0MQpleMOCoAUIASARg15zo1fOlh8BeigELcFl6UUFiSFE3SkUM/C087D3747EDE68ECB0117B116F1386799927D27B.B5125157131C2AE2F5FF765B548DDE3E17138CB7/yt8/1/video.3gp","NetFlix","Apr 7, 2020","3,808,912 views","0");
            helper.insertData("Android RxJava Networking with Retrofit, Gson Notes App","rtsp://r2---sn-npoe7ne7.googlevideo.com/Cj0LENy73wIaNAnlFp3NeFYAtRMYESARFC06QpleMOCoAUIASARg15zo1fOlh8BeigELcFl6UUFiSFE3SkUM/C4BE43F4AB77EE81C95D13B4C1B3CB4E33765806.85F35721DBF6C9D01E8230DCE589DD60D7D24DC0/yt8/1/video.3gp","Android Archeive","Apr 7, 2020","3,808,912 views","0");
            helper.insertData("Ronny Bhaiya meets Kaleen Bhaiya | Amazon Prime Video","rtsp://r4---sn-npoeene6.googlevideo.com/Cj0LENy73wIaNAkbmdX2yUKLyhMYESARFC1VQpleMOCoAUIASARg15zo1fOlh8BeigELcFl6UUFiSFE3SkUM/5CD8756C690766B33E39AF9AA71AAD5341AD8BA3.1723F3489B23BD0AB5AC3570B85B7BF36D171461/yt8/1/video.3gp","Zaik Khan","Apr 7, 2020","3,808,912 views","0");
            helper.insertData("Maze Toh Couple Kar Rahe Hain","rtsp://r4---sn-npoe7ne6.googlevideo.com/Cj0LENy73wIaNAnBw26NCXT7WhMYESARFC16QpleMOCoAUIASARg15zo1fOlh8BeigELcFl6UUFiSFE3SkUM/ADF284B11BF31AD65BFC57D159406A4BFBA4CA06.DDFB4D655781D118A54465D1711E7FC598A9A611/yt8/1/video.3gp","Zaik Khan","Apr 7, 2020","3,808,912 views","0");
////
////            Log.e("SearchResult",helper.getSearchResult());
//        }

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                    Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(i);



                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
