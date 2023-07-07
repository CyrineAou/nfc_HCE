package com.example.nfc3;

import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.util.Log;

import java.util.Arrays;

public class sendAdpeService extends HostApduService {

    private static final String TAG = "sendAdpeService";
    private static final String HEX_STRING = "6F2D840E325041592E5359532E4444463031A51BBF0C1861164F07A0000000031010500B56495341204352454449549000";

    @Override
    public byte[] processCommandApdu(byte[] bytes, Bundle bundle) {
        // Your logic to send the byte array
        byte[] byteArrayToSend = hexStringToByteArray(HEX_STRING);

        //  logic to send the byteArrayToSend
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : byteArrayToSend) {
            stringBuilder.append(b & 0xFF).append(" sended APDU");
        }
        Log.d(TAG, stringBuilder.toString());

        // Return a response if necessary
        return byteArrayToSend;
    }

    @Override
    public void onDeactivated(int reason) {
        Log.d(TAG, "Deactivated: " + reason);
    }

    private byte[] hexStringToByteArray(String hexString) {
        String[] hexValues = hexString.split(" ");
        byte[] byteArray = new byte[hexValues.length];

        for (int i = 0; i < hexValues.length; i++) {
            byteArray[i] = (byte) Integer.parseInt(hexValues[i], 16);
        }

        return byteArray;
    }
}
