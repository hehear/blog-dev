package com.blog.service.impl;

import com.blog.mapper.ArticleMapper;
import com.blog.mapper.TagMapper;
import com.blog.mapper.WikiMapper;
import com.blog.model.Tag;
import com.blog.model.Wiki;
import com.blog.service.ITagService;
import com.blog.service.IWikiService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * service 实现类
 * @description
 * @author dengxiangying
 * @date 2019年1月8日
 */
@Service("wikiService")
public class WikiServiceImpl implements IWikiService {

	@Resource
	private WikiMapper wikiMapper ;



	@Override
	public List findList(Wiki wiki) {

		List<Wiki> list=(List<Wiki>)wikiMapper.selectList(wiki);

		return list;
	}

	@Override
	public Integer findMaxWikiId() {

		return wikiMapper.selectMaxWikiId()+1;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void add(Wiki wiki) {

		wikiMapper.insert(wiki);

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updt(Wiki wiki) {

		wikiMapper.updateByPrimaryKey(wiki);
	}

	/**
	 * 删除
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void remove(Integer id) {

		wikiMapper.deleteByPrimaryKey(id);

	}

}
