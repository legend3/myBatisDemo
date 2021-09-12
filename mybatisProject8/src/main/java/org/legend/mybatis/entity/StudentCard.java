package org.legend.mybatis.entity;

public class StudentCard {//一对一，resultMap形式
    private int cardId;
    private String cardInfo;

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(String cardInfo) {
        this.cardInfo = cardInfo;
    }
}
