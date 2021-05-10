package org.oucgroup.wxprogram.controller.lecturer;

import org.oucgroup.wxprogram.controller.course.CourseController;
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
public class LecturerController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserController userController;

    @Autowired
    private CourseController courseController;

    @RequestMapping("/lecturer/lecturers")
    public RespData<List<Map<String, Object>>> getLecturerList() {
        String sql = "SELECT id, name, info FROM t_lecturer";
        return RespDataUtil.buildSuccess(jdbcTemplate.queryForList(sql));
    }

    @RequestMapping("/lecturer/lecturer")
    public RespData<Map<String, Object>> getLecturer(String lecturerId) {
        if(lecturerId == null) return RespDataUtil.buildError("invalid parameter");

        String sql = "SELECT id, name, info FROM t_lecturer WHERE id = " + lecturerId;
        try {
            return RespDataUtil.buildSuccess(jdbcTemplate.queryForMap(sql));
        } catch(IncorrectResultSizeDataAccessException e) {
            return RespDataUtil.buildError("cannot specify lecturer");
        }
    }

    @RequestMapping("/lecturer/comment")
    RespData<List<Map<String, Object>>> getLecturerComment(String lecturerId) {
        if(lecturerId == null) return RespDataUtil.buildError("invalid parameter");

        String sql = "SELECT " +
                "* " +
                "FROM t_lecturer_comment WHERE id = " + lecturerId;
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        result.forEach(e->{
            String userId = String.valueOf(e.get("user_id"));
            e.put("user_info", userController.getUser(userId, null).getData());
            e.put("lecturer_info", getLecturer(lecturerId).getData());
        });
        return RespDataUtil.buildSuccess(result);
    }

    @RequestMapping("lecturer/course")
    RespData<List<Map<String, Object>>> getLecturerCourse(String lecturerId) {
        if(lecturerId == null) return RespDataUtil.buildError("invalid parameter");

        String sql = "SELECT t_course.id, t_course.name " +
                "FROM t_course INNER JOIN t_lecturer ON t_course.lecturer_id = t_lecturer.id " +
                "WHERE t_lecturer.id = " + lecturerId;
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        return RespDataUtil.buildSuccess(result);
    }
}
