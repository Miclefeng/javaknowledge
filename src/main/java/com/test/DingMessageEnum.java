package com.test;

import java.util.Map;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/12/22 15:31
 */
public enum DingMessageEnum {

    /**
     * 商户水单充值发送到钉钉财务水单审核群
     */
    RECHARGE_FINANCIAL_WATER_BILL_REVIEW_GROUP(
            "钉钉财务水单审核群",
            "d0a23762a09b11ee9546043f72d8d22e",
            "【商户水单审核提醒】\r\n商户 ${portal} 已经提交充值水单，充值金额：${amount} ${currency}，请注意审核。"
    );

    /**
     * 名称
     */
    private final String name;

    /**
     * 钉钉消息服务标识码
     */
    private final String code;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 占位符前缀
     */
    public static final String PLACEHOLDER_PREFIX = "${";

    /**
     * 占位符后缀
     */
    public static final String PLACEHOLDER_SUFFIX = "}";

    DingMessageEnum(String name, String code, String content) {
        this.name = name;
        this.code = code;
        this.content = content;
    }

    /**
     * 替换 content 占位符
     *
     * @param data
     * @return
     */
    public String buildContent(Map<String, String> data) {
        for (String k : data.keySet()) {
           content = content.replace(PLACEHOLDER_PREFIX+k+PLACEHOLDER_SUFFIX, data.get(k));
        }
        return content;
    }
}
