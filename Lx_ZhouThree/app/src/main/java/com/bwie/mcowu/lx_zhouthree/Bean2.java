package com.bwie.mcowu.lx_zhouthree;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 姓名：McoWu
 * 时间:2017/11/20 20:23.
 * 本类作用:
 */
@Entity
public class Bean2 {
    @Id
    private Long id;
    @Property(nameInDb = "RANGE")
    private Long range;
    @Property(nameInDb = "START")
    private Long start;
    @Property(nameInDb = "END")
    private Long end;
    @Property(nameInDb = "ThREADNUM")
    private String ThreadNum;
    @Generated(hash = 1723735411)
    public Bean2(Long id, Long range, Long start, Long end, String ThreadNum) {
        this.id = id;
        this.range = range;
        this.start = start;
        this.end = end;
        this.ThreadNum = ThreadNum;
    }
    @Generated(hash = 655088622)
    public Bean2() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getRange() {
        return this.range;
    }
    public void setRange(Long range) {
        this.range = range;
    }
    public Long getEnd() {
        return this.end;
    }
    public void setEnd(Long end) {
        this.end = end;
    }
    public String getThreadNum() {
        return this.ThreadNum;
    }
    public void setThreadNum(String ThreadNum) {
        this.ThreadNum = ThreadNum;
    }
    public Long getStart() {
        return this.start;
    }
    public void setStart(Long start) {
        this.start = start;
    }
   
}
