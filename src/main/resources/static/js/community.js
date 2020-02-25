$(document).ready(function () {
    $(".btn-comment").click(function () {
        let parentId = $("#parent_id").val();
        let comment = $(".text-comment").val();
        $.ajax({
            type: 'POST',
            contentType:"application/json",
            url: "/comment",
            data: JSON.stringify({
                "parentId": parentId,
                "comment": comment,
                "type": 1
            }),
            success:  function (data) {
                if (data.code = 200) {
                    $("#comment_selection").hide();
                } else {
                    alert(data.message);
                }
            }
        });
    });
});