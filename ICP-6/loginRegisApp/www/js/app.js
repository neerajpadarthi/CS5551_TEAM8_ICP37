// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
angular.module('starter', ['ionic','ngStorage', 'ngMessages'])

.run(function($ionicPlatform) {
  $ionicPlatform.ready(function() {
    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs).
    // The reason we default this to hidden is that native apps don't usually show an accessory bar, at
    // least on iOS. It's a dead giveaway that an app is using a Web View. However, it's sometimes
    // useful especially with forms, though we would prefer giving the user a little more room
    // to interact with the app.
    if (window.cordova && window.Keyboard) {
      window.Keyboard.hideKeyboardAccessoryBar(true);
    }
    if (window.StatusBar) {
      // Set the statusbar to use the default style, tweak this to
      // remove the status bar on iOS or change it to use white instead of dark colors.
      StatusBar.styleDefault();
    }
  });
})
.controller('loginRegister', function ($scope, $ionicModal,$localStorage,$ionicPopup) {
  console.log("local value User "+ $localStorage.userNameLogin);
    $scope.loginPage=function () {
      console.log($scope.userName);
      console.log($scope.password);
      $scope.errortag=" ";
      console.log( $scope.errortag);
      x=true;
      if ($scope.userName==null||!$scope.password)
      {
        console.log("null");
        $scope.errortag="Mandatory Columns should be entered";
        x=false;
      }
      if ($localStorage.userNameLogin==$scope.userName && $localStorage.passwordLogin==$scope.password && $scope.userName!=null ) {
        console.log("inside why") ;
        var alertPopup = $ionicPopup.alert({
            title: 'Login Successful'
          });
      }
      else if(x==true) {
        $scope.errortag="Invalid Credentials";
      }
      else
      {
        console.log("no issue");
      }
    }
  })
  .controller('register', function ($scope, $ionicModal,$localStorage,$window) {
    $scope.registerPage=function () {
      $localStorage.userNameLogin="";
      $localStorage.userNameLogin = $scope.userName;
      $localStorage.passwordLogin = $scope.password;
      check=true;
      var x=$localStorage.userNameLogin;
      // console.log("local value User "+ $localStorage.userNameLogin);
      // console.log($scope.password);
      if (!$scope.userName || !$scope.password)
      {
        $scope.errortag="Mandatory Columns should be entered";
        check=false;
      }

      if($scope.password!=$scope.cPassword)
      {
        $scope.errortag="Passwords should be same";
        check=false;
      }
      if (check==true) {
        $window.location.href = 'index.html';
      }
    }
  })
