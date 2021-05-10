package org.oucgroup.wxprogram.controller.course;

import org.oucgroup.wxprogram.controller.lecturer.LecturerController;
import org.oucgroup.wxprogram.controller.user.UserController;
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
public class CourseController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserController userController;

    @Autowired
    private LecturerController lecturerController;

    @RequestMapping("/course/courses")
    public RespData<List<Map<String, Object>>> getCourseList() {
        String sql = "SELECT * FROM t_course";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        result.forEach(e->{
            String lecturerId = String.valueOf(e.get("lecturer_id"));
            e.put("lecturer_info", lecturerController.getLecturer(lecturerId).getData());
        });
        return RespDataUtil.buildSuccess(result);
    }

    @RequestMapping("/course/course")
    public RespData<Map<String, Object>> getCourse(String courseId) {
        if(courseId == null) return RespDataUtil.buildError("invalid parameter");

        String sql = "SELECT t_course.id, t_course.name, t_course.lecturer_id FROM t_course WHERE t_course.id = " + courseId;
        try {
            Map<String, Object> result = jdbcTemplate.queryForMap(sql);
            String lecturerId = String.valueOf(result.get("lecturer_id"));
            result.put("lecturer_info", lecturerController.getLecturer(lecturerId).getData());
            return RespDataUtil.buildSuccess(result);
        } catch(IncorrectResultSizeDataAccessException e) {
            return RespDataUtil.buildError("course not found");
        }
    }

    @RequestMapping("/course/comment")
    public RespData<List<Map<String, Object>>> getComments(String courseId) {
        String sql = "SELECT user_id, course_id, content, time, creditability, votes FROM t_course_comment WHERE course_id = " + courseId;
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        result.forEach(e->{
            String userId = String.valueOf(e.get("user_id"));
            e.put("user_info", userController.getUser(userId, null).getData());
        });
        return RespDataUtil.buildSuccess(result);
    }

    /*
    @RequestMapping("course/lecturer")
    RespData<List<Map<String, Object>>> getCourseLecturer(String courseId) {
        if(courseId == null) return RespDataUtil.buildError("invalid parameter");

        String sql = "SELECT t_lecturer.id FROM t_lecturer INNER JOIN t_course " +
                "ON t_course.lecturer_id = t_lecturer.id " +
                "WHERE t_course.id = " + courseId;
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        result.forEach(e->{
            String lecturerId = String.valueOf(e.get("id"));
            e.put("lecturer_info", lecturerController.getLecturer(lecturerId).getData());
        });
        return RespDataUtil.buildSuccess(result);
    }
    */

}
