package com.meteorite.core.datasource.file;

import com.meteorite.core.datasource.DataMap;
import com.meteorite.core.datasource.DataSource;
import com.meteorite.core.datasource.DataSourceType;
import com.meteorite.core.meta.model.Meta;
import com.meteorite.fxbase.ui.component.form.ICanQuery;

import java.sql.SQLException;
import java.util.List;

/**
 * 文件系统数据源
 *
 * @author wei_jc
 * @since 1.0.0
 */
public class FileDataSource implements DataSource {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public DataSourceType getType() {
        return DataSourceType.FILE_SYSTEM;
    }

    @Override
    public Meta getProperties() {
        return null;
    }

    @Override
    public List<DataMap> retrieve(Meta meta, List<ICanQuery> queryList) throws SQLException {
        return null;
    }

    @Override
    public void delete(Meta meta, String... keys) throws Exception {

    }
}
