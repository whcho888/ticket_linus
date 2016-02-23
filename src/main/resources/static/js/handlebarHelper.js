/**
 * Created by wonhyuk on 2016. 2. 23..
 */
function loadHelper() {
    Handlebars.registerHelper('trimString', function (passedString) {
        var theString = passedString.substring(0, 10);
        return new Handlebars.SafeString(theString)
    });
}