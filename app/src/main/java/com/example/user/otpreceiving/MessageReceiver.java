package com.example.user.otpreceiving;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.TextView;

public class MessageReceiver extends BroadcastReceiver {
    private static MessageListener mListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data = intent.getExtras();
        Object[] pdus = (Object[]) data.get("pdus");
        for(int i=0; i<pdus.length; i++){
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
//            String message = "Sender : " + smsMessage.getDisplayOriginatingAddress()
//                    + "Email From: " + smsMessage.getEmailFrom()
//                    + "Emal Body: " + smsMessage.getEmailBody()
//                    + "Display message body: " + smsMessage.getDisplayMessageBody()
//                    + "Time in millisecond: " + smsMessage.getTimestampMillis()
//                    + "Message: " + smsMessage.getMessageBody();
            String message = smsMessage.getDisplayMessageBody().replaceAll("\\D", "");
            mListener.messageReceived(message);
        }


    }


    public static void bindListener(MessageListener listener){
        mListener = listener;
    }

    }
