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
        String hexStringReceivedApdu = ByteUtils.byteArray2HexString(commandApdu);
        Log.d(TAG, "Received APDU: " + hexStringReceivedApdu);
        String hexStringApduToSend = getResponse(hexStringReceivedApdu);
        return ByteUtils.hexString2ByteArray(hexStringApduToSend);
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

    public static String getResponse(String commandApdu) {
        String response;
        switch (commandApdu) {
            case "00A404000E325041592E5359532E444446303100" ->
                    response = "6F2D840E325041592E5359532E4444463031A51BBF0C1861164F07A0000000031010500B56495341204352454449549000";
            case "00A4040007A000000003101000" ->
                    response="6F3E8407A0000000031010A533500B56495341204352454449549F38189F66049F02069F03069F1A0295055F2A029A039C019F3704BF0C089F5A0500084008409000";
            case "00B2031C00" -> response="70145A0847617390010101195F24032412315F3401019000";
            case "00B2021400" ->
                    response="7081E08F01929081B004D6DCA77E2DDFB5600A9DDE4EA22391BF4AF337F2FB994191E0397074D535E8ABD27F43906ADDD45F01540198EF233C9D4CC898AD56A956A966D7385855866813F5E929FB72FAE5E440642EB1EEA30D80C3E6D281E0E6FB516B251DD75C01D59C71782827391F89D8FFD9E6EA302CBE1BC986BEA0365C36F7A6DA9FFD80F4D0FF9ED89DD5159A5B003C786A111794FD93923D6CED154D354EA7F04E4A48761A74D9FC184848F1C633CE5ADF549635BD922485AADF043FAA3848ED869D6EB3D970F4AC08A97474278842C85584E34B6FACC07207BC419F3201039000";
            case "00B2031400" ->
                    response="7081B89F4681B0BD23BD0AB91EDCD2EB07D61B94D1DDA60219552672A42801DD7B16B43BC1A5A6FDD19C9ED342BCE74AA337C3E7B1FD06EAA01D3E8B43397BCAD8491816D28961F248259C783F205A7441E6A5FD576F40BE4E091EBE69D37B2D71A340C6778D94F582742F22980495E31722D64A3AB38CB5658451E237E89806E2D2B0DCCEE0A37E722F1DEC18044DA826F8BA7A38AE6C9F1F8A569084B95280187100A62877FDBCE8F7C6D7B7126662BC665884653E179F4701039000";
            case "00B2041400" -> response="700A9F69070172D45C3800009000";
            default -> {
                if(commandApdu.startsWith("80A8000023"))
                    response="7781BD57104761739001010119D24122011758947282022000940818030301100204009F100706010A039000009F260856271522EE6A65269F2701409F360200019F4B70B1AF61C895F8D9EA364D0B39F2B644A9526F372193C9F844156956A75E282A04BDB173A0AC4F1DC6ABE9483243742824115F6AA3568C764B7F1335CD0539FCE3AA91744F94812F0673983408984F0EA8279A95945D568FD597396EE41FE538BD87D439CCC8E67D1404F49816861E60459F6C0240009F6E04207000009000";
                else
                 response="6A83";
            }
        }
        return response;
    }
}