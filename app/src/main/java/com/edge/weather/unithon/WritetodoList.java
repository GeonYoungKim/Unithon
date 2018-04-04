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
EditText addTodoText;
ImageView addTodoButton;
DatePicker addTodoDate;
String todo;
int year,month,day;
ImageView writeFinish;
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
        addTodoText = (EditText)findViewById(R.id.addtodotext);
        addTodoButton = (ImageView)findViewById(R.id.addtodobutton);
        addTodoDate = (DatePicker)findViewById(R.id.addtododate);
        writeFinish=(ImageView)findViewById(R.id.Write_finish);
        Intent intent=getIntent();
        token=(String)intent.getExtras().get("token");
        email=(String)intent.getExtras().get("email");
        writeFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        addTodoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WritetodoList.this, ReservationActivity.class);
                   todo= addTodoText.getText().toString();
                   year = addTodoDate.getYear();
                   month =addTodoDate.getMonth()+1;
                   day = addTodoDate.getDayOfMonth();
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
