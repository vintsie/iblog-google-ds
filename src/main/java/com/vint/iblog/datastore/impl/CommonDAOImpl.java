package com.vint.iblog.datastore.impl;

import com.vint.iblog.datastore.PMF;
import com.vint.iblog.datastore.dataclass.ebean.G_GitHubCatalog;
import com.vint.iblog.datastore.define.CommonDAO;
import org.vint.iblog.common.bean.nor.CBNGitHubCatalog;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.util.*;

/**
 *
 * Created by Vin on 14-5-17.
 */
public class CommonDAOImpl implements CommonDAO {

    @Override
    public List<CBNGitHubCatalog> getGitHubCatalogs() throws Exception {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        List<CBNGitHubCatalog> data = new ArrayList<CBNGitHubCatalog>();

        Query query = pm.newQuery(G_GitHubCatalog.class);
        try {
            Object object = query.execute();
            if (null != object) {
                List resultSet = (List) object;
                for (Object element : resultSet) {
                    G_GitHubCatalog gCatalog = (G_GitHubCatalog) element;
                    CBNGitHubCatalog catalog = new CBNGitHubCatalog();
                    catalog.setOwner(gCatalog.getOwner());
                    catalog.setPath(gCatalog.getPath());
                    catalog.setRepo(gCatalog.getRepo());
                    catalog.setType(gCatalog.getType());
                    data.add(catalog);
                }
            }
        } finally {
            pm.close();
        }
        return data;
    }

    @Override
    public void saveGitHubCatalog(String owner, String repo, String path, String type) throws Exception {
        G_GitHubCatalog catalog = new G_GitHubCatalog();
        catalog.setOwner(owner);
        catalog.setRepo(repo);
        catalog.setPath(path);
        catalog.setType(type);
        persistenceGitHubCatalog(catalog);
    }

    /**
     * 持久化目录数据
     *
     * @param gitHubCatalog 目录对象
     */
    private void persistenceGitHubCatalog(G_GitHubCatalog gitHubCatalog) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.makePersistent(gitHubCatalog);
        } finally {
            pm.close();
        }
    }
}
