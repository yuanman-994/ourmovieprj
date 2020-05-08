package com.movieprj.services;


import com.movieprj.beans.Message;

import java.util.List;
import java.util.Map;

public interface MessageService {

    public Message findMessageWithUserById(Integer message_id);

    public List<Message> findAllMessageWithUser();

    public int addMessage(Message message);

    public int deleteMessage(Map<String, List> message_ids);

    public int updateMessage(Message message);

    public int replyMessage(Integer message_id,Integer user_id,String reply_content);
}
