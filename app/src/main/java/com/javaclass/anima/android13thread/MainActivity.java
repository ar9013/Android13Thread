package com.javaclass.anima.android13thread;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    View btnThread1,btnThread2;
    TextView tvMessage;
    MyHandler handler;
    MyThread t1,t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new MyHandler();

        btnThread1 = findViewById(R.id.btnThread1);
        btnThread2 = findViewById(R.id.btnThread2);

        tvMessage = (TextView) findViewById(R.id.message);

        btnThread1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doThread1();
            }
        });

        btnThread2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopThread1();
            }
        });

    }

   private void doThread1(){

       t1 = new MyThread("A");
       t1.start();
    }

    private void stopThread1(){

        if(t1!=null && t1.isAlive()){
            t1.interrupt();
        }

    }

    private class MyThread extends Thread{
        String name;

        MyThread(String n){
            name = n;
        }

        @Override
        public void run() {
            for(int i =0 ; i<100; i++){
                Log.i("brad",name+":i = "+i);
            handler.sendEmptyMessage(i);
                try{
                    Thread.sleep(200);
                }catch (Exception e){
                    break;
                }

            }


        }
    }

    private class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            tvMessage.setText(t1.name +" :i= "+msg.what);
        }
    }
}
