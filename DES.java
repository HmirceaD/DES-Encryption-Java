package com.mirceadan.stacker;

import com.sun.xml.internal.fastinfoset.util.StringArray;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DES {

    private String HEX_STRING = "0123456789ABCDEFabcdef";
    private String mess;
    private String key;

    int PC_1[] = {57,   49,    41,   33,    25,    17,    9,
            1,   58,    50,   42,   34,    26,   18,
            10,   2,    59,   51,    43,   35,   27,
            19,  11,     3,   60,    52,    44,   36,
            63,   55,    47,   39,   31,    23,   15,
            7,   62,    54,   46,    38,    30,   22,
            14,    6,    61,   53,    45,    37,   29,
            21,   13,     5,   28,    20,   12,    4};

    int PC_2[] = {14,    17,   11,    24,     1,   5,
            3,    28,   15,     6,    21,   10,
            23,    19,   12,     4,    26,    8,
            16,     7,   27,    20,    13,    2,
            41,    52,   31,    37,    47,   55,
            30,    40,   51,    45,    33,   48,
            44,    49,   39,    56,    34,   53,
            46,    42,   50,    36,    29,   32};

    int IP[] = {58    ,50   ,42    ,34    ,26   ,18    ,10    ,2
            ,60    ,52   ,44    ,36    ,28   ,20    ,12    ,4
            ,62    ,54   ,46    ,38    ,30   ,22    ,14    ,6
            ,64    ,56   ,48    ,40    ,32   ,24    ,16    ,8
            ,57    ,49   ,41    ,33    ,25   ,17     ,9    ,1
            ,59    ,51   ,43    ,35    ,27   ,19    ,11    ,3
            ,61    ,53   ,45    ,37    ,29   ,21    ,13    ,5
            ,63    ,55   ,47    ,39    ,31   ,23    ,15    ,7};

    int EBit[] = {32,1,2,3,4,5,4,5,6,7,8,9,8,9,10,11,12,13,12,13,14,15,16,17,16,17,18,19,20,21,20,21,22,23,24,25,24,25,26,27,28,29,28,29,30,31,32,1};

    int P[] = {16,7,20,21,29,12,28,17,1,15,23,26,5,18,31,10,2,8,24,14,32,27,3,9,19,13,30,6,22,11,4,25};

    int IP_INV[] =  {40,8,48,16,56,24,64,32,39,7,47,15,55,23,63,31,38,6,46,14,54,22,62,30,37,5,45,13,53,21,61,29,36,4,44,12,52,20,60,28,35,3,43,11,51,19,59,27,34,2,42,10,50,18,58,26,33,1,41,9,49,17,57,25};

    int S1[][] = {{14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7},{0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8},{4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0},{15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13}};
    int S2[][] = {{15,1,8,14,6,11,3,4,9,7,2,13,12,0,5,10},{3,13,4,7,15,2,8,14,12,0,1,10,6,9,11,5},{0,14,7,11,10,4,13,1,5,8,12,6,9,3,2,15},{13,8,10,1,3,15,4,2,11,6,7,12,0,5,14,9}};
    int S3[][] = {{10,0,9,14,6,3,15,5,1,13,12,7,11,4,2,8},{13,7,0,9,3,4,6,10,2,8,5,14,12,11,15,1},{13,6,4,9,8,15,3,0,11,1,2,12,5,10,14,7},{1,10,13,0,6,9,8,7,4,15,14,3,11,5,2,12}};
    int S4[][] = {{7,13,14,3,0,6,9,10,1,2,8,5,11,12,4,15},{13,8,11,5,6,15,0,3,4,7,2,12,1,10,14,9},{10,6,9,0,12,11,7,13,15,1,3,14,5,2,8,4},{3,15,0,6,10,1,13,8,9,4,5,11,12,7,2,14}};
    int S5[][] = {{2,12,4,1,7,10,11,6,8,5,3,15,13,0,14,9},{14,11,2,12,4,7,13,1,5,0,15,10,3,9,8,6},{4,2,1,11,10,13,7,8,15,9,12,5,6,3,0,14},{11,8,12,7,1,14,2,13,6,15,0,9,10,4,5,3}};
    int S6[][] = {{12,1,10,15,9,2,6,8,0,13,3,4,14,7,5,11},{10,15,4,2,7,12,9,5,6,1,13,14,0,11,3,8},{9,14,15,5,2,8,12,3,7,0,4,10,1,13,11,6},{4,3,2,12,9,5,15,10,11,14,1,7,6,0,8,13}};
    int S7[][] = {{4,11,2,14,15,0,8,13,3,12,9,7,5,10,6,1},{13,0,11,7,4,9,1,10,14,3,5,12,2,15,8,6},{1,4,11,13,12,3,7,14,10,15,6,8,0,5,9,2},{6,11,13,8,1,4,10,7,9,5,0,15,14,2,3,12}};
    int S8[][] = {{13,2,8,4,6,15,11,1,10,9,3,14,5,0,12,7},{1,15,13,8,10,3,7,4,12,5,6,11,0,14,9,2},{7,11,4,1,9,12,14,2,0,6,10,13,15,3,5,8},{2,1,14,7,4,10,8,13,15,12,9,0,3,5,6,11}};

    int SMatrixes[][][] = {S1,S2,S3,S4,S5,S6,S7,S8};

    private int NumOfShiftsPerIter[] = {1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1};

    public DES(){}

    public String encryptMessage(String mess, String key){

        if(checkIfHex(mess, key)){

            String messBits = hexToBits(mess);
            String keyBits = hexToBits(key);

            ArrayList<String> subKeys = createSubKey(keyBits);
            String encodedMessage[] = splitString(encodeMessage(messBits, subKeys), 4);

            String hexMessage = "";

            for (String s: encodedMessage){
                int decimal = Integer.parseInt(s,2);
                hexMessage += Integer.toString(decimal, 16);
            }

            return hexMessage;

        }else{
            return "Message or Key is not in hexadecimal and cannot be encrypted";
        }

    }

    public String tDes(String message, String key1, String key2, String key3){

        return encryptMessage(encryptMessage(encryptMessage(message, key1), key2), key3);
    }

    private String encodeMessage(String messBits, ArrayList<String> subKeys) {
        /**
         * does the actual DES encoding on the message
         */
        String ip = "";

        for(int i = 0; i < IP.length; i++){

            ip += messBits.charAt(IP[i] - 1);
        }

        String[] parts = {ip.substring(0, ip.length() / 2), ip.substring(ip.length() / 2)};

        String l_ip = parts[0];
        String r_ip = parts[1];

        return permuteMessageAndKeys(l_ip, r_ip, subKeys);


    }

    private String xorBitString (String firstStr, String secondStr){
        /**
         * XOR two bit strings
         */
        String newStr = "";

        for(int i=0; i < firstStr.length(); i++){
            if(firstStr.charAt(i) == secondStr.charAt(i))
                newStr += "0";
            else
                newStr += "1";
        }

        return newStr;

    }

    private String permuteMessageAndKeys(String l_ip, String r_ip, ArrayList<String> subKeys) {
        /**
         * create the L0,L1,L2..., R0,R1,R2 etc.
         */
        ArrayList<String> l_arr = new ArrayList<>();
        ArrayList<String> r_arr = new ArrayList<>();


        l_arr.add(l_ip);
        r_arr.add(r_ip);


        for(int i = 1; i < 17; i ++){

            l_arr.add(r_arr.get(i-1));
            r_arr.add(xorBitString(l_arr.get(i-1), f(r_arr.get(i-1), subKeys.get(i-1))));

        }


        String l_r = r_arr.get(r_arr.size()-1) + l_arr.get(l_arr.size()-1) ;

        String encodedString = "";
        for(int ind = 0; ind < IP_INV.length; ind++){
            encodedString += l_r.charAt(IP_INV[ind]-1);
        }

        return encodedString;


    }

    private String f(String firstStr, String secondStr) {
        /**
         * f function to calculate Rn = Ln-1 + f(Rn-1,Kn)
         */
        String ebitString = "";

        for(int i = 0; i < EBit.length; i++){
            ebitString += firstStr.charAt(EBit[i] -1);
        }

        String xorString[] = splitString(xorBitString(ebitString, secondStr), 6);

        ArrayList<String> sString = new ArrayList<>();

        int matNum = 0;

        for(String bitBlock:xorString){

            int i = Integer.parseInt(String.valueOf(bitBlock.charAt(0)) + String.valueOf(bitBlock.charAt(bitBlock.length()-1)), 2);
            int j = Integer.parseInt(bitBlock.substring(1, bitBlock.length()-1), 2);

            sString.add(String.valueOf(SMatrixes[matNum][i][j]));
            matNum += 1;
        }

        String sPermutation = "";
        for(String block: sString){
            String decToBin = Integer.toBinaryString(Integer.parseInt(String.valueOf(block), 10));

            while(decToBin.length() < 4){
                decToBin = "0" + decToBin;
            }
            sPermutation += decToBin;
        }

        String finalStringF = "";
        for(int ind = 0; ind < P.length; ind++){
            finalStringF += sPermutation.charAt(P[ind]-1);
        }

        return finalStringF;
    }

    private ArrayList<String> shiftStringNTimes(String str, int n){
        /**
         * from one string, get an arraylist of 16 string all shifted
         */
        ArrayList<String> shiftedStrings = new ArrayList<>();

        shiftedStrings.add(str);

        for(int i = 1; i < n; i++){

            shiftedStrings.add(leftShiftStringArr(shiftedStrings.get(i - 1), NumOfShiftsPerIter[i-1]));

        }

        return shiftedStrings;

    }

    private String leftShiftStringArr(String str, int iters){

        /**
         * SHIFTING of a string one or two times
         */
        //TODO: refactor this
        if(iters != 1){

            String auxStr = str.substring(1, str.length()) + str.charAt(0);
            return auxStr.substring(1, auxStr.length()) + auxStr.charAt(0);

        }else{
            return str.substring(1, str.length()) + str.charAt(0);
        }
    }

    private String[] splitString(String str, int len){

        return str.split("(?<=\\G.{" + len + "})");

    }

    private ArrayList<String> createSubKey(String keyBits) {
        /**
         * creates the subkey k0,k1,k2 etc.
         */
        StringBuilder subKey = new StringBuilder();

        //Todo Refactor this;
        for(int i = 0; i < 56; i++){
           subKey.append(String.valueOf(keyBits.charAt(PC_1[i] - 1)));
        }

        String subKeyString = subKey.toString();

        String[] parts = {subKeyString.substring(0, subKeyString.length() / 2), subKeyString.substring(subKeyString.length() / 2)};

        String c_subkey = parts[0];
        String d_subkey = parts[1];

        ArrayList<String> c_n = shiftStringNTimes(c_subkey, 17);
        ArrayList<String> d_n = shiftStringNTimes(d_subkey, 17);

        ArrayList<String> k_n = new ArrayList<>();


        for(int i = 1; i < c_n.size(); i++){
            String auxStr = "";
            for(int j = 0; j < PC_2.length; j++){

                auxStr += (c_n.get(i) + d_n.get(i)).charAt(PC_2[j] - 1);

            }

            k_n.add(auxStr);
        }

        return k_n;

    }

    private String hexToBits(String str) {
        /**
         * converts every character into groups of 4 bits
         */
        ArrayList<String> messList = new ArrayList<>();
        for(char c: str.toCharArray()){
            messList.add(Integer.toBinaryString(Integer.parseInt(String.valueOf(c), 16)));
        }


        StringBuilder bytes = new StringBuilder();
        for(String s: messList){
            while(s.length() < 4){
                s = "0" + s;
            }

            bytes.append(s);
        }


        return bytes.toString();

    }

    private boolean checkIfHex(String mess, String key) {
        /**
         * checks if the strings are groups of hexadecimal strings
         */
        boolean Ok = true;
        //TODO: check if message is 64 bits
        if(key.length() != 16){
            Ok = false;
        }

        for(char c: mess.toCharArray()){

            if(!HEX_STRING.contains(String.valueOf(c))){

                Ok = false;
            }
        }

        for(char c: key.toCharArray()){

            if(!HEX_STRING.contains(String.valueOf(c))){
                Ok = false;
            }
        }

        return Ok;
    }
}
