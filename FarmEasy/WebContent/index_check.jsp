<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="images/favi.png" rel="shortcut icon" type="image/x-icon">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/index_check.css">
<title>게시판</title>

<link
	href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap"
	rel="stylesheet" />
<script src="https://kit.fontawesome.com/77e29b57dd.js"
	crossorigin="anonymous"></script>
<script>
	function popLogin() {
		window.open("e_login.jsp", "popL",
				"width=650, height=650, left=800, top=50");
	}

	function popSignup() {
		window.open("e_signup.jsp", "popS",
				"width=750, height=800, left=800, top=50");
	}
</script>
<script src="js/index.js" defer></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
	<!-- nav 시작 -->
	<!-- container -->
	<nav class="navbar">
		<!-- logo -->
		<div class="navbar-logo">
			<a href="index.jsp">귀농쉽농</a>
		</div>
		<!-- menu -->
		<ul class="navbar-menu">
			<li class="depth1"><a href="a_select_step.jsp">길라잡이</a>
				<ul class="submenu">
					<li><a href="a_select_step.jsp">귀농절차</a></li>
					<li><a href="a_select_chung.jsp">지역선정</a></li>
				</ul></li>
			<li class="depth1"><a href="b_all_info.jsp">정책 조회</a>
				<ul class="submenu">
					<li><a href="b_chungcheong_info.jsp">충청도 정책</a></li>
					<li><a href="b_gyeongsang_info.jsp">경상도 정책</a></li>
					<li><a href="b_jeolla_info.jsp">전라도 정책</a></li>
				</ul></li>
			<li class="depth1"><a href="c_bigdata.jsp">농산물 빅데이터</a></li>
			<li class="depth1"><a href="/FarmEasy/list.do">게시판</a>
				<ul class="submenu">
					<li><a href="/FarmEasy/list.do">게시판</a></li>
					<li><a href="d_notice.jsp">갤러리</a></li>
				</ul></li>
		</ul>
		<!-- icon -->
		<ul class="navbar-icon">
			<li><a href="e_login.jsp">로그인</a></li>
			<li><a href="e_signup.jsp">회원가입</a></li>
		</ul>
		<a href="#" class="navbar-more"> <i class='fa fa-bars'
			style='color: white; margin-top: 14px;'></i>
		</a>
	</nav>


	<!-- nav 끝 -->


	<div class="wd-basic-960 mb-auto mt-5 mb-5" id="check_score">
		<h4 class="mb-4">귀농 단계별 체크리스트</h4>
		<p>· 귀농 단계별 체크리스트를 작성하여 단계별 부족한 점을 보완하여 효율적인 귀농계획을 수립하도록 합니다.</p>
		<form>
			<table>
				<tr>
					<td>단계</td>
					<td>체크 내용</td>
					<td>YES</td>
					<td>NO</td>
				</tr>
				<!-- Q1. 귀농결심 수집 -->
				<tr>
					<td rowspan="2" class="ft-bold">귀농결심</td>
					<td>귀농결정에 대한 2년 이상고민 했습니까?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>귀농을 결심하게 된 이유와 목적이 분명합니까?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<!-- Q2. 귀농 정보 수집 -->
				<tr>
					<td rowspan="3" class="ft-bold">귀농 정보 수집</td>
					<td>민·관·학계 및 인터넷상의 귀농정보를 모두 수집하고 있나요?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>귀농인을 적극적으로 유치하는 지자체를 2군데 이상 방문해 봤습니까?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>농작업은 계절마다 다릅니다. 귀농 현장 답사시 한 장소에 세 번이상 방문하였습니까?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<!-- Q3. 귀농 교육 수강 -->
				<tr>
					<td rowspan="3" class="ft-bold">귀농 교육 수강</td>
					<td>귀농교육은 교차수강이 필요합니다. 민·관·학계의 귀농교육을 골고루 수강했나요?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>주말농장 경험 또는 텃밭농사를 1년 이상 지어 보셨습니까?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>교육수강 후 귀농한 농자에서 한 작목의 시작부터 수확까지 모든 과정을 경험해 봤나요?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<!-- Q4. 가족 동의 -->
				<tr>
					<td rowspan="2" class="ft-bold">귀농 교육 수강</td>
					<td>배우자 및 자녀의 동의를 구했나요?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>자녀의 미래교육문제에 대해 부부(가족) 간 합의를 하셨나요?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<!-- Q5. 자금계획 -->
				<tr>
					<td rowspan="6" class="ft-bold">자금계획</td>
					<td>농업소득과 자기자금으로 2~3년은 생활이 가능합니까?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>가족 중 농업 외 소득을 올릴 아이템이 있습니까?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>정부의 귀농창업자금지원에 대해 잘 알고 있나요?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>가족 중 농업 외 소득을 올릴 아이템이 있습니까?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>영농에 필요한 자금계획은 세워져 있습니까?<br> (초기 설비투자자금과 1년간의 자재 및
						재료비, 농지구입시 토지대금)
					</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>회사원일 때는 급여에서 차감되어 온 세금 및 4대보험 중에서 주민세, 건강보험료는<br> 전년도
						소득을 기준으로 과세됩니다. 이에 대하여 고려하고 있습니까?
					</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<!-- Q6. 작목 확정 및 귀농계획서 작성 -->
				<tr>
					<td rowspan="10" class="ft-bold">작목 확정 및 귀농계획서 작성</td>
					<td>작목은 선택했습니까? (어떤 작물을 재배할 것입니까?)</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>선정할 작목에 대해 가족의 이해가 있나요?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>경영형태(단일경영 또는 복합경영)는 선택했습니까?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>선정할 작목의 최근 5년간 가격등락폭에 대해 알고 있나요?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>선정할 작목과 귀농지역의 농업환경이 잘 맞습니까?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>선정할 작목이 가급적 저장성이 있거나 택배 발송이 가능한 작목입니까?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>선정할 작목이 외국에서 수입되고 있다면 수입농산물의 가격과 동향을 파악 했나요?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>선정할 작목에 필요한 농기계 이용 대책은 있나요?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>선택작목의 생산기술은 습득하고 있습니까? 또는 귀농 후에 기술 습득이 가능합니까?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>귀농현장전문가의 도움을 받아 귀농계획서를 작성했나요?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<!-- Q7. 시설 확보 및 판매 계획 -->
				<tr>
					<td rowspan="4" class="ft-bold">시설 확보 및 판매 계획</td>
					<td>농업기계·시설 확보(구입 및 차입) 계획을 세웠습니까?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>귀농 첫 해 농사계획을 세웠습니까?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>농작물 생산 후 판매계획을 세웠습니까?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>인터넷 활용 계획은 세우셨습니까?(블로그/카페/홈페이지 등)</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<!-- Q8. 귀농지 선정 -->
				<tr>
					<td rowspan="8" class="ft-bold">귀농지 선정</td>
					<td>귀농지는 농업 경영목표의 작목 및 재배방법의 선택과 일치합니까?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>선택작목의 주산지로서 생산기술 지도체계 및 생산물 출하, <br> 판매 체계가 정비되어 있고
						신규 귀농인의 지원도 기대할 수 있습니까?
					</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>귀농지의 시·군·(읍·면)의 행정 등이 신규 귀농인을 받아들이 기에 적극적이고, <br>
						연수부터 영농까지 체계적인 지원 장치가 있습니까?
					</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>생활조건(시내중심까지의 거리 및 도로, 교통사정, 학교, 병원, 상점까지의 거리)을 충분히
						검토하였습니까?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>귀농지를 선정시, 결정을 내리기 전에 가족 전원이 현지에 동행한 적이 있습니까?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>현지를 답사했을 때, 현지의 농가(주민)로부터 적극적으로 현지상황에 대하여 이야기를 들었습니까?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>농지확보(구입 또는 임대)를 할 수 있는 정보를 갖고 있습니까?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>귀농지에서 귀농한 이후에 도와줄 사람이 있습니까?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<!-- Q8. 주택 및 농지매입 -->
				<tr>
					<td rowspan="4" class="ft-bold">주택 및 농지매입</td>
					<td>귀농계획의 실행에 적합한 농지와 주택인가요?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>농지매입시 주변지가를 파악했나요?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>구입하려는 농지가 농지원부발급대상 농지인지 시·군청에 확인했나요?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>매입할 부지의 도로, 교통사정, 학교, 병원, 공공시설로의 접근성은 검토 하였습니까?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<!-- Q8. 주택 및 농지매입 -->
				<tr>
					<td rowspan="3" class="ft-bold">영농 및 정착</td>
					<td>동네의 경조사와 지역의 사회활동에 참여할 예정입니까?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>선정한 작목과 관련한 지역의 작목반 및 관련연구회를 알아 보고 참여할 예정입니까?</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
				<tr>
					<td>농촌에서는 영농을 위해서라도 그 지역 주민과 사귀는 것이 중요합니다. <br> 이렇게 적극적으로
						주민들과 유대할 준비가 되어 있습니까?
					</td>
					<td><input type="radio"></td>
					<td><input type="radio"></td>
				</tr>
			</table>
		</form>
	</div>









	<!-- 푸터 -->
	<footer>
		<div class="container">
			<div>
				<span>&copy; 귀농 인구를 위한 지역 추천 및 농산물가격 예측 서비스</span>
			</div>
			<ul>
				<li class="ms-3"><a class="text-muted" href="#"><svg
							class="bi" width="24" height="24">
							<img src="images/iconmonstr-twitter-4.svg"></svg></a></li>
				<li class="ms-3"><a class="text-muted" href="#"><svg
							class="bi" width="24" height="24">
							<img src="images/iconmonstr-facebook-4.svg"></svg></a></li>
				<li class="ms-3"><a class="text-muted" href="#"><svg
							class="bi" width="24" height="24">
							<img src="images/iconmonstr-instagram-14.svg"></svg></a></li>
			</ul>
		</div>
	</footer>

</body>
</html>