let passwordmanager = angular.module('passwordmanager');
passwordmanager.controller('searchController',function($scope, $http, $location) {
    $scope.getAllPasswords = function() {
        $http.get("/capstone/webapi/passwords")
        .then(function(response) {
            $scope.passwords = response.data;
            for (let i = 0; i < $scope.passwords.length; i++) {
                console.log('Original password: ' + $scope.passwords[i].password);
                let decodedPassword = decodePassword($scope.passwords[i].password);
                console.log('Decoded password: ' + decodedPassword);
                $scope.passwords[i].password = decodedPassword;
            }
            console.log('$scope.passwords: ' + $scope.passwords);
        },
        function(response) {
            console.log('error http GET passwords: ' + response.status);
        });
    }
    $scope.goToUpdateView = function(passwordId) {
    	console.log('go to update view');
    	console.log('passwordId');
        $location.path('/update/' + passwordId);
    }

    rot13 = function(str) {
        let result = '';
        for (let i = 0; i < str.length; i++) {
            let charCode = str.charCodeAt(i);
            if (charCode >= 65 && charCode <= 90) {
                charCode += 13;
                if (charCode > 90) {
                    charCode -= 26;
                }
            } else if (charCode >= 97 && charCode <= 122) {
                charCode += 13;
                if (charCode > 122) {
                    charCode -= 26;
                }
            }
            result += String.fromCharCode(charCode);
        }
        return result;
    }
    	let decodePassword = function(password) {
        let decodedPassword = window.atob(rot13(password));
        return decodedPassword;
    }
});