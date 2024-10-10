(function() {
	
	var passwordmanager = angular.module('passwordmanager');
	
	passwordmanager.controller('updateController', function($scope, $http, $routeParams, $location) {
		 $scope.getPasswordsById = function(){
			 $http.get("/capstone/webapi/passwords/" + $routeParams.passwordId)
			 .then(function(response){
				var passwords = response.data;
				if(passwords.length == 1){$scope.password = passwords[0];}
				else{}
			 }, function(response){
				console.log('error http GET passwords by id: ' + response.status);
			 });
			 console.log($scope.password);
		 }
		 $scope.getPasswordsById();
		 $scope.updatePassword = function(){
			$http.put('/capstone/webapi/passwords/update/' + $routeParams.passwordId, {id: $routeParams.passwordId,password: $scope.password.password})
			.then(function(response){
				$scope.updateStatus = 'update successful';
			}, function (response) {
				$scope.updateStatus = 'error trying to update password';
				console.log('error http PUT passwords: ' + response.status);
			});
		}
		$scope.deletePassword = function(){
			var passwordId = $routeParams.passwordId;
			$http.delete('/capstone/webapi/passwords/delete/' + passwordId)
				.then(function(response) {
				// handle success
				$scope.updateStatus = 'delete successful';
				$scope.disableUpdate = true;
				}, function(response) {
				// handle error
				$scope.updateStatus = 'error trying to delete password';
				console.log('error http DELETE password: ' + response.status);
				});
		}
		$scope.goToSearchView = function(){
			console.log('go to search view');
			$location.path('/search');
		}
	});
})()