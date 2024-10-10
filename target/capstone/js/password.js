(function () {
  var passwordmanager = angular.module('passwordmanager', ['ngRoute']);
  passwordmanager.config(function($routeProvider) {
	  $routeProvider
	  .when("/search", {
	    templateUrl : "search.html",
	    controller	: "searchController"
	  })
    .when("/update/:passwordId", {
	    templateUrl : "update.html",
	    controller: "updateController"
	  })
    .when("/create", {
	    templateUrl : "create.html",
	    controller:	"createController"
	  })
    .when("/stack", {
	    templateUrl : "stack.html"
	  })
	  .when("/resume", {
	    templateUrl : "resume.html"
	  })
	  .when("/main", {
	    templateUrl : "main.html"
	  })
	  .otherwise({
	    templateUrl: "main.html"
	  });
	});
})()