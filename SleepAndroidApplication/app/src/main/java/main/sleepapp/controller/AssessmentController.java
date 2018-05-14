package main.sleepapp.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.DateInterval;
import android.os.Build;
import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import main.sleepapp.model.MeetingModel;
import main.sleepapp.model.SleepModel;
import main.sleepapp.model.StudentModel;
import main.sleepapp.util.SleepModelList;

public class AssessmentController{

    private String result;
    private Context context;
    private StudentModel studentModel;

    public AssessmentController(StudentModel studentModel, Context context) {
        this.context = context;
        this.studentModel = studentModel;
        handleData(new SleepModelList(studentModel).sleepModelList);
    }

    private void handleData(List<SleepModel> sleepModelList) {
        ArrayList<Date> sleepTime = new ArrayList<>();
        ArrayList<Date> awokeTime = new ArrayList<>();
        ArrayList<Long> diffTime = new ArrayList<>();
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdfDate.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        Long diff = 0L;
        for(SleepModel sleepModel: sleepModelList) {
            try {
                sleepTime.add(sdfDate.parse(sleepModel.getSleep_time()));
                awokeTime.add(sdfDate.parse(sleepModel.getAwoke_time()));
            } catch (ParseException e) {
                System.out.println("DATES IS WRONG");
            }
        }
        for (int i = 0; i < sleepTime.size(); i++) {
            diffTime.add((sleepTime.get(i).getTime() - awokeTime.get(i).getTime())*-1);
            diff += diffTime.get(i);
        }
        diff = diff/diffTime.size();
        if (diff < 28800000L) {
            System.out.println(diff);
            alertStudent();
        }


    }

    public void alertStudent() {
        if (!new MeetingModel().checkModel(studentModel)) {

            final AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(context);
            }
            builder.setTitle("Møde")
                    .setMessage("Du sover for lidt. Vil du have et møde?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            if(!new MeetingModel().checkModel(studentModel)) {
                                Intent intent = new Intent(context, MeetingController.class);
                                intent.putExtra("studentID",studentModel.getStudent_id());
                                context.startActivity(intent);
                            }

                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

}
