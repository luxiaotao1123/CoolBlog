package cn.blog.controller;

import cn.blog.utils.R1;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("admin/")
public class StreamController {
    private static final Logger logger = LoggerFactory.getLogger(StreamController.class);

    @ApiOperation(value = "文件流传输API",notes = "")
    @ApiImplicitParam(name = "myfile",value = "上传的文件",required = true,dataType = "multipart/form-data")
    @PostMapping("file")
    public R1 fileUpload(@RequestParam("myfile") MultipartFile myfile) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String datefile = sdf.format(new Date());
        if (myfile.isEmpty()){
            return R1.error(500,"文件为空");
        }
        String fileName = myfile.getOriginalFilename();
        logger.info(fileName+"文件已上传");
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
        String newfileName = datefile+fileSuffix;
        String filePath = "//picture/";
        File getFile = new File(filePath+newfileName);
        if (getFile.getParentFile().exists()){
            myfile.transferTo(getFile);
            return R1.success(200,"106.15.205.155:8079/"+newfileName);
        }
        return R1.error(500,"目标文件夹不存在");
    }

    @ApiOperation(value = "上传博客预览图API",notes = "")
    @ApiImplicitParam(name = "blogpreview",value = "上传的博客预览图",required = true,dataType = "multipart/form-data")
    @PostMapping("blog/preview")
    public R1 previewUpload(@RequestParam("blogpreview") MultipartFile blogpreview) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String datefile = sdf.format(new Date());
        if (blogpreview.isEmpty()){
            return R1.error(500,"预览图为空");
        }
        String previewName = blogpreview.getOriginalFilename();
        logger.info(previewName+"文件已上传");
        String previewSuffix  = previewName.substring(previewName.lastIndexOf("."));
        String newPreviewName = datefile+previewSuffix;
        String filePath = "//picture/preview/";
        String thumPath = "//picture/thumpreview/thum"+newPreviewName;
        String homePagePath = filePath+newPreviewName;
        File getFile = new File(filePath+newPreviewName);
        if (getFile.getParentFile().exists()){
            Thumbnails.of(blogpreview.getInputStream()).size(960,326).toFile(thumPath);     //首页
            Thumbnails.of(blogpreview.getInputStream()).width(760).toFile(homePagePath);
            //blogpreview.transferTo(getFile);
            R1 r1 = new R1();
            r1.put("preview","106.15.205.155:8079/preview/"+newPreviewName);
            r1.put("thumpreview","106.15.205.155:8079/thumpreview/thum"+newPreviewName);
            return r1;
        }
        return R1.error(500,"目标文件夹不存在");
    }
}

