package com.demo.featureswitches.featureswitches.controller;

import com.demo.featureswitches.featureswitches.dto.Request;
import com.demo.featureswitches.featureswitches.dto.Response;
import com.demo.featureswitches.featureswitches.entity.UserFeatures;
import com.demo.featureswitches.featureswitches.service.UserFeaturesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * This is user feature controller
 * To return user feature status and create or update user feature
 */
@RestController
@RequestMapping("/api/v1")
public class UserFeaturesController {
    private final Logger log = LoggerFactory.getLogger(UserFeaturesController.class);
     @Autowired
    UserFeaturesService userFeaturesService;

    /**
     * This method is created to check
     * user feature is enable or disable by email and feature name
     * @param email
     * @param featureName
     * @return canAccess enabled or disabled
     * Otherwise return Not Found status if the email and feature not exist
     */
    @GetMapping("/feature")
    public ResponseEntity<Response> getFeatureStatusByEmailAndName(@RequestParam(required = true) String email,
                                                                  @RequestParam(required = true) String featureName) {
        Optional<UserFeatures> userFeatures = Optional.ofNullable(userFeaturesService.findByFeatureNameAndEmail(featureName, email));
        return (ResponseEntity) userFeatures.map((status) -> {
            Response response = new Response();
            response.setCanAccess(status.getEnable());
            return ((ResponseEntity.BodyBuilder) ResponseEntity.ok()).body(response);
        }).orElseThrow(() -> {
            return new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
    }

    /**
     * @param request which is the feature name and email and enable or disable
     * @return success 200 or 304 not modifiable
     */
    @PostMapping("/feature")
    public ResponseEntity<Request> createFeature(@Valid @RequestBody Request request)  {
        try {
            userFeaturesService.saveFeatureRequest(request);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception ex){
            log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
    }

    /**
     * @param ex
     * handle specified types of exceptions when submit request
     * if the request did not meet the validation the error message will be returned
     * when the specified request object is invalid this method will be called
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
