package com.meteorite.core.ui.model;

import com.meteorite.core.meta.model.Meta;
import com.meteorite.core.meta.model.MetaField;
import com.meteorite.core.util.UNumber;
import com.meteorite.core.util.UString;

import java.util.*;

/**
 * 视图
 *
 * @author wei_jc
 * @since  1.0.0
 */
public class View {
    /** 视图ID */
    private String id;
    /** 视图名称 */
    private String name;
    /** 显示名 */
    private String displayName;
    /** 描述 */
    private String desc;
    /** 是否有效 */
    private boolean isValid;
    /** 录入时间 */
    private Date inputDate;
    /** 排序号 */
    private int sortNum;

    /** 元字段属性Map */
    private Map<MetaField, Map<String, ViewProperty>> fieldPropMap = new HashMap<>();

    private List<ViewLayout> layoutList;
    private List<ViewConfig> configs;
    private List<ViewProperty> viewProperties;

    private Map<String, ViewProperty> propMap = new HashMap<>();
    private Meta meta;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

    public List<ViewLayout> getLayoutList() {
        if (layoutList == null) {
            layoutList = new ArrayList<>();
        }
        return layoutList;
    }

    public void setLayoutList(List<ViewLayout> layoutList) {
        this.layoutList = layoutList;
    }

    public List<ViewConfig> getConfigs() {
        if (configs == null) {
            configs = new ArrayList<>();
        }
        return configs;
    }

    public void setConfigs(List<ViewConfig> configs) {
        this.configs = configs;
    }

    public void addConfig(ViewConfig config) {
        getConfigs().add(config);
    }

    public ViewLayout getTableLayout() {
        for (ViewLayout layout : layoutList) {
            if (layout.getLayout().getName().equals("TABLE")) {
                return layout;
            }
        }

        return null;
    }

    public ViewLayout getFormLayout() {
        for (ViewLayout layout : layoutList) {
            if (layout.getLayout().getName().equals("FORM")) {
                return layout;
            }
        }

        return null;
    }

    public List<ViewProperty> getViewProperties() {
        return viewProperties;
    }

    public void setViewProperties(List<ViewProperty> viewProperties) {
        this.viewProperties = viewProperties;
        for (ViewProperty property : viewProperties) {
            propMap.put(property.getProperty().getId(), property);
            MetaField field = property.getField();
            if (field != null) {
                Map<String, ViewProperty> propMap = fieldPropMap.get(field);
                if (propMap == null) {
                    propMap = new HashMap<>();
                    fieldPropMap.put(field, propMap);
                }
                propMap.put(property.getProperty().getId(), property);
            }
        }
    }

    public ViewProperty getProperty(String propertyId) {
        return propMap.get(propertyId);
    }

    public String getStringProperty(String propertyId) {
        return propMap.get(propertyId).getValue();
    }

    public int getIntProperty(String propertyId) {
        return UNumber.toInt(propMap.get(propertyId).getValue());
    }

    public boolean getBooleanProperty(String propertyId) {
        return UString.toBoolean(propMap.get(propertyId).getValue());
    }

    /**
     * 根据元字段Id获得此元字段的布局配置信息
     *
     * @param field 元字段ID
     * @return 返回元字段配置
     */
    public Map<String, ViewProperty> getMetaFieldConfig(MetaField field) {
        return fieldPropMap.get(field);
    }

    public List<MetaField> getMetaFieldList() {
        return new ArrayList<>(fieldPropMap.keySet());
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public ViewProperty getViewProperty(MetaField field, String propName) {
        for (ViewProperty property : viewProperties) {
            if (property.getField() == null || property.getProperty() == null) {
                continue;
            }

            if (property.getField() == field && property.getProperty().getName().equalsIgnoreCase(propName)) {
                return property;
            }
        }

        return null;
    }
}
