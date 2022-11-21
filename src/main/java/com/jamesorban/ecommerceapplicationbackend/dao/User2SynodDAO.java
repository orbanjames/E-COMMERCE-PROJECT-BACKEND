package com.jamesorban.ecommerceapplicationbackend.dao;


public interface User2SynodDAO{

    String BASIC_QUERY =
            "SELECT * FROM `register_to_synod` WHERE user = 2 and synod = 1";
}
