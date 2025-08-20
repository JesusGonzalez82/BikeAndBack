package com.jesus.bikeandride.Dao;

import com.jesus.bikeandride.Model.BikeDdbb;
import java.util.List;

public interface IUserDao {

    List<BikeDdbb> getListUserByUserId(long idUser);
}
