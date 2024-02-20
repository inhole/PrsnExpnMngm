var color01 = "#ff0000";
var color02 = "#0024ff";

<!-- FullCalendar 초기화 및 이벤트 설정 -->
document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView : 'dayGridMonth'
        , locale    : 'ko'
        , displayEventTime: false // 이벤트에 시간 표시 여부
        , eventSources : [{
            events : function (info, successCallback, failureCallback) {
                const events = [];
                // axios.post("/axios/viewMain", JSON.stringify(info))
                axios.post("/axios/viewMain", info)
                .then(res => {

                    $.each(res.data, function(index, item)
                    {
                        const event = {};
                        event.start = item.expenseDt;
                        event.end = item.expenseDt;
                        if (item.categoryCd === "01") {
                            event.title = "+" + item.amount.toLocaleString();
                            event.color = color01;
                        } else if (item.categoryCd === "02") {
                            event.title = "-" + item.amount.toLocaleString();
                            event.color = color02;
                        }

                        events.push(event);
                    });

                    successCallback(events);

                }).catch(err => {
                    console.log("err", err);
                });
            }
        }]
        // , events    :  function () {}
        // [
        //     {
        //         title : '+30,000'
        //         , start : '2024-02-20'
        //         , color : '#f37b7b'
        //     }
        //     , {
        //         title : '-10,000'
        //         , start : '2024-02-20'
        //         , color : '#788bf6'
        //     }
        // ]
    });

    calendar.render();
});
