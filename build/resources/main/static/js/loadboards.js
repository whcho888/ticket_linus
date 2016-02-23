/**
 * Created by wonhyuk on 2016. 2. 23..
 */
function loadBoards(){
    ajaxManager.addReq({
        url : "/search/?idx=0",
        type : "GET", // http method
        success : function(view) {
            $("#mainContainer").replaceWith(view);
        },
        error : function(err) {
            alert("error on ajax!");
            // provide a bit more info about the error to the console
        }
    });
}