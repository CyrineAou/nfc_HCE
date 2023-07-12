package com.example.nfc3;

import java.util.HashMap;
import java.util.Map;

public class BERTLVConstructor {
//    public static String generateTLV(Map<String, String> tlvMap) {
//        StringBuilder tlvBuilder = new StringBuilder();
//
//        for (Map.Entry<String, String> entry : tlvMap.entrySet()) {
//            String tag = entry.getKey();
//            String value = entry.getValue();
//            int length = value.length() / 2; // Assuming the value is in hexadecimal format
//
//            // Append the tag
//            tlvBuilder.append(tag);
//
//            // Append the length
//            String lengthHex = Integer.toHexString(length);
//            if (lengthHex.length() % 2 != 0) {
//                lengthHex = "0" + lengthHex; // Add leading zero if needed
//            }
//            tlvBuilder.append(lengthHex);
//
//            // Append the value
//            tlvBuilder.append(value);
//        }
//
//        return tlvBuilder.toString();
//    }



//    public static String generateTLV(Map<String, String> tlvMap) {
//        StringBuilder tlvBuilder = new StringBuilder();
//
//        for (Map.Entry<String, String> entry : tlvMap.entrySet()) {
//            String tag = entry.getKey();
//            String value = entry.getValue();
//            int length = value.length() / 2; // Assuming the value is in hexadecimal format
//
//            // Append the tag
//            tlvBuilder.append(tag);
//
//            // Append the length
//            if (length <= 127) {
//                tlvBuilder.append(String.format("%02X", length)); // Short form: 1 byte length
//            } else {
//                // Long form: 3 bytes length
//                tlvBuilder.append("81"); // Leading byte with B8 set to 1
//                tlvBuilder.append(String.format("%04X", length)); // 2 bytes length
//            }
//
//            // Append the value
//            tlvBuilder.append(value);
//        }
//
//        return tlvBuilder.toString();
//    }


    public static String generateTLV(Map<String, String> tlvMap) {
        StringBuilder tlvBuilder = new StringBuilder();

        for (Map.Entry<String, String> entry : tlvMap.entrySet()) {
            String tag = entry.getKey();
            String value = entry.getValue();
            int length = value.length() / 2; // Assuming the value is in hexadecimal format

            // Append the tag
            tlvBuilder.append(tag);

            // Append the length
            if (length <= 127) {
                tlvBuilder.append(String.format("%02X", length)); // Short form: 1 byte length
            } else {
                // Long form: variable-length encoding
                int numBytes = (int) Math.ceil((Math.log(length) / Math.log(2) + 1) / 8); // Calculate number of bytes needed
                int leadingByte = 0x80 | numBytes; // Leading byte with B8 set to 1 and B7-B1 encoding number of subsequent bytes

                tlvBuilder.append(String.format("%02X", leadingByte));

                for (int i = numBytes - 1; i >= 0; i--) {
                    int shift = 8 * i;
                    int byteValue = (length >> shift) & 0xFF;
                    tlvBuilder.append(String.format("%02X", byteValue));
                }
            }

            // Append the value
            tlvBuilder.append(value);
        }

        return tlvBuilder.toString();
    }


//    public static void main(String[] args) {
//        Map<String, String> tlvMap = new HashMap<>();
//        tlvMap.put("84", "325041592E5359532E4444463031");
//       // tlvMap.put("9f46", "BD23BD0AB91EDCD2EB07D61B94D1DDA60219552672A42801DD7B16B43BC1A5A6FDD19C9ED342BCE74AA337C3E7B1FD06EAA01D3E8B43397BCAD8491816D28961F248259C783F205A7441E6A5FD576F40BE4E091EBE69D37B2D71A340C6778D94F582742F22980495E31722D64A3AB38CB5658451E237E89806E2D2B0DCCEE0A37E722F1DEC18044DA826F8BA7A38AE6C9F1F8A569084B95280187100A62877FDBCE8F7C6D7B7126662BC665884653E17");
//        String generatedTLV = generateTLV(tlvMap);
//
//        System.out.println("Generated TLV: " + generatedTLV);
//    }
}
