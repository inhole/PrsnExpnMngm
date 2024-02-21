var color01 = "#ff0000";
var color02 = "#0024ff";

window.onload = function() {

    const modal = document.getElementById("modal");

    const closeBtn = modal.querySelector(".close-area");
    closeBtn.addEventListener("click", e => {
        modalOff();
    });
    modal.addEventListener("click", e => {
        const evTarget = e.target
        if (evTarget.classList.contains("modal-overlay")) {
            modalOff();
        }
    });

    // today title
}

// 모달창 on
function modalOn() {
    $("#modal").css("display", "flex");
}

// 모달창 off
function modalOff() {
    $("#modal").css("display", "none");
}

function selectModalView(info) {
    // 비동기 api 통신
    // 지출 통계 상세 조회

    axios({
        method: "get",
        url: "/axios/viewMain/"+ info.dateStr,
    }).then(res => {

        var html = "";
        $.each(res.data, function(index, item) {
            var category = "";
            if (item.categoryCd === "01") {
                category = "수입";
            } else {
                category = "지출";
            }

            html += "<p>가격 : "+ item.amount.toLocaleString() +"원</p>";
            html += "<p>카테고리 : "+ category +"</p>";
            html += "<p>카테고리 이름 : "+ item.categoryName +"</p>";
            html += "<p>설명 : "+ item.description +"</p>";
            // html += "<p>일시 : "+ item.expenseDt +"</p><br>";
            html += "<p>일시 : "+ moment(item.expenseDt).format('LT') +"</p><br>";
        });

        $("#modal .content").html(html);

        modalOn();
    }).catch(err => {
        console.log("err", err);
    });
}

<!-- FullCalendar 초기화 및 이벤트 설정 -->
document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView : 'dayGridMonth'
        , locale    : 'ko'
        , displayEventTime: false // 이벤트에 시간 표시 여부
        , dateClick : function ( info ) { // 날짜 클릭 시 발생할 이벤트
            selectModalView(info);
        }
        , eventSources : [{
            events : function (info, successCallback, failureCallback) {
                const events = [];
                axios.post("/axios/viewMain", info)
                .then(res => {

                    $.each(res.data, function(index, item)
                    {
                        if (moment(item.expenseDt).format('YYYY-MM-DD') !== moment().format('YYYY-MM-DD')) {

                            const event = {};
                            event.start = item.expenseDt;

                            if (item.categoryCd === "01") {
                                event.title = "+" + item.amount.toLocaleString();
                                event.color = color01;
                            } else if (item.categoryCd === "02") {
                                event.title = "-" + item.amount.toLocaleString();
                                event.color = color02;
                            }

                            events.push(event);
                        } else {
                        }
                    });

                    successCallback(events);

                }).catch(err => {
                    console.log("err", err);
                });
            }
        }]
    });

    calendar.render();
});
