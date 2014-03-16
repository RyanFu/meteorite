package com.meteorite.core.datasource.db.sql;

import com.meteorite.core.util.UString;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Sql Ԥ�������������
 *     ʹ�÷�����
 *         SqlBuilder.getInstance()
 *             .from("table name")
 *             .join("join table_name on (....)")
 *             .query("column name")
 *             .where("where condition")
 *             .and("and condition")
 *             .and("column_name [=|>|<...] ", value)
 *             .build()
 * @author wei_jc
 * @since 1.0.0
 */
public class SqlBuilder {
    private String table;
    private static final String SELECT_FORMAT = "SELECT %s FROM %s";
    private static final String SELECT_WHERE_FORMAT = "SELECT %s FROM %s WHERE %s";
    private static final String WITH_SELECT_FORMAT = "%s " + SELECT_FORMAT;
    private static final String WITH_SELECT_WHERE_FORMAT = "%s " + SELECT_WHERE_FORMAT;
    private static final String GROUP_FORMAT = " GROUP BY %s";
    private String columns = "";
    private String where;
    private String with;
    private String group;
    private boolean haveWhere;
    private boolean haveWith;
    private boolean isQuery = true;
    private boolean isBracket = false;
    private Queue<Object> paramQueue = new LinkedList<Object>();

    private SqlBuilder() {}

    public static SqlBuilder getInstance() {
        return new SqlBuilder();
    }

    /**
     * ��ѯ���У������ǵ���������Ҳ�����Ƕ���Զ��ŷָ�������
     *
     * @param columns ����
     * @return ����SqlԤ�������������
     */
    public SqlBuilder query(String columns) {
        if (UString.isEmpty(columns)) {
            return this;
        }
        if (this.columns.trim().length() > 0) {
            this.columns += ", " + columns;
        } else {
            this.columns += columns;
        }

        this.isQuery = true;

        return this;
    }

    /**
     * �����������ǵ���������Ҳ�����Ƕ���Զ��ŷָ��ı���
     *
     * @param table ����
     * @return ����SqlԤ�������������
     */
    public SqlBuilder from(String table) {
        this.table = table;

        return this;
    }

    /**
     * �������Ӳ����ı��������Ҫ�����������������ڱ���������������������� builder.join("table_name ON (...)")
     *
     * @param joinTable ���Ӳ����ı���
     * @return ����SqlԤ�������������
     */
    public SqlBuilder join(String joinTable) {
        table += " JOIN " + joinTable;

        return this;
    }

    /**
     * ��� where����
     *
     * @param where where����
     * @return ����SqlԤ�������������
     */
    public SqlBuilder where(String where) {
        this.where = where;
        this.haveWhere = true;

        return this;
    }

    /**
     * ��� and ����
     *
     * @param and and����
     * @return ����SqlԤ�������������
     */
    public SqlBuilder and(String and) {
        where += " AND " + and;

        return this;
    }

    /**
     * ������ AND (
     *
     * @return ����SqlԤ�������������
     */
    public SqlBuilder andBracket() {
        where += " AND (";
        isBracket = true;

        return this;
    }

    /**
     * ��� and ���������һ���AND �������������
     *
     * @param and and����
     * @param value ����ֵ
     * @return ����SqlԤ�������������
     */
    public SqlBuilder andBracket(String and, Object value) {
        if (value != null) {
            if (UString.isNotEmpty(value.toString())) {
                where += " AND (" + and + "?";
                paramQueue.offer(value);
                isBracket = true;
            }
        }

        return this;
    }

    /**
     * ��� and like ������ ���һ���AND �������������
     *
     * @param and and����
     * @param format likeƥ����ʽ��%%��ʾһ��%��%s��ʾƥ���ַ���
     * @param value ����ֵ
     * @return ����SqlԤ�������������
     */
    public SqlBuilder andBracketLike(String and, String format, String value) {
        if (UString.isNotEmpty(value)) {
            where += " AND (" + and + " LIKE ?";
            paramQueue.offer(String.format(format, value));
            isBracket = true;
        }

        return this;
    }

    /**
     * ����������
     *
     * @return ����SqlԤ�������������
     */
    public SqlBuilder leftBracket() {
        where += "(";
        isBracket = true;

        return this;
    }

    /**
     * ���������ţ���ƥ��������
     *
     * @return ����SqlԤ�������������
     */
    public SqlBuilder rightBracket() {
        if (isBracket) {
            if ("AND (".equals(where.substring(where.length() - 5))) {
                where = where.substring(0, where.length() - 6);
            } else {
                where += ")";
            }
            isBracket = false;
        }

        return this;
    }

    /**
     * ��� and in ����
     *
     * @param and   and in ����
     * @param value ����ֵ
     * @return ����SqlԤ�������������
     */
    public SqlBuilder andIn(String and, String value) {
        if (UString.isEmpty(value)) {
            return this;
        }

        where += " AND " + and + " IN (" + value+ ")";

        return this;
    }

    /**
     * ��� or value ����
     *
     * @param and   or value ����
     * @param value ����ֵ
     * @return ����SqlԤ�������������
     */
    public SqlBuilder orIn(String and, String value) {
        if (UString.isEmpty(value)) {
            return this;
        }

        where += " OR " + and + " IN (" + value+ ")";

        return this;
    }

    /**
     * ��� and like ����
     *
     * @param and   and like ����
     * @param format likeƥ����ʽ��%%��ʾһ��%��%s��ʾƥ���ַ���
     * @param value ����ֵ
     * @return ����SqlԤ�������������
     */
    public SqlBuilder andLike(String and, String format, String value) {
        if (UString.isNotEmpty(value)) {
            where += " AND " + and + " LIKE ?";
            paramQueue.offer(String.format(format, value));
        }

        return this;
    }

    /**
     * ��� and ����
     *
     * @param and   and����
     * @param value ����ֵ
     * @return ����SqlԤ�������������
     */
    public SqlBuilder and(String and, Object value) {
        if (null == value) {
            return this;
        }

        if (UString.isNotEmpty(value.toString())) {
            where += " AND " + and + "?";
            paramQueue.offer(value);
        }

        return this;
    }

    /**
     * ��� and ����������to_date(?, 'yyyy-MM-dd')
     *
     * @param and   and ����
     * @param value ����ֵ
     * @return ����SqlԤ�������������
     */
    public SqlBuilder andDate(String and, String value) {
        if (UString.isNotEmpty(value)) {
            where += " AND " + and + " to_date(?, 'yyyy-MM-dd')";
            paramQueue.offer(value);
        }

        return this;
    }

    /**
     * ��� or ����
     * @param or or����
     * @return ����SqlԤ�������������
     */
    public SqlBuilder or(String or) {
        where += " OR " + or;

        return this;
    }

    /**
     * ��� or ����
     * @param or    or ����
     * @param value ����ֵ
     * @return ����SqlԤ�������������
     */
    public SqlBuilder or(String or, Object value) {
        if (null == value) {
            return this;
        }
        if (UString.isNotEmpty(value.toString())) {
            where += " OR " + or + "?";
            paramQueue.offer(value);
        }

        return this;
    }

    /**
     * ��� or like ����
     *
     * @param or   or like ����
     * @param format likeƥ����ʽ��%%��ʾһ��%��%s��ʾƥ���ַ���
     * @param value ����ֵ
     * @return ����SqlԤ�������������
     */
    public SqlBuilder orLike(String or, String format, String value) {
        if (UString.isNotEmpty(value)) {
            where += " OR " + or + " LIKE ?";
            paramQueue.offer(String.format(format, value));
        }
        return this;
    }

    /**
     * ��� like ����
     *
     * @param name   like ��������
     * @param format likeƥ����ʽ��%%��ʾһ��%��%s��ʾƥ���ַ���
     * @param value ����ֵ
     * @return ����SqlԤ�������������
     */
    public SqlBuilder like(String name, String format, String value) {
        if (UString.isNotEmpty(name) && UString.isNotEmpty(value)) {
            where += name + " LIKE ?";
            paramQueue.offer(String.format(format, value));
        }
        return this;
    }

    /**
     * ʹ�ý�������
     *
     * @param desc �����ֶ�
     * @return ����SqlԤ�������������
     */
    public SqlBuilder desc(String desc) {
        if (UString.isNotEmpty(desc)) {
            where += " ORDER BY " + desc + " DESC";
        }

        return this;
    }

    /**
     * ��sql��ͷ����һ��with���
     * @param with with���
     * @return ����SqlԤ�������������
     */
    public SqlBuilder with(String with) {
        this.with = with;
        this.haveWith = true;

        return this;
    }

    /**
     * sql group by
     *
     * @param group Ҫ���з�����У��磺name, age
     * @return ����SqlԤ�������������
     */
    public SqlBuilder group(String group) {
        this.group = group;

        return this;
    }

    /**
     * sql max column
     *
     * @param column Ҫ����max����
     * @return ����SqlԤ�������������
     */
    public SqlBuilder max(String column) {
        return query(String.format("MAX(%s) AS max_%1$s", column));
    }

    /**
     * ��ӡ�������Sql���
     *
     * @return ���ع���õ�Sql���
     */
    public String build() {
        if (UString.isEmpty(columns)) {
            columns = "*";
        }

        String result = "";

        if (isQuery) {
            if (haveWhere) {
                if (haveWith) {
                    result = String.format(WITH_SELECT_WHERE_FORMAT, with, columns, table, where);
                } else {
                    result = String.format(SELECT_WHERE_FORMAT, columns, table, where);
                }
            } else {
                if (haveWith) {
                    result = String.format(WITH_SELECT_FORMAT, with, columns, table);
                } else {
                    result = String.format(SELECT_FORMAT, columns, table);
                }
            }
        }

        if (UString.isNotEmpty(group)) {
            result += String.format(GROUP_FORMAT, group);
        }

        return result;
    }

    /**
     * ��ȡ�ܼ�¼����sql���
     *
     * @return �����ܼ�¼����sql���
     */
    public String getCountSql() {
        if (isQuery) {
            if (haveWhere) {
                if (haveWith) {
                    return String.format(WITH_SELECT_WHERE_FORMAT, with, "count(1)", table, where);
                } else {
                    return String.format(SELECT_WHERE_FORMAT, "count(1)", table, where);
                }
            } else {
                if (haveWith) {
                    return String.format(WITH_SELECT_FORMAT, with, "count(1)", table);
                } else {
                    return String.format(SELECT_FORMAT, "count(1)", table);
                }
            }
        }
        return "";
    }

    @Override
    public String toString() {
        return build();
    }

    /**
     * ��ȡOracle��ҳSql
     *
     * @param start ��¼��ʼ��
     * @param end   ��¼������
     * @return ����Oracle��ҳ��
     */
    public String getPageSql(int start, int end) {
        return String.format("SELECT * FROM (SELECT nowpage.*,rownum rn FROM (%s) nowpage WHERE rownum<=%d) WHERE rn>%d", build(), end, start);
    }

    /**
     * ��ȡ����ֵ����
     *
     * @return ���ز���ֵ����
     */
    public Object[] getParamsValue() {
        return paramQueue.toArray();
    }
}
