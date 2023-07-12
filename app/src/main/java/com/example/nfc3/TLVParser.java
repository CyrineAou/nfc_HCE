package com.example.nfc3;

import java.util.HashMap;
import java.util.Map;

    public class TLVParser {

        public static Map<String, String> parseTLV(String tlvCode) {
            Map<String, String> tlvMap = new HashMap<>();

            int index = 0;
            while (index < tlvCode.length()) {
                String tag = tlvCode.substring(index, index + 2);
                index += 2;

                int length = Integer.parseInt(tlvCode.substring(index, index + 2), 16);
                index += 2;

                String value = tlvCode.substring(index, index + length);
                index += length;

                tlvMap.put(tag, value);
            }
            return tlvMap;
        }

//        public static void main(String[] args) {
//            String tlvCode = "010A546573744D657373616765";
//            Map<String, String> parsedTLV = parseTLV(tlvCode);
//
//            // Print the parsed TLV data
//            for (Map.Entry<String, String> entry : parsedTLV.entrySet()) {
//                System.out.println("Tag: " + entry.getKey() + ", Value: " + entry.getValue());
//            }
//        }
    }
