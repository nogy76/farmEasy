<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>귀농하여 지방살리기</title>
  <link rel="stylesheet" href="css/pop_info.css">
  <link rel="stylesheet" href="css/header-footer.css">
  <link href="images/favi.png" rel="shortcut icon" type="image/x-icon">
</head>

<body>
  <form>
    <div class="main-container">
      <div class="main-wrap">
        <fieldset id="signup_fieldset">
        <header>
          <div class="logo-wrap">
            <h2>정책 상세 조회 페이지</h1>
          </div>
        </header>
        <div class="signup_div">
          <table>
            <tr>
              <td align=left class="td_name">ㆍ사업명</td>
              <td>
                임산물 상품화 지원
              </td>
            </tr>
            <tr>
              <td align=left class="td_name" >ㆍ지원금</td>
              <td>
                 
              </td>
            </tr>
            <tr>
              <td align=left class="td_name">ㆍ이자율</td>
              <td>
             
              </td>
            </tr>
            <tr>
              <td align=left class="td_name">ㆍ지원 내용</td>
              <td>
                임산물 표준규격출하 및 상품화 촉진을 위한 포장디자인 개선<br> 
                및 내·외부 포장재 지원 50백만원/개소
              </td>
            </tr>
            <tr>
              <td align=left class="td_name">ㆍ담당 부서 전화번호</td>
              <td>
                797-3573
              </td>
            </tr>
            <tr>
              <td align=left class="td_name">ㆍ관련 링크</td>
              <td>
                귀농귀촌 종합센터<br>
                <a href="http://www.returnfarm.com" target="_blank">http://www.retrunfarm.com</a>
              </td>
            </tr>
          </table>
        </div>
        <div class="login-button-wrap"> 
          <input type="button" value="리스트에 담기" class="pick">
          <input type="button" value="닫기" onclick="closePop()" class="close_info">
        </div>
      </fieldset>
      </div>
    </div>
  </form>
  <script>
    function closePop() {
      window.close();
    }
  </script>
</body>

</html>