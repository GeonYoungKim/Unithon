package com.edge.weather.unithon;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.net.HttpURLConnection;

public class ReservationActivity extends Activity {
ImageView reservation;
ImageView reservation_cancle;

String todo;

String date;
String email;
String token;
String until;
JsonObject jsonObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Gson gson=new Gson();

        requestWindowFeature(Window.FEATURE_NO_TITLE);


        Intent intent=getIntent();
        todo=(String)intent.getExtras().get("Todo");
        date=(String)intent.getExtras().get("date");
        email=(String)intent.getExtras().get("email");
        token=(String) intent.getExtras().get("token");

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags  = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 15f;
        getWindow().setAttributes(layoutParams);
        setContentView(R.layout.activity_reservation);
        reservation=(ImageView)findViewById(R.id.reservation);
        reservation_cancle=(ImageView)findViewById(R.id.reservation_cancle);
        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*new CreateTodo().execute();*/
                JsonObject jsonObject=new JsonObject();
                jsonObject.addProperty("title",todo);
                jsonObject.addProperty("done",false);
                jsonObject.addProperty("date",date);
                MainActivity.toDOViewAdapter.addItem(jsonObject);
                CalendarCall calendarCall=new CalendarCall();
                calendarCall.setAccess_token(token);
                calendarCall.setStart_day("20180128");
                calendarCall.setEnd_day(date.replaceAll("-","")+"");
                calendarCall.setTitle(todo);
                calendarCall.setEmail(email);
                calendarCall.setCal_id("asdfasdfffff");
                calendarCall.execute();
                calendarCall=new CalendarCall();
                calendarCall.setAccess_token(token);
                calendarCall.setStart_day("20180128");
                calendarCall.setEnd_day(date.replaceAll("-","")+"");
                calendarCall.setTitle(todo);
                calendarCall.setEmail(email);
                calendarCall.setCal_id("asdfasdfffff");
                calendarCall.execute();
                finish();
            }
        });
        reservation_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}
