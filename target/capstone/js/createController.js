(function(){
    var passwordmanager = angular.module('passwordmanager');
    passwordmanager.controller('createController', function($scope, $http){
        $scope.createPassword = function(){
            $http.post("/capstone/webapi/passwords/create/", {password: $scope.password.password})
            .then(function(response){
                $scope.createStatus = 'create successful';
                $scope.disableCreate = true;
            }, function(response){
                $scope.createStatus = 'error trying to create password';
                console.log('error http POST passwords: ' + response.status);
            });
        }
        $scope.clear = function(){
            $scope.password.password = '';
            $scope.disableCreate  = false;
        }
    });
})()