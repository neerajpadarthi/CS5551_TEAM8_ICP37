/**
 * Created by user on 23/10/2016.
 */
var myapp = angular.module('demoMongo',[]);
myapp.run(function ($http) {
    // Sends this header with any AJAX request
    $http.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
    // Send this header only in post requests. Specifies you are sending a JSON object
    $http.defaults.headers.post['dataType'] = 'json'
});
myapp.controller('MongoRestController',function($scope,$http,$window){
    $scope.insertData = function(){
        console.log($scope.formData.classID);
        // var dataParams = {
        //     'classid' : $scope.formData.classID,
        //     'studentname' : $scope.formData.studentName,
        //     'coursesudy' : $scope.formData.courseStudy,
        //     'major' : $scope.formData.major,
        //     'minor' : $scope.formData.minor
        // };
        var config = {
            headers : {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
            }
        }
        var req = $http.post('http://127.0.0.1:8081/enroll',$scope.formData);
        req.success(function(data, status, headers, config) {
            $scope.message = data;
            console.log("here "+data);
            $window.location.href = 'getData.html';
        });
        req.error(function(data, status, headers, config) {
            alert( "failure message: " + JSON.stringify({data: data}));
        });
    };
});
myapp.controller('getController',function($scope,$http){
    $scope.getDbData = function(){
        console.log($scope.formData.major);
        var config = {
            headers : {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
            }
        }
        // var req = $http.get('http://127.0.0.1:8081/getData');
        $http.get('http://127.0.0.1:8081/getData?keywords='+$scope.formData.major).then(function(d)
            {
                // console.log(typeof(d));
                // console.log("length is "+d.data.length);
                var document=[];
                for (i=0;i<d.data.length;i++)
                {
                    // console.log("it is "+d.data[i].classID);
                    // console.log("it is "+d.data[i].major);
                    document.push(new Array(d.data[i].classID+'-'+d.data[i].studentName+'+'+d.data[i].courseStudy+'@'+d.data[i].major+'#'+d.data[i].minor));
                }
                // console.log("document is "+document);
                // console.log("val "+JSON.stringify({d: d}));

                $scope.fullDocument =[];
                for(var x=0;x<document.length;x++) {
                    var val= document[x];
                    console.log('Data is '+document[x]);
                    $scope.fullDocument.push(val.toString());
                }

                // values =JSON.stringify({d: d});
                // console.log("it is "+values.d.data);

                // console.log("val ");
            },function(err)
            {
                console.log(err);
            }
        )
    };
});
