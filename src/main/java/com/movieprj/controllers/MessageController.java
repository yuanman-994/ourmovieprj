package com.movieprj.controllers;

import com.movieprj.beans.Message;
import com.movieprj.services.MessageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MessageController {

    @Autowired
    private MessageServiceImp messageService;

    @RequestMapping("/admin_messageboard")
    public  String admin_messageboard(){
        return "admin_messageboard";
    }


    @PostMapping("/deleteMessage")
    @ResponseBody
    public Map<String,Integer> deleteMessage(@RequestBody Map<String, List> message_ids) {
        int check;
        check = messageService.deleteMessage(message_ids);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("check",check);
        return map;
    }

    @RequestMapping("/findAllMessages")
    @ResponseBody
    public  Map<String,Object> findAllMessages(){
        List<Message> messagesList = messageService.findAllMessageWithUser();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("messagesList",messagesList);
        return map;
    }

    @RequestMapping("/findMessage")
    @ResponseBody
    public  Map<String,Object> findMessage(Integer message_id){
        Message message = messageService.findMessageWithUserById(message_id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message",message);
        return map;
    }

    @PostMapping("/replyMessage")
    public String replyMessage(HttpServletRequest request){
        Integer message_id=null;
        Integer user_id=null;
        String reply_content=null;
        if(request.getParameter("message_id")!=null){
            message_id = Integer.valueOf(request.getParameter("message_id").toString());
        }
        if(request.getParameter("user_id")!=null){
            user_id = Integer.valueOf(request.getParameter("user_id").toString());
        }
        if(request.getParameter("reply_content")!=null){
            reply_content = request.getParameter("reply_content").toString();
        }
        messageService.replyMessage(message_id,user_id,reply_content);
        return "redirect:/admin_messageboard";
    }

}
