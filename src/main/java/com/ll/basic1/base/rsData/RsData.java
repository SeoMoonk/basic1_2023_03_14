package com.ll.basic1.base.rsData;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RsData {
    private final String resultCode;
    private final String msg;
    private final Object data;

    public static RsData of(String resultCode, String msg)
    {
        return new RsData(resultCode, msg, null);
    }

    public static RsData of(String resultCode, String msg, Object data)
    {
        return new RsData(resultCode, msg, data);
    }

    public boolean isSuccess() {
        //성공했을 경우 성공했음을 남김.

        return resultCode.startsWith("S-");
    }

}
