var checkEmail = "";
$( function() {
    $("#emailSend").on("click", emailSend);
})

/** 이메일 인증 전송 */
function emailSend() {
    const email = $(".input_email").val().trim();
    console.log(email);
    axios({
        method: "get",
        url: "/axios/send/mail/"+ email,
    }).then(res => {

        checkEmail = res.data;

        $("#emailChkDiv").css("display", "block");
        $("#emailChkBtn").on("click", emailChk);
    }).catch(err => {
        console.log("err", err);
    });
}

function emailChk() {
    const check = $("#emailChk").val();
    if (check == checkEmail) {
        $("#submit").css("display", "block");
        alert("인증이 완료되었습니다.");
    } else {
        alert("인증번호를 다시 확인해주세요.")
    }
}