package com.kenny.user.service.center;

import com.kenny.user.pojo.Users;
import com.kenny.user.pojo.bo.center.CenterUserBO;
import org.springframework.web.bind.annotation.*;

@RequestMapping("center-user-api")
public interface CenterUserService {

    /**
     * Query user information by user ID
     * @param userId
     * @return
     */
    @GetMapping("profile")
    public Users queryUserInfo(@RequestParam String userId);

    /**
     * Update user information
     * @param userId
     * @param centerUserBO
     */
    @PutMapping("profile/{userId}")
    public Users updateUserInfo(@PathVariable String userId, @RequestBody CenterUserBO centerUserBO);

    /**
     * Update user profile picture
     * @param userId
     * @param faceUrl
     * @return
     */
    @PostMapping("updatePhoto")
    public Users updateUserFace(@RequestParam String userId, @RequestParam String faceUrl);
}
