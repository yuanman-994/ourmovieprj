package com.movieprj.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LogController {

    private Logger logger = LogManager.getLogger(this.getClass());

    @RequestMapping("/admin_log")
    public String admin_log(){
        return "admin_log";
    }

    @RequestMapping("/showSqlLog")
    @ResponseBody
    public Map<String,Object> showSqlLog(){
        Path path = Paths.get("logs","log-sql.log");
        List<String> lines;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            lines = Files.readAllLines(path);
            map.put("log",lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("/showErrorLog")
    @ResponseBody
    public Map<String,Object> showErrorLog(){
        logger.error("这是error信息（测试）！");
        Path path = Paths.get("logs","log-error.log");
        List<String> lines;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            lines = Files.readAllLines(path);
            map.put("log",lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("/showLog")
    @ResponseBody
    public Map<String,Object> showLog(){
        Path path = Paths.get("logs","log.log");
        List<String> lines;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            lines = Files.readAllLines(path);
            map.put("log",lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value="/sqlLog", method = { RequestMethod.GET, RequestMethod.POST })
    public void downloadsqllog(HttpServletRequest req, Model model, HttpServletResponse response)
            throws ServletException, IOException {
        Path path = Paths.get("logs","log-sql.log");
        String filename=path.toString();
        String downFilename="log-sql.log";//要下载的文件名称
        response.setContentType("text/plain");
        response.setHeader("Location",downFilename);
        response.setHeader("Content-Disposition", "attachment; filename=" + downFilename);
        OutputStream outputStream = response.getOutputStream();
        InputStream inputStream = new FileInputStream( filename);
        byte[] buffer = new byte[1024];
        int i = -1;
        while ((i = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, i);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();

    }

    @RequestMapping(value="/errorLog", method = { RequestMethod.GET, RequestMethod.POST })
    public void dowloaderrorlog(HttpServletRequest req, Model model, HttpServletResponse response)
            throws ServletException, IOException {
        Path path = Paths.get("logs","log-error.log");
        String filename=path.toString();
        String downFilename="log-error.log";//要下载的文件名称
        response.setContentType("text/plain");
        response.setHeader("Location",downFilename);
        response.setHeader("Content-Disposition", "attachment; filename=" + downFilename);
        OutputStream outputStream = response.getOutputStream();
        InputStream inputStream = new FileInputStream( filename);
        byte[] buffer = new byte[1024];
        int i = -1;
        while ((i = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, i);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();

    }

    @RequestMapping(value="/log", method = { RequestMethod.GET, RequestMethod.POST })
    public void log(HttpServletRequest req, Model model, HttpServletResponse response)
            throws ServletException, IOException {
        Path path = Paths.get("logs","log.log");
        String filename=path.toString();
        String downFilename="log.log";//要下载的文件名称
        response.setContentType("text/plain");
        response.setHeader("Location",downFilename);
        response.setHeader("Content-Disposition", "attachment; filename=" + downFilename);
        OutputStream outputStream = response.getOutputStream();
        InputStream inputStream = new FileInputStream( filename);
        byte[] buffer = new byte[1024];
        int i = -1;
        while ((i = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, i);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();

    }

}
