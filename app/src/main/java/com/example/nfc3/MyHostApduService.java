package com.example.nfc3;

import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import android.util.Log;

public class MyHostApduService extends HostApduService {

    private static final String TAG = "MyHostApduService";

    private static final String SELECT_APDU_HEADER = "00A40400";
    private static final String SAMPLE_APP_AID = "F000000001";

    @Override
    public byte[] processCommandApdu(byte[] commandApdu, Bundle extras) {
        if (commandApdu == null) {
            return new byte[0];
        }

        String command = ByteArrayToHexString(commandApdu);

        Log.d(TAG, "Received APDU: " + command);

        if (command.startsWith(SELECT_APDU_HEADER)) {
            if (command.endsWith(SAMPLE_APP_AID)) {
                byte[] response = "OK".getBytes();
                return ConcatArrays(response, HexStringToByteArray("9000"));
            } else {
                byte[] response = "ERROR".getBytes();
                return ConcatArrays(response, HexStringToByteArray("6A82"));
            }
        } else {
            byte[] response = "UNKNOWN".getBytes();
            return ConcatArrays(response, HexStringToByteArray("6D00"));
        }
    }

    private String ByteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    private byte[] HexStringToByteArray(String hexString) {
        int length = hexString.length() / 2;
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = (byte) Integer.parseInt(hexString.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

    private byte[] ConcatArrays(byte[] firstArray, byte[] secondArray) {
        byte[] result = new byte[firstArray.length + secondArray.length];
        System.arraycopy(firstArray, 0, result, 0, firstArray.length);
        System.arraycopy(secondArray, 0, result, firstArray.length, secondArray.length);
        return result;
    }

    @Override
    public void onDeactivated(int reason) {
        Log.d(TAG, "Deactivated: " + reason);
    }
}