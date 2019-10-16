package com.blog.rest;

import com.blog.common.CommonRest;
import com.blog.common.SimpleMessage;
import com.blog.model.Tag;
import com.blog.service.ITagService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * rest
 * @description
 * @author dengxiangying
 * @date 2019年6月25日
 */
@RestController
@RequestMapping("/tag")
@SuppressWarnings("all")
public class TagRest extends CommonRest{


	@Resource(name="tagService")
	private ITagService tagService;


	/**
	 * 查询
	 * @return
	 */
	@RequestMapping("/getArticleTagList")
	public SimpleMessage<Object> getArticleTagList(){

		SimpleMessage<Object> sm = new SimpleMessage<>();
		try {

			List list = tagService.findArticleTagList();

			sm.setList(list);

		} catch (Exception e) {
			//封装报错信息
			sm.setMessage(e.getMessage());
			sm.setCode("500");
			return error(e);
		}

		return sm;
	}

	/**
	 * 查询
	 * @return
	 */
	@RequestMapping(value="/list",method = RequestMethod.POST)
	public SimpleMessage<Object> getList(Tag tag){

		SimpleMessage<Object> sm = new SimpleMessage<>();
		try {

			List list = tagService.findList(tag);

			sm.setList(list);

		} catch (Exception e) {
			//封装报错信息
			sm.setMessage(e.getMessage());
			sm.setCode("500");
			return error(e);
		}

		return sm;
	}

	/**
	 * 保存
	 * @return
	 */
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public SimpleMessage<Object> save(Tag tag){

		SimpleMessage<Object> sm = new SimpleMessage<>();
		try {

			if(tag.getId()==null){
				tagService.add(tag);
			}else{
				tagService.updt(tag);
			}

		} catch (Exception e) {
			//封装报错信息
			sm.setMessage(e.getMessage());
			sm.setCode("500");
			return error(e);
		}

		return sm;
	}

	/**'
	 * 删除
	 * @param
	 * @return
	 */
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	public SimpleMessage<Object> delete(Integer id){

		SimpleMessage<Object> sm = new SimpleMessage<>();

		try {

			tagService.remove(id);

		} catch (Exception e) {
			//封装报错信息
			sm.setMessage(e.getMessage());
			sm.setCode("500");
			error(e);
		}
		return sm;
	}

}
