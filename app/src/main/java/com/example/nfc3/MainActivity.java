package com.example.nfc3;

import static com.example.nfc3.MyHostApduService.getResponse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String PAYMENT_CATEGORY = "android.intent.category.DEFAULT";
    private static final String PAYMENT_ACTION = "android.intent.action.PAY";

    private boolean isNfcEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Check if NFC is enabled
        if (!isNfcEnabled) {
            isNfcEnabled = checkNfcEnabled();
            if (!isNfcEnabled) {
                // NFC is not enabled, redirect the user to NFC settings
                Intent settingsIntent = new Intent(Settings.ACTION_NFC_SETTINGS);
                startActivity(settingsIntent);
                return; // Finish the activity to prevent further execution
            }
        }
//
//        Intent serviceIntent = new Intent(this, MyHostApduService.class);
//        startService(serviceIntent);

//
        Intent serviceIntent = new Intent(this, sendAdpeService.class);
        startService(serviceIntent);

        if (isDefaultPaymentApp()) {
            // Your app is recognized as a contactless payment app
            // Add your logic here
            Toast.makeText(this, "Your app is recognized as a contactless payment app", Toast.LENGTH_SHORT).show();
        }

    }


    private boolean checkNfcEnabled() {
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        return nfcAdapter != null && nfcAdapter.isEnabled();
    }

    private boolean isDefaultPaymentApp() {
        PackageManager packageManager = getPackageManager();
        Intent paymentIntent = new Intent(PAYMENT_ACTION);
        paymentIntent.addCategory(PAYMENT_CATEGORY);

        ResolveInfo resolveInfo = packageManager.resolveActivity(paymentIntent, PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo != null && resolveInfo.activityInfo.packageName.equals(getPackageName());
    }






}



//package com.example.nfc3;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.ComponentName;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.content.pm.ResolveInfo;
//import android.nfc.cardemulation.CardEmulation;
//import android.os.Bundle;
//
//import java.util.List;
//
//public class MainActivity extends AppCompatActivity {
//    private static final String PAYMENT_CATEGORY = "android.intent.category.DEFAULT";
//    private static final String PAYMENT_ACTION = "android.intent.action.PAY";
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//
//
//        Intent serviceIntent = new Intent(this, MyHostApduService.class);
//        startService(serviceIntent);
//        if (isDefaultPaymentApp()) {
//            // Your app is recognized as a contactless payment app
//            // Add your logic here
//            int k=5;
//        }
//    }
//
//
//    private boolean isDefaultPaymentApp() {
//        PackageManager packageManager = getPackageManager();
//
//        // Create an intent with payment action and category
//        Intent paymentIntent = new Intent(PAYMENT_ACTION);
//        paymentIntent.addCategory(PAYMENT_CATEGORY);
//
//        // Resolve the intent to get the list of activities that handle it
//        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(paymentIntent, 0);
//
//        // Check if any activity in your app handles the payment intent
//        for (ResolveInfo resolveInfo : resolveInfos) {
//            if (resolveInfo.activityInfo.packageName.equals(getPackageName())) {
//                return true; // App is registered as a default payment app
//            }
//        }
//
//        return false; // App is not registered as a default payment app
//    }
//
//}