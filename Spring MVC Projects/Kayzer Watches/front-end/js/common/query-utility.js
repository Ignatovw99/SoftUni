var app = app || {};

app.queryUtility = (function () {
    return {
        queryParameter: function (name) {
            url = window.location.href;
            name = name.replace(/[\[\]]/g, "\\$&");
            var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
                results = regex.exec(url);
            if (!results) return null;
            if (!results[2]) return '';
            return decodeURIComponent(results[2].replace(/\+/g, " "));
        }
    }
})();


// $.ajax({
//     type: 'POST',
//     url: 'localhost:8000/users/register',
//     headers: {
//         'Content-Type': 'application/json'
//     },
//     data: JSON.stringify(
//         {
//             username: 'Pesho',
//             password: '123',
//             confirmPassword: '321'
//         }
//     )
// }).done(function (data, next ,test) {
//     console.log(data);
// });