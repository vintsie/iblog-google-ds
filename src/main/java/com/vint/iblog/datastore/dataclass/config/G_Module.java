package com.vint.iblog.datastore.dataclass.config;

import com.google.appengine.api.datastore.Key;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 *
 * Created by Vin on 14-4-14.
 */
@PersistenceCapable
public class G_Module {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    private String moduleId;
    private String preModuleId;

    private String ModuleCode;
    private String moduleType;
    private String ModuleName;
    private String ModuleUrl;
    private String State;

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getPreModuleId() {
        return preModuleId;
    }

    public void setPreModuleId(String preModuleId) {
        this.preModuleId = preModuleId;
    }

    public String getModuleCode() {
        return ModuleCode;
    }

    public void setModuleCode(String moduleCode) {
        ModuleCode = moduleCode;
    }

    public String getModuleName() {
        return ModuleName;
    }

    public void setModuleName(String moduleName) {
        ModuleName = moduleName;
    }

    public String getModuleUrl() {
        return ModuleUrl;
    }

    public void setModuleUrl(String moduleUrl) {
        ModuleUrl = moduleUrl;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }
}
