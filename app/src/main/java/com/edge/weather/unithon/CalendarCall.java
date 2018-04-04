package com.edge.weather.unithon;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by c2619 on 2018-01-27.
 */

public class CalendarCall extends AsyncTask<Void, Void, String> {
    String result="";
    private String accessToken;
    private String startDay;
    private String endDay;
    private String title;
    private String email;
    private String calulatorId;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCalulatorId() {
        return calulatorId;
    }

    public void setCalulatorId(String calulatorId) {
        this.calulatorId = calulatorId;
    }

    @Override
    protected void onPreExecute() {
        //mApiResultText.setText((String) "");
    }
    @Override
    protected String doInBackground(Void... params) {
        String token = accessToken;
        String header = "Bearer " + token; // Bearer 다음에 공백 추가
        try {
            String apiURL = "https://openapi.naver.com/calendar/createSchedule.json";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", header);
            String calSum =  URLEncoder.encode(title, "UTF-8");
            String uid = "abcdefg112";
            String scheduleIcalString = "BEGIN:VCALENDAR\n" +
                    "VERSION:2.0\n" +
                    "PRODID:Naver Calendar\n" +
                    "CALSCALE:GREGORIAN\n" +
                    "BEGIN:VTIMEZONE\n" +
                    "TZID:Asia/Seoul\n" +
                    "BEGIN:STANDARD\n" +
                    "DTSTART:19700101T000000\n" +
                    "TZNAME:GMT%2B09:00\n" +
                    "TZOFFSETFROM:%2B0900\n" +
                    "TZOFFSETTO:%2B0900\n" +
                    "END:STANDARD\n" +
                    "END:VTIMEZONE\n" +
                    "BEGIN:VEVENT\n" +
                    "SEQUENCE:0\n" +
                    "CLASS:PUBLIC\n" +
                    "TRANSP:OPAQUE\n" +
                    "UID:" + calulatorId + "\n" +                          // 일정 고유 아이디
                    "DTSTART;TZID=Asia/Seoul:"+startDay+"T170000\n" +  // 시작 일시
                    "DTEND;TZID=Asia/Seoul:"+endDay+"T173000\n" +    // 종료 일시
                    "SUMMARY:"+ calSum +" \n" +                    // 일정 제목
                    "ORGANIZER;CN=관리자:mailto:"+email+"\n" + // 일정 만든 사람
                    "ATTENDEE;ROLE=REQ-PARTICIPANT;PARTSTAT=NEEDS-ACTION;CN=admin:mailto:"+email+"\n" + // 참석자
                    "CREATED:20171116T160000\n" +         // 일정 생성시각
                    "LAST-MODIFIED:20171116T160000\n" +   // 일정 수정시각
                    "DTSTAMP:20161116T160000\n" +         // 일정 타임스탬프
                    "END:VEVENT\n" +
                    "END:VCALENDAR";
            String postParams = "calendarId=defaultCalendarId&scheduleIcalString=" + scheduleIcalString;
            System.out.println(postParams);
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            result=response.toString();
            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    protected void onPostExecute(String content) {

        //mApiResultText.setText(content);
    }
}