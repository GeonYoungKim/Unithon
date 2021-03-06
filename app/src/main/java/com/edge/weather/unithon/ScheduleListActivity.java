package com.edge.weather.unithon;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ssomai.android.scalablelayout.ScalableLayout;

import java.util.ArrayList;
import java.util.Calendar;

public class ScheduleListActivity extends AppCompatActivity implements View.OnClickListener{

    private enum DayOfTheWeek {
        SUN, MON, TUE, WED, THU, FRI, SAT
    }
    private final String PM_COLOR = "#FF4000";
    private final String BM_COLOR = "#FF8000";

    private Context context;
    private TextView currentDate;
    private GridView gridView;
    /**
     * Clalendar Instance를 이용해서 지정된 Month의 day를 순서대로 세팅
     *
     * */

    private ArrayList<DayVo> dayList;
    private Calendar calendar;
    private ScheduleCalendarAdapter adapter;
    private String today;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_list);

        this.context = getApplicationContext();
        currentDate = (TextView) findViewById(R.id.date);
        calendar = Calendar.getInstance();
        gridView = (GridView) findViewById(R.id.sCal_calGrid);
        Button prevButton = (Button) findViewById(R.id.sCal_prevButton);
        Button nextButton = (Button) findViewById(R.id.sCal_nextButton);
        prevButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);

        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        StringBuffer buffer = new StringBuffer();
        buffer.append(currentYear);
        buffer.append(currentMonth);
        buffer.append(calendar.get(Calendar.DATE));
        today = buffer.toString();

        /* gridview 상단에 요일 표시 */
        ScalableLayout scalableLayout = new ScalableLayout(context, 70, 7);
        TextView sunTv = createGridViewTop(DayOfTheWeek.SUN.name());
        scalableLayout.addView(sunTv, 0, 0, 10, 10);
        scalableLayout.setScale_TextSize(sunTv, 2);
        TextView monTv = createGridViewTop(DayOfTheWeek.MON.name());
        scalableLayout.addView(monTv, 10, 0, 10, 10);
        scalableLayout.setScale_TextSize(monTv, 2);
        TextView tueTv = createGridViewTop(DayOfTheWeek.TUE.name());
        scalableLayout.addView(tueTv, 20, 0, 10, 10);
        scalableLayout.setScale_TextSize(tueTv, 2);
        TextView webTv = createGridViewTop(DayOfTheWeek.WED.name());
        scalableLayout.addView(webTv, 30, 0, 10, 10);
        scalableLayout.setScale_TextSize(webTv, 2);
        TextView thuTv = createGridViewTop(DayOfTheWeek.THU.name());
        scalableLayout.addView(thuTv, 40, 0, 10, 10);
        scalableLayout.setScale_TextSize(thuTv, 2);
        TextView friTv = createGridViewTop(DayOfTheWeek.FRI.name());
        scalableLayout.addView(friTv, 50, 0, 10, 10);
        scalableLayout.setScale_TextSize(friTv, 2);
        TextView satTv = createGridViewTop(DayOfTheWeek.SAT.name());
        scalableLayout.addView(satTv, 60, 0, 10, 10);
        scalableLayout.setScale_TextSize(satTv, 2);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.gridViewContainer);
        linearLayout.addView(scalableLayout, 0);

        refreshAdapter(0);
    }
    private TextView createGridViewTop(String dayOfTheWeek) {
        TextView tv = new TextView(context);
        tv.setText(dayOfTheWeek);
        tv.setTypeface(null, Typeface.BOLD);
        tv.setGravity(Gravity.CENTER);
        return tv;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sCal_prevButton :
                refreshAdapter(-1);
                break;
            case R.id.sCal_nextButton :
                refreshAdapter(1);
                break;
        }
    }

    /**
     * setting adapter
     *
     * */
    public void refreshAdapter(int value) {
        dayList = new ArrayList<DayVo>();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, value);
        /* 선택된 년월 첫주의 요일을 구한다. */
        int dayNum = calendar.get(Calendar.DAY_OF_WEEK);
        /* 시작일 전은 공백으로 처리 */
        for (int i=1; i<dayNum; i++) {
            DayVo vo = new DayVo();
            vo.setDay("");
            dayList.add(vo);
        }

        for (int i= 0; i<calendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            DayVo vo = new DayVo();
            vo.setDay(String.valueOf(i + 1));
            dayList.add(vo);
        }

        ArrayList<DayVo> scheduleList = getSchedule();
        for(DayVo schedule : scheduleList) {
            int sDay = Integer.parseInt(schedule.getDay());
            for(DayVo vo : dayList) {
                int day;
                try {
                    day = Integer.parseInt(vo.getDay());
                } catch (NumberFormatException e) {
                    continue;
                }
                if (sDay == day) {
                    vo.setScheduleList(new ArrayList<DateVo>());
                    for(int i=0; i<schedule.getScheduleList().size(); i++) {
                        DateVo sv = schedule.getScheduleList().get(i);
                        vo.getScheduleList().add(sv);
                    }
                }
            }
        }
        currentDate.setText(calendar.get(Calendar.YEAR) + "년 " + (calendar.get(Calendar.MONTH) + 1) + "월");
        adapter = new ScheduleCalendarAdapter();
        gridView.setAdapter(adapter);
    }

    /**
     * get Schedule form server
     *
     * */
    private ArrayList<DayVo> getSchedule() {
        ArrayList<DayVo> list = new ArrayList<>();
        /* 이 부분에서 스케줄을 읽어 옵니다. */
        return list;
    }

    public class ScheduleCalendarAdapter extends BaseAdapter implements View.OnClickListener {

        private LayoutInflater inflater;
        private class ViewHolder {
            public TextView day;
            public TextView title;
        }

        public ScheduleCalendarAdapter() {
            inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return dayList.size();
        }

        @Override
        public Object getItem(int i) {
            return dayList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.empty_layout, viewGroup, false);
                Context ctx = convertView.getContext();
                ScalableLayout scalableLayout = new ScalableLayout(ctx, 80, 155);
                TextView dayTv = new TextView(ctx);
                TextView title = new TextView(ctx);
                dayTv.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);
                title.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);
                scalableLayout.addView(dayTv, 0, 0, 80, 25);
                scalableLayout.addView(title, 0, 20, 80, 25);
                scalableLayout.setScale_TextSize(dayTv, 20);
                scalableLayout.setScale_TextSize(title, 20);
                ((LinearLayout) convertView).addView(scalableLayout);
                holder = new ViewHolder();

                holder.day = dayTv;
                holder.title=title;
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }

            final DayVo vo = dayList.get(position);
            holder.day.setText(vo.getDay());

            if(!(vo.getDay().equals(""))){
                holder.title.setText("title");
            }

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(),vo.getDay()+"",Toast.LENGTH_SHORT).show();
                }
            });

            if(vo.getDay().equals("")) {
                convertView.setClickable(false);
            }

            /* setting text color of weekend */
            if (position % 7 == DayOfTheWeek.SAT.ordinal()) {
                holder.day.setTextColor(Color.parseColor("#2E64FE"));
            } else if (position % 7 == DayOfTheWeek.SUN.ordinal()) {
                holder.day.setTextColor(Color.RED);
            }
            /* setting schedule list of day */
            if(vo.getScheduleList() != null) {
                LinearLayout linearLayout = (LinearLayout) convertView;
                ScalableLayout scalableLayout = (ScalableLayout) linearLayout.getChildAt(0);
                int scheduleCnt = vo.getScheduleList().size();
                for(int i=0; i<scheduleCnt; i++) {
                    DateVo sv = vo.getScheduleList().get(i);
                    TextView scheduleTv = new TextView(convertView.getContext());
                    scheduleTv.setGravity(Gravity.CENTER_VERTICAL);
                    if(i == 2) {
                        scalableLayout.addView(scheduleTv, 0, 70, 80, 25);
                        scalableLayout.setScale_TextSize(scheduleTv, 15);
                        scheduleTv.setText("TOTAL : " + String.valueOf(scheduleCnt+1));
                        break;
                    }
                    scheduleTv.setTextColor(Color.WHITE);
                    scheduleTv.setSingleLine();
                    scalableLayout.addView(scheduleTv, 0, 25+(i*25), 80, 25);
                    scalableLayout.setScale_TextSize(scheduleTv, 15);
                    if(sv.getType().equals("PM")) {
                        scheduleTv.setBackgroundColor(Color.parseColor(PM_COLOR));
                    } else if(sv.getType().equals("BM")) {
                        scheduleTv.setBackgroundColor(Color.parseColor(BM_COLOR));
                    }
                    scheduleTv.setText(sv.getName());
                }
            }

            if(today.equals(calendar.get(Calendar.YEAR)+""+(calendar.get(Calendar.MONTH) + 1)+""+vo.getDay())) {
                holder.day.setText("★"+vo.getDay());
            }
            return convertView;
        }

        @Override
        public void onClick(View v) {

        }
    }
}
