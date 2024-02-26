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
                axios.post("/expense/axios/list", info)
                .then(res => {

                    $.each(res.data, function(index, item)
                    {
                        // if (moment(item.expenseDt).format('YYYY-MM-DD') !== moment().format('YYYY-MM-DD')) {

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
                        // } else {
                        // }
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

// 지출 통계 상세 조회
function selectModalView(info) {

    axios({
        method: "get",
        url: "/expense/axios/list/view/"+ info.dateStr,
    }).then(res => {

        if (res.data.length === 0) {
            alert("해당 날짜에는 내역이 없습니다.");
            return false;
        }

        var html = "";
        $.each(res.data, function(index, item) {

            let category = "";
            let amount = "";
            let expenseDt = moment(item.expenseDt).format('LT');
            let td = "";

            if (item.categoryCd === "01") {
                category = "수입";
                amount = "+" + item.amount.toLocaleString() + "원";
                td = "<td style='color: "+ color01 +"'>";
            } else {
                category = "지출";
                amount = "-" + item.amount.toLocaleString() + "원";
                td = "<td style='color: "+ color02 +"'>";
            }

            html += "<tr>";
            html += "    <td>" + expenseDt + "</td>";
            html += "    <td>" + item.categoryName + "</td>";
            html += td;
            html += amount + "</td>";
            html += "    <td>" + item.description + "</td>";
            html += "</tr>";
        });

        $("#modal .content table tbody").html(html);

        modalOn();
    }).catch(err => {
        console.log("err", err);
    });
}