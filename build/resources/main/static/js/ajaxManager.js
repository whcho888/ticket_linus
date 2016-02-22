/**
 * Created by wonhyuk on 2016. 2. 22..
 */
var ajaxManager = (function() {
    var requests = [];
    var history = [];
    return {
        addReq:  function(opt) {
            requests.push(opt);
            history.push(opt);
            $.ajax(opt);
        },
        removeReq:  function(opt) {
            if( $.inArray(opt, requests) > -1 )
                requests.splice($.inArray(opt, requests), 1);
        },
        clearHistory:function(){
            if(history.length)
                history=[];
        },
        goBack: function(){
            if(history.length){
                history.pop()
                $.ajax(history[history.length-1]);
            }
        },
        run: function() {
            var self = this,
                oriSuc;
            console.log(history);
            if( requests.length ) {
                oriSuc = requests[0].complete;

                requests[0].complete = function() {
                    if( typeof(oriSuc) === 'function' ) oriSuc();
                    requests.shift();
                    self.run.apply(self, []);
                };

                $.ajax(requests[0]);
            } else {
                self.tid = setTimeout(function() {
                    self.run.apply(self, []);
                }, 300);
            }
        },
        stop:  function() {
            requests = [];
            clearTimeout(this.tid);
        }
    };
}());