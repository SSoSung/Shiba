package time.clock.shiba.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import time.clock.shiba.domain.UseTime;
import time.clock.shiba.domain.User;
import time.clock.shiba.global.exception.BadRequestException;
import time.clock.shiba.record.request.CreateUseTime;
import time.clock.shiba.record.request.DeleteUseTime;
import time.clock.shiba.repo.UseTimeRepo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.hibernate.internal.util.NullnessHelper.coalesce;


@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UseTimeServiceImpl implements UseTimeService {

    private final UseTimeRepo useTimeRepo;


    @Override
    @Transactional
    public void createUseTime(CreateUseTime request) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = user.getUserId();

        String today = new SimpleDateFormat("yyyyMMdd").format(new Date());

        Integer maxYmdSeq = useTimeRepo.findByMaxYmdSeq(userId, today);

        useTimeRepo.save(request.toEntity(userId, request.categoryId(), today, coalesce(maxYmdSeq, 0) + 1, request.second()));

    }

    @Override
    @Transactional
    public void deleteUseTime(DeleteUseTime request) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = user.getUserId();

        Optional<UseTime> optUseTime = useTimeRepo.findByUserIdAndCategoryIdAndYmdAndYmdSeq(userId, request.categoryId(), request.ymd(), request.ymdSeq());

        if(optUseTime.isPresent()){
            UseTime useTime = optUseTime.get();
            useTime.deleteUseTime();
        }else{
            throw new BadRequestException("존재하지 않는 저장시간입니다.");
        }

    }
}
