package com.example.password.Util;

public class Transform {
    private String encode_str;
    public Transform(String str) {
        this.encode_str=str;
    }

    public String encode(String text){
        char [] chars_text=text.toCharArray();
        for(int i=0;i<chars_text.length;i++){
            chars_text[i]= (char) (chars_text[i]+code());
        }
        return new String(chars_text);
    }

    public String decode(String text){
        char [] chars_text=text.toCharArray();
        for(int i=0;i<chars_text.length;i++){
            chars_text[i]= (char) (chars_text[i]-code());
        }
        return new String(chars_text);
    }

    public char code(){
        char [] chars_code=encode_str.toCharArray();
        char flag=chars_code[0];
        for(int i=1;i<chars_code.length;i++){
            flag= (char) (flag+chars_code[1]);
        }
        System.out.println(flag);
        return flag;
    }


}
