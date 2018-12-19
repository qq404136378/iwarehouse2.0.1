package com.prt.iwarehouse.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by 锴锴兴 on 2018/11/29.
 */

public class RecordLog<T> {
    private RecordLog(){

    }
    public static RecordLog getInstance(){
       return singleInstance.recordLog;
    }
    private static  class  singleInstance{
        private  static  final RecordLog recordLog=new RecordLog();
    }

    public List<String> checkLogList(){
        @SuppressLint("SdCardPath")
         File[] file=new File("/data/data/com.prt.iwarehouse/files/").listFiles();
        List<String> fileList=new ArrayList<String>();
        if(file!=null) {
            for (File file1 : file) {
                fileList.add(file1.getName());
            }
        }
        return  fileList;
    }
    public StringBuilder checkLogInfo(final String logDate) {
        StringBuilder sb = new StringBuilder();
                try {
                    @SuppressLint("SdCardPath")
                    FileReader reader = new FileReader("/data/data/com.prt.iwarehouse/files/" + logDate);
                    BufferedReader reader1 = new BufferedReader(reader);
                    String line = reader1.readLine();
                    while (line != null) {
                        sb.insert(0, line + "\n");
                        line = reader1.readLine();
                        Thread.sleep(1);
                    }
                    reader1.close();
                    reader.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        return   sb;
    }
    public void record(final T t){
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Calendar calendar=Calendar.getInstance();
                    FileOutputStream fileOutputStream= AppContext
                            .getInstance()
                            .getApplicationContext()
                            .openFileOutput("LOG"+calendar.get(Calendar.MONTH)+calendar.get(Calendar.DATE)+".TXT", Context.MODE_APPEND);
                    String log=""+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DATE)+" "
                            +calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+":"
                            +calendar.get(Calendar.SECOND)+"\n"+(t!=null?t.toString():null)+"\n"
                            +Thread.currentThread().getName()+"\n";
                    fileOutputStream.write(log.getBytes("UTF-8"));
                    fileOutputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/

    }
    public void record(String url,String buffer){
        /*FileOutputStream fileOutputStream = null;
        Calendar calendar = Calendar.getInstance();
        try {
            fileOutputStream = AppContext
                    .getInstance()
                    .getApplicationContext()
                    .openFileOutput("LOG"+calendar.get(Calendar.MONTH)+calendar.get(Calendar.DATE)+".TXT", Context.MODE_APPEND);
            String log = "" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DATE) + " "
                    + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":"
                    + calendar.get(Calendar.SECOND) + "\n" + url+"\n"
                    +"\n"+buffer
                    +  "\n"+
                    Thread.currentThread().getName()+"\n";
            fileOutputStream.write(log.getBytes("UTF-8"));
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
    public void deleteLogAutomation(){
        Calendar calendar=Calendar.getInstance();
        int month=calendar.get(Calendar.MONTH)==1?12:calendar.get(Calendar.MONTH)-1;
        //  int month=calendar.get(Calendar.MONTH);
        int date=calendar.get(Calendar.DATE);
        @SuppressLint("SdCardPath")
        File file=new File("/data/data/com.prt.iwarehouse/files/"+"LOG"+month+date+".TXT");
        if(file.exists()) {
            file.delete();
            Log.i("文件操作","文件已删除");
        }
    }
    public void deleteLogHand(String fileName){
        @SuppressLint("SdCardPath")
        File file=new File("/data/data/com.prt.iwarehouse/files/"+fileName);
        file.delete();
    }
}
