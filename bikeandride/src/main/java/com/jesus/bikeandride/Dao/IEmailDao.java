package com.jesus.bikeandride.Dao;

import com.jesus.bikeandride.Model.BikeDdbb;
import com.jesus.bikeandride.Model.EmailDdbb;

import java.util.List;

public interface IEmailDao {
    List<EmailDdbb> getListEmailByUserId(long idUser);
}
