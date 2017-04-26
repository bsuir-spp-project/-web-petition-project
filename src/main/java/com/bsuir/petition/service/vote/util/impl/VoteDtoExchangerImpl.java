package com.bsuir.petition.service.vote.util.impl;

import com.bsuir.petition.bean.dto.user.UserDTO;
import com.bsuir.petition.bean.dto.vote.VoteDTO;
import com.bsuir.petition.bean.dto.vote.VoteListDTO;
import com.bsuir.petition.bean.entity.Vote;
import com.bsuir.petition.dao.UserDao;
import com.bsuir.petition.dao.impl.UserDaoImpl;
import com.bsuir.petition.service.user.util.UserDtoExchanger;
import com.bsuir.petition.service.vote.util.VoteDtoExchanger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VoteDtoExchangerImpl implements VoteDtoExchanger {

    private UserDtoExchanger userDtoExchanger;

    @Autowired
    public void setUserDtoExchanger(UserDtoExchanger userDtoExchanger) {
        this.userDtoExchanger = userDtoExchanger;
    }

    @Override
    public VoteListDTO getVoteListDTO(List<Vote> votes) {
        VoteListDTO voteListDTO = new VoteListDTO();
        ArrayList<VoteDTO> voteDTOs = voteListDTO.getVotes();

        for (Vote vote : votes) {
            VoteDTO voteDTO = new VoteDTO();
            voteDTO.setReason(vote.getReason());

            UserDTO userDTO;
            userDTO = userDtoExchanger.getUserDTO(vote.getUser());
//            voteDTO.setUserDTO(userDTO);
            voteDTO.setUser(vote.getUser());
            voteDTO.setUserId(vote.getUser().getId());
            voteDTOs.add(voteDTO);
        }

        return voteListDTO;
    }
}
