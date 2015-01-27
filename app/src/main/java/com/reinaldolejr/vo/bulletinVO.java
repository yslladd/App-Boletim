package com.reinaldolejr.vo;

import java.util.List;

/**
 * Created by Reinaldo on 1/17/2015.
 */
public class bulletinVO {
    private String code;
    private String matter;
    private String team;
    private List<studentVO> students;

    public String getMatter() {
        return matter;
    }

    public void setMatter(String matter) {
        this.matter = matter;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public List<studentVO> getStudents() {
        return students;
    }

    public void setStudents(List<studentVO> students) {
        this.students = students;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
