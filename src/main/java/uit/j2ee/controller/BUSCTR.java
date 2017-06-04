/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.j2ee.controller;
 
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import uit.j2ee.app.BUSRespone;
import uit.j2ee.app.BUSStatus;
import uit.j2ee.util.BUSReflection;

/**
 *
 * @author LAP10599-local
 */
@Controller
public class BUSCTR {

    @RequestMapping("/bus")
    public ModelAndView ajaxBusiness(HttpServletRequest request, HttpServletResponse response) {
        BUSReflection rf = new BUSReflection("uit.j2ee.model");
        try {
            if (rf.setDataFromRequest(request, response) == false) {
                return new ModelAndView("/home/json", "data", new BUSRespone(
                        null, BUSStatus.paramsInvalid()));
            }
            Object re = rf.involke();
            return new ModelAndView("/home/json", "data", re);
        } catch (Exception ex) {
            return new ModelAndView("/home/json", "data", new BUSRespone(
                    null, BUSStatus.exceptiond(), ex));
        }
    }

    @RequestMapping("/upload") // //new annotation since 4.3
    public ModelAndView singleFileUpload(HttpServletRequest request, @RequestParam("file") MultipartFile file, @RequestParam("folder") String folder) {

        if (file.isEmpty()) {
            return new ModelAndView("/home/json", "data", new BUSRespone(
                    null, BUSStatus.paramsInvalid()));
        }

        String folderName = folder.toString().trim();
        if (folderName.length() == 0) {
            return new ModelAndView("/home/json", "data", new BUSRespone(
                    null, BUSStatus.paramsInvalid()));
        }

        try {

//             Get the file and save it somewhere
            byte[] bytes = file.getBytes();

            String uploadFileName = file.getOriginalFilename();
            int iDot = uploadFileName.lastIndexOf('.');
            String extension = iDot != -1 ? uploadFileName.substring(iDot + 1) : "";
            DateFormat formatter = new SimpleDateFormat("yyyyMMdd-HHmmss-SSSS");

            String url = String.format("/%s/%s.%s",
                    folderName,
                    formatter.format(new Date()),
                    extension);

            String baseFolder = request.getServletContext().getInitParameter("upload-folder");
            String filePath = baseFolder + url;
            Path path = Paths.get(filePath);
            Files.write(path, bytes);

            return new ModelAndView("/home/json", "data", new BUSRespone(url));
        } catch (Exception ex) {
            return new ModelAndView("/home/json", "data", new BUSRespone(
                    null, BUSStatus.exceptiond(), ex));
        }
    }

    @RequestMapping(value = "/image/{value}.{ext}")
    public void src(HttpServletRequest request, HttpServletResponse response, @PathVariable String value, @PathVariable String ext) {
        try {
            String baseFolder = request.getServletContext().getInitParameter("upload-folder"); 
            String path = String.format("%simage/%s.%s", baseFolder, value, ext);
            System.out.println(path); 
            Path pathOb = Paths.get(path);
            byte[] bs = Files.readAllBytes(pathOb);
            //response.setContentType(ufile.type);
            response.setContentLength(bs.length);
            FileCopyUtils.copy(bs, response.getOutputStream());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/image/{value}/{sub}.{ext}")
    public void src1(HttpServletRequest request, HttpServletResponse response, @PathVariable String value, @PathVariable String sub, @PathVariable String ext) {
        src(request, response, value + "/" + sub, ext);
    }

    @RequestMapping(value = "/image/{value}/{sub}/{sub2}.{ext}")
    public void src2(HttpServletRequest request, HttpServletResponse response, @PathVariable String value, @PathVariable String sub, @PathVariable String sub2, @PathVariable String ext) {
        src(request, response,
                String.format("%s/%s/%s", value, sub, sub2),
                ext);
    }
}

class UploadedFile {

    public int length;
    public byte[] bytes;
    public String name;
    public String type;
}
