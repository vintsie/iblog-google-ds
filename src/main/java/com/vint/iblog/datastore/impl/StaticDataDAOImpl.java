package com.vint.iblog.datastore.impl;

import com.vint.iblog.datastore.PMF;
import com.vint.iblog.datastore.dataclass.config.G_StaticData;
import com.vint.iblog.datastore.define.StaticDataDAO;
import org.vint.iblog.common.bean.config.StaticData;
import org.vint.iblog.common.constant.StateConst;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基于Google DataStore实现的静态数据存储
 * Created by Vin on 14-2-17.
 */
public class StaticDataDAOImpl implements StaticDataDAO {
    @Override
    public List<StaticData> getAllStaticData() throws Exception {

        PersistenceManager pm = PMF.get().getPersistenceManager();
        List<StaticData> staticDatas = new ArrayList<StaticData>();

        Query query = pm.newQuery(G_StaticData.class);
        query.setFilter("State == state");
        query.declareParameters("String state");

        try {
            List<G_StaticData> g_staticDatas =
                    (List<G_StaticData>) query.execute(StateConst.STATE_VALID);

            if (!g_staticDatas.isEmpty()) {
                // 遍历数据库对象，把数据库对象转换成上层的配置数据对象。
                for (G_StaticData g_staticData : g_staticDatas) {
                    StaticData sd = new StaticData();
                    sd.setDataType(g_staticData.getDataType());
                    sd.setDataValue(g_staticData.getDataValue());
                    sd.setState(g_staticData.getState());
                    staticDatas.add(sd);
                }
            }
        } finally {
            pm.close();
        }
        return staticDatas;
    }

    @Override
    public Map<String, StaticData> getMappedStaticData() throws Exception {

        PersistenceManager pm = PMF.get().getPersistenceManager();
        Map<String, StaticData> staticDatas = new HashMap<String, StaticData>();

        Query query = pm.newQuery(G_StaticData.class);
        query.setFilter("State == state");
        query.declareParameters("String state");
        try{
            List<G_StaticData> g_staticDatas =
                    (List<G_StaticData>)query.execute(StateConst.STATE_VALID);
            if(! g_staticDatas.isEmpty()){
                for(G_StaticData gsd : g_staticDatas){
                    StaticData sd = new StaticData();
                    sd.setState(gsd.getState());
                    sd.setDataType(gsd.getDataType());
                    sd.setDataValue(gsd.getDataValue());
                    staticDatas.put(gsd.getDataType(), sd);
                }
            }
        }finally {
            pm.close();
        }
        return staticDatas;
    }

    @Override
    public void newStaticData(String dataType, String dataValue) throws Exception {
        G_StaticData g_staticData = new G_StaticData();
        g_staticData.setDataType(dataType);
        g_staticData.setDataValue(dataValue);
        g_staticData.setState(StateConst.STATE_VALID);

        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(g_staticData);
        } finally {
            pm.close();
        }
    }

    @Override
    public void delStaticData(String dataType, String dataValue) throws Exception {

    }
}
