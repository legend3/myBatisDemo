package org.legend.mybatis.entity;

public class StudentBusiness extends Student{//实现多继承: 包含属性少的类，继承多的！
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

    @Override
    public String toString() {
        return
                super.toString() +
                "-cardId=" + cardId +
                "-cardInfo='" + cardInfo + '\'' +
                '}';
    }
}
