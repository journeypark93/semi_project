<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>semi project meetingboard</title>
<link rel="stylesheet" href="css/write.css" />
<script>
console.log("sessionStorage:",sessionStorage.user_id);
console.log("localStorage:",localStorage.user_name);
</script>
  </head>
  <body>
    <div id="bg">
      <div class="container">
        <div class="row">
        <form method="post" action="b_writeOK.do">
            <table>
                <th>
                    <tr>
                      <td colspan ="2" id="title">게시판</td>
                      <input type="button" class="backBtn" value="<"> 
                      <input type="submit" class="btn" value="완료">
                    </tr>
                </th>
            </table>
            <hr>
          <table class="table">
                <tr>
                    <td>
                        <select name="notice" id="notice">
                        
                            <option placeholder="false">비공지</option>
                            <option placeholder="false">공지</option>
                        </td>
                    </tr>
                    
                <tbody>
                    <tr>
                        <td id="writetitle"><input type="text" placeholder="제목을 입력해 주세요"name="title"maxlength="50"></td>
                    </tr>
                    <tr>
                        <td id="writer"><input type="text" placeholder="작성자(닉네임으로 지어주세요):"name="writer"maxlength="12"></td>
                    </tr>
                    <tr>
                        <td id="contents"><textarea type="text"  placeholder="게시글을 작성해주세요"name="contents"maxlength="500" style="height:350px; width:300px; border:1px solid white; font-size:20px"></textarea></td>
                    </tr>
                    </tbody>
                </table>
        </form>
        
      </div>

      
    </div>
    </div>
  </body>
</html>
