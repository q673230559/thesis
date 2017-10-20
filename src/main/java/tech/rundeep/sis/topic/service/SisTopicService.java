/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package tech.rundeep.sis.topic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import tech.rundeep.sis.topic.entity.SisTopic;
import tech.rundeep.sis.topic.dao.SisTopicDao;
import tech.rundeep.sis.topic.entity.SisTopicPro;
import tech.rundeep.sis.topic.dao.SisTopicProDao;
import tech.rundeep.sis.topic.entity.SisTopicResource;
import tech.rundeep.sis.topic.dao.SisTopicResourceDao;

/**
 * 选题Service
 * @author rundeep
 * @version 2017-10-08
 */
@Service
@Transactional(readOnly = true)
public class SisTopicService extends CrudService<SisTopicDao, SisTopic> {

	@Autowired
	private SisTopicProDao sisTopicProDao;
	@Autowired
	private SisTopicResourceDao sisTopicResourceDao;
	
	public SisTopic get(String id) {
		SisTopic sisTopic = super.get(id);
		sisTopic.setSisTopicProList(sisTopicProDao.findList(new SisTopicPro(sisTopic)));
		sisTopic.setSisTopicResourceList(sisTopicResourceDao.findList(new SisTopicResource(sisTopic)));
		return sisTopic;
	}
	
	public List<SisTopic> findList(SisTopic sisTopic) {
		return super.findList(sisTopic);
	}
	
	public Page<SisTopic> findPage(Page<SisTopic> page, SisTopic sisTopic) {
		return super.findPage(page, sisTopic);
	}
	
	@Transactional(readOnly = false)
	public void save(SisTopic sisTopic) {
		super.save(sisTopic);
		for (SisTopicPro sisTopicPro : sisTopic.getSisTopicProList()){
			if (sisTopicPro.getId() == null){
				continue;
			}
			if (SisTopicPro.DEL_FLAG_NORMAL.equals(sisTopicPro.getDelFlag())){
				if (StringUtils.isBlank(sisTopicPro.getId())){
					sisTopicPro.setSis_topic(sisTopic);
					sisTopicPro.preInsert();
					sisTopicProDao.insert(sisTopicPro);
				}else{
					sisTopicPro.preUpdate();
					sisTopicProDao.update(sisTopicPro);
				}
			}else{
				sisTopicProDao.delete(sisTopicPro);
			}
		}
		for (SisTopicResource sisTopicResource : sisTopic.getSisTopicResourceList()){
			if (sisTopicResource.getId() == null){
				continue;
			}
			if (SisTopicResource.DEL_FLAG_NORMAL.equals(sisTopicResource.getDelFlag())){
				if (StringUtils.isBlank(sisTopicResource.getId())){
					sisTopicResource.setSis_topic(sisTopic);
					sisTopicResource.preInsert();
					sisTopicResourceDao.insert(sisTopicResource);
				}else{
					sisTopicResource.preUpdate();
					sisTopicResourceDao.update(sisTopicResource);
				}
			}else{
				sisTopicResourceDao.delete(sisTopicResource);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(SisTopic sisTopic) {
		super.delete(sisTopic);
		sisTopicProDao.delete(new SisTopicPro(sisTopic));
		sisTopicResourceDao.delete(new SisTopicResource(sisTopic));
	}
	
}