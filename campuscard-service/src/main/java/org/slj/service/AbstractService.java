package org.slj.service;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.slj.MyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
*@Description: 基于通用MyBatis Mapper插件的Service接口的实现
*@create: 2019/3/14
*@Author: SLJ
*/
public abstract class AbstractService<T> {

    @Autowired
    protected MyMapper<T> mapper;

    /**
     * 当前泛型真实类型的Class
     */
    private Class<T> modelClass;

    public AbstractService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    /**
     * 单体插入
     * @param model
     */
    public void saveModel(T model) {
        mapper.insertSelective(model);
    }

    /**
     * 批量插入
     * @param models
     */
    public void saveList(List<T> models) {
        mapper.insertList(models);
    }

    /**
     * 根据id删除
     * @param id
     */
    public void deleteById(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据id批量删除
     * @param ids
     */
    public void deleteByIds(String ids) {
        mapper.deleteByIds(ids);
    }

    /**
     * 单体更新
     * @param model
     */
    public void update(T model) {
        mapper.updateByPrimaryKeySelective(model);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public T findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @SuppressWarnings("unchecked")
	public T findBy(String fieldName, Object value) throws TooManyResultsException {
        try {
            T model = modelClass.newInstance();
            Field field = modelClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(model, value);
            return mapper.selectOne(model);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("查询操作异常!");
        }
    }

    /**
     * 根据id批量查询
     * @param ids
     * @return
     */
    public List<T> findByIds(String ids) {
        return mapper.selectByIds(ids);
    }

    /**
     * 根据条件查询
     * @param condition
     * @return
     */
    public List<T> findByCondition(Condition condition) {
        return mapper.selectByCondition(condition);
    }

    /**
     * 查询全部
     * @return
     */
    public List<T> findAll() {
        return mapper.selectAll();
    }
}
