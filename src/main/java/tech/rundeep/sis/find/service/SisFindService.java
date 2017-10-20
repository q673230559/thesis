/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package tech.rundeep.sis.find.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import tech.rundeep.sis.find.entity.SisFind;
import tech.rundeep.sis.find.dao.SisFindDao;
import tech.rundeep.sis.find.entity.SisFindAdd;
import tech.rundeep.sis.find.dao.SisFindAddDao;

/**
 * 主子表生成Service
 * @author rundeep
 * @version 2017-09-20
 */
@Service
@Transactional(readOnly = true)
public class SisFindService extends CrudService<SisFindDao, SisFind> {

	@Autowired
	private SisFindAddDao sisFindAddDao;
	
	public SisFind get(String id) {
		SisFind sisFind = super.get(id);
		sisFind.setSisFindAddList(sisFindAddDao.findList(new SisFindAdd(sisFind)));
		return sisFind;
	}
	
	public List<SisFind> findList(SisFind sisFind) {
		return super.findList(sisFind);
	}
	
	public Page<SisFind> findPage(Page<SisFind> page, SisFind sisFind) {
		return super.findPage(page, sisFind);
	}
	
	@Transactional(readOnly = false)
	public void save(SisFind sisFind) {
		super.save(sisFind);
		for (SisFindAdd sisFindAdd : sisFind.getSisFindAddList()){
			if (sisFindAdd.getId() == null){
				continue;
			}
			if (SisFindAdd.DEL_FLAG_NORMAL.equals(sisFindAdd.getDelFlag())){
				if (StringUtils.isBlank(sisFindAdd.getId())){
					sisFindAdd.setSis_find(sisFind);
					sisFindAdd.preInsert();
					sisFindAddDao.insert(sisFindAdd);
				}else{
					sisFindAdd.preUpdate();
					sisFindAddDao.update(sisFindAdd);
				}
			}else{
				sisFindAddDao.delete(sisFindAdd);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(SisFind sisFind) {
		super.delete(sisFind);
		sisFindAddDao.delete(new SisFindAdd(sisFind));
	}
	
}