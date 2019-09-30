package com.liutao.services;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

enum SqlType {
    Update,
    Create
}
public class TemplateBase
{
    //@Autowired
    //@Qualifier("springdbJdbcTemplate")
    protected NamedParameterJdbcTemplate jtm;
    //保存对象的属性名和属性值
    protected MapSqlParameterSource paramMap = new MapSqlParameterSource();
    protected String tableName;

    public TemplateBase(){
        //System.out.println(String.format("jmt:%s",String.valueOf(jtm)));
    }
    public TemplateBase(String para_tableName){
        this.tableName = para_tableName;
    }

    public Object queryById(Class typeClass, int id)
    {
        String sql = "SELECT * FROM " + tableName + " WHERE id = :id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        List<Class<?>> products = jtm.query(sql, map, new BeanPropertyRowMapper(typeClass));

        return products==null|| products.size() == 0 ? null:products.get(0);
    }

    public <T> List<T> queryAll(Class<T> typeClass)
    {
        String sql = "SELECT * FROM " + tableName;
        List<T> entities = jtm.query(sql, new BeanPropertyRowMapper(typeClass));

        return entities;
    }

    public int insert(Object entity, SqlType type) throws Exception
    {
        insertExecute(entity, type);
        int id = (int)jtm.queryForObject("select last_insert_id()", new HashMap<>(),Integer.class);

        return id;
    }

    public Object insertWithFlush(Object entity, SqlType type) throws Exception
    {
        insertExecute(entity, type);
        Integer id = (Integer)jtm.queryForObject("select last_insert_id()", new HashMap<>(),Integer.class);
        Object ret = queryById(entity.getClass(), id);

        return ret;
    }

    private int insertExecute(Object entity, SqlType type) throws Exception {
        String sql = createSql(entity, type, tableName);

        int affectrows = (int) jtm.execute(sql, paramMap, (PreparedStatementCallback) statement -> statement.executeUpdate());
        return  affectrows;
    }

    /**
     * 获得属性名称和值的集合
     */
    private void load(Object obj) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {
        Class c = obj.getClass();
        Method[] methods = c.getMethods();
        for (Method m : methods) {
            String mName = m.getName();
            if (mName.startsWith("get") && !mName.startsWith("getClass")) {
                String fieldName = mName.substring(3, mName.length());

                Object value = m.invoke(obj, (Object[]) null);
                if (value instanceof String) {
                    //paramMap.addValue(fieldName, "\"" + value + "\"");
                    paramMap.addValue(fieldName, value);
                } else {
                    paramMap.addValue(fieldName, value);
                }
            }
        }
    }
    /**
     * createUpdateSql 自动生成添删改的SQL语句
     * 表中 字段名只能有一个包含id的字段
     * @param obj 对象
     * @param type 传递过来的操作类型 SqlType.delete update insert
     * @return auto generated sql statement
     */
    public String createSql(Object obj, SqlType type, String tableName) throws Exception
    {
        load(obj);//load class info......
        Class c = obj.getClass();
        tableName = !tableName.isEmpty() ? tableName : c.getSimpleName();
        StringBuffer strb = new StringBuffer();
        Object[] keys = paramMap.getParameterNames();
        int len = keys.length;
        switch (type) {
            case Create:
                strb.append("insert into " + tableName + "(");
                for (int i = 0; i < len; i++) {
                    strb.append(keys[i]);
                    strb.append(",");
                }
                strb.deleteCharAt(strb.length()-1);
                strb.append(") values(");
                for (int i = 0; i < len; i++) {
                    strb.append(":" +keys[i]+ ",");
                    //strb.append("?,");
                }
                strb.deleteCharAt(strb.length()-1);
                strb.append(")");
                break;
            case Update:
                strb.append("update " + tableName + " ");
                for (int i = 0; i < len; i++) {
                    strb.append("set" + keys[i] + "=:"+ keys[i]);
                    strb.append(",");
                }
                strb.deleteCharAt(strb.length()-1);
                for (int i = 0; i < len; i++) {
                    if ( ((String) keys[i]).toUpperCase() == "ID" ) {
                        strb.append(" where id=:id");
                        break;
                    }
                }
                break;
            default: throw new Exception("please provide valid type");
        }

        return  strb.toString();
    }
}
