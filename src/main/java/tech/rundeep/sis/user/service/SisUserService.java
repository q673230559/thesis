/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package tech.rundeep.sis.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import tech.rundeep.sis.user.entity.SisUser;
import tech.rundeep.sis.user.dao.SisUserDao;
import tech.rundeep.sis.user.entity.SisUserPow;
import tech.rundeep.sis.user.dao.SisUserPowDao;
import tech.rundeep.sis.user.entity.SisUserPro;
import tech.rundeep.sis.user.dao.SisUserProDao;

/**
 * 用户相关功能Service
 * @author rundeep
 * @version 2017-09-26
 */
@Service
@Transactional(readOnly = true)
public class SisUserService extends CrudService<SisUserDao, SisUser> {

	@Autowired
	private SisUserPowDao sisUserPowDao;
	@Autowired
	private SisUserProDao sisUserProDao;
	
	public SisUser get(String id) {
		SisUser sisUser = super.get(id);
		sisUser.setSisUserPowList(sisUserPowDao.findList(new SisUserPow(sisUser)));
		sisUser.setSisUserProList(sisUserProDao.findList(new SisUserPro(sisUser)));
		return sisUser;
	}
	
	public List<SisUser> findList(SisUser sisUser) {
		return super.findList(sisUser);
	}
	
	public Page<SisUser> findPage(Page<SisUser> page, SisUser sisUser) {
		return super.findPage(page, sisUser);
	}
	
	@Transactional(readOnly = false)
	public void save(SisUser sisUser) {
		super.save(sisUser);
		for (SisUserPow sisUserPow : sisUser.getSisUserPowList()){
			if (sisUserPow.getId() == null){
				continue;
			}
			if (SisUserPow.DEL_FLAG_NORMAL.equals(sisUserPow.getDelFlag())){
				if (StringUtils.isBlank(sisUserPow.getId())){
					sisUserPow.setSis_user(sisUser);
					sisUserPow.preInsert();
					sisUserPowDao.insert(sisUserPow);
				}else{
					sisUserPow.preUpdate();
					sisUserPowDao.update(sisUserPow);
				}
			}else{
				sisUserPowDao.delete(sisUserPow);
			}
		}
		for (SisUserPro sisUserPro : sisUser.getSisUserProList()){
			if (sisUserPro.getId() == null){
				continue;
			}
			if (SisUserPro.DEL_FLAG_NORMAL.equals(sisUserPro.getDelFlag())){
				if (StringUtils.isBlank(sisUserPro.getId())){
					sisUserPro.setSis_user(sisUser);
					sisUserPro.preInsert();
					sisUserProDao.insert(sisUserPro);
				}else{
					sisUserPro.preUpdate();
					sisUserProDao.update(sisUserPro);
				}
			}else{
				sisUserProDao.delete(sisUserPro);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(SisUser sisUser) {
		super.delete(sisUser);
		sisUserPowDao.delete(new SisUserPow(sisUser));
		sisUserProDao.delete(new SisUserPro(sisUser));
	}
	
}