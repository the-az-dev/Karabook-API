package org.theaz.karabookapi.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.theaz.karabookapi.dto.update.*;
import org.theaz.karabookapi.entity.*;
import org.theaz.karabookapi.repository.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ImageProgressService {
    @Autowired
    private ImageProgressRepository imageProgressRepository;

    @Autowired
    private AchivementProgressRepository achivementProgressRepository;

    @Autowired
    private AchivementRepository achivementRepository;

    public List<ImageProgress> getAllImageProgress() {
        return this.imageProgressRepository.findAll();
    }

    public ImageProgress getImageProgressById(Long imageProgressId) {
        return this.imageProgressRepository.findByImageProgressId(imageProgressId);
    }

    public List<ImageProgress> getImageProgressByUserId(Long userId) {
        return this.imageProgressRepository.findAllByUserId(userId);
    }

    public void save(ImageProgress imageProgress, ImageService imageService, UserService userService, AchivementService achivementService, AchivementProgressService achivementProgressService) {
        Image currentImage = imageService.getImageByImageId(imageProgress.getImageId()); // беремо картинку з бази

        if(imageProgress.getIsCompleted()){ // перевірка чи картинка у нас розмальована

            if(!Objects.equals(currentImage.getAchivementsIds(), "")){
                if(List.of(currentImage.getAchivementsIds().split(",")) == null ){
                    String achivementId = currentImage.getAchivementsIds();
                    AchivementProgress currentProgress = achivementProgressService.getOneByUserIdAndAchivementId(Long.parseLong(achivementId), imageProgress.getUserId()); // дістаємо прогрес юзера за даною ачівкою
                    if(currentProgress != null){
                        if(!Objects.equals(currentProgress.getCompletedPoints(), achivementService.getOneById(Long.parseLong(achivementId)).getAchivementsMaxPoints())){ // перевіряємо чи воно не рівне максимальній к-сті поінтів
                            currentProgress.setCompletedPoints(currentProgress.getCompletedPoints() + 1); // збільшуємо на 1 к-сть поінтів в прогресі
                            AchivementsProgressUpdateDTO newAchivementProgress = getAchivementsProgressUpdateDTO(currentProgress); // робимо копію для ДТО
                            achivementProgressService.update(newAchivementProgress, currentProgress); // оновлюємо
                        } else{ // якщо хибна умова, тобто рівні 2 значення
                            currentProgress.setIsCompleted(true); // прогрес виконано, юзер отримає ачівку
                            User exitingUser = userService.findByUserID(imageProgress.getUserId());
                            exitingUser.setHintsAmount(achivementService.getOneById(Long.parseLong(achivementId)).getTipsNumber());
                            userService.save(exitingUser);
                            AchivementsProgressUpdateDTO newAchivementProgress = getAchivementsProgressUpdateDTO(currentProgress); // копія ДТО
                            achivementProgressService.update(newAchivementProgress, currentProgress); // оновлюємо
                        }
                    }
                    else{
                        AchivementProgress newAchivementProgress = new AchivementProgress(
                                null,
                                imageProgress.getUserId(),
                                Long.parseLong(achivementId),
                                false,
                                false,
                                1L,
                                new Date()
                        );
                        achivementProgressService.save(newAchivementProgress); // оновлюємо
                    }
                }
                else{
                    List<String> achivementsStringIds = List.of(currentImage.getAchivementsIds().split(",")); // витягуємо список ІД ачівок
                    for(String achivementId : achivementsStringIds){ // оперуємо кожну ачівку
                        AchivementProgress currentProgress = achivementProgressService.getOneByUserIdAndAchivementId(Long.parseLong(achivementId), imageProgress.getUserId()); // дістаємо прогрес юзера за даною ачівкою
                        if(currentProgress == null){
                            AchivementProgress newAchivementProgress = new AchivementProgress(
                                    null,
                                    imageProgress.getUserId(),
                                    Long.parseLong(achivementId),
                                    false,
                                    false,
                                    1L,
                                    new Date()
                            );
                            achivementProgressService.save(newAchivementProgress); // оновлюємо
                        } else{
                            if(!Objects.equals(currentProgress.getCompletedPoints(), achivementService.getOneById(Long.parseLong(achivementId)).getAchivementsMaxPoints())){ // перевіряємо чи воно не рівне максимальній к-сті поінтів
                                currentProgress.setCompletedPoints(currentProgress.getCompletedPoints() + 1); // збільшуємо на 1 к-сть поінтів в прогресі

                                if(Objects.equals(currentProgress.getCompletedPoints(), achivementService.getOneById(Long.parseLong(achivementId)).getAchivementsMaxPoints())){
                                    currentProgress.setIsCompleted(true); // прогрес виконано, юзер отримає ачівку
                                    User exitingUser = userService.findByUserID(imageProgress.getUserId());
                                    exitingUser.setHintsAmount(achivementService.getOneById(Long.parseLong(achivementId)).getTipsNumber());
                                    userService.save(exitingUser);
                                    AchivementsProgressUpdateDTO newAchivementProgress = getAchivementsProgressUpdateDTO(currentProgress); // копія ДТО
                                    achivementProgressService.update(newAchivementProgress, currentProgress); // оновлюємо
                                } else{
                                    AchivementsProgressUpdateDTO newAchivementProgress = getAchivementsProgressUpdateDTO(currentProgress); // робимо копію для ДТО
                                    achivementProgressService.update(newAchivementProgress, currentProgress); // оновлюємо
                                }
                            } else{ // якщо хибна умова, тобто рівні 2 значення
                                currentProgress.setIsCompleted(true); // прогрес виконано, юзер отримає ачівку
                                User exitingUser = userService.findByUserID(imageProgress.getUserId());
                                exitingUser.setHintsAmount(achivementService.getOneById(Long.parseLong(achivementId)).getTipsNumber());
                                userService.save(exitingUser);
                                AchivementsProgressUpdateDTO newAchivementProgress = getAchivementsProgressUpdateDTO(currentProgress); // копія ДТО
                                achivementProgressService.update(newAchivementProgress, currentProgress); // оновлюємо
                            }
                        }
                    }
                }
            }
        }
        this.imageProgressRepository.save(imageProgress);
    }

    public ImageProgress update(ImageProgress exitingImageProgress, ImageProgressUpdateDTO imageProgress, UserService userService, ImageService imageService, AchivementService achivementService, AchivementProgressService achivementProgressService) {
        Date newDate = new Date();

        exitingImageProgress.setCompletedImageParts(
                imageProgress.getCompletedImageParts() != null
                        ? imageProgress.getCompletedImageParts()
                        : exitingImageProgress.getCompletedImageParts());

        exitingImageProgress.setIsCompleted(
                imageProgress.getIsCompleted() != null
                        ? imageProgress.getIsCompleted()
                        : exitingImageProgress.getIsCompleted());

        exitingImageProgress.setUserId(
                imageProgress.getUserId() != null
                        ? imageProgress.getUserId()
                        : exitingImageProgress.getUserId());

        exitingImageProgress.setImageId(
                imageProgress.getImageId() != null
                        ? imageProgress.getImageId()
                        : exitingImageProgress.getImageId());

        if(exitingImageProgress.getIsCompleted()){ // перевірка чи картинка у нас розмальована
            Image currentImage = imageService.getImageByImageId(exitingImageProgress.getImageId()); // беремо картинку з бази

            if(!Objects.equals(currentImage.getAchivementsIds(), "")){
                if(List.of(currentImage.getAchivementsIds().split(",")) == null ){
                    String achivementId = currentImage.getAchivementsIds();
                    AchivementProgress currentProgress = achivementProgressService.getOneByUserIdAndAchivementId(Long.parseLong(achivementId), exitingImageProgress.getUserId()); // дістаємо прогрес юзера за даною ачівкою
                    if(currentProgress != null){
                        if(!Objects.equals(currentProgress.getCompletedPoints(), achivementService.getOneById(Long.parseLong(achivementId)).getAchivementsMaxPoints())){ // перевіряємо чи воно не рівне максимальній к-сті поінтів
                            currentProgress.setCompletedPoints(currentProgress.getCompletedPoints() + 1); // збільшуємо на 1 к-сть поінтів в прогресі
                            AchivementsProgressUpdateDTO newAchivementProgress = getAchivementsProgressUpdateDTO(currentProgress); // робимо копію для ДТО
                            achivementProgressService.update(newAchivementProgress, currentProgress); // оновлюємо
                        } else{ // якщо хибна умова, тобто рівні 2 значення
                            currentProgress.setIsCompleted(true); // прогрес виконано, юзер отримає ачівку
                            User exitingUser = userService.findByUserID(exitingImageProgress.getUserId());
                            exitingUser.setHintsAmount(achivementService.getOneById(Long.parseLong(achivementId)).getTipsNumber());
                            userService.save(exitingUser);
                            AchivementsProgressUpdateDTO newAchivementProgress = getAchivementsProgressUpdateDTO(currentProgress); // копія ДТО
                            achivementProgressService.update(newAchivementProgress, currentProgress); // оновлюємо
                        }
                    }
                    else{
                        AchivementProgress newAchivementProgress = new AchivementProgress(
                                null,
                                exitingImageProgress.getUserId(),
                                Long.parseLong(achivementId),
                                false,
                                false,
                                1L,
                                new Date()
                        );
                        achivementProgressService.save(newAchivementProgress); // оновлюємо
                    }
                }
                else{
                    List<String> achivementsStringIds = List.of(currentImage.getAchivementsIds().split(",")); // витягуємо список ІД ачівок
                    for(String achivementId : achivementsStringIds){ // оперуємо кожну ачівку
                        AchivementProgress currentProgress = achivementProgressService.getOneByUserIdAndAchivementId(Long.parseLong(achivementId), exitingImageProgress.getUserId()); // дістаємо прогрес юзера за даною ачівкою
                        if(currentProgress == null){
                            AchivementProgress newAchivementProgress = new AchivementProgress(
                                    null,
                                    exitingImageProgress.getUserId(),
                                    Long.parseLong(achivementId),
                                    false,
                                    false,
                                    1L,
                                    new Date()
                            );
                            achivementProgressService.save(newAchivementProgress); // оновлюємо
                        } else{
                            if(!Objects.equals(currentProgress.getCompletedPoints(), achivementService.getOneById(Long.parseLong(achivementId)).getAchivementsMaxPoints())){ // перевіряємо чи воно не рівне максимальній к-сті поінтів
                                currentProgress.setCompletedPoints(currentProgress.getCompletedPoints() + 1); // збільшуємо на 1 к-сть поінтів в прогресі

                                if(Objects.equals(currentProgress.getCompletedPoints(), achivementService.getOneById(Long.parseLong(achivementId)).getAchivementsMaxPoints())){
                                    currentProgress.setIsCompleted(true); // прогрес виконано, юзер отримає ачівку
                                    User exitingUser = userService.findByUserID(exitingImageProgress.getUserId());
                                    exitingUser.setHintsAmount(achivementService.getOneById(Long.parseLong(achivementId)).getTipsNumber());
                                    userService.save(exitingUser);
                                    AchivementsProgressUpdateDTO newAchivementProgress = getAchivementsProgressUpdateDTO(currentProgress); // копія ДТО
                                    achivementProgressService.update(newAchivementProgress, currentProgress); // оновлюємо
                                } else{
                                    AchivementsProgressUpdateDTO newAchivementProgress = getAchivementsProgressUpdateDTO(currentProgress); // робимо копію для ДТО
                                    achivementProgressService.update(newAchivementProgress, currentProgress); // оновлюємо
                                }
                            } else{ // якщо хибна умова, тобто рівні 2 значення
                                currentProgress.setIsCompleted(true); // прогрес виконано, юзер отримає ачівку
                                User exitingUser = userService.findByUserID(exitingImageProgress.getUserId());
                                exitingUser.setHintsAmount(achivementService.getOneById(Long.parseLong(achivementId)).getTipsNumber());
                                userService.save(exitingUser);
                                AchivementsProgressUpdateDTO newAchivementProgress = getAchivementsProgressUpdateDTO(currentProgress); // копія ДТО
                                achivementProgressService.update(newAchivementProgress, currentProgress); // оновлюємо
                            }
                        }
                    }
                }
            }
        }

        exitingImageProgress.setModifiedDate(newDate);

        this.imageProgressRepository.save(exitingImageProgress);
        return exitingImageProgress;
    }

    private static AchivementsProgressUpdateDTO getAchivementsProgressUpdateDTO(AchivementProgress currentProgress) {
        AchivementsProgressUpdateDTO newAchivementProgress = new AchivementsProgressUpdateDTO(
                currentProgress.getAchivementsProgressId(),
                currentProgress.getUserId(),
                currentProgress.getAchivementId(),
                currentProgress.getIsCompleted(),
                currentProgress.getIsRecived(),
                currentProgress.getCompletedPoints(),
                currentProgress.getModifiedDate()
        );
        newAchivementProgress.setModifiedDate(new Date());
        return newAchivementProgress;
    }

    public void deleteById(Long imageProgressId) {
        this.imageProgressRepository.deleteByImageProgressId(imageProgressId);
    }

    public void deleteByImageId(Long imageProgressId) {
        this.imageProgressRepository.deleteByImageId(imageProgressId);
    }

    public void deleteByUserId(Long userId) {
        this.imageProgressRepository.deleteByUserId(userId);
    }

    public void deleteByUserIdAndImageId(Long imageId, Long userId) {
        this.imageProgressRepository.deleteByImageIdAndUserId(imageId, userId);
    }
}
