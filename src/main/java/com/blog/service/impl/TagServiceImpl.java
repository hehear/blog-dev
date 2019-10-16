package com.blog.service.impl;

import com.blog.mapper.ArticleMapper;
import com.blog.mapper.TagMapper;
import com.blog.model.ArticleType;
import com.blog.model.Tag;
import com.blog.service.ITagService;
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
@Service("tagService")
public class TagServiceImpl implements ITagService {

	@Resource
	private TagMapper tagMapper ;
	@Resource
	private ArticleMapper articleMapper ;

	@Override
	public List findArticleTagList() {

		//所有的文章类型
		List<Tag> list=(List<Tag>)tagMapper.selectAll();
		
		return list;
	}

	@Override
	public List findList(Tag tag) {
		//所有的文章标签
		List<Tag> list=(List<Tag>)tagMapper.selectList(tag);

		return list;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void add(Tag tag) {

		tagMapper.insert(tag);

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updt(Tag tag) {

		tagMapper.updateByPrimaryKey(tag);
	}

	/**
	 * 删除
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void remove(Integer id) {

		tagMapper.deleteByPrimaryKey(id);

	}

}
