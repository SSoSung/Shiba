package time.clock.shiba.service;

import org.springframework.http.ResponseEntity;
import time.clock.shiba.global.common.ShibaApiResponse;
import time.clock.shiba.record.request.*;

import java.util.List;

public interface UseTimeService {

    void createUseTime(CreateUseTime request);

    void deleteUseTime(DeleteUseTime request);

}
