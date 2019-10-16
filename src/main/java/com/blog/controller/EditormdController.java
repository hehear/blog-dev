package com.blog.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.util.FileUtil;

/**
 * Editormdcontroller
 * @author dengxiangying
 * @date  2018年12月4日
 * @version V1.12.0
 */
@RestController
@RequestMapping("/editormd")  
public class EditormdController {  
	 
	@Value("${editormd.images.upload}")
	private String UPLOADED_FOLDER;
	
    @Autowired
    private ResourceLoader resourceLoader;
	
	private Logger logger=LoggerFactory.getLogger(EditormdController.class); 


	@RequestMapping(value="/uploadimg")
    public @ResponseBody Map<String,Object> demo(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file, HttpServletRequest request) {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        String fileName = file.getOriginalFilename();
        
        //保存
        try {
        	FileUtil.mkdirs(UPLOADED_FOLDER);
            byte[] bytes = file.getBytes();
            //保存到服务器
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            File dest = new File(UPLOADED_FOLDER + file.getOriginalFilename());
            while(dest.exists()){
                dest = new File(UPLOADED_FOLDER + file.getOriginalFilename());
            }
            file.transferTo(dest);
            
            resultMap.put("success", 1);
            resultMap.put("message", "上传成功！");
            //返回url
            resultMap.put("url","/editormd/upload/"+fileName+"/");
        } catch (Exception e) {
            resultMap.put("success", 0);
            resultMap.put("message", "上传失败！");
            e.printStackTrace();
        }
        System.out.println(resultMap.get("success"));
        return resultMap;
    }
	
	
    /**
     * 根据图片的名字，获取图片
     * @param filename
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/upload/{filename:.+}")
    public ResponseEntity<?> getFile(@PathVariable String filename) {
        logger.info("开始根据图片的名称，获取图片的请求");
        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(UPLOADED_FOLDER, filename).toString()));
        } catch (Exception e) {
            logger.warn("根据图片的名称，获取图片的请求，图片不存在，获取失败");
            return ResponseEntity.notFound().build();
        }

    }

}
