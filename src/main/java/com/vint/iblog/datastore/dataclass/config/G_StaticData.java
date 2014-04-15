package com.vint.iblog.datastore.dataclass.config;

import com.google.appengine.api.datastore.Key;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * 配置数据
 * Created by Vin on 14-2-17.
 */
@PersistenceCapable
@SuppressWarnings("unused")
public class G_StaticData {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;


    public int getSort() {
        return Sort;
    }

    public void setSort(int sort) {
        this.Sort = sort;
    }

    @Persistent
    private int Sort;

    @Persistent
    private String DataType;

    @Persistent
    private String DataValue;

    @Persistent
    private String State;

    public String getDataType() {
        return DataType;
    }

    public void setDataType(String dataType) {
        DataType = dataType;
    }

    public String getDataValue() {
        return DataValue;
    }

    public void setDataValue(String dataValue) {
        DataValue = dataValue;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }
}
