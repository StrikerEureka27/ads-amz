package gt.com.ad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gt.com.ad.data.AdsLog;
import gt.com.ad.data.IAdslogDao;

@Service
public class AdsLogService implements IAdsLog{

    @Autowired
    IAdslogDao adslog;

    @Override
    public Iterable<AdsLog> getAllLogs() {
        return adslog.findAll();
    }
    
}
