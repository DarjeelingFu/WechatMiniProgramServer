<div style="background-color: #fff2a7; border-radius: 15px; padding: 10px 20px">
    <p style="color: #a86026;"><b>注意&nbsp</b>仅完成了GET相关的接口，暂时用于开发测试</p>
</div>
# 服务器配置

服务器端口：8080

h2数据库控制台地址：localhost:8081/h2-console（登录无密码）



响应格式：

`json`格式，包含属性：`code`，`msg`，`data`

`code`：0000为响应成功，1111为响应出错，`msg`中含有出错信息

响应成功时，实体数据在`data`属性中。



# API

## 用户相关

**API**

`/user/user`

**参数**

| 参数名   | 含义     | 取值 |
| -------- | -------- | ---- |
| `userId` | 用户id   | -    |
| `stuId`  | 用户学号 | -    |

**功能**

返回特定用户信息，参数二选一，同时存在时，`userId`优先

**响应实例**

```json
{"code":"0000","msg":"请求成功","data":{"ID":1,"STU_ID":"21020001001","GRADE":"19","NICK_NAME":"Michael"}}
```

****



**API**

`/user/comment`

**参数**

| 参数名        | 含义                 | 取值               |
| ------------- | -------------------- | ------------------ |
| `userId`      | 用户id               | -                  |
| `commentType` | 评论类型             | `course, lecturer` |
| `targetId`    | 评论对象的ID，非必须 | -                  |

**功能**

返回指定用户发表的，类型为`commentType`的评论。当不指定`targetId`时，返回所有评论，指定时，返回该用户对特定课程或教师的评论。

**响应实例**

![](https://i.loli.net/2021/05/10/QfYWAO7TNp14yaS.png)

****



## 课程相关

**API**

`/course/courses`

**参数**

无参数

**功能**

返回课程列表

**响应实例**

![](https://i.loli.net/2021/05/10/48T6PidXwfW7CLN.png)

****



**API**

`/course/course`

**参数**

| 参数名     | 含义   | 取值 |
| ---------- | ------ | ---- |
| `courseId` | 课程ID | -    |

**功能**

返回特定课程的信息，包括其授课教师的信息

**响应实例**

![](https://i.loli.net/2021/05/10/SgO6IhFPtKmeYGA.png)

****



**API**

`/course/comment`

**参数**

| 参数名     | 含义   | 取值 |
| ---------- | ------ | ---- |
| `courseId` | 课程ID | -    |

**功能**

返回特定课程的评论，包括该评论的用户信息

**响应实例**

![](https://i.loli.net/2021/05/10/ljfgqkmzItCiU2e.png)

****



## 教师相关

**API**

`/lecturer/lecturers`

**参数**

无参数

**功能**

返回教师列表

**响应实例**

![](https://i.loli.net/2021/05/10/ZzIrDdXu3lhBe8J.png)

****



**API**

`/lecturer/lecturer`

**参数**

| 参数名       | 含义   | 取值 |
| ------------ | ------ | ---- |
| `lecturerId` | 教师ID | -    |

**功能**

返回ID为`lecturerId`的教师的信息

**响应实例**

![](https://i.loli.net/2021/05/10/JQOsCYBoVGSeqWg.png)

****



**API**

`/lecturer/comment`

**参数**

| 参数名       | 含义   | 取值 |
| ------------ | ------ | ---- |
| `lecturerId` | 教师ID | -    |

**功能**

返回ID为`lecturerId`的教师的评论信息，包含该评论的用户信息

**响应实例**

![](https://i.loli.net/2021/05/10/EHmLPNQbs2CuMKk.png)

****



**API**

`/lecturer/course`

**参数**

| 参数名       | 含义   | 取值 |
| ------------ | ------ | ---- |
| `lecturerId` | 教师ID | -    |

**功能**

返回ID为`lecturerId`的教师所教授的课程

**响应实例**

![](https://i.loli.net/2021/05/10/Kx3n6IELPhmYcsM.png)

****



