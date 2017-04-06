package com.bsuir.petition.service.util.impl;

import com.bsuir.petition.bean.entity.City;
import com.bsuir.petition.bean.entity.User;
import com.bsuir.petition.bean.entity.UserInformation;
import com.bsuir.petition.dao.CityDao;
import com.bsuir.petition.service.util.Creator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreatorImpl implements Creator {

    private CityDao cityDao;


    @Autowired
    public void setCityDao(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public UserInformation getUserInformation(User user, String cityName) {
        UserInformation userInformation = new UserInformation();
        userInformation.setUser(user);

        City city = cityDao.getCityByName(cityName);
        userInformation.setCity(city);

        return userInformation;
    }
}
