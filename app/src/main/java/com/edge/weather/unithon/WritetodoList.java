package com.edge.weather.unithon;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

public class WritetodoList extends Activity {
EditText addtodotext;
ImageView addtodobutton;
DatePicker addtododate;
String todo;
int year,month,day;
ImageView Write_finish;
String token;
String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags  = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.7f;
        getWindow().setAttributes(layoutParams);
        setContentView(R.layout.activity_writetodo_list);
        addtodotext = (EditText)findViewById(R.id.addtodotext);
        addtodobutton = (ImageView)findViewById(R.id.addtodobutton);
        addtododate = (DatePicker)findViewById(R.id.addtododate);
        Write_finish=(ImageView)findViewById(R.id.Write_finish);
        Intent intent=getIntent();
        token=(String)intent.getExtras().get("token");
        email=(String)intent.getExtras().get("email");
        Write_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        addtodobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WritetodoList.this, ReservationActivity.class);
                   todo= addtodotext.getText().toString();
                   year = addtododate.getYear();
                   month =addtododate.getMonth()+1;
                   day = addtododate.getDayOfMonth();
                intent.putExtra("Todo",todo+"");
                intent.putExtra("date",year+"-"+month+"-"+day);
                intent.putExtra("token",token+"");
                intent.putExtra("email",email+"");
                startActivity(intent);
                finish();

            }
        });
    }

}
