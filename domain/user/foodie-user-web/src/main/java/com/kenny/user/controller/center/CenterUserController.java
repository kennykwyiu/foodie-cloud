package com.kenny.user.controller.center;

import com.kenny.controller.BaseController;
import com.kenny.user.pojo.Users;
import com.kenny.user.pojo.bo.center.CenterUserBO;
import com.kenny.user.resource.FileUpload;
import com.kenny.user.service.center.CenterUserService;
import com.kenny.utils.CookieUtils;
import com.kenny.utils.DateUtil;
import com.kenny.pojo.IMOOCJSONResult;
import com.kenny.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "User Information", tags = {"User information related endpoints"})
@RestController
@RequestMapping("userInfo")
public class CenterUserController extends BaseController {

    @Autowired
    private CenterUserService centerUserService;

    @Autowired
    private FileUpload fileUpload;

    @ApiOperation(value = "Update user avatar", notes = "Update user avatar", httpMethod = "POST")
    @PostMapping("uploadFace")
    public IMOOCJSONResult uploadFace(
            @ApiParam(name = "userId", value = "User ID", required = true)
            @RequestParam String userId,
            @ApiParam(name = "file", value = "User avatar", required = true)
            MultipartFile file,
            HttpServletRequest request, HttpServletResponse response) {

        // .sh .php

        // Define avatar save location
//        String fileSpace = IMAGE_USER_FACE_LOCATION;
        String fileSpace = fileUpload.getImageUserFaceLocation();
        // Add userid to path to distinguish different user uploads
        String uploadPathPrefix = File.separator + userId;

        // Start file upload
        if (file != null) {
            FileOutputStream fileOutputStream = null;
            try {
                // Get original filename
                String fileName = file.getOriginalFilename();

                if (StringUtils.isNotBlank(fileName)) {

                    // Rename file  imooc-face.png -> ["imooc-face", "png"]
                    String fileNameArr[] = fileName.split("\\.");

                    // Get file extension
                    String suffix = fileNameArr[fileNameArr.length - 1];

                    if (!suffix.equalsIgnoreCase("png") &&
                            !suffix.equalsIgnoreCase("jpg") &&
                            !suffix.equalsIgnoreCase("jpeg") ) {
                        return IMOOCJSONResult.errorMsg("Invalid image format!");
                    }

                    // face-{userid}.png
                    // Reorganize filename for overwrite upload, incremental: append current timestamp
                    String newFileName = "face-" + userId + "." + suffix;

                    // Final save location for uploaded avatar
                    String finalFacePath = fileSpace + uploadPathPrefix + File.separator + newFileName;
                    // Path for web service access
                    uploadPathPrefix += ("/" + newFileName);

                    File outFile = new File(finalFacePath);
                    if (outFile.getParentFile() != null) {
                        // Create directory
                        outFile.getParentFile().mkdirs();
                    }

                    // Save file to directory
                    fileOutputStream = new FileOutputStream(outFile);
                    InputStream inputStream = file.getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileOutputStream != null) {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else {
            return IMOOCJSONResult.errorMsg("File cannot be empty!");
        }

        // Get image server URL
        String imageServerUrl = fileUpload.getImageServerUrl();

        // Add timestamp to ensure updated images refresh immediately due to browser caching
        String finalUserFaceUrl = imageServerUrl + uploadPathPrefix
                + "?t=" + DateUtil.getCurrentDateString(DateUtil.DATE_PATTERN);

        // Update user avatar in database
        Users userResult = centerUserService.updateUserFace(userId, finalUserFaceUrl);

        userResult = setNullProperty(userResult);
        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(userResult), true);

        // TODO Update later to add token and integrate with redis for distributed session

        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "Update user information", notes = "Update user information", httpMethod = "POST")
    @PostMapping("update")
    public IMOOCJSONResult update(
            @ApiParam(name = "userId", value = "User ID", required = true)
            @RequestParam String userId,
            @RequestBody @Valid CenterUserBO centerUserBO,
            BindingResult result,
            HttpServletRequest request, HttpServletResponse response) {

        System.out.println(centerUserBO);

        // Check if BindingResult contains validation errors, if yes, return immediately
        if (result.hasErrors()) {
            Map<String, String> errorMap = getErrors(result);
            return IMOOCJSONResult.errorMap(errorMap);
        }

        Users userResult = centerUserService.updateUserInfo(userId, centerUserBO);

        userResult = setNullProperty(userResult);
        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(userResult), true);

        // TODO Update later to add token and integrate with redis for distributed session

        return IMOOCJSONResult.ok();
    }

    private Map<String, String> getErrors(BindingResult result) {
        Map<String, String> map = new HashMap<>();
        List<FieldError> errorList = result.getFieldErrors();
        for (FieldError error : errorList) {
            // Property that caused validation error
            String errorField = error.getField();
            // Validation error message
            String errorMsg = error.getDefaultMessage();

            map.put(errorField, errorMsg);
        }
        return map;
    }

    private Users setNullProperty(Users userResult) {
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }
}
