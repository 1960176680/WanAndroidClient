package com.wanandroid.mykotlin;

import android.view.View;
import android.widget.Button;

public class ZZZ {
    Button aa;
    public void gett(DD d){

        aa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    public void getcc(){
        gett(new DD());
    }

    class  DD{

    }
}
