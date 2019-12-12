package com.method.service;

import com.method.dto.PaginationDTO;
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

    public PaginationDTO list(Integer page, Integer size){
        //分页处理
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer count = questionMapper.count();
        paginationDTO.setPagination(count,page,size);
        if (page<1){
            page = 1;
        }

        if (page>paginationDTO.getTotalPage()){
            page = paginationDTO.getTotalPage();
        }
        //5*(i-1),5*(i-1)
        int offset = size * (page - 1);
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList  = new ArrayList<>();


        for (Question question: questions){
            User user = userMapper.findByID(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //把对象复制到对象中
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return  paginationDTO;
    }
}
