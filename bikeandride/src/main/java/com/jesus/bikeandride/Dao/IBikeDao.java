package com.jesus.bikeandride.Dao;

import com.jesus.bikeandride.model.BikeDdbb;
import java.util.List;

public interface IBikeDao {

    List <BikeDdbb> getListBikeByUserId(long idUser);

}
