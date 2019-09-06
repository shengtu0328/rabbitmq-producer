package com.xrq.rabbitmq.entity;

import java.util.Date;

/**
 * 消息日志表
 */
public class BrokerMessageLog {


    //消息唯一ID
    private String messageId;

    //order对象json化后的存储
    private String message;

    //重试次数
    private Integer tryCount;

    //消息投递状态  0 投递中 1 投递成功   2 投递失败
    private String status;

    //下一次重试时间 或 超时时间
    private Date nextRetry;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId == null ? null : messageId.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Integer getTryCount() {
        return tryCount;
    }

    public void setTryCount(Integer tryCount) {
        this.tryCount = tryCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getNextRetry() {
        return nextRetry;
    }

    public void setNextRetry(Date nextRetry) {
        this.nextRetry = nextRetry;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

