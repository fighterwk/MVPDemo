package com.kyle.mvp.bean;

import com.bigkoo.pickerview.model.IPickerViewData;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/11/2
 */
public class RegionBean implements IPickerViewData {

    /**
     * Id : 3
     * Code : 110101
     * ParentCode : 110100
     * Name : 东城区
     * Level : 3
     * Latitude : 39.938574
     * Longitude : 116.421885
     */

    private int Id;
    private int Code;
    private int ParentCode;
    private String Name;
    private int Level;
    private String Latitude;
    private String Longitude;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public int getParentCode() {
        return ParentCode;
    }

    public void setParentCode(int ParentCode) {
        this.ParentCode = ParentCode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int Level) {
        this.Level = Level;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String Latitude) {
        this.Latitude = Latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String Longitude) {
        this.Longitude = Longitude;
    }

    @Override
    public String getPickerViewText() {
        return Name == null ? "" : Name;
    }
}
