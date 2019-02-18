var app = angular.module("springDemo", []);

app.controller("AppCtrl", function($scope, $http) {
    $scope.websites = [];

    $http.get('http://localhost:9000/api').then(function (response) {
            $scope.websites=response.data;
        });

});
