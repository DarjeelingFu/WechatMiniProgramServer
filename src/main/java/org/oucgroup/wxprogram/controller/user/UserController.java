package org.oucgroup.wxprogram.controller.user;

import org.oucgroup.wxprogram.controller.course.CourseController;
import org.oucgroup.wxprogram.controller.lecturer.LecturerController;
import org.oucgroup.wxprogram.utils.RespData;
import org.oucgroup.wxprogram.utils.RespDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CourseController courseController;

    @Autowired
    private LecturerController lecturerController;

    @RequestMapping("/user/users")
    public RespData<List<Map<String, Object>>> getUserList() {
        String sql = "select * from t_user";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);

        if(result == null) return RespDataUtil.buildError("user not found");
        return RespDataUtil.buildSuccess(result);
    }

    @RequestMapping("/user/user")
    public RespData<Map<String, Object>> getUser(String userId, String stuId) {
        if(userId == null && stuId == null) return RespDataUtil.buildError("no parameters");

        if(userId != null) {
            String sql = "SELECT id, stu_id, grade, nick_name FROM t_user WHERE id = " + userId;
            Map<String, Object> result = jdbcTemplate.queryForMap(sql);
            if(result == null) return RespDataUtil.buildError("no such user");
            return RespDataUtil.buildSuccess(result);
        }

        else {
            String sql = "SELECT id, stu_id, grade, nick_name FROM t_user WHERE stu_id = " + stuId;
            Map<String, Object> result = null;
            try {
                result = jdbcTemplate.queryForMap(sql);
            } catch (IncorrectResultSizeDataAccessException e) {
                return RespDataUtil.buildError("cannot specify user");
            }
            return RespDataUtil.buildSuccess(result);
        }
    }

    @RequestMapping("/user/comment")
    public RespData<List<Map<String, Object>>> getCommentOfUser(String userId, String commentType, String targetId) {
        if(userId == null || commentType == null) return RespDataUtil.buildError("invalid parameter");
        if(targetId != null) return getCommentOfUserTo(userId, commentType, targetId);

        if(commentType.equals("course")) {
            String sql = "SELECT t_course_comment.id, course_id, content, time, creditability, votes FROM t_course_comment LEFT OUTER JOIN t_user ON t_course_comment.user_id = t_user.id WHERE user_id = " + userId;
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
            result.forEach(e->{
                String courseId = String.valueOf(e.get("course"));
                e.put("course_info", courseController.getCourse(courseId).getData());
            });
            return RespDataUtil.buildSuccess(result);
        }

        if(commentType.equals("lecturer")) {
            String sql = "SELECT t_lecturer_comment.id, lecturer_id, content, time FROM t_lecturer_comment LEFT OUTER JOIN t_user ON t_lecturer_comment.user_id = t_user.id WHERE user_id = " + userId;
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
            result.forEach(e->{
                String lecturerId = String.valueOf(e.get("lecturer_id"));
                e.put("lecturer_info", lecturerController.getLecturer(lecturerId).getData());
            });
            return RespDataUtil.buildSuccess(result);
        }

        return RespDataUtil.buildError("invalid parameter");
    }

    public RespData<List<Map<String, Object>>> getCommentOfUserTo(String userId, String commentType, String targetId) {
        if(commentType.equals("course")) {
            String sql = "SELECT t_course_comment.id, content, creditability, votes FROM t_course_comment LEFT OUTER JOIN t_user ON t_course_comment.user_id = t_user.id WHERE user_id = " + userId + " AND t_course_comment.course_id = " + targetId;
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
            return RespDataUtil.buildSuccess(result);
        }

        if(commentType.equals("lecturer")) {
            String sql = "SELECT t_lecturer_comment.id, content FROM t_lecturer_comment LEFT OUTER JOIN t_user ON t_lecturer_comment.user_id = t_user.id WHERE user_id = " + userId + " AND t_lecturer_comment.lecturer_id = " + targetId;
            List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
            return RespDataUtil.buildSuccess(result);
        }

        return RespDataUtil.buildError("invalid parameter");
    }

}
