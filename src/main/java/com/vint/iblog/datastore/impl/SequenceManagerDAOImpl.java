package com.vint.iblog.datastore.impl;

import com.vint.iblog.datastore.PMF;
import com.vint.iblog.datastore.dataclass.config.G_Sequence;
import com.vint.iblog.datastore.define.SequenceManagerDAO;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.util.List;

/**
 * 基于Google DataStore实现的序列管理器
 * <p/>
 * Created by Vin on 14-2-17.
 */
public class SequenceManagerDAOImpl implements SequenceManagerDAO {

    @Override
    public String getCurrentSeq(String type) throws Exception {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        Query query = pm.newQuery(G_Sequence.class);
        query.setFilter("Type == inType");
        query.declareParameters("String inType");
        try {
            List<G_Sequence> obj = (List<G_Sequence>) query.execute(type);
            if (!obj.isEmpty()) {
                return obj.get(0).getHexMark();
            } else
                throw new Exception("Sequence not found [" + type + "]");
        } finally {
            pm.close();
        }
    }

    @Override
    public String getNextSeq(String type) throws Exception {
        return null;
    }

    @Override
    public List<String> getNextRangeSeq(String type, int range) throws Exception {
        return null;
    }

    @Override
    public void createSequence(String type, String hexMark) throws Exception {
        G_Sequence g_sequence = new G_Sequence();
        g_sequence.setDecCount(0);
        g_sequence.setHexMark(hexMark);
        g_sequence.setType(type);

        PersistenceManager pm = PMF.get().getPersistenceManager();

        // 查询指定类型的序列记录是否存在
        Query query = pm.newQuery(G_Sequence.class);
        query.setFilter("Type == inType");
        query.declareParameters("String inType");

        try {
            List<G_Sequence> g_sequences = (List<G_Sequence>) query.execute(type);
            if (!g_sequences.isEmpty()) {
                for (G_Sequence gs : g_sequences)
                    pm.deletePersistent(gs);
            }
            pm.makePersistent(g_sequence);
        } finally {
            pm.close();
        }

    }
}
