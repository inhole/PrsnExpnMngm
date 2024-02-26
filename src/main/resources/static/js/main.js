/** 개인 지출 관리 셀렉트 박스 onchange 함수 */
function testAxios ()
{

    var langSelect = document.getElementById("categoryCd");

    // select element에서 선택된 option의 value가 저장된다.
    var selectValue = langSelect.options[langSelect.selectedIndex].value;

    // select element에서 선택된 option의 text가 저장된다.
    var selectText = langSelect.options[langSelect.selectedIndex].text;

    if (selectText === "선택") {
        $("#categoryId").empty().attr("disabled", true);
        return false;
    }


    axios({
        method: "get",
        url: "/category/axios/"+ selectValue,
    }).then(res => {
        $("#categoryId").empty().attr("disabled", false);

        $.each(res.data, function(index, item)
        {
            console.log(item);
            $("#categoryId").append("<option value='"+ item.categoryId +"'>"+ item.categoryName +"</option>");

        })
    }).catch(err => {
        console.log("err", err);
    });
}
