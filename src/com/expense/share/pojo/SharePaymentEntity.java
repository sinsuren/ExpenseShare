package com.expense.share.pojo;

import com.expense.share.ShareType;

import java.util.Map;

/**
 * Created by surender.s on 07/09/19.
 */
public class SharePaymentEntity {
    private Map<String, Integer> userIdToShareMap;
    private String paidBy;
    private ShareType shareType;
    private Double totalAmount;

    public SharePaymentEntity(Map<String, Integer> userIdToShareMap, String fromUser, Double totalAmount, ShareType
            shareType) {
        this.userIdToShareMap = userIdToShareMap;
        this.totalAmount = totalAmount;
        this.paidBy = fromUser;
        this.shareType = shareType;
    }

    public Map<String, Integer> getUserIdToShareMap() {
        return userIdToShareMap;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public ShareType getShareType() {
        return shareType;
    }
}
