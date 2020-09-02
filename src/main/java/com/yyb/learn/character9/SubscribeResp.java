package com.yyb.learn.character9;

import java.io.Serializable;

/**
 * @Author yyb
 * @Description
 * @Date Create in 2019-04-03
 * @Time 16:55
 */
public class SubscribeResp implements Serializable {
    private static final long serialVersionUID = -1610780199823922669L;
    private int subReqID;
    private int respCode;
    private String desc;

    public int getSubReqID() {
        return subReqID;
    }

    public void setSubReqID(int subReqID) {
        this.subReqID = subReqID;
    }

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "SubscribeResp[" +
                "subReqID=" + subReqID +
                ", respCode=" + respCode +
                ", desc='" + desc + '\'' +
                ']';
    }
}
