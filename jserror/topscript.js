
function gamma() {
    console.log('gamma');
    jQuery.ajax({
        url: "evalscript.js",
        dataType: 'script',
        success: function (data) {
            console.log("loaded eval()");
            eval(data);
        },
        async: true
    });
}


function san() {
    var myScript = document.createElement('script');
    myScript.onload = function () {
        console.log("loaded <script>")
    };
    myScript.src = "tagscript.js";
    document.head.appendChild(myScript)
}


$(document).ready(function () {
    gamma();
    san();
});


