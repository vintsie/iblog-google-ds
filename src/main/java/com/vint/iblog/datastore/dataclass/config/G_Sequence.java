package com.vint.iblog.datastore.dataclass.config;

import com.google.appengine.api.datastore.Key;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 *
 *
 * Created by Vin on 14-2-17.
 */
@PersistenceCapable
@SuppressWarnings("unused")
public class G_Sequence {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    @Persistent
    private String Type;
    @Persistent
    private int DecCount;
    @Persistent
    private String HexMark;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getDecCount() {
        return DecCount;
    }

    public void setDecCount(int decCount) {
        DecCount = decCount;
    }

    public String getHexMark() {
        return HexMark;
    }

    public void setHexMark(String hexMark) {
        HexMark = hexMark;
    }
}
