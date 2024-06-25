<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>
        <head>
            <meta charset="UTF-8">
            <title>Insert title here</title>

            <%@ include file="../common/common-file.jsp" %>
            <link rel="stylesheet" href="resources/css/bosspage/bosspage.css" />
            <link rel="stylesheet" href="resources/css/bosspage/locationinfo.css" />
            <link rel="stylesheet" href="resources/css/common/common.css" />
            <script src="resources\js\bosspage\bosspage.js"></script>

        </head>

        <body>
            <%@ include file="../common/header.jsp" %>

                <div id="bossmainpage-wrap" class="wrapper">
                    <div class="bossmainpage">
                        <div>
                            <%@ include file="../bosspage/bossmanubar.jsp" %>
                        </div>
                        <div class="privacy-page">
                            <div class="privacy-top">동물병원 정보</div>
                            <div id="location-details">
                                <div>
                                    <p>상호명(*필수*)</p>
                                    <input type="text" name="상호명">
                                </div>
                                <div>
                                    <p>병원 전화번호(*필수*)</p>
                                    <input type="tel" style="background: white;" placeholder="(ex.지역번호)-0000-0000">
                                </div>
                                <div id="operating">
                                    <p>운영시간(*필수*)</p>
                                    <div id="operating-hours">
                                        <p>월</p>
                                        <p>휴무</p>
                                        <input type="checkbox" name="휴무">
                                        <select class="opne-time">
                                            <option value="">00:00</option>
                                            <option value="">01:00</option>
                                            <option value="">02:00</option>
                                            <option value="">03:00</option>
                                            <option value="">04:00</option>
                                            <option value="">05:00</option>
                                            <option value="">06:00</option>
                                            <option value="">07:00</option>
                                            <option value="">08:00</option>
                                            <option value="">09:00</option>
                                            <option value="">10:00</option>
                                            <option value="">11:00</option>
                                            <option value="">12:00</option>
                                            <option value="">13:00</option>
                                            <option value="">14:00</option>
                                            <option value="">15:00</option>
                                            <option value="">16:00</option>
                                            <option value="">17:00</option>
                                            <option value="">18:00</option>
                                            <option value="">19:00</option>
                                            <option value="">20:00</option>
                                            <option value="">21:00</option>
                                            <option value="">22:00</option>
                                            <option value="">23:00</option>
                                        </select>
                                        <p style="margin-right: 0;margin-left: 30px;">~</p>
                                        <select class="close-time">
                                            <option value="">00:00</option>
                                            <option value="">01:00</option>
                                            <option value="">02:00</option>
                                            <option value="">03:00</option>
                                            <option value="">04:00</option>
                                            <option value="">05:00</option>
                                            <option value="">06:00</option>
                                            <option value="">07:00</option>
                                            <option value="">08:00</option>
                                            <option value="">09:00</option>
                                            <option value="">10:00</option>
                                            <option value="">11:00</option>
                                            <option value="">12:00</option>
                                            <option value="">13:00</option>
                                            <option value="">14:00</option>
                                            <option value="">15:00</option>
                                            <option value="">16:00</option>
                                            <option value="">17:00</option>
                                            <option value="">18:00</option>
                                            <option value="">19:00</option>
                                            <option value="">20:00</option>
                                            <option value="">21:00</option>
                                            <option value="">22:00</option>
                                            <option value="">23:00</option>
                                        </select>
                                    </div>
                                    <div id="operating-hours">
                                        <p>화</p>
                                        <p>휴무</p>
                                        <input type="checkbox" name="휴무">
                                        <select class="opne-time">
                                            <option value="">00:00</option>
                                            <option value="">01:00</option>
                                            <option value="">02:00</option>
                                            <option value="">03:00</option>
                                            <option value="">04:00</option>
                                            <option value="">05:00</option>
                                            <option value="">06:00</option>
                                            <option value="">07:00</option>
                                            <option value="">08:00</option>
                                            <option value="">09:00</option>
                                            <option value="">10:00</option>
                                            <option value="">11:00</option>
                                            <option value="">12:00</option>
                                            <option value="">13:00</option>
                                            <option value="">14:00</option>
                                            <option value="">15:00</option>
                                            <option value="">16:00</option>
                                            <option value="">17:00</option>
                                            <option value="">18:00</option>
                                            <option value="">19:00</option>
                                            <option value="">20:00</option>
                                            <option value="">21:00</option>
                                            <option value="">22:00</option>
                                            <option value="">23:00</option>
                                        </select>
                                        <p style="margin-right: 0;margin-left: 30px;">~</p>
                                        <select class="close-time">
                                            <option value="">00:00</option>
                                            <option value="">01:00</option>
                                            <option value="">02:00</option>
                                            <option value="">03:00</option>
                                            <option value="">04:00</option>
                                            <option value="">05:00</option>
                                            <option value="">06:00</option>
                                            <option value="">07:00</option>
                                            <option value="">08:00</option>
                                            <option value="">09:00</option>
                                            <option value="">10:00</option>
                                            <option value="">11:00</option>
                                            <option value="">12:00</option>
                                            <option value="">13:00</option>
                                            <option value="">14:00</option>
                                            <option value="">15:00</option>
                                            <option value="">16:00</option>
                                            <option value="">17:00</option>
                                            <option value="">18:00</option>
                                            <option value="">19:00</option>
                                            <option value="">20:00</option>
                                            <option value="">21:00</option>
                                            <option value="">22:00</option>
                                            <option value="">23:00</option>
                                        </select>
                                    </div>
                                    <div id="operating-hours">
                                        <p>수</p>
                                        <p>휴무</p>
                                        <input type="checkbox" name="휴무">
                                        <select class="opne-time">
                                            <option value="">00:00</option>
                                            <option value="">01:00</option>
                                            <option value="">02:00</option>
                                            <option value="">03:00</option>
                                            <option value="">04:00</option>
                                            <option value="">05:00</option>
                                            <option value="">06:00</option>
                                            <option value="">07:00</option>
                                            <option value="">08:00</option>
                                            <option value="">09:00</option>
                                            <option value="">10:00</option>
                                            <option value="">11:00</option>
                                            <option value="">12:00</option>
                                            <option value="">13:00</option>
                                            <option value="">14:00</option>
                                            <option value="">15:00</option>
                                            <option value="">16:00</option>
                                            <option value="">17:00</option>
                                            <option value="">18:00</option>
                                            <option value="">19:00</option>
                                            <option value="">20:00</option>
                                            <option value="">21:00</option>
                                            <option value="">22:00</option>
                                            <option value="">23:00</option>
                                        </select>
                                        <p style="margin-right: 0;margin-left: 30px;">~</p>
                                        <select class="close-time">
                                            <option value="">00:00</option>
                                            <option value="">01:00</option>
                                            <option value="">02:00</option>
                                            <option value="">03:00</option>
                                            <option value="">04:00</option>
                                            <option value="">05:00</option>
                                            <option value="">06:00</option>
                                            <option value="">07:00</option>
                                            <option value="">08:00</option>
                                            <option value="">09:00</option>
                                            <option value="">10:00</option>
                                            <option value="">11:00</option>
                                            <option value="">12:00</option>
                                            <option value="">13:00</option>
                                            <option value="">14:00</option>
                                            <option value="">15:00</option>
                                            <option value="">16:00</option>
                                            <option value="">17:00</option>
                                            <option value="">18:00</option>
                                            <option value="">19:00</option>
                                            <option value="">20:00</option>
                                            <option value="">21:00</option>
                                            <option value="">22:00</option>
                                            <option value="">23:00</option>
                                        </select>
                                    </div>
                                    <div id="operating-hours">
                                        <p>목</p>
                                        <p>휴무</p>
                                        <input type="checkbox" name="휴무">
                                        <select class="opne-time">
                                            <option value="">00:00</option>
                                            <option value="">01:00</option>
                                            <option value="">02:00</option>
                                            <option value="">03:00</option>
                                            <option value="">04:00</option>
                                            <option value="">05:00</option>
                                            <option value="">06:00</option>
                                            <option value="">07:00</option>
                                            <option value="">08:00</option>
                                            <option value="">09:00</option>
                                            <option value="">10:00</option>
                                            <option value="">11:00</option>
                                            <option value="">12:00</option>
                                            <option value="">13:00</option>
                                            <option value="">14:00</option>
                                            <option value="">15:00</option>
                                            <option value="">16:00</option>
                                            <option value="">17:00</option>
                                            <option value="">18:00</option>
                                            <option value="">19:00</option>
                                            <option value="">20:00</option>
                                            <option value="">21:00</option>
                                            <option value="">22:00</option>
                                            <option value="">23:00</option>
                                        </select>
                                        <p style="margin-right: 0;margin-left: 30px;">~</p>
                                        <select class="close-time">
                                            <option value="">00:00</option>
                                            <option value="">01:00</option>
                                            <option value="">02:00</option>
                                            <option value="">03:00</option>
                                            <option value="">04:00</option>
                                            <option value="">05:00</option>
                                            <option value="">06:00</option>
                                            <option value="">07:00</option>
                                            <option value="">08:00</option>
                                            <option value="">09:00</option>
                                            <option value="">10:00</option>
                                            <option value="">11:00</option>
                                            <option value="">12:00</option>
                                            <option value="">13:00</option>
                                            <option value="">14:00</option>
                                            <option value="">15:00</option>
                                            <option value="">16:00</option>
                                            <option value="">17:00</option>
                                            <option value="">18:00</option>
                                            <option value="">19:00</option>
                                            <option value="">20:00</option>
                                            <option value="">21:00</option>
                                            <option value="">22:00</option>
                                            <option value="">23:00</option>
                                        </select>
                                    </div>
                                    <div id="operating-hours">
                                        <p>금</p>
                                        <p>휴무</p>
                                        <input type="checkbox" name="휴무">
                                        <select class="opne-time">
                                            <option value="">00:00</option>
                                            <option value="">01:00</option>
                                            <option value="">02:00</option>
                                            <option value="">03:00</option>
                                            <option value="">04:00</option>
                                            <option value="">05:00</option>
                                            <option value="">06:00</option>
                                            <option value="">07:00</option>
                                            <option value="">08:00</option>
                                            <option value="">09:00</option>
                                            <option value="">10:00</option>
                                            <option value="">11:00</option>
                                            <option value="">12:00</option>
                                            <option value="">13:00</option>
                                            <option value="">14:00</option>
                                            <option value="">15:00</option>
                                            <option value="">16:00</option>
                                            <option value="">17:00</option>
                                            <option value="">18:00</option>
                                            <option value="">19:00</option>
                                            <option value="">20:00</option>
                                            <option value="">21:00</option>
                                            <option value="">22:00</option>
                                            <option value="">23:00</option>
                                        </select>
                                        <p style="margin-right: 0;margin-left: 30px;">~</p>
                                        <select class="close-time">
                                            <option value="">00:00</option>
                                            <option value="">01:00</option>
                                            <option value="">02:00</option>
                                            <option value="">03:00</option>
                                            <option value="">04:00</option>
                                            <option value="">05:00</option>
                                            <option value="">06:00</option>
                                            <option value="">07:00</option>
                                            <option value="">08:00</option>
                                            <option value="">09:00</option>
                                            <option value="">10:00</option>
                                            <option value="">11:00</option>
                                            <option value="">12:00</option>
                                            <option value="">13:00</option>
                                            <option value="">14:00</option>
                                            <option value="">15:00</option>
                                            <option value="">16:00</option>
                                            <option value="">17:00</option>
                                            <option value="">18:00</option>
                                            <option value="">19:00</option>
                                            <option value="">20:00</option>
                                            <option value="">21:00</option>
                                            <option value="">22:00</option>
                                            <option value="">23:00</option>
                                        </select>
                                    </div>
                                    <div id="operating-hours">
                                        <p>토</p>
                                        <p>휴무</p>
                                        <input type="checkbox" name="휴무">
                                        <select class="opne-time">
                                            <option value="">00:00</option>
                                            <option value="">01:00</option>
                                            <option value="">02:00</option>
                                            <option value="">03:00</option>
                                            <option value="">04:00</option>
                                            <option value="">05:00</option>
                                            <option value="">06:00</option>
                                            <option value="">07:00</option>
                                            <option value="">08:00</option>
                                            <option value="">09:00</option>
                                            <option value="">10:00</option>
                                            <option value="">11:00</option>
                                            <option value="">12:00</option>
                                            <option value="">13:00</option>
                                            <option value="">14:00</option>
                                            <option value="">15:00</option>
                                            <option value="">16:00</option>
                                            <option value="">17:00</option>
                                            <option value="">18:00</option>
                                            <option value="">19:00</option>
                                            <option value="">20:00</option>
                                            <option value="">21:00</option>
                                            <option value="">22:00</option>
                                            <option value="">23:00</option>
                                        </select>
                                        <p style="margin-right: 0;margin-left: 30px;">~</p>
                                        <select class="close-time">
                                            <option value="">00:00</option>
                                            <option value="">01:00</option>
                                            <option value="">02:00</option>
                                            <option value="">03:00</option>
                                            <option value="">04:00</option>
                                            <option value="">05:00</option>
                                            <option value="">06:00</option>
                                            <option value="">07:00</option>
                                            <option value="">08:00</option>
                                            <option value="">09:00</option>
                                            <option value="">10:00</option>
                                            <option value="">11:00</option>
                                            <option value="">12:00</option>
                                            <option value="">13:00</option>
                                            <option value="">14:00</option>
                                            <option value="">15:00</option>
                                            <option value="">16:00</option>
                                            <option value="">17:00</option>
                                            <option value="">18:00</option>
                                            <option value="">19:00</option>
                                            <option value="">20:00</option>
                                            <option value="">21:00</option>
                                            <option value="">22:00</option>
                                            <option value="">23:00</option>
                                        </select>
                                    </div>
                                    <div id="operating-hours">
                                        <p>일</p>
                                        <p>휴무</p>
                                        <input type="checkbox" name="휴무">
                                        <select class="opne-time">
                                            <option value="">00:00</option>
                                            <option value="">01:00</option>
                                            <option value="">02:00</option>
                                            <option value="">03:00</option>
                                            <option value="">04:00</option>
                                            <option value="">05:00</option>
                                            <option value="">06:00</option>
                                            <option value="">07:00</option>
                                            <option value="">08:00</option>
                                            <option value="">09:00</option>
                                            <option value="">10:00</option>
                                            <option value="">11:00</option>
                                            <option value="">12:00</option>
                                            <option value="">13:00</option>
                                            <option value="">14:00</option>
                                            <option value="">15:00</option>
                                            <option value="">16:00</option>
                                            <option value="">17:00</option>
                                            <option value="">18:00</option>
                                            <option value="">19:00</option>
                                            <option value="">20:00</option>
                                            <option value="">21:00</option>
                                            <option value="">22:00</option>
                                            <option value="">23:00</option>
                                        </select>
                                        <p style="margin-right: 0;margin-left: 30px;">~</p>
                                        <select class="close-time">
                                            <option value="">00:00</option>
                                            <option value="">01:00</option>
                                            <option value="">02:00</option>
                                            <option value="">03:00</option>
                                            <option value="">04:00</option>
                                            <option value="">05:00</option>
                                            <option value="">06:00</option>
                                            <option value="">07:00</option>
                                            <option value="">08:00</option>
                                            <option value="">09:00</option>
                                            <option value="">10:00</option>
                                            <option value="">11:00</option>
                                            <option value="">12:00</option>
                                            <option value="">13:00</option>
                                            <option value="">14:00</option>
                                            <option value="">15:00</option>
                                            <option value="">16:00</option>
                                            <option value="">17:00</option>
                                            <option value="">18:00</option>
                                            <option value="">19:00</option>
                                            <option value="">20:00</option>
                                            <option value="">21:00</option>
                                            <option value="">22:00</option>
                                            <option value="">23:00</option>
                                        </select>
                                    </div>
                                </div>
                                <div id="storeinfo">
                                    <p>상세 설명(필수*)</p>
                                    <textarea style="border-radius: 15px;"
                                        placeholder="*장소에 대한 설명과 반려동물 풀입 시 도움이 될 만한 정보를 적어주세요. (ex)테이블 밀집도, 케이지 필요 여부 등."></textarea>
                                </div>
                                <div>
                                    <p>반려동물 종류 및 크기(*필수*)</p>
                                    <div id="animal-check">
                                        <p>소형견</p>
                                        <input type="checkbox" name="소형견">
                                        <p>중형견</p>
                                        <input type="checkbox" name="중형견">
                                        <p>대형견</p>
                                        <input type="checkbox" name="대형견">
                                        <p>고양이</p>
                                        <input type="checkbox" name="고양이">
                                    </div>
                                </div>
                                <div id="reservation-link">
                                    <p>외부 예약링크(선택)</p>
                                    <input type="text" placeholder="ex)www.mnguide.com">
                                </div>
                                <div>
                                    <p>업체 사진등록(*필수*)</p>
                                    <div id="photo-registration">
                                        <label for="file1">
                                            <div class="registration-upload"><img
                                                    src="<%=contextPath%>/resources/img/myPage/+.png"></div>
                                        </label>
                                        <input type="file" name="file" id="file1" class="company-file">
                                        <label for="file2">
                                            <div class="registration-upload"><img
                                                    src="<%=contextPath%>/resources/img/myPage/+.png"></div>
                                        </label>
                                        <input type="file" name="file" id="file2" class="company-file">
                                        <label for="file3">
                                            <div class="registration-upload"><img
                                                    src="<%=contextPath%>/resources/img/myPage/+.png"></div>
                                        </label>
                                        <input type="file" name="file" id="file3" class="company-file">
                                    </div>
                                </div>
                                <div id="upload-location">
                                    <button class="upload-bt">등록</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <%@ include file="../common/footer.jsp" %>





        </body>

    </html>