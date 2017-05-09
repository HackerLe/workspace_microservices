package fdse.microservice.service;

import fdse.microservice.domain.Information;
import fdse.microservice.domain.Station;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface StationService {
    //CRUD
    boolean create(Information info);
    boolean exist(Information info);
    //boolean update(Information info);
    boolean delete(Information info);
}
