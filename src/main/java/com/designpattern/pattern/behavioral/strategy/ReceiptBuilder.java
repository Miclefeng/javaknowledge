package com.designpattern.pattern.behavioral.strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: some desc
 * @Author: miclefengzss
 * @Date: 2023/10/24 21:51
 */
public class ReceiptBuilder {

    public static List<Receipt> getReceiptList() {

        //模拟回执信息
        List<Receipt> list = new ArrayList<>();
        //MT1101、MT2101、MT4101、MT8104
        list.add(new Receipt("MT1101", "MT1101回执信息"));
        list.add(new Receipt("MT2101", "MT2101回执信息"));
//        list.add(new Receipt("MT4101回执信息","MT4101"));
//        list.add(new Receipt("MT8104回执信息","MT8104"));

        //......
        return list;
    }
}
