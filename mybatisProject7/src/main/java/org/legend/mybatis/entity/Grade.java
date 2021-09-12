package org.legend.mybatis.entity;

import java.util.List;

public class Grade {
    //学号集合
    private List<Integer> stuNos;

    public List<Integer> getStuNos() {
        return stuNos;
    }

    public void setStuNos(List<Integer> stuNos) {
        this.stuNos = stuNos;
    }
}
