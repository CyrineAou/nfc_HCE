package com.example.nfc3;

import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.util.Log;

public class MyHostApduService extends HostApduService {

    private static final String TAG = "MyHostApduService";

    private static final String SELECT_APDU_HEADER = "00A40400";
    private static final String SAMPLE_APP_AID = "F000000001";

    private static final String HEX_STRING = "6F2D840E325041592E5359532E4444463031A51BBF0C1861164F07A0000000031010500B56495341204352454449549000";
//    @Override
//    public byte[] processCommandApdu(byte[] commandApdu, Bundle extras) {
//
//        byte[] byteArrayToSend = hexStringToByteArray(HEX_STRING);
//
//        //  logic to send the byteArrayToSend
//        StringBuilder stringBuilder = new StringBuilder();
//        for (byte b : byteArrayToSend) {
//            stringBuilder.append(b & 0xFF).append(" sended APDU");
//        }
//        Log.d(TAG, stringBuilder.toString());
//
//
//        if (commandApdu == null) {
//            return byteArrayToSend;
//        }
//
//        String command = ByteArrayToHexString(commandApdu);
//
//        Log.d(TAG, "Received APDU: " + command);
//
//        if (command.startsWith(SELECT_APDU_HEADER)) {
//            if (command.endsWith(SAMPLE_APP_AID)) {
//                byte[] response = "OK".getBytes();
//                return ConcatArrays(response, HexStringToByteArray("9000"));
//            } else {
//                byte[] response = "ERROR".getBytes();
//                return ConcatArrays(response, HexStringToByteArray("6A82"));
//            }
//        } else {
//            byte[] response = "UNKNOWN".getBytes();
//            return ConcatArrays(response, HexStringToByteArray("6D00"));
//        }
//    }


    public byte[] processCommandApdu(byte[] commandApdu, Bundle extras) {
        byte[] byteArrayToSend = hexStringToByteArray(HEX_STRING);
        Log.d(TAG, "Received APDU: " + ByteUtils.byteArray2HexString(commandApdu));

        return byteArrayToSend;
    }


    private String ByteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }



    private String byteArrayToHexString(byte[] byteArray) {
        StringBuilder stringBuilder = new StringBuilder(byteArray.length * 2);
        for (byte b : byteArray) {
            stringBuilder.append(String.format("%02X", b));
        }
        return stringBuilder.toString();
    }

    private byte[] concatArrays(byte[] array1, byte[] array2) {
        byte[] concatenatedArray = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, concatenatedArray, 0, array1.length);
        System.arraycopy(array2, 0, concatenatedArray, array1.length, array2.length);
        return concatenatedArray;
    }



    @Override
    public void onDeactivated(int reason) {
        Log.d(TAG, "Deactivated: " + reason);
    }


    private byte[] hexStringToByteArray(String hexString) {

        byte[] byteArray = ByteUtils.hexString2ByteArray(hexString);


        return byteArray;
    }



}