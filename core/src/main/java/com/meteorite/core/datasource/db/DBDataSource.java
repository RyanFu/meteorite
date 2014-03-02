package com.meteorite.core.datasource.db;

import com.alibaba.fastjson.annotation.JSONField;
import com.meteorite.core.datasource.DataSource;
import com.meteorite.core.datasource.DataSourceType;
import com.meteorite.core.dict.DictManager;
import com.meteorite.core.meta.MetaDataType;
import com.meteorite.core.meta.annotation.MetaElement;
import com.meteorite.core.meta.annotation.MetaFieldElement;
import com.meteorite.core.meta.model.Meta;
import com.meteorite.core.meta.model.MetaField;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库数据源
 *
 * @author wei_jc
 * @since 1.0.0
 */
@XmlRootElement
@XmlType(propOrder = {"databaseType", "driverClass", "url", "username", "password", "dbVersion", "filePath"})
@MetaElement(displayName = "数据库数据源")
public class DBDataSource implements DataSource {
    public static final String DRIVER_CLASS = "driverClass";
    public static final String URL = "url";
    public static final String USER_NAME = "username";
    public static final String PASSWORD = "password";
    public static final String FILE_PATH = "filePath";
    public static final String DB_VERSION = "dbVersion";
    public static final String DATABASE_TYPE = "databaseType";

    private String name;

    private Meta properties;

    public DBDataSource() {
        initProperties();
    }

    public DBDataSource(String name, String driverClass, String url, String username, String password, String dbVersion) {
        this.name = name;

        initProperties();
        setDriverClass(driverClass);
        setUrl(url);
        setUsername(username);
        setPassword(password);
        setDbVersion(dbVersion);
    }

    private void initProperties() {
        List<MetaField> fields = new ArrayList<>();
        fields.add(new MetaField(DRIVER_CLASS, "驱动类"));
        fields.add(new MetaField(URL, "数据库URL"));
        fields.add(new MetaField(USER_NAME, "用户名"));
        fields.add(new MetaField(PASSWORD, "密码"));
        fields.add(new MetaField(FILE_PATH, "数据库文件路径"));
        fields.add(new MetaField(DB_VERSION, "数据库版本"));
        fields.add(new MetaField(DATABASE_TYPE, "数据库类型", DictManager.getDict(DatabaseType.class).getId()));

        properties = new Meta("DBDataSourceProperties", "数据库连接属性");
        properties.setFields(fields);
    }

    @XmlAttribute
    @MetaFieldElement(displayName = "数据库类型", dataType = MetaDataType.DICT, dictId = "com.meteorite.core.datasource.db.DatabaseType")
    public DatabaseType getDatabaseType() {
        return DatabaseType.get(properties.getFieldValue(DATABASE_TYPE));
    }

    public void setDatabaseType(DatabaseType databaseType) {
        properties.setFieldValue(DATABASE_TYPE, databaseType.getName());
    }

    @XmlAttribute
    @MetaFieldElement(displayName = "名称")
    @JSONField(name = "displayName")
    public String getName() {
        return name;
    }

    @Override
    public DataSourceType getType() {
        return DataSourceType.DATABASE;
    }

    @Override
    @JSONField(serialize = false)
    public Meta getProperties() {
        return properties;
    }

    public void setName(String name) {
        this.name = name;
    }

    @MetaFieldElement(displayName = "驱动类")
    public String getDriverClass() {
        return properties.getFieldValue(DRIVER_CLASS);
    }

    public void setDriverClass(String driverClass) {
        properties.setFieldValue(DRIVER_CLASS, driverClass);
    }

    @MetaFieldElement(displayName = "数据库URL")
    public String getUrl() {
        return properties.getFieldValue(URL);
    }

    public void setUrl(String url) {
        properties.setFieldValue(URL, url);
    }

    @MetaFieldElement(displayName = "用户名")
    public String getUsername() {
        return properties.getFieldValue(USER_NAME);
    }

    public void setUsername(String username) {
        properties.setFieldValue(USER_NAME, username);
    }

    @MetaFieldElement(displayName = "密码", dataType = MetaDataType.PASSWORD)
    public String getPassword() {
        return properties.getFieldValue(PASSWORD);
    }

    public void setPassword(String password) {
        properties.setFieldValue(PASSWORD, password);
    }

    @MetaFieldElement(displayName = "数据库文件路径")
    public String getFilePath() {
        return properties.getFieldValue(FILE_PATH);
    }

    public void setFilePath(String filePath) {
        properties.setFieldValue(FILE_PATH, filePath);
    }

    @MetaFieldElement(displayName = "数据库版本")
    public String getDbVersion() {
        return properties.getFieldValue(DB_VERSION);
    }

    public void setDbVersion(String dbVersion) {
        properties.setFieldValue(DB_VERSION, dbVersion);
    }
}
