package com.douding.file.controller.admin;

import com.douding.server.dto.FileDto;
import com.douding.server.dto.ResponseDto;
import com.douding.server.service.FileService;
import com.douding.server.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/*
    返回json 应用@RestController
    返回页面  用用@Controller
 */
@RequestMapping("/admin/file")
@RestController
public class UploadController {

    private static final Logger LOG = LoggerFactory.getLogger(UploadController.class);
//    public  static final String BUSINESS_NAME ="文件上传";
    @Resource
    private TestService testService;



    @Value("${file.path}")
    private String FILE_PATH;

    @Value("${file.domain}")
    private String FILE_DOMAIN;


    @Resource
    private FileService fileService;

    @RequestMapping("/upload")
    public ResponseDto upload(@RequestBody FileDto fileDto) throws Exception {
        ResponseDto<Object> responseDto = new ResponseDto<>();
        //调用合并分片
        if (fileDto!=null){
            String use = fileDto.getUse();
            fileService.save(fileDto,use);
           responseDto.setSuccess(true);
        }
        return responseDto;
    }



    //合并分片
    public static void merge(FileDto fileDto) throws Exception {
        LOG.info("合并分片开始");

    }

    @GetMapping("/check/{key}")
    public ResponseDto check(@PathVariable String key) throws Exception {
        LOG.info("检查上传分片开始：{}", key);
        //查询数据库是否有分片
        FileDto fileDto = fileService.findByKey(key);
        //获取相应参数
        ResponseDto<Object> responseDto = new ResponseDto<>();
        if (fileDto==null){
           responseDto.setSuccess(false);
        }
        responseDto.setSuccess(true);
        return responseDto;
    }

}//end class
