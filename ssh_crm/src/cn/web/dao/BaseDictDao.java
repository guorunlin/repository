package cn.web.dao;

import java.util.List;

import cn.web.entity.BaseDict;

public interface BaseDictDao extends BaseDao<BaseDict>{
	//根据类型获得数据字典列表
	List<BaseDict> getListByTypeCode(String dict_type_code);

}
