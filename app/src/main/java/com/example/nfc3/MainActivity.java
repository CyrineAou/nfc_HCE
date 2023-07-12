package com.example.nfc3;

import static com.example.nfc3.BERTLVConstructor.generateTLV;
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

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String PAYMENT_CATEGORY = "android.intent.category.DEFAULT";
    private static final String PAYMENT_ACTION = "android.intent.action.PAY";

    private boolean isNfcEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testGenerateTLV();


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

    private void testGenerateTLV() {
        // Create a sample map of TLV data
        Map<String, String> tlvMap = new HashMap<>();
        tlvMap.put("84", "325041592E5359532E4444463031");
        tlvMap.put("9f47", "7081B89F4681B0BD23BD0AB91EDCD2EB07D61B94D1DDA60219552672A42801DD7B16B43BC1A5A6FDD19C9ED342BCE74AA337C3E7B1FD06EAA01D3E8B43397BCAD8491816D28961F248259C783F205A7441E6A5FD576F40BE4E091EBE69D37B2D71A340C6778D94F582742F22980495E31722D64A3AB38CB5658451E237E89806E2D2B0DCCEE0A37E722F1DEC18044DA826F8BA7A38AE6C9F1F8A569084B95280187100A62877FDBCE8F7C6D7B7126662BC665884653E179F4701039000");
        tlvMap.put("9f46", "BD23BD0AB91EDCD2EB07D61B94D1DDA60219552672A42801DD7B16B43BC1A5A6FDD19C9ED342BCE74AA337C3E7B1FD06EAA01D3E8B43397BCAD8491816D28961F248259C783F205A7441E6A5FD576F40BE4E091EBE69D37B2D71A340C6778D94F582742F22980495E31722D64A3AB38CB5658451E237E89806E2D2B0DCCEE0A37E722F1DEC18044DA826F8BA7A38AE6C9F1F8A569084B95280187100A62877FDBCE8F7C6D7B7126662BC665884653E17BD23BD0AB91EDCD2EB07D61B94D1DDA60219552672A42801DD7B16B43BC1A5A6FDD19C9ED342BCE74AA337C3E7B1FD06EAA01D3E8B43397BCAD8491816D28961F248259C783F205A7441E6A5FD576F40BE4E091EBE69D37B2D71A340C6778D94F582742F22980495E31722D64A3AB38CB5658451E237E89806E2D2B0DCCEE0A37E722F1DEC18044DA826F8BA7A38AE6C9F1F8A569084B95280187100A62877FDBCE8F7C6D7B7126662BC665884653E17");
        // Call the generateTLV function
        String tlvResult = generateTLV(tlvMap);
        // Print the result
        System.out.println("TLV Result: " + tlvResult);
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