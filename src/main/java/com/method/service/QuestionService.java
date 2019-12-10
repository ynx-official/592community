package com.method.service;

import com.method.dto.QuestionDTO;
import com.method.mapper.QuestionMapper;
import com.method.mapper.UserMapper;
import com.method.model.Question;
import com.method.model.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Yu
 * @date 2019/12/10 21:40
 *
 */
@Service
public class QuestionService {
    @Autowired(required = false)
    private QuestionMapper questionMapper;
    @Autowired(required = false)
    private UserMapper userMapper;

    public List<QuestionDTO> list(){
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOList  = new ArrayList<>();
        for (Question question: questions){
            User user = userMapper.findByID(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //把对象复制到对象中
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return  questionDTOList;
    }
}
