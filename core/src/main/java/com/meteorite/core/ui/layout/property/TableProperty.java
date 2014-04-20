package com.meteorite.core.ui.layout.property;

import com.meteorite.core.meta.model.Meta;
import com.meteorite.core.meta.model.MetaField;
import com.meteorite.core.ui.layout.LayoutManager;
import com.meteorite.core.ui.layout.PropertyNames;
import com.meteorite.core.ui.model.*;
import com.meteorite.core.util.UUIDUtil;

import java.util.*;

/**
 * 表格属性配置
 *
 * @author wei_jc
 * @since 1.0.0
 */
public class TableProperty implements PropertyNames {
    private List<TableFieldProperty> fieldProperties;

    public TableProperty(View view) {
        fieldProperties = new ArrayList<>();
        for (MetaField field : view.getMetaFieldList()) {
            Map<String, String> map = view.getMetaFieldConfig(field);
            fieldProperties.add(new TableFieldProperty(field, map));
        }
        // 排序
        Collections.sort(fieldProperties, new Comparator<TableFieldProperty>() {
            @Override
            public int compare(TableFieldProperty o1, TableFieldProperty o2) {
                return o1.getSortNum() - o2.getSortNum();
            }
        });
    }

    public List<TableFieldProperty> getFieldProperties() {
        return fieldProperties;
    }

    public static View createTableView(Meta meta) {
        View view = new View();
        view.setId(UUIDUtil.getUUID());
        view.setName(meta.getName() + "TableView");
        view.setDisplayName(meta.getDisplayName() + "表格视图");
        view.setValid(true);
        view.setInputDate(new Date());
        view.setSortNum(0);

        List<ViewProperty> viewProperties = new ArrayList<>();


        // 创建属性配置
        for (MetaField field : meta.getFields()) {
            viewProperties.addAll(TableFieldProperty.getViewProperties(view, field));
        }
        view.setViewProperties(viewProperties);

        return view;
    }
}
